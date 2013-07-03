package estructuras;

//Esta clase representa una rasgo terminal Entero
public class RasgoTerminalEntero implements Rasgo{
	
	//Etiqueta (nombre) del rasgo
	private String etiqueta; 
	
	//Valor del rasgo
	private int valor;

	//Constructores
	public RasgoTerminalEntero (){
		
	}
	
	public RasgoTerminalEntero (String et, int v){
		etiqueta = et;
		valor = v;
	}

	//Getters y setters
	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}
	
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	@Override
	public String getEtiqueta() {
		return etiqueta;
	}

	@Override	
	//Devuelve true si los rasgos son iguales, false en caso contrario
	public boolean equals(Rasgo r) {
		boolean b = false;
		if(r instanceof RasgoTerminalEntero){
			RasgoTerminalEntero rasgo = (RasgoTerminalEntero) r;
			b = (rasgo.getValor() == getValor()) && rasgo.getEtiqueta().equals(getEtiqueta());
		}
		return b;

	}
	
	//Imprime el rasgo
	public String toString(){
		String s = etiqueta + ":" + valor;
		return s;
	}




}
