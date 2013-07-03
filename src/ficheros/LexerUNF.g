///////////////////////////////////
////////// Lexer UNF///////////////
//////////////////////////////////
header {
	package ficheros;
}

class LexerUNF extends Lexer;
options {
	importVocab=ParserUNF;
	testLiterals=false;
	k=2;
	charVocabulary = '\u1000'..'\u1fff';
}
tokens {
	UNIFICA = "Unifica";
}
//Tokens inœtiles
BT : (' '|'\t'|'$') {$setType(Token.SKIP);};
SL : "\r\n" {newline();$setType(Token.SKIP);} //Windows
	 | '\n' {newline();$setType(Token.SKIP);} //Unix
	 ;
	 
//Signos de puntuaci—n
PA : '(';
PC : ')';
ASP : '&';
//Lexemas auxiliares
protected DIGITO : ('0'..'9');
protected LETRA : ('a'..'z' | 'A'..'Z' | '_');

//Enteros

IDENT options {testLiterals=true;}: LETRA(LETRA|DIGITO)*;