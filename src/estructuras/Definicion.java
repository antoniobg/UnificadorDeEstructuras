package estructuras;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Esta clase representa a cada definici—n que tiene una estructura
 */
public class Definicion {
	

	//Mapa de String y Rasgo. String es la etiqueta del Rasgo, y Rasgo puede ser
	//un Rasgo Terminal (Entero o String) o una ECR interna
	private Map<String, Rasgo> rasgos;

	// Constructores
	
	public Definicion() {
		rasgos = new HashMap<String, Rasgo>();
	}

	//Getters y Setters
	public Map<String, Rasgo> getRasgos() {
		return rasgos;
	}
	
	public void setRasgos(Map<String, Rasgo> r) {
		rasgos = r;
	}

	//A–ade un rasgo a la definici—n
	public void add(Rasgo r) throws IllegalArgumentException {
		if (r == null)
			throw new IllegalArgumentException(
					"Error. No se puede a–adir un rasgo nulo");
		rasgos.put(r.getEtiqueta(), r);
	}

	//Devuelve true si la definici—n contiene al rasgo 'rasgo', false en caso contrario
	public boolean contains(Rasgo rasgo) throws IllegalArgumentException {
		boolean b = false;
		if (rasgo == null)
			throw new IllegalArgumentException();
		if (getRasgos().containsKey(rasgo.getEtiqueta()))
			b = true;
		return b;
	}

	//Devuelve el nœmero de rasgos que tiene la definici—n
	public int size() {
		return rasgos.size();
	}


	//Imprime una definici—n
	public String toString() {
		String s = "";
		Rasgo rasgo;
		boolean first = true;
		Map.Entry<String, Rasgo> ent;
		Iterator<Map.Entry<String, Rasgo>> it = rasgos.entrySet().iterator();
		while (it.hasNext()) {
			ent = it.next();
			rasgo = ent.getValue();
			if (first) {
				s = rasgo.toString();
				first = false;
			} else
				s = s.concat(",\n " + rasgo.toString());
		}

		return s;
	}

	//Unifica dos definiciones, devuelve null si las definiciones no son unificables
	public Definicion unifica(Definicion def2) {
		Definicion union;
		Set<String> rasgosAUnificar = new HashSet<String>(getRasgos().keySet());
		rasgosAUnificar.addAll(def2.getRasgos().keySet());
		union = unificaAux(rasgosAUnificar, getRasgos(), def2.getRasgos());
		return union;
	}

/**
 * 
 * @param claves: Set con todas las claves pertenecientes a las dos definiciones
 * @param rasgos1: Map con los rasgos de la definici—n 1
 * @param rasgos2: Map con los rasgos de la definici—n 2
 * @return una definici—n unificada o null en caso de que no sean unificables
 */
	public Definicion unificaAux(Set<String> claves,
			Map<String, Rasgo> rasgos1, Map<String, Rasgo> rasgos2) {
		Definicion union = new Definicion();
		boolean unificable = true;
		Iterator<String> it = claves.iterator();
		String clave;
		Rasgo r1, r2;
		//Mientras existan rasgos y sea unificable
		while (it.hasNext() && unificable) {
			clave = it.next();
			r1 = rasgos1.get(clave);
			r2 = rasgos2.get(clave);
			//Si el rasgo no est‡ en la definici—n uno, lo a–adimos
			if (r1 == null)
				union.add(r2);
			//Si el rasgo no est‡ en la definici—n dos, lo a–adimos
			else if (r2 == null)
				union.add(r1);
			//Si el rasgo est‡ en ambas definiciones
			else {
				//Si son del mismo tipo
				if (rasgosCompatibles(r1, r2)) {
					//Si es RTEntero o RTString
					if (r1 instanceof RasgoTerminalEntero
							|| r1 instanceof RasgoTerminalString)
						//Si son iguales lo a–adimos
						if (r1.equals(r2))
							union.add(r1);
						//Si no, no es unificable
						else
							unificable = false;
					//Si el rasgo es una ECR interna
					else {
						ECRInterna ecri1 = (ECRInterna) r1;
						ECRInterna ecri2 = (ECRInterna) r2;
						//Si son iguales la a–adimos
						if (ecri1.equals(ecri2))
							union.add(ecri1);
						//Si no son iguales las unificamos
						else {
							ECRInterna ecri3 = ecri1.unifica(ecri2);
							//Si la estructura obtenida tiene rasgos == null, no es unificable
							if (ecri3.getRasgos() == null)
								unificable = false;
							//En caso contrario la a–adimos
							else
								union.add(ecri3);
						}
					}
				//Si los rasgos no son compatibles no es unificable
				} else {
					unificable = false;
				}
			}
		}
		//Si no es unificable, devolvemos null
		if (!unificable)
			union = null;
		return union;
	}

	/**
	 * @param r1: Rasgo
	 * @param r2: Rasgo
	 * @return true si son del mismo tipo, false en caso contrario
	 */
	private boolean rasgosCompatibles(Rasgo r1, Rasgo r2) {
		boolean b = false;
		if ((r2 instanceof RasgoTerminalEntero)
				&& (r1 instanceof RasgoTerminalEntero))
			b = true;
		else if ((r2 instanceof RasgoTerminalString)
				&& (r1 instanceof RasgoTerminalString))
			b = true;
		else if ((r2 instanceof ECRInterna) && (r1 instanceof ECRInterna))
			b = true;
		return b;
	}

}
