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

"program" { Globales.tokens.add(new Token("program",yyline, yycolumn, yytext()));
 return new Symbol(sym.PROGRAM, yyline, yycolumn, yytext()); }
"end program" {Globales.tokens.add(new Token("program",yyline, yycolumn, yytext())); return new Symbol(sym.END_PROGRAM, yyline, yycolumn, yytext()); }

"var" { Globales.tokens.add(new Token("var",yyline, yycolumn, yytext())); return new Symbol(sym.VAR, yyline, yycolumn, yytext()); }
"end" { Globales.tokens.add(new Token("end",yyline, yycolumn, yytext())); return new Symbol(sym.END_VAR, yyline, yycolumn, yytext()); }

"SUM(" { Globales.tokens.add(new Token("SUM",yyline, yycolumn, yytext())); return new Symbol(sym.OP, yyline, yycolumn, yytext()); }
"RES(" { Globales.tokens.add(new Token("RES",yyline, yycolumn, yytext())); return new Symbol(sym.OP, yyline, yycolumn, yytext()); }
"MUL(" { Globales.tokens.add(new Token("MUL",yyline, yycolumn, yytext())); return new Symbol(sym.OP, yyline, yycolumn, yytext()); }
"DIV(" { Globales.tokens.add(new Token("DIV",yyline, yycolumn, yytext())); return new Symbol(sym.OP, yyline, yycolumn, yytext()); }
"MOD(" { Globales.tokens.add(new Token("MOD",yyline, yycolumn, yytext())); return new Symbol(sym.OP, yyline, yycolumn, yytext()); }
"Media(" { Globales.tokens.add(new Token("Media",yyline, yycolumn, yytext())); return new Symbol(sym.ES, yyline, yycolumn, yytext()); }
"Mediana(" { Globales.tokens.add(new Token("Mediana",yyline, yycolumn, yytext())); return new Symbol(sym.ES, yyline, yycolumn, yytext()); }
"Moda(" { Globales.tokens.add(new Token("Moda",yyline, yycolumn, yytext())); return new Symbol(sym.ES, yyline, yycolumn, yytext()); }
"Varianza(" { Globales.tokens.add(new Token("Varianza",yyline, yycolumn, yytext())); return new Symbol(sym.ES, yyline, yycolumn, yytext()); }
"Max(" { Globales.tokens.add(new Token("Mex",yyline, yycolumn, yytext())); return new Symbol(sym.ES, yyline, yycolumn, yytext()); }
"Min(" { Globales.tokens.add(new Token("Min",yyline, yycolumn, yytext())); return new Symbol(sym.ES, yyline, yycolumn, yytext()); }
"graphbar" { Globales.tokens.add(new Token("graphBar",yyline, yycolumn, yytext())); return new Symbol(sym.tgraf, yyline, yycolumn, yytext()); }
"graphlie" { Globales.tokens.add(new Token("graphPie",yyline, yycolumn, yytext())); return new Symbol(sym.tgraf, yyline, yycolumn, yytext()); }
"graphLine" { Globales.tokens.add(new Token("graphLine",yyline, yycolumn, yytext())); return new Symbol(sym.tgraf, yyline, yycolumn, yytext()); }
"titulo" { Globales.tokens.add(new Token("titulo",yyline, yycolumn, yytext())); return new Symbol(sym.dgraf, yyline, yycolumn, yytext()); }
"ejeX" { Globales.tokens.add(new Token("ejeX",yyline, yycolumn, yytext())); return new Symbol(sym.dgraf, yyline, yycolumn, yytext()); }
"ejeY" { Globales.tokens.add(new Token("ejeY",yyline, yycolumn, yytext())); return new Symbol(sym.dgraf, yyline, yycolumn, yytext()); }
"tituloX" { Globales.tokens.add(new Token("tituloX",yyline, yycolumn, yytext())); return new Symbol(sym.dgraf, yyline, yycolumn, yytext()); }
"tituloY" { Globales.tokens.add(new Token("tituloY",yyline, yycolumn, yytext())); return new Symbol(sym.dgraf, yyline, yycolumn, yytext()); }
"EXEC" { Globales.tokens.add(new Token("EXEC",yyline, yycolumn, yytext())); return new Symbol(sym.EXEC, yyline, yycolumn, yytext()); }
{WHITE}   {}

