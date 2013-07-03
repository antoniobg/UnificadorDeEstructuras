///////////////////////////////////
//////////Parser ECR///////////////
///////////////////////////////////

header {
	package ficheros;
	import java.util.*;
	import antlr.*;
	import estructuras.*;
}

class ParserUNF extends Parser;

options {
	buildAST = true;
}

tokens {
	UNION;
	DECLARACIONES;
}
{
	List<AUnificar> listado = new LinkedList<AUnificar>();
	
	void anadeUnificacion(AST est1, AST est2){
		AUnificar au = new AUnificar(est1.getText(),est2.getText());
		listado.add(au);
	}
	
	public List<AUnificar> getListado(){
		return listado;
	}
}	



//Raiz

programa : declaraciones EOF!
			;

declaraciones : (d:declaracion)+
				{## = #(#[DECLARACIONES, "Declaraciones"], ##);}
				 ;
declaracion! : UNIFICA PA! i1:IDENT ASP! i2:IDENT PC!
				{#declaracion = #(#[UNION, "Unifica"], #i1, #i2);
				anadeUnificacion(#i1, #i2);}
				;