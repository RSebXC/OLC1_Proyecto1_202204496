package Generador;

import java_cup.runtime.Symbol;

%%

%class Scanner 
// Nombre de la clase que genera JFlex
%public // Para tener acceso desde otros paquetes
%line // Para registrar las líneas
%char // Llevar un conteo de caracteres
%cup // Habilita la integración con Cup
%unicode // Reconocimiento de caracteres unicode
%ignorecase // Omite el case sensitive, mayúsculas y minúsculas son iguales

%init{ // Constructor del analizador
    yyline = 1; 
    yycolumn=1;
%init}

%column
// Expresiones regulares
WHITE = [ \r\t\n]+
ENTERO = [0-9]+
DECIMAL = {ENTERO}\.{ENTERO} 
CADENA = [\"][^\n\"]*[\"]
ID = [a-zA-Z][a-zA-Z0-9]*


%%

"program" { return new Symbol(sym.PROGRAM, yyline, yycolumn, yytext()); }
"end program" { return new Symbol(sym.END_PROGRAM, yyline, yycolumn, yytext()); }

"var" { return new Symbol(sym.VAR, yyline, yycolumn, yytext()); }
"end" { return new Symbol(sym.END_VAR, yyline, yycolumn, yytext()); }

"!"[^\n]*\n   { /* Ignorar comentarios de una línea */ }

"<!"[^!]*!>{ENTERO}   { /* Ignorar comentarios multilínea */ }




"SUM(" { return new Symbol(sym.OP, yyline, yycolumn, yytext()); }
"RES(" { return new Symbol(sym.OP, yyline, yycolumn, yytext()); }
"MUL(" { return new Symbol(sym.OP, yyline, yycolumn, yytext()); }
"DIV(" { return new Symbol(sym.OP, yyline, yycolumn, yytext()); }
"MOD(" { return new Symbol(sym.OP, yyline, yycolumn, yytext()); }
"Media(" { return new Symbol(sym.OP, yyline, yycolumn, yytext()); }
"Mediana(" { return new Symbol(sym.OP, yyline, yycolumn, yytext()); }
"Moda(" { return new Symbol(sym.OP, yyline, yycolumn, yytext()); }
"Varianza(" { return new Symbol(sym.OP, yyline, yycolumn, yytext()); }
"Max(" { return new Symbol(sym.OP, yyline, yycolumn, yytext()); }
"Min(" { return new Symbol(sym.OP, yyline, yycolumn, yytext()); }
{WHITE}   {}

{ENTERO}    { return new Symbol(sym.ENTERO, yyline, yycolumn, yytext()); }


"char" { return new Symbol(sym.TIPO, yyline, yycolumn, yytext()); }
"double" { return new Symbol(sym.TIPO, yyline, yycolumn, yytext()); }
":" { return new Symbol(sym.DEST, yyline, yycolumn, yytext()); }
"arr" { return new Symbol(sym.ARR, yyline, yycolumn, yytext()); }
"@" { return new Symbol(sym.AT, yyline, yycolumn, yytext()); }
"[" { return new Symbol(sym.LBRACKET, yyline, yycolumn, yytext()); }
"]" { return new Symbol(sym.RBRACKET, yyline, yycolumn, yytext()); }
")" { return new Symbol(sym.PARDER, yyline, yycolumn, yytext()); }
"," { return new Symbol(sym.COMMA, yyline, yycolumn, yytext()); }
"CONSOLE"  { return new Symbol(sym.CON, yyline, yycolumn, yytext()); }
"print"  { return new Symbol(sym.PRINT, yyline, yycolumn, yytext()); }
"column" {return new Symbol(sym.COL, yyline, yycolumn, yytext());}
">" { return new Symbol(sym.MAS, yyline, yycolumn, yytext()); }
"<" { return new Symbol(sym.MENOS, yyline, yycolumn, yytext()); }

"-" { return new Symbol(sym.LINEA, yyline, yycolumn, yytext()); }
";" {return new Symbol(sym.PYC, yyline, yycolumn, yytext()); }
{ID} { return new Symbol(sym.ID, yyline, yycolumn, yytext()); }
{CADENA}   { return new Symbol(sym.CADENA, yyline, yycolumn, yytext()); }
{DECIMAL}   { return new Symbol(sym.DECIMAL, yyline, yycolumn, yytext()); }
"=" { return new Symbol(sym.IGUAL, yyline, yycolumn, yytext()); }

. {
    System.out.println("Lexical error: "+yytext()+" linea: "+yyline+" columna: "+yycolumn);
}
