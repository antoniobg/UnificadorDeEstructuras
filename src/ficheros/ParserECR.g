///////////////////////////////////
//////////Parser ECR///////////////
///////////////////////////////////

header {
	package ficheros;
	import java.util.*;
	import antlr.*;
	import estructuras.*;
}

class ParserECR extends Parser;

options {
	buildAST = true;
}

tokens {
	DECLARACION;
	ESTRUCTURA;
	RASGOS;
	RTENTERO;
	RTCADENA;
	PROGRAMA;
	DECLARACIONES;

}

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
}

//Raiz

programa : {crearDeclaracionFichero();}
			declaraciones EOF!
			;

declaraciones : (d:declaracion)+
				 {##= #(#[DECLARACIONES, "Declaraciones"], ##);}
				 ;

declaracion! : CA! i:IDENT IGUAL! PA! e:expresion PC! CC!
					{#declaracion = #(#[ESTRUCTURA, "ECR"], #i, #e);
					 crearDeclaracionEstructuraExterna(#i, #e);}
					;

expresion :  rasgos
			 {## = #(#[RASGOS, "Rasgos"], ##);}
			 ;

rasgos : rasgo (COMA! rasgo)* 
		 | 
		 ;

rasgo : (IDENT DP PA) => estructuraInterna
		| (IDENT DP LIT_ENTERO) => rasgoTEntero
		| rasgoTCadena
		;

estructuraInterna! : i:IDENT DP! PA! e:expresion PC!
					 {#estructuraInterna = #(#[ESTRUCTURA, "ECR"], #i, #e);}
					 ;
					 
rasgoTEntero! : i:IDENT DP! e:LIT_ENTERO
				{#rasgoTEntero = #(#[RTENTERO, "RTEntero"], #i, #e);}
				;
		
rasgoTCadena! : i:IDENT DP! v:IDENT
				{#rasgoTCadena = #(#[RTCADENA, "RTString"], #i, #v);}
				;
