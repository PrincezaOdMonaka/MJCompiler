package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"   { return new_symbol(sym.PROGRAM, yytext()); }
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"return" 	{ return new_symbol(sym.RETURN, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }
"break" 	{ return new_symbol(sym.BREAK, yytext()); }
"class" 	{ return new_symbol(sym.CLASS, yytext()); }
"abstract" 	{ return new_symbol(sym.ABSTRACT, yytext()); }
"if"	 	{ return new_symbol(sym.IF, yytext()); }
"else"  	{ return new_symbol(sym.ELSE, yytext()); }
"const" 	{ return new_symbol(sym.CONST, yytext()); }
"new" 	    { return new_symbol(sym.NEW, yytext()); }
"read" 		{ return new_symbol(sym.READ, yytext()); }
"for" 		{ return new_symbol(sym.FOR, yytext()); }
"continue" 	{ return new_symbol(sym.CONTINUE, yytext()); }

[0-9]+ { return new_symbol(sym.NUM_CONST, new Integer(yytext())); }
'.' { return new_symbol(sym.CHAR_CONST, yytext().charAt(1)); }
"true" { return new_symbol(sym.BOOL_CONST, true); }
"false" { return new_symbol(sym.BOOL_CONST, false); }
[a-zA-Z][a-zA-Z0-9_]* { return new_symbol(sym.IDENT, yytext()); }

"+" { return new_symbol(sym.OP_PLUS, yytext()); }
"-" { return new_symbol(sym.OP_MINUS, yytext()); }
"*" { return new_symbol(sym.OP_MUL, yytext()); }
"/" { return new_symbol(sym.OP_DIV, yytext()); }
"%" { return new_symbol(sym.OP_MOD, yytext()); }
"==" { return new_symbol(sym.OP_EQUALS, yytext()); }
"!=" { return new_symbol(sym.OP_NOT_EQUALS, yytext()); }
">" { return new_symbol(sym.OP_GREATER_THAN, yytext()); }
">=" { return new_symbol(sym.OP_GREATER_EQUAL, yytext()); }
"<" { return new_symbol(sym.OP_LESS_THAN, yytext()); }
"<=" { return new_symbol(sym.OP_LESS_EQUAL, yytext()); }
"&&" { return new_symbol(sym.OP_AND, yytext()); }
"||" { return new_symbol(sym.OP_OR, yytext()); }
"=" { return new_symbol(sym.OP_ASSIGN, yytext()); }
"++" { return new_symbol(sym.OP_PLUS, yytext()); }
"--" { return new_symbol(sym.OP_MINUS, yytext()); }
";" { return new_symbol(sym.OP_SEMICOLON, yytext()); }
"," { return new_symbol(sym.OP_COMMA, yytext()); }
"." { return new_symbol(sym.OP_DOT, yytext()); }
"(" { return new_symbol(sym.OP_LEFT_PAREN, yytext()); }
")" { return new_symbol(sym.OP_RIGHT_PAREN, yytext()); }
"[" { return new_symbol(sym.OP_LEFT_SQUARE, yytext()); }
"]" { return new_symbol(sym.OP_RIGHT_SQUARE, yytext()); }
"{" { return new_symbol(sym.OP_LEFT_BRACE, yytext()); }
"}" { return new_symbol(sym.OP_RIGHT_BRACE, yytext()); }


<YYINITIAL> "//" { yybegin(COMMENT); }
<COMMENT> .      { yybegin(COMMENT); }
<COMMENT> "\r\n" { yybegin(YYINITIAL); }

. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); }






