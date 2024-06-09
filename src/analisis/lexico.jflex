package analisis;

import java_cup.runtime.Symbol;

%%

%{
    String cadena = "";
%}

%init{
    yyline = 1;
    yycolumn = 1;
%init}

%cup
%class scanner
%public
%line
%char
%column
%full
%ignorecase

%state CADENA

DOSP = ":"
P_CO = ";"
PAR_A = "("
PAR_C = ")"
LLAVE_A = "{"
LLAVE_C = "}"
BRAKET_A = "["
BRAKET_C = "]"
MAS = "+"
MENOS = "-"
MULT = "*"
DIV = "/"
MODULO = "%"
IGUAL = "="
ADMIRACION = "!"
MENOR = "<"
MAYOR = ">"
OR = "||"
AND = "&&"
XOR = "^"
PUNTO = "."
ENTEROS = [0-9]+
DECIMAL = [0-9]+"."[0-9]+
BLANCOS = [\ \r\t\f\n\\\"\t\']+
GUION_BA = "_"

TK_VAR = "var"
TK_ENTERO = "int"
TK_DOUBLE = "double"
TK_BOOLEAN = "bool"
TK_CHAR = "char"
TK_STRING = "string"
TK_VER = "true"
TK_FAL = "false"
TK_IF = "if"
TK_ CONST = "const"
TK_ELSE = "else"
TK_MATCH = "match"
TK_WHILE = "while"
TK_FOR = "for"
TK_DO = "do"
TK_BREAK = "break"
TK_CONTINUE = "continue"
TK_RETURN = "return"
TK_LIST = "list"
TK_APPEND = "append"
TK_NEW = "new"
TK_REMOVE = "remove"
TK_STRUCT = "struct"
TK_PRINTLN = "println"
TK_ROUND = "round"
TK_LENGTH = "length"
TK_TOSTRING = "tostring"
TK_FIND = "find"
TK_STARTWITH = "start_with"

%%
//de primero reservadas, luego ID
//decimal antes que entero!
<YYINITIAL> {TKEVALUAR}     {return new Symbol(sym.TKEVALUAR,yyline,yycolumn,yytext());}
<YYINITIAL> {TKEVALUAR}     {return new Symbol(sym.TKEVALUAR,yyline,yycolumn,yytext());}
<YYINITIAL> {TKEVALUAR}     {return new Symbol(sym.TKEVALUAR,yyline,yycolumn,yytext());}
<YYINITIAL> {TKEVALUAR}     {return new Symbol(sym.TKEVALUAR,yyline,yycolumn,yytext());}
<YYINITIAL> {TKEVALUAR}     {return new Symbol(sym.TKEVALUAR,yyline,yycolumn,yytext());}
<YYINITIAL> {TKEVALUAR}     {return new Symbol(sym.TKEVALUAR,yyline,yycolumn,yytext());}
<YYINITIAL> {TKEVALUAR}     {return new Symbol(sym.TKEVALUAR,yyline,yycolumn,yytext());}
<YYINITIAL> {TKEVALUAR}     {return new Symbol(sym.TKEVALUAR,yyline,yycolumn,yytext());}
<YYINITIAL> {TKEVALUAR}     {return new Symbol(sym.TKEVALUAR,yyline,yycolumn,yytext());}
<YYINITIAL> {TKEVALUAR}     {return new Symbol(sym.TKEVALUAR,yyline,yycolumn,yytext());}
<YYINITIAL> {TKEVALUAR}     {return new Symbol(sym.TKEVALUAR,yyline,yycolumn,yytext());}}
