package ejecucion;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import antlr.ANTLRException;
import antlr.collections.AST;
import antlr.debug.misc.ASTFrame;
import estructuras.AUnificar;
import estructuras.Definicion;
import estructuras.ECRExterna;
import estructuras.ECRInterna;
import estructuras.Rasgo;
import excepciones.RasgoNoCompatibleException;
import ficheros.LexerECR;
import ficheros.LexerUNF;
import ficheros.ParserECR;
import ficheros.ParserUNF;
import gui.Ventana;

public class Main {

	public static void main(String args[]) {
		procesaFicheroECR("./src/test/Test01/Test01.ecr");
		procesaFicheroUNF("./src/test/Test01/Test01.unf");
		Ventana v = new Ventana();
		v.setVisible(true);
	}
	
	public static boolean ejecutar (String rutaEcr, String rutaUnf, String rutaTrace) throws RasgoNoCompatibleException{
		List<ECRExterna> unificadas;
		Map<String, ECRExterna> estructuras;
		List<AUnificar> listado;
		
		//Obtenemos las estructuras especificadas en el fichero .ECR
		estructuras = procesaFicheroECR(rutaEcr);
		
		//Obtenemos las estructuras a unificar del fichero UNF
		listado = procesaFicheroUNF(rutaUnf);
		
		//Obtenemos las estructuras unificadas que nos piden en el fichero .UNF
		unificadas = unificar(estructuras, listado);
		
		//Imprimimos las estructuras en un fichero .trace

		generaFicheroTrace(estructuras, unificadas, rutaTrace);
		
		return true;

	}
	
	
	/**
	 * @param ruta del fichero
	 * @return un mapa con las estructuras especificadas en el fichero
	 */
		public static Map<String, ECRExterna> procesaFicheroECR(String ruta) {
			Map<String, ECRExterna> ests = null;
			try {
				FileInputStream fis = new FileInputStream(ruta);
				LexerECR lexerEcr = new LexerECR(fis);
				ParserECR parserEcr = new ParserECR(lexerEcr);
			//	AST arbol = null;
				parserEcr.programa();
			/*	arbol = parserEcr.getAST();
				ASTFrame frame = new ASTFrame("./src/test/Test01/Test01.ecr",
				arbol);
				frame.setBounds(0, 0, 500, 600);
				frame.setVisible(true);*/
				ests = parserEcr.getEstructuras();
				
				/* System.out.println(ests.size()); 
				 Iterator<Map.Entry<String, EstructuraComplejaDeRasgos>> it = ests .entrySet().iterator();
				 Map.Entry<String, EstructuraComplejaDeRasgos> e; 
				 while (it.hasNext()) {
					 e = it.next(); 
					 System.out.println(e.getValue());
				}*/
				 


			} catch (ANTLRException ae) {
				System.err.println(ae.getMessage());

			} catch (FileNotFoundException fnfe) {
				System.err.println("No se encontro el fichero");
			}
			return ests;
		}
		
		/**
		 * @param ruta del fichero
		 * @return una lista de objetos AUnificar, que tiene dos atributos string con el nombre de
		 * dos estructuras que debemos unificar
		 */
		public static List<AUnificar> procesaFicheroUNF(String ruta) {
			List<AUnificar> listado = null;
			try {
				FileInputStream fis = new FileInputStream(ruta);
				LexerUNF lexerUnf = new LexerUNF(fis);
				ParserUNF parserUnf = new ParserUNF(lexerUnf);
				//AST arbol = null;
				parserUnf.programa();
			/*	arbol = parserUnf.getAST();
				ASTFrame frame = new ASTFrame("./src/test/Test01/Test01.unf",
				arbol);
				frame.setBounds(0, 0, 500, 600);
				frame.setVisible(true);*/
				listado = parserUnf.getListado();

			} catch (ANTLRException ae) {
				System.err.println(ae.getMessage());

			} catch (FileNotFoundException fnfe) {
				System.err.println("No se encontro el fichero");
			}
			return listado;
		}

