/*
 *  Copyright (C) 2010-2014 JPEXS, All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */
package com.jpexs.decompiler.flash.action.model;

import com.jpexs.decompiler.flash.SourceGeneratorLocalData;
import com.jpexs.decompiler.flash.action.swf4.ActionPush;
import com.jpexs.decompiler.flash.action.swf4.ConstantIndex;
import com.jpexs.decompiler.flash.action.swf4.RegisterNumber;
import com.jpexs.decompiler.flash.ecma.Null;
import com.jpexs.decompiler.flash.ecma.Undefined;
import com.jpexs.decompiler.flash.helpers.GraphTextWriter;
import com.jpexs.decompiler.graph.CompilationException;
import com.jpexs.decompiler.graph.GraphSourceItem;
import com.jpexs.decompiler.graph.GraphTargetItem;
import com.jpexs.decompiler.graph.SourceGenerator;
import com.jpexs.decompiler.graph.model.LocalData;
import com.jpexs.helpers.Helper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class DirectValueActionItem extends ActionItem {

    public Object value;
    public List<String> constants;
    public GraphTargetItem computedRegValue;

    public DirectValueActionItem(Object o) {
        this(null, 0, o, new ArrayList<String>());
    }

    public DirectValueActionItem(GraphSourceItem instruction, int instructionPos, Object value, List<String> constants) {
        super(instruction, PRECEDENCE_PRIMARY);
        this.constants = constants;
        this.value = value;
        this.pos = instructionPos;
    }

    @Override
    public boolean isVariableComputed() {
        if (computedRegValue != null) {
            return true;
        }
        return false;
    }

    @Override
    public Object getResult() {
        if (computedRegValue != null) {
            return computedRegValue.getResult();
        }
        if (value instanceof Double) {
            return (Double) value;
        }
        if (value instanceof Float) {
            return (double) (Float) value;
        }
        if (value instanceof Long) {
            return (double) (Long) value;
        }
        if (value instanceof Boolean) {
            return value;
        }

        if (value instanceof String) {
            return value;
        }
        if (value instanceof ConstantIndex) {
            return (this.constants.get(((ConstantIndex) value).index));
        }
        if (value instanceof RegisterNumber) {
            return new Undefined(); //has not computed value
        }
        return value;
    }

    @Override
    public String toStringNoQuotes(LocalData localData) {
        if (value instanceof Double) {
            if (Double.compare((double) (Double) value, 0) == 0) {
                return "0";
            }
        }
        if (value instanceof Float) {
            if (Float.compare((float) (Float) value, 0) == 0) {
                return "0";
            }
        }
        if (value instanceof String) {
            return (String) value;
        }
        if (value instanceof ConstantIndex) {
            return this.constants.get(((ConstantIndex) value).index);
        }
        return value.toString();
    }

    @Override
    public GraphTextWriter appendToNoQuotes(GraphTextWriter writer, LocalData localData) {
        if (value instanceof Double) {
            if (Double.compare((double) (Double) value, 0) == 0) {
                return writer.append("0");
            }
        }
        if (value instanceof Float) {
            if (Float.compare((float) (Float) value, 0) == 0) {
                return writer.append("0");
            }
        }
        if (value instanceof String) {
            return writer.append((String) value);
        }
        if (value instanceof ConstantIndex) {
            return writer.append(this.constants.get(((ConstantIndex) value).index));
        }
        return writer.append(value.toString());
    }

    public String toStringNoH(ConstantPool constants) {
        if (value instanceof Double) {
            if (Double.compare((double) (Double) value, 0) == 0) {
                return ("0");
            }
        }
        if (value instanceof Float) {
            if (Float.compare((float) (Float) value, 0) == 0) {
                return ("0");
            }
        }
        if (value instanceof String) {
            return (String) value;
        }
        if (value instanceof ConstantIndex) {
            return (this.constants.get(((ConstantIndex) value).index));
        }
        return value.toString();
    }

    @Override
    public GraphTextWriter appendTo(GraphTextWriter writer, LocalData localData) {
        if (value instanceof Double) {
            if (Double.compare((double) (Double) value, 0) == 0) {
                return writer.append("0");
            }
        }
        if (value instanceof Float) {
            if (Float.compare((float) (Float) value, 0) == 0) {
                return writer.append("0");
            }
        }
        if (value instanceof String) {
            return writer.append("\"" + Helper.escapeString((String) value) + "\"");
        }
        if (value instanceof ConstantIndex) {
            return writer.append("\"" + Helper.escapeString(this.constants.get(((ConstantIndex) value).index)) + "\"");
        }
        if (value instanceof RegisterNumber) {
            return writer.append(((RegisterNumber) value).translate());
        }
        return writer.append(value.toString());
    }

    @Override
    public boolean isCompileTime(Set<GraphTargetItem> dependencies) {
        if (computedRegValue != null) {
            if (dependencies.contains(computedRegValue)) {
                return false;
            }
            dependencies.add(computedRegValue);
        }
        return (value instanceof Double) || (value instanceof Float) || (value instanceof Boolean) || (value instanceof Long) || (value instanceof Null) || (computedRegValue != null && computedRegValue.isCompileTime(dependencies)) || (value instanceof String) || (value instanceof ConstantIndex);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.value);
        hash = 71 * hash + System.identityHashCode(this.constants);
        hash = 71 * hash + pos;
        return hash;
    }

    @Override
    public boolean valueEquals(GraphTargetItem obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof DirectValueActionItem)) {
            return false;
        }
        final DirectValueActionItem other = (DirectValueActionItem) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (!Objects.equals(this.constants, other.constants)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DirectValueActionItem other = (DirectValueActionItem) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (!Objects.equals(this.constants, other.constants)) {
            return false;
        }
        if (other.pos != this.pos) {
            return false;
        }
        return true;
    }

    @Override
    public List<GraphSourceItem> toSource(SourceGeneratorLocalData localData, SourceGenerator generator) throws CompilationException {
        return toSourceMerge(localData, generator, new ActionPush(value));
    }

    @Override
    public boolean hasReturnValue() {
        return true;
    }

    public boolean isString() {
        return (value instanceof String) || (value instanceof ConstantIndex);
    }

    public String getAsString() {
        if (!isString()) {
            return null;
        }
        return (String) getResult();
    }

    @Override
    public String toString() {
        return "" + getResult();
    }

}
