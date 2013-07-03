///////////////////////////////////
////////// Lexer ECR///////////////
//////////////////////////////////
header {
	package ficheros;
}

class LexerECR extends Lexer;
options {
	importVocab=ParserECR;
	testLiterals=false;
	k=2;
}

//Tokens inœtiles
BT : (' '|'\t') {$setType(Token.SKIP);};
SL : "\r\n" {newline();$setType(Token.SKIP);} //Windows
	 | '\n' {newline();$setType(Token.SKIP);} //Unix
	 ;
	 
//Signos de puntuaci—n
DP : ':';
PA : '(';
PC : ')';
CA : '[';
CC : ']';
COMA : ',';
IGUAL : '=';

//Lexemas auxiliares
protected DIGITO : ('0'..'9');
protected LETRA : ('a'..'z' | 'A'..'Z' | '_');

//Enteros
NUMERO : (DIGITO)+ {$setType(LIT_ENTERO);} ;

IDENT options {testLiterals=true;}: LETRA(LETRA|DIGITO)*;