// $ANTLR : "ParserUNF.g" -> "ParserUNF.java"$

	package ficheros;
	import java.util.*;
	import antlr.*;
	import estructuras.*;

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.collections.AST;
import java.util.Hashtable;
import antlr.ASTFactory;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;

public class ParserUNF extends antlr.LLkParser       implements ParserUNFTokenTypes
 {

	List<AUnificar> listado = new LinkedList<AUnificar>();
	
	void anadeUnificacion(AST est1, AST est2){
		AUnificar au = new AUnificar(est1.getText(),est2.getText());
		listado.add(au);
	}
	
	public List<AUnificar> getListado(){
		return listado;
	}

protected ParserUNF(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public ParserUNF(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected ParserUNF(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public ParserUNF(TokenStream lexer) {
  this(lexer,1);
}

public ParserUNF(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final void programa() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST programa_AST = null;
		
		try {      // for error handling
			declaraciones();
			astFactory.addASTChild(currentAST, returnAST);
			match(Token.EOF_TYPE);
			programa_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		returnAST = programa_AST;
	}
	
	public final void declaraciones() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST declaraciones_AST = null;
		AST d_AST = null;
		
		try {      // for error handling
			{
			int _cnt265=0;
			_loop265:
			do {
				if ((LA(1)==UNIFICA)) {
					declaracion();
					d_AST = (AST)returnAST;
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt265>=1 ) { break _loop265; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt265++;
			} while (true);
			}
			declaraciones_AST = (AST)currentAST.root;
			declaraciones_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(DECLARACIONES,"Declaraciones")).add(declaraciones_AST));
			currentAST.root = declaraciones_AST;
			currentAST.child = declaraciones_AST!=null &&declaraciones_AST.getFirstChild()!=null ?
				declaraciones_AST.getFirstChild() : declaraciones_AST;
			currentAST.advanceChildToEnd();
			declaraciones_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		returnAST = declaraciones_AST;
	}
	
	public final void declaracion() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST declaracion_AST = null;
		Token  i1 = null;
		AST i1_AST = null;
		Token  i2 = null;
		AST i2_AST = null;
		
		try {      // for error handling
			AST tmp2_AST = null;
			tmp2_AST = astFactory.create(LT(1));
			match(UNIFICA);
			match(PA);
			i1 = LT(1);
			i1_AST = astFactory.create(i1);
			match(IDENT);
			match(ASP);
			i2 = LT(1);
			i2_AST = astFactory.create(i2);
			match(IDENT);
			match(PC);
			declaracion_AST = (AST)currentAST.root;
			declaracion_AST = (AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(UNION,"Unifica")).add(i1_AST).add(i2_AST));
							anadeUnificacion(i1_AST, i2_AST);
			currentAST.root = declaracion_AST;
			currentAST.child = declaracion_AST!=null &&declaracion_AST.getFirstChild()!=null ?
				declaracion_AST.getFirstChild() : declaracion_AST;
			currentAST.advanceChildToEnd();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		returnAST = declaracion_AST;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"UNION",
		"DECLARACIONES",
		"UNIFICA",
		"PA",
		"IDENT",
		"ASP",
		"PC"
	};
	
	protected void buildTokenTypeASTClassMap() {
		tokenTypeToASTClassMap=null;
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 66L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	
	}