	/**
	 * 
	 * @param estructuras: las estructuras obtenidas en el fichero ECR
	 * @param unificadas: las estructuras unificadas que nos ped’an en el fichero UNF
	 * @param ruta: el fichero de salida
	 */
		private static void generaFicheroTrace(Map<String, ECRExterna> estructuras,
				List<ECRExterna> unificadas, String ruta) {
			FileWriter fichero = null;
			PrintWriter pw = null;
			try {
				fichero = new FileWriter(ruta);
				pw = new PrintWriter(fichero);
				Iterator<ECRExterna> it = unificadas.iterator();
				ECRExterna ecr;
				String et1, et2;
				int index;
				while (it.hasNext()) {
					ecr = it.next();
					index = ecr.getEtiqueta().indexOf('&');
					et1 = ecr.getEtiqueta().substring(0, index);
					et2 = ecr.getEtiqueta().substring(index + 1);
					pw.println("\n#####################################################\n"
							+ "######  UNIFICA\n" + "##########################\n"
							+ "## Entrada 1: " + et1 + "\n"
							+ "##########################\n" + "##\n"
							+ imprimeEstructura(estructuras.get(et1))
							+ "##########################\n" + "## Entrada 2: "
							+ et2 + "\n" + "##########################\n" + "##\n"
							+ imprimeEstructura(estructuras.get(et2))
							+ "##########################\n" + "## RESULTADO:\n"
							+ "##########################\n" + "##\n"
							+ "##   UNIFICACION " + uniOFallo(ecr) + "\n##\n"
							+ imprimeEstructura(ecr) + "##");

				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					// Nuevamente aprovechamos el finally para
					// asegurarnos que se cierra el fichero.
					if (null != fichero)
						fichero.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}

		//MŽtodo auxiliar para generar el fichero .trace 
		private static String uniOFallo(ECRExterna ecr) {
			String s;
			if (ecr.getDefiniciones().isEmpty())
				s = "FALLO";
			else
				s = "OK";
			return s;
		}

		//MŽtodo auxiliar para generar el fichero .trace
		private static String imprimeEstructura(ECRExterna ecr) {
			String cadena = "";
			Iterator<Definicion> it = ecr.getDefiniciones().iterator();
			Definicion def;
			while (it.hasNext()){
				def = it.next();
				cadena = cadena.concat("##        -> (" + imprimeDefinicion(def) + ")\n");
			}

			return cadena;
		}
		
		//MŽtodo auxiliar para generar el fichero .trace 
		private static String imprimeDefinicion(Definicion def) {
			boolean first = true;
			String s = "";
			Iterator<Map.Entry<String, Rasgo>> it = def.getRasgos().entrySet().iterator();
			Map.Entry<String, Rasgo> ent;
			while (it.hasNext()){
				ent = it.next();
				if(first) {
					s = s.concat(imprimeRasgo(ent.getValue(), 0, first));
					first = false;
				}else
					s = s.concat(",\n##            " + imprimeRasgo(ent.getValue(),0, first));
			}
			return s;
		}
		
		//MŽtodo auxiliar para generar el fichero .trace
		private static String imprimeRasgo(Rasgo r, int nivel, boolean first){
			String s = "";
			if(!first)
				for (int i = 0; i <= nivel; i++)
					s = s.concat("             ");	
			if(nivel == 0)
				s = "";
			if (!(r instanceof ECRInterna))
				s = s.concat(r.toString());
			else 
				s = s.concat(imprimeECRInterna((ECRInterna) r, nivel+1));
			return s;
		}
		
		//MŽtodo auxiliar para generar el fichero .trace
		private static String imprimeECRInterna (ECRInterna ecr, int nivel){
			String s = ecr.getEtiqueta() + ":(";
			boolean first = true;
			Iterator<Map.Entry<String, Rasgo>> it = ecr.getRasgos().entrySet().iterator();
			Map.Entry<String, Rasgo> ent;
			while (it.hasNext()){
				ent = it.next();
				if (first){
					s = s.concat(imprimeRasgo(ent.getValue(), nivel, first));
					first = false;
				} else
					s = s.concat(",\n##" + imprimeRasgo(ent.getValue(), nivel, first));
			}
			s = s.concat(")");
			return s;
		}
		
		/**
		 * 
		 * @param estructuras: las estructuras obtenidas en el fichero ECR
		 * @param listado: lista de objetos AUnificar, que nos dice que estructuras tenemos que unificar
		 * @return una lista de estructuras con las estructuras unificadas
		 * @throws RasgoNoCompatibleException
		 */
		public static List<ECRExterna> unificar(
				Map<String, ECRExterna> estructuras, List<AUnificar> listado)
				throws RasgoNoCompatibleException {
			ECRExterna ecr1, ecr2, unf;
			List<ECRExterna> unificadas = new LinkedList<ECRExterna>();
			AUnificar au;
			Iterator<AUnificar> it = listado.iterator();
			while (it.hasNext()) {
				au = it.next();
				ecr1 = estructuras.get(au.getEst1());
				ecr2 = estructuras.get(au.getEst2());
				unf = ecr1.unifica(ecr2);
				unificadas.add(unf);
			}
			return unificadas;

		}
		




}
