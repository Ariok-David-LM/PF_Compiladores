package fes.aragon.compilador;
import java_cup.runtime.Symbol;
import java.io.Reader;
import javax.swing.JOptionPane;
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
Espacio= " "
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
{Espacio} {System.out.println("espacio"); return new Symbol(sym.ESPACIO); }
{NUM}+ {
		System.out.println("numero");
		return new Symbol(sym.NUMERO, new Integer(yytext())); }
[\t\r\f]  {}
[\n] {}
. { System.out.println("Caracter no valido. "+yytext()+"-"); 
	JOptionPane.showMessageDialog(null, "Caracter no valido. "+yytext()+"\n Verifique en su codigo que no exista.\n Se generara el codigo intermedio pero con errores\nNO PROCEDA con correrlo.");}