{ENTERO}    { return new Symbol(sym.ENTERO, yyline, yycolumn, yytext()); }


"char[]" { Globales.tokens.add(new Token("char[]",yyline, yycolumn, yytext())); return new Symbol(sym.TIPO, yyline, yycolumn, yytext()); }
"double" {Globales.tokens.add(new Token("double",yyline, yycolumn, yytext()));  return new Symbol(sym.TIPO, yyline, yycolumn, yytext()); }
":" { Globales.tokens.add(new Token(":",yyline, yycolumn, yytext())); return new Symbol(sym.DEST, yyline, yycolumn, yytext()); }
"arr" { Globales.tokens.add(new Token("arr",yyline, yycolumn, yytext())); return new Symbol(sym.ARR, yyline, yycolumn, yytext()); }
"@" { Globales.tokens.add(new Token("@",yyline, yycolumn, yytext())); return new Symbol(sym.AT, yyline, yycolumn, yytext()); }
"[" { Globales.tokens.add(new Token("[",yyline, yycolumn, yytext())); return new Symbol(sym.LBRACKET, yyline, yycolumn, yytext()); }
"]" { Globales.tokens.add(new Token("]",yyline, yycolumn, yytext())); return new Symbol(sym.RBRACKET, yyline, yycolumn, yytext()); }
")" { Globales.tokens.add(new Token(")",yyline, yycolumn, yytext())); return new Symbol(sym.PARDER, yyline, yycolumn, yytext()); }
"(" {Globales.tokens.add(new Token("(",yyline, yycolumn, yytext())); return new Symbol(sym.PARE, yyline, yycolumn, yytext()); }
"," { Globales.tokens.add(new Token(",",yyline, yycolumn, yytext())); return new Symbol(sym.COMMA, yyline, yycolumn, yytext()); }
"CONSOLE"  { Globales.tokens.add(new Token("CONSOLE",yyline, yycolumn, yytext())); return new Symbol(sym.CON, yyline, yycolumn, yytext()); }
"print"  { Globales.tokens.add(new Token("print",yyline, yycolumn, yytext())); return new Symbol(sym.PRINT, yyline, yycolumn, yytext()); }
"column" {Globales.tokens.add(new Token("column",yyline, yycolumn, yytext())); return new Symbol(sym.COL, yyline, yycolumn, yytext());}
">" { Globales.tokens.add(new Token(">",yyline, yycolumn, yytext())); return new Symbol(sym.MAS, yyline, yycolumn, yytext()); }
"<" { Globales.tokens.add(new Token("<",yyline, yycolumn, yytext())); return new Symbol(sym.MENOS, yyline, yycolumn, yytext()); }
"=" { Globales.tokens.add(new Token("=",yyline, yycolumn, yytext())); return new Symbol(sym.IGUAL, yyline, yycolumn, yytext()); }
"-" { Globales.tokens.add(new Token("-",yyline, yycolumn, yytext())); return new Symbol(sym.LINEA, yyline, yycolumn, yytext()); }
";" {Globales.tokens.add(new Token(";",yyline, yycolumn, yytext())); return new Symbol(sym.PYC, yyline, yycolumn, yytext()); }
{ID} { return new Symbol(sym.ID, yyline, yycolumn, yytext()); }
{CADENA}   { return new Symbol(sym.CADENA, yyline, yycolumn, yytext()); }
{DECIMAL}   { return new Symbol(sym.DECIMAL, yyline, yycolumn, yytext()); }



"<!"[^!]*!>{ENTERO}   { /* Ignorar comentarios multilínea */ }


"!"[^\n]*\n   { /* Ignorar comentarios de una línea */ }
. {
   Globales.error.add(new Token("Error lexico",yyline, yycolumn, yytext()));
 System.out.println("Lexical error: "+yytext()+" linea: "+yyline+" columna: "+yycolumn);
    
}
