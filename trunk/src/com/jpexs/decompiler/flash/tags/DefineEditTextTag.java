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
package com.jpexs.decompiler.flash.tags;

import com.jpexs.decompiler.flash.SWFInputStream;
import com.jpexs.decompiler.flash.SWFOutputStream;
import com.jpexs.decompiler.flash.tags.base.BoundedTag;
import com.jpexs.decompiler.flash.tags.base.CharacterTag;
import com.jpexs.decompiler.flash.types.RECT;
import com.jpexs.decompiler.flash.types.RGBA;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 * @author JPEXS
 */
public class DefineEditTextTag extends CharacterTag implements BoundedTag {

   public int characterID;
   public RECT bounds;
   public boolean hasText;
   public boolean wordWrap;
   public boolean multiline;
   public boolean password;
   public boolean readOnly;
   public boolean hasTextColor;
   public boolean hasMaxLength;
   public boolean hasFont;
   public boolean hasFontClass;
   public boolean autoSize;
   public boolean hasLayout;
   public boolean noSelect;
   public boolean border;
   public boolean wasStatic;
   public boolean html;
   public boolean useOutlines;
   public int fontId;
   public String fontClass;
   public int fontHeight;
   public RGBA textColor;
   public int maxLength;
   public int align;
   public int leftMargin;
   public int rightMargin;
   public int indent;
   public int leading;
   public String variableName;
   public String initialText;

   @Override
   public RECT getRect(HashMap<Integer, CharacterTag> allCharacters) {
      return bounds;
   }

   @Override
   public int getCharacterID() {
      return characterID;
   }

   /**
    * Gets data bytes
    *
    * @param version SWF version
    * @return Bytes of data
    */
   @Override
   public byte[] getData(int version) {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      OutputStream os = baos;
      SWFOutputStream sos = new SWFOutputStream(os, version);
      try {
         sos.writeUI16(characterID);
         sos.writeRECT(bounds);
         sos.writeUB(1, hasText ? 1 : 0);
         sos.writeUB(1, wordWrap ? 1 : 0);
         sos.writeUB(1, multiline ? 1 : 0);
         sos.writeUB(1, password ? 1 : 0);
         sos.writeUB(1, readOnly ? 1 : 0);
         sos.writeUB(1, hasTextColor ? 1 : 0);
         sos.writeUB(1, hasMaxLength ? 1 : 0);
         sos.writeUB(1, hasFont ? 1 : 0);
         sos.writeUB(1, hasFontClass ? 1 : 0);
         sos.writeUB(1, autoSize ? 1 : 0);
         sos.writeUB(1, hasLayout ? 1 : 0);
         sos.writeUB(1, noSelect ? 1 : 0);
         sos.writeUB(1, border ? 1 : 0);
         sos.writeUB(1, wasStatic ? 1 : 0);
         sos.writeUB(1, html ? 1 : 0);
         sos.writeUB(1, useOutlines ? 1 : 0);
         if (hasFont) {
            sos.writeUI16(fontId);
         }
         if (hasFontClass) {
            sos.writeString(fontClass);
         }
         if (hasFont) {
            sos.writeUI16(fontHeight);
         }
         if (hasTextColor) {
            sos.writeRGBA(textColor);
         }
         if (hasMaxLength) {
            sos.writeUI16(maxLength);
         }
         if (hasLayout) {
            sos.writeUI8(align);
            sos.writeUI16(leftMargin);
            sos.writeUI16(rightMargin);
            sos.writeUI16(indent);
            sos.writeSI16(leading);
         }
         sos.writeString(variableName);
         if (hasText) {
            sos.writeString(initialText);
         }

      } catch (IOException e) {
      }
      return baos.toByteArray();
   }

   /**
    * Constructor
    *
    * @param data Data bytes
    * @param version SWF version
    * @throws IOException
    */
   public DefineEditTextTag(byte data[], int version, long pos) throws IOException {
      super(37, "DefineEditText", data, pos);
      SWFInputStream sis = new SWFInputStream(new ByteArrayInputStream(data), version);
      characterID = sis.readUI16();
      bounds = sis.readRECT();
      hasText = sis.readUB(1) == 1;
      wordWrap = sis.readUB(1) == 1;
      multiline = sis.readUB(1) == 1;
      password = sis.readUB(1) == 1;
      readOnly = sis.readUB(1) == 1;
      hasTextColor = sis.readUB(1) == 1;
      hasMaxLength = sis.readUB(1) == 1;
      hasFont = sis.readUB(1) == 1;
      hasFontClass = sis.readUB(1) == 1;
      autoSize = sis.readUB(1) == 1;
      hasLayout = sis.readUB(1) == 1;
      noSelect = sis.readUB(1) == 1;
      border = sis.readUB(1) == 1;
      wasStatic = sis.readUB(1) == 1;
      html = sis.readUB(1) == 1;
      useOutlines = sis.readUB(1) == 1;
      if (hasFont) {
         fontId = sis.readUI16();
      }
      if (hasFontClass) {
         fontClass = sis.readString();
      }
      if (hasFont) {
         fontHeight = sis.readUI16();
      }
      if (hasTextColor) {
         textColor = sis.readRGBA();
      }
      if (hasMaxLength) {
         maxLength = sis.readUI16();
      }
      if (hasLayout) {
         align = sis.readUI8(); //0 left,1 right, 2 center, 3 justify
         leftMargin = sis.readUI16();
         rightMargin = sis.readUI16();
         indent = sis.readUI16();
         leading = sis.readSI16();
      }
      variableName = sis.readString();
      if (hasText) {
         initialText = sis.readString();
      }

   }

   @Override
   public Set<Integer> getNeededCharacters() {
      HashSet<Integer> needed = new HashSet<Integer>();
      if (hasFont) {
         needed.add(fontId);
      }
      return needed;
   }
}