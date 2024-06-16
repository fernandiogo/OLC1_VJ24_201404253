package analisis;

import java_cup.runtime.Symbol;
import java.util.LinkedList;
import excepciones.Errores;

%%

%{
   public LinkedList<Errores> listaErrores = new LinkedList<>();
%}

%init{
    yyline = 1;
    yycolumn = 1;
    listaErrores = new LinkedList<>();
%init}

%cup
%class scanner
%public
%line
%char
%column
%full
%ignorecase


PAR_A = "("
PAR_C = ")"
IGUAL = "="
MAS =  "+"
MENOS = "-"
MULT = "*"
DIV = "/"
POTENCIA = "**"
MODULO = "%"
IGUAL2 = "=="
NEGAR = "!="
MENOR = "<"
MAYOR = ">"
MENORIQ = "<="
MAYORIQ = ">="
OR = "||"
AND = "&&"
XOR = "^"
NOT = "!"
FINCADENA = ";"
LLAVE1 = "{"
LLAVE2 = "}"
BLANCOS = [\ \r\t\f\n]+
ENTEROS = [0-9]+
DECIMAL = [0-9]+"."[0-9]+
ID = [a-zA-Z][a-zA-Z0-9_]*
CADENA = [\"]([^\"])*[\"]
CHAR = [\']([^\"])*[\']


TK_PRINTLN = "println"
INT = "int"
DOUBLE = "double"
STRING = "string"
BOOL = "bool" 
IF = "if"
TRUE = "true"
FALSE = "false"
FOR = "for"

%%

<YYINITIAL> {TK_PRINTLN}   {return new Symbol(sym.TK_PRINTLN, yyline, yycolumn,yytext());}
<YYINITIAL> {INT}          {return new Symbol(sym.INT, yyline, yycolumn,yytext());}
<YYINITIAL> {DOUBLE}       {return new Symbol(sym.DOUBLE, yyline, yycolumn,yytext());}
<YYINITIAL> {STRING}       {return new Symbol(sym.STRING, yyline, yycolumn,yytext());}
<YYINITIAL> {TRUE}         {return new Symbol(sym.TRUE, yyline, yycolumn,yytext());}
<YYINITIAL> {FALSE}        {return new Symbol(sym.FALSE, yyline, yycolumn,yytext());}
<YYINITIAL> {IF}           {return new Symbol(sym.IF, yyline, yycolumn,yytext());}
<YYINITIAL> {BOOL}         {return new Symbol(sym.BOOL, yyline, yycolumn,yytext());}
<YYINITIAL> {FOR}         {return new Symbol(sym.FOR, yyline, yycolumn,yytext());}

<YYINITIAL> {ID}           {return new Symbol(sym.ID, yyline, yycolumn,yytext());}

<YYINITIAL> {DECIMAL}      {return new Symbol(sym.DECIMAL, yyline, yycolumn,yytext());}
<YYINITIAL> {ENTEROS}      {return new Symbol(sym.ENTEROS, yyline, yycolumn,yytext());}

<YYINITIAL> {CADENA} {
    String cadena = yytext();
    cadena = cadena.substring(1, cadena.length()-1);
    return new Symbol(sym.CADENA, yyline, yycolumn,cadena);
    }
<YYINITIAL> {CHAR} {
    String cadena = yytext();
    cadena = cadena.substring(1, cadena.length()-1);
    char cr = cadena.charAt(0);
    return new Symbol(sym.CHAR, yyline, yycolumn,cr);
    }

<YYINITIAL> {FINCADENA}    {return new Symbol(sym.FINCADENA, yyline, yycolumn,yytext());}
<YYINITIAL> {PAR_A}        {return new Symbol(sym.PAR_A, yyline, yycolumn,yytext());}
<YYINITIAL> {PAR_C}        {return new Symbol(sym.PAR_C, yyline, yycolumn,yytext());}
<YYINITIAL> {LLAVE1}        {return new Symbol(sym.LLAVE1, yyline, yycolumn,yytext());}
<YYINITIAL> {LLAVE2}        {return new Symbol(sym.LLAVE2, yyline, yycolumn,yytext());}

<YYINITIAL> {MAS}          {return new Symbol(sym.MAS, yyline, yycolumn,yytext());}
<YYINITIAL> {MENOS}        {return new Symbol(sym.MENOS, yyline, yycolumn,yytext());}
<YYINITIAL> {POTENCIA}     {return new Symbol(sym.POTENCIA, yyline, yycolumn,yytext());}
<YYINITIAL> {MULT}         {return new Symbol(sym.MULT, yyline, yycolumn,yytext());}
<YYINITIAL> {DIV}          {return new Symbol(sym.DIV, yyline, yycolumn,yytext());}
<YYINITIAL> {MODULO}       {return new Symbol(sym.MODULO, yyline, yycolumn,yytext());}


<YYINITIAL> {IGUAL2}       {return new Symbol(sym.IGUAL2, yyline, yycolumn,yytext());}
<YYINITIAL> {IGUAL}        {return new Symbol(sym.IGUAL, yyline, yycolumn,yytext());}
<YYINITIAL> {NEGAR}        {return new Symbol(sym.NEGAR, yyline, yycolumn,yytext());}
<YYINITIAL> {MENOR}        {return new Symbol(sym.MENOR, yyline, yycolumn,yytext());}
<YYINITIAL> {MAYOR}        {return new Symbol(sym.MAYOR, yyline, yycolumn,yytext());}
<YYINITIAL> {MENORIQ}      {return new Symbol(sym.MENORIQ, yyline, yycolumn,yytext());}
<YYINITIAL> {MAYORIQ}      {return new Symbol(sym.MAYORIQ, yyline, yycolumn,yytext());}

<YYINITIAL> {OR}           {return new Symbol(sym.OR, yyline, yycolumn,yytext());}
<YYINITIAL> {AND}          {return new Symbol(sym.AND, yyline, yycolumn,yytext());}
<YYINITIAL> {XOR}          {return new Symbol(sym.XOR, yyline, yycolumn,yytext());}
<YYINITIAL> {NOT}          {return new Symbol(sym.NOT, yyline, yycolumn,yytext());}


<YYINITIAL> {BLANCOS}      {}

<YYINITIAL> . {
                listaErrores.add(new Errores("LEXICO", "El caracter" + " "+ 
                yytext() + " "+ "no esta definido en nuestro lenguaje!", yyline, yycolumn));
}
