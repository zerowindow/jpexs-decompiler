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
package com.jpexs.decompiler.flash.abc.avm2.model.operations;

import com.jpexs.decompiler.flash.SourceGeneratorLocalData;
import com.jpexs.decompiler.flash.abc.avm2.instructions.AVM2Instruction;
import com.jpexs.decompiler.flash.abc.avm2.instructions.InstructionDefinition;
import com.jpexs.decompiler.flash.abc.avm2.instructions.comparison.LessEqualsIns;
import com.jpexs.decompiler.flash.abc.avm2.instructions.jumps.IfLeIns;
import com.jpexs.decompiler.flash.abc.avm2.instructions.jumps.IfNLeIns;
import com.jpexs.decompiler.flash.ecma.EcmaScript;
import com.jpexs.decompiler.graph.CompilationException;
import com.jpexs.decompiler.graph.GraphSourceItem;
import com.jpexs.decompiler.graph.GraphTargetItem;
import com.jpexs.decompiler.graph.SourceGenerator;
import com.jpexs.decompiler.graph.TypeItem;
import com.jpexs.decompiler.graph.model.BinaryOpItem;
import com.jpexs.decompiler.graph.model.LogicalOpItem;
import java.util.List;

public class LeAVM2Item extends BinaryOpItem implements LogicalOpItem, IfCondition {

    public LeAVM2Item(GraphSourceItem instruction, GraphTargetItem leftSide, GraphTargetItem rightSide) {
        super(instruction, PRECEDENCE_RELATIONAL, leftSide, rightSide, "<=");
    }

    @Override
    public InstructionDefinition getIfDefinition() {
        return new IfLeIns();
    }

    @Override
    public InstructionDefinition getIfNotDefinition() {
        return new IfNLeIns();
    }

    @Override
    public GraphTargetItem invert() {
        return new GtAVM2Item(src, leftSide, rightSide);
    }

    @Override
    public Object getResult() {
        Object ret = EcmaScript.compare(rightSide.getResult(), leftSide.getResult());
        if (ret == Boolean.TRUE) {
            return Boolean.FALSE;
        }
        if (ret == Boolean.FALSE) {
            return Boolean.TRUE;
        }
        return ret;//undefined
    }

    @Override
    public List<GraphSourceItem> toSource(SourceGeneratorLocalData localData, SourceGenerator generator) throws CompilationException {
        return toSourceMerge(localData, generator, leftSide, rightSide,
                new AVM2Instruction(0, new LessEqualsIns(), null)
        );
    }

    @Override
    public GraphTargetItem returnType() {
        return new TypeItem("Boolean");
    }
}
