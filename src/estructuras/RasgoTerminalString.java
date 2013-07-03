package estructuras;


//Esta clase representa una rasgo terminal String
public class RasgoTerminalString implements Rasgo{
	
	//Etiqueta (nombre) del rasgo
	private String etiqueta;
	
	//Valor del rasgo
	private String valor;

	//Constructores
	public RasgoTerminalString (){
		
	}
	
	public RasgoTerminalString (String et, String v){
		etiqueta = et;
		valor = v;
	}
	
	//Getters y setters
	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getValor() {
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
	public boolean equals(Rasgo r){
		boolean b = false;
		if(r instanceof RasgoTerminalString){
			RasgoTerminalString rasgo = (RasgoTerminalString) r;
			b = (getValor().equals(rasgo.getValor())) && (getEtiqueta().equals(rasgo.getEtiqueta()));
		}
		return b;
	}
	
	//Imprime el rasgo
	public String toString(){
		String s = etiqueta + ":'" + valor+"'";
		return s;
	}



}
