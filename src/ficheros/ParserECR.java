// $ANTLR : "ParserECR.g" -> "ParserECR.java"$

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

public class ParserECR extends antlr.LLkParser       implements ParserECRTokenTypes
 {

	Map<String, ECRExterna> estructuras;
	
	//Declaraciones
	void crearDeclaracionFichero (){
		estructuras  = new HashMap<String, ECRExterna>();
	}
	
	void crearDeclaracionEstructuraExterna(AST nombre, AST expresion){
		ECRExterna ecr = estructuras.get(nombre.getText());
		Definicion def = new Definicion();
		int size = expresion.getNumberOfChildren();
		if (size > 0){
			AST rasgo = expresion.getFirstChild();
			size--;
			Rasgo r = crearRasgo(rasgo);
			if (r instanceof ECRInterna){
					ECRInterna aux = (ECRInterna) r;
					if (aux.tieneTerminal())
						def.add(r);
				} else 
					def.add(r);
			for (int i = 0; i < size; i++){
				rasgo = rasgo.getNextSibling();
				r = crearRasgo(rasgo);
				if (r instanceof ECRInterna){
					ECRInterna aux = (ECRInterna) r;
					if (aux.tieneTerminal())
						def.add(r);
				} else 
					def.add(r);
						
			}
		}
		
		if (ecr == null) 
			ecr = new ECRExterna(nombre.getText());
		if (!def.getRasgos().isEmpty() || ecr.getDefiniciones().isEmpty())
				ecr.add(def);
		
		estructuras.put(nombre.getText(), ecr);
	}

		

	
	ECRInterna crearDeclaracionEstructuraInterna(AST nombre, AST expresion){
		ECRInterna ecr = new ECRInterna(nombre.getText());
		int size = expresion.getNumberOfChildren();
		if (size > 0){
			AST rasgo = expresion.getFirstChild();
			size--;
			Rasgo r = crearRasgo(rasgo);
			if (r instanceof RasgoTerminalString || r instanceof RasgoTerminalEntero){
					ecr.setTieneTerminal(true);
					ecr.add(r);
			} else {
				ECRInterna aux = (ECRInterna) r;
				if (aux.tieneTerminal())
					ecr.add(r);
			}
			for (int i = 0; i < size; i++){
				rasgo = rasgo.getNextSibling();
				r = crearRasgo(rasgo);
				if (r instanceof RasgoTerminalString || r instanceof RasgoTerminalEntero){
					ecr.setTieneTerminal(true);
					ecr.add(r);
				} else {
					ECRInterna aux = (ECRInterna) r;
				if (aux.tieneTerminal())
					ecr.add(r);
				}
			}
			
		}
		return ecr;
			
			
	}
	
	Rasgo crearRasgo(AST rasgo){
		Rasgo r;
		if (rasgo.getText().equals("RTEntero"))
			r = crearRTEntero(rasgo);
		else if (rasgo.getText().equals("RTString"))
			r = crearRTString(rasgo);
		else
			r = (Rasgo) crearDeclaracionEstructuraInterna(rasgo.getFirstChild(),rasgo.getFirstChild().getNextSibling());
		return r;
	}
	
	Rasgo crearRTEntero(AST rasgo){
		String s = rasgo.getFirstChild().getText();
		int i = Integer.parseInt(rasgo.getFirstChild().getNextSibling().getText());
		Rasgo r = new RasgoTerminalEntero(s, i);
		return r;
	}
	
	Rasgo crearRTString(AST rasgo){
		String s1 = rasgo.getFirstChild().getText();
		String s2 = rasgo.getFirstChild().getNextSibling().getText();
		Rasgo r = new RasgoTerminalString(s1, s2);
		return r;
	}
		
	public Map<String, ECRExterna> getEstructuras(){
		if (estructuras == null)
			estructuras = new HashMap<String, ECRExterna>();
		return estructuras;
	}

protected ParserECR(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public ParserECR(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected ParserECR(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public ParserECR(TokenStream lexer) {
  this(lexer,1);
}

public ParserECR(ParserSharedInputState state) {
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
			if ( inputState.guessing==0 ) {
				crearDeclaracionFichero();
			}
			declaraciones();
			astFactory.addASTChild(currentAST, returnAST);
			match(Token.EOF_TYPE);
			programa_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
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
			int _cnt4=0;
			_loop4:
			do {
				if ((LA(1)==CA)) {
					declaracion();
					d_AST = (AST)returnAST;
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt4>=1 ) { break _loop4; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt4++;
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				declaraciones_AST = (AST)currentAST.root;
				declaraciones_AST= (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(DECLARACIONES,"Declaraciones")).add(declaraciones_AST));
				currentAST.root = declaraciones_AST;
				currentAST.child = declaraciones_AST!=null &&declaraciones_AST.getFirstChild()!=null ?
					declaraciones_AST.getFirstChild() : declaraciones_AST;
				currentAST.advanceChildToEnd();
			}
			declaraciones_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = declaraciones_AST;
	}
	
	public final void declaracion() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST declaracion_AST = null;
		Token  i = null;
		AST i_AST = null;
		AST e_AST = null;
		
		try {      // for error handling
			match(CA);
			i = LT(1);
			i_AST = astFactory.create(i);
			match(IDENT);
			match(IGUAL);
			match(PA);
			expresion();
			e_AST = (AST)returnAST;
			match(PC);
			match(CC);
			if ( inputState.guessing==0 ) {
				declaracion_AST = (AST)currentAST.root;
				declaracion_AST = (AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(ESTRUCTURA,"ECR")).add(i_AST).add(e_AST));
									 crearDeclaracionEstructuraExterna(i_AST, e_AST);
				currentAST.root = declaracion_AST;
				currentAST.child = declaracion_AST!=null &&declaracion_AST.getFirstChild()!=null ?
					declaracion_AST.getFirstChild() : declaracion_AST;
				currentAST.advanceChildToEnd();
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_1);
			} else {
			  throw ex;
			}
		}
		returnAST = declaracion_AST;
	}
	
	public final void expresion() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expresion_AST = null;
		
		try {      // for error handling
			rasgos();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				expresion_AST = (AST)currentAST.root;
				expresion_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(RASGOS,"Rasgos")).add(expresion_AST));
				currentAST.root = expresion_AST;
				currentAST.child = expresion_AST!=null &&expresion_AST.getFirstChild()!=null ?
					expresion_AST.getFirstChild() : expresion_AST;
				currentAST.advanceChildToEnd();
			}
			expresion_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_2);
			} else {
			  throw ex;
			}
		}
		returnAST = expresion_AST;
	}
	
	public final void rasgos() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST rasgos_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case IDENT:
			{
				rasgo();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop9:
				do {
					if ((LA(1)==COMA)) {
						match(COMA);
						rasgo();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop9;
					}
					
				} while (true);
				}
				rasgos_AST = (AST)currentAST.root;
				break;
			}
			case PC:
			{
				rasgos_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_2);
			} else {
			  throw ex;
			}
		}
		returnAST = rasgos_AST;
	}
	
	public final void rasgo() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST rasgo_AST = null;
		
		try {      // for error handling
			boolean synPredMatched12 = false;
			if (((LA(1)==IDENT))) {
				int _m12 = mark();
				synPredMatched12 = true;
				inputState.guessing++;
				try {
					{
					match(IDENT);
					match(DP);
					match(PA);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched12 = false;
				}
				rewind(_m12);
inputState.guessing--;
			}
			if ( synPredMatched12 ) {
				estructuraInterna();
				astFactory.addASTChild(currentAST, returnAST);
				rasgo_AST = (AST)currentAST.root;
			}
			else {
				boolean synPredMatched14 = false;
				if (((LA(1)==IDENT))) {
					int _m14 = mark();
					synPredMatched14 = true;
					inputState.guessing++;
					try {
						{
						match(IDENT);
						match(DP);
						match(LIT_ENTERO);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched14 = false;
					}
					rewind(_m14);
inputState.guessing--;
				}
				if ( synPredMatched14 ) {
					rasgoTEntero();
					astFactory.addASTChild(currentAST, returnAST);
					rasgo_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==IDENT)) {
					rasgoTCadena();
					astFactory.addASTChild(currentAST, returnAST);
					rasgo_AST = (AST)currentAST.root;
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				if (inputState.guessing==0) {
					reportError(ex);
					recover(ex,_tokenSet_3);
				} else {
				  throw ex;
				}
			}
			returnAST = rasgo_AST;
		}
		
	public final void estructuraInterna() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST estructuraInterna_AST = null;
		Token  i = null;
		AST i_AST = null;
		AST e_AST = null;
		
		try {      // for error handling
			i = LT(1);
			i_AST = astFactory.create(i);
			match(IDENT);
			match(DP);
			match(PA);
			expresion();
			e_AST = (AST)returnAST;
			match(PC);
			if ( inputState.guessing==0 ) {
				estructuraInterna_AST = (AST)currentAST.root;
				estructuraInterna_AST = (AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(ESTRUCTURA,"ECR")).add(i_AST).add(e_AST));
				currentAST.root = estructuraInterna_AST;
				currentAST.child = estructuraInterna_AST!=null &&estructuraInterna_AST.getFirstChild()!=null ?
					estructuraInterna_AST.getFirstChild() : estructuraInterna_AST;
				currentAST.advanceChildToEnd();
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = estructuraInterna_AST;
	}
	
	public final void rasgoTEntero() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST rasgoTEntero_AST = null;
		Token  i = null;
		AST i_AST = null;
		Token  e = null;
		AST e_AST = null;
		
		try {      // for error handling
			i = LT(1);
			i_AST = astFactory.create(i);
			match(IDENT);
			match(DP);
			e = LT(1);
			e_AST = astFactory.create(e);
			match(LIT_ENTERO);
			if ( inputState.guessing==0 ) {
				rasgoTEntero_AST = (AST)currentAST.root;
				rasgoTEntero_AST = (AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(RTENTERO,"RTEntero")).add(i_AST).add(e_AST));
				currentAST.root = rasgoTEntero_AST;
				currentAST.child = rasgoTEntero_AST!=null &&rasgoTEntero_AST.getFirstChild()!=null ?
					rasgoTEntero_AST.getFirstChild() : rasgoTEntero_AST;
				currentAST.advanceChildToEnd();
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = rasgoTEntero_AST;
	}
	
	public final void rasgoTCadena() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST rasgoTCadena_AST = null;
		Token  i = null;
		AST i_AST = null;
		Token  v = null;
		AST v_AST = null;
		
		try {      // for error handling
			i = LT(1);
			i_AST = astFactory.create(i);
			match(IDENT);
			match(DP);
			v = LT(1);
			v_AST = astFactory.create(v);
			match(IDENT);
			if ( inputState.guessing==0 ) {
				rasgoTCadena_AST = (AST)currentAST.root;
				rasgoTCadena_AST = (AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(RTCADENA,"RTString")).add(i_AST).add(v_AST));
				currentAST.root = rasgoTCadena_AST;
				currentAST.child = rasgoTCadena_AST!=null &&rasgoTCadena_AST.getFirstChild()!=null ?
					rasgoTCadena_AST.getFirstChild() : rasgoTCadena_AST;
				currentAST.advanceChildToEnd();
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = rasgoTCadena_AST;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"DECLARACION",
		"ESTRUCTURA",
		"RASGOS",
		"RTENTERO",
		"RTCADENA",
		"PROGRAMA",
		"DECLARACIONES",
		"CA",
		"IDENT",
		"IGUAL",
		"PA",
		"PC",
		"CC",
		"COMA",
		"DP",
		"LIT_ENTERO"
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
		long[] data = { 2050L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 32768L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 163840L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	
	}
