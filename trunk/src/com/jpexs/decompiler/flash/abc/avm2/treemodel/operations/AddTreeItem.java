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
package com.jpexs.decompiler.flash.abc.avm2.treemodel.operations;

import com.jpexs.decompiler.flash.abc.avm2.ConstantPool;
import com.jpexs.decompiler.flash.abc.avm2.instructions.AVM2Instruction;
import com.jpexs.decompiler.flash.abc.avm2.treemodel.TreeItem;
import java.util.HashMap;
import java.util.List;

public class AddTreeItem extends BinaryOpTreeItem {

   public AddTreeItem(AVM2Instruction instruction, TreeItem leftSide, TreeItem rightSide) {
      super(instruction, PRECEDENCE_ADDITIVE, leftSide, rightSide, "+");
   }

   @Override
   public String toString(ConstantPool constants, HashMap<Integer, String> localRegNames, List<String> fullyQualifiedNames) {
      if (rightSide.precedence >= precedence) {
         String ret = "";
         if (leftSide.precedence > precedence) {
            ret += "(" + leftSide.toString(constants, localRegNames, fullyQualifiedNames) + ")";
         } else {
            ret += leftSide.toString(constants, localRegNames, fullyQualifiedNames);
         }
         ret += hilight(operator);
         ret += "(" + rightSide.toString(constants, localRegNames, fullyQualifiedNames) + ")";
         return ret;
      } else {
         return super.toString(constants, localRegNames, fullyQualifiedNames);
      }
   }
}