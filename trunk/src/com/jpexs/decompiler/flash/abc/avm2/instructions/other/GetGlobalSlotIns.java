/*
 *  Copyright (C) 2010-2013 JPEXS
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jpexs.decompiler.flash.abc.avm2.instructions.other;

import com.jpexs.decompiler.flash.abc.ABC;
import com.jpexs.decompiler.flash.abc.avm2.AVM2Code;
import com.jpexs.decompiler.flash.abc.avm2.ConstantPool;
import com.jpexs.decompiler.flash.abc.avm2.instructions.AVM2Instruction;
import com.jpexs.decompiler.flash.abc.avm2.instructions.InstructionDefinition;
import com.jpexs.decompiler.flash.abc.avm2.treemodel.GetSlotTreeItem;
import com.jpexs.decompiler.flash.abc.avm2.treemodel.TreeItem;
import com.jpexs.decompiler.flash.abc.avm2.treemodel.clauses.ExceptionTreeItem;
import com.jpexs.decompiler.flash.abc.types.MethodInfo;
import com.jpexs.decompiler.flash.abc.types.Multiname;
import com.jpexs.decompiler.flash.abc.types.traits.TraitSlotConst;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class GetGlobalSlotIns extends InstructionDefinition {

   public GetGlobalSlotIns() {
      super(0x6e, "getglobalslot", new int[]{AVM2Code.DAT_SLOT_INDEX});
   }

   @Override
   public void translate(boolean isStatic, int classIndex, java.util.HashMap<Integer, TreeItem> localRegs, Stack<TreeItem> stack, java.util.Stack<TreeItem> scopeStack, ConstantPool constants, AVM2Instruction ins, MethodInfo[] method_info, List<TreeItem> output, com.jpexs.decompiler.flash.abc.types.MethodBody body, com.jpexs.decompiler.flash.abc.ABC abc, HashMap<Integer, String> localRegNames, List<String> fullyQualifiedNames) {
      int slotIndex = ins.operands[0];
      TreeItem obj = (TreeItem) scopeStack.get(0); //scope
      Multiname slotname = null;
      if (obj instanceof ExceptionTreeItem) {
         slotname = constants.constant_multiname[((ExceptionTreeItem) obj).exception.name_index];
      } else {

         for (int t = 0; t < body.traits.traits.length; t++) {
            if (body.traits.traits[t] instanceof TraitSlotConst) {
               if (((TraitSlotConst) body.traits.traits[t]).slot_id == slotIndex) {
                  slotname = body.traits.traits[t].getName(abc);
               }
            }

         }
      }
      stack.push(new GetSlotTreeItem(ins, obj, slotname));
   }

   @Override
   public int getStackDelta(AVM2Instruction ins, ABC abc) {
      return 1;
   }
}