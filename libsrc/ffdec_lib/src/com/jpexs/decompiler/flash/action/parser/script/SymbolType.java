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
 * License along with this library. */
package com.jpexs.decompiler.flash.action.parser.script;

/**
 *
 * @author JPEXS
 */
public enum SymbolType {
    //Keywords

    BREAK,
    CASE,
    CONTINUE,
    DEFAULT,
    DO,
    WHILE,
    ELSE,
    FOR,
    EACH,
    IN,
    IF,
    RETURN,
    SUPER,
    SWITCH,
    THROW,
    TRY,
    CATCH,
    FINALLY,
    WITH,
    DYNAMIC,
    INTERNAL,
    OVERRIDE,
    PRIVATE,
    PROTECTED,
    PUBLIC,
    STATIC,
    CLASS,
    CONST,
    EXTENDS,
    FUNCTION,
    GET,
    IMPLEMENTS,
    INTERFACE,
    NAMESPACE,
    PACKAGE,
    SET,
    VAR,
    IMPORT,
    USE,
    FALSE,
    NULL,
    THIS,
    TRUE,
    //Operators
    PARENT_OPEN,
    PARENT_CLOSE,
    CURLY_OPEN,
    CURLY_CLOSE,
    BRACKET_OPEN,
    BRACKET_CLOSE,
    SEMICOLON,
    COMMA,
    REST,
    DOT,
    ASSIGN,
    GREATER_THAN,
    LOWER_THAN,
    NOT,
    NEGATE,
    TERNAR,
    COLON,
    EQUALS,
    STRICT_EQUALS,
    LOWER_EQUAL,
    GREATER_EQUAL,
    NOT_EQUAL,
    STRICT_NOT_EQUAL,
    AND,
    OR,
    FULLAND,
    FULLOR,
    INCREMENT,
    DECREMENT,
    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE,
    BITAND,
    BITOR,
    XOR,
    MODULO,
    SHIFT_LEFT,
    SHIFT_RIGHT,
    USHIFT_RIGHT,
    ASSIGN_PLUS,
    ASSIGN_MINUS,
    ASSIGN_MULTIPLY,
    ASSIGN_DIVIDE,
    ASSIGN_BITAND,
    ASSIGN_BITOR,
    ASSIGN_XOR,
    ASSIGN_MODULO,
    ASSIGN_SHIFT_LEFT,
    ASSIGN_SHIFT_RIGHT,
    ASSIGN_USHIFT_RIGHT,
    AS,
    DELETE,
    INSTANCEOF,
    IS,
    NAMESPACE_OP,
    NEW,
    TYPEOF,
    VOID,
    ATTRIBUTE,
    //Other
    STRING,
    COMMENT,
    XML,
    IDENTIFIER,
    INTEGER,
    DOUBLE,
    TYPENAME,
    EOF,
    TRACE,
    GETURL,
    GOTOANDSTOP,
    NEXTFRAME,
    PLAY,
    PREVFRAME,
    TELLTARGET,
    STOP,
    STOPALLSOUNDS,
    TOGGLEHIGHQUALITY,
    ORD,
    CHR,
    DUPLICATEMOVIECLIP,
    STOPDRAG,
    GETTIMER,
    LOADVARIABLES,
    LOADMOVIE,
    GOTOANDPLAY,
    MBORD,
    MBCHR,
    MBLENGTH,
    MBSUBSTRING,
    RANDOM,
    REMOVEMOVIECLIP,
    STARTDRAG,
    SUBSTR,
    LENGTH, //string.length
    INT,
    TARGETPATH,
    NUMBER_OP,
    STRING_OP,
    IFFRAMELOADED,
    INFINITY,
    EVAL,
    UNDEFINED,
    NEWLINE,
    NAN,
    GETVERSION,
    CALL,
    LOADMOVIENUM,
    LOADVARIABLESNUM,
    PRINT,
    PRINTNUM,
    PRINTASBITMAP,
    PRINTASBITMAPNUM,
    UNLOADMOVIE,
    UNLOADMOVIENUM,
    FSCOMMAND
}
