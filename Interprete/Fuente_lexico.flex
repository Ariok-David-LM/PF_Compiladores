package fes.aragon.compilador;
import java_cup.runtime.Symbol;
import java.io.Reader;
%%
%{
	public int getYyline() {
                return yyline;
        }

        public int getYy_currentPos() {
            return yy_currentPos-1;
        }
%}
%class Lexico
%public
%char
%line
%cup
%ignorecase
%type java_cup.runtime.Symbol
//%implements java_cup.runtime.Scanner
%eofval{
 return new Symbol(sym.EOF,new String("Fin del archivo"));
//return null;
%eofval}
DIGITO=[0-9]
NUM={DIGITO}+
%%
"coloca" {System.out.println("coloca"); return new Symbol(sym.COLOCA); }
"arriba" {System.out.println("arriba"); return new Symbol(sym.ARRIBA); }
"abajo" {System.out.println("abajo"); return new Symbol(sym.ABAJO); }
"izquierda" {System.out.println("izquierda"); return new Symbol(sym.IZQUIERDA); }
"derecha" {System.out.println("derecha"); return new Symbol(sym.DERECHA); }
"mover" {System.out.println("mover"); return new Symbol(sym.MOVER); }
"repetir" {System.out.println("repetir"); return new Symbol(sym.REPETIR); }
"ver fruta" {System.out.println("ver fruta"); return new Symbol(sym.VERFRUTA); }
";" {System.out.println("puntocoma"); return new Symbol(sym.PUNTOYCOMA); }
"{" {System.out.println("llavea"); return new Symbol(sym.LLAVEA); }
"}" {System.out.println("llavec"); return new Symbol(sym.LLAVEC); }

{NUM}+ {
		System.out.println("numero");
		return new Symbol(sym.NUMERO, new Integer(yytext())); }
[\t\r\f]  {}
[\n] {}
" " {System.out.println("Simbolo ."+yytext());}
. { System.out.println("Caracter no valido. "+yytext()+"-"); }


