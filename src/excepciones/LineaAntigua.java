/*package excepciones;

import estructuras.EstructuraComplejaDeRasgos;
import estructuras.Rasgo;
import estructuras.RasgoTerminalEntero;
import estructuras.RasgoTerminalString;

import org.apache.commons.lang3.StringUtils;

public class LineaAntigua {

	private String linea;
	private int cont = 0;
	private EstructuraComplejaDeRasgos ecr;
	
	public LineaAntigua(String l){
		setLinea(l);
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public String getLinea() {
		return linea;
	}
	
	public EstructuraComplejaDeRasgos tratarLinea(){
		String propiedades = tratamientoInicial();
		tratarPropiedades(propiedades);
		
		return ecr;
	}
	public void tratarPropiedades(String propiedades){
		boolean fin = false;
		String propiedad = null;
		String nombre = null;
		String valor = null;
		char car;
		int entero;
		Rasgo rasgo;
		while(!fin){
			j = propiedades.indexOf(',');
			propiedad = propiedades.substring(i, j);
			propiedades = propiedades.substring(j + 2);
			i = propiedad.indexOf(':');
			j = propiedad.indexOf(',');
			nombre = propiedad.substring(0, i);
			valor = propiedad.substring(i + 1,  j);
			car = valor.charAt(0);
			if (car != '(') {
				if (StringUtils.isNumeric(valor)){
					entero = Integer.parseInt(valor);
					rasgo = new RasgoTerminalEntero(nombre, entero);
					ecr.add(rasgo);
				} else {
					rasgo = new RasgoTerminalString(nombre, valor);
					ecr.add(rasgo);	
				}
			} else {
				
			}
			
			
			
		}
		
		return ecr;
	}
	
	private String tratamientoInicial(){
		ecr = new EstructuraComplejaDeRasgos();
		int j = linea.indexOf('=');
		String et = linea.substring(2, j - 1);
		ecr.setEtiqueta(et);
		int i = linea.indexOf('(');
		j = linea.indexOf(']');
		return linea.substring(i + 1, j - 2);
	}
}
*/