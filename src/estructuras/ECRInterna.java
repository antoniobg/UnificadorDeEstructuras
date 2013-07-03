package estructuras;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/**
 * Esta clase representa una ECRInterna
 */
public class ECRInterna implements Rasgo {

	//Etiqueta (nombre) de la ECR
	private String etiqueta;

	//Mapa con los rasgos, cuya clave es la etiqueta del Rasgo, y el valor el Rasgo mismo
	private Map<String, Rasgo> rasgos;
	
	//Booleano que comprueba que una estructura interna, contiene un rasgo que es Terminal.
	//En caso contrario la estructura no se almacena porque es insustancial
	private boolean tieneTerminal = false;

	//Constructores
	public ECRInterna(String et) {
		etiqueta = et;
		rasgos = new HashMap<String, Rasgo>();

	}

	public ECRInterna() {
		this.rasgos = new HashMap<String, Rasgo>();
	}

	//Getters y Setters
	public Map<String, Rasgo> getRasgos() {
		return rasgos;
	}

	public void setRasgos(Map<String, Rasgo> r) {
		rasgos = r;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	@Override
	public String getEtiqueta() {
		return etiqueta;
	}

	public void setTieneTerminal(boolean tieneTerminal) {
		this.tieneTerminal = tieneTerminal;
	}

	public boolean tieneTerminal() {
		return tieneTerminal;
	}

	//A–ade un rasgo a la ECRInterna
	public void add(String clave, Rasgo valor) throws IllegalArgumentException {
		if (clave == null || valor == null)
			throw new IllegalArgumentException();
		rasgos.put(clave, valor);

	}

	//A–ade un rasgo a la ECRInterna
	public void add(Rasgo rasgo) throws IllegalArgumentException {
		if (rasgo.getEtiqueta() == null || rasgos == null)
			throw new IllegalArgumentException();
		rasgos.put(rasgo.getEtiqueta(), rasgo);

	}

	//Devuelve true si la ECR contiene el rasgo 'rasgo'
	public boolean contains(Rasgo rasgo) throws IllegalArgumentException {
		boolean b = false;
		if (rasgo == null)
			throw new IllegalArgumentException();
		if (getRasgos().containsKey(rasgo.getEtiqueta()))
			b = true;
		return b;
	}

	//Devuelve el n¼ de rasgos que contiene la ECR
	public int size() {
		return rasgos.size();
	}

	@Override
	//Devuelve true si el rasgo 'r' es igual a la ECRInterna
	public boolean equals(Rasgo r) {
		boolean b = true;
		if (!(r instanceof ECRInterna))
			b = false;
		else {
			Map<String, Rasgo> r2 = ((ECRInterna) r).getRasgos();
			if (r2.size() != getRasgos().size())
				b = false;
			else {
				String key;
				Rasgo e1, e2;
				Iterator<String> it = getRasgos().keySet().iterator();
				while (it.hasNext() && b) {
					key = it.next();
					e1 = getRasgos().get(key);
					e2 = r2.get(key);
					if (e1 == null || e2 == null || !e2.equals(e1))
						b = false;
				}
			}
		}
		return b;
	}

	//Imprime la ECRInterna
	public String toString() {
		String s;
		Rasgo rasgo;
		boolean first = true;
		Map.Entry<String, Rasgo> ent;
		s = etiqueta.concat(":(");
		Iterator<Map.Entry<String, Rasgo>> it = rasgos.entrySet().iterator();
		while (it.hasNext()) {
			ent = it.next();
			rasgo = ent.getValue();
			if (first) {
				s = s.concat(rasgo.toString());
				first = false;
			} else
				s = s.concat(", " + rasgo.toString());
		}
		if (s != null)
			s = s.concat(")");

		return s;
	}



	/**
	 * @param ecr2: ECRInterna con la que se quiere unificar
	 * @return otra ECRInterna unificada, si union.getRasgos() == null, no es unificable
	 */
	public ECRInterna unifica(ECRInterna ecr2) {
		ECRInterna union = new ECRInterna (getEtiqueta());
		Set<String> rasgosAUnificar = new HashSet<String>(getRasgos().keySet());
		rasgosAUnificar.addAll(ecr2.getRasgos().keySet());
		union = unificaAux(union, rasgosAUnificar, getRasgos(), ecr2.getRasgos());
		return union;
	}

	/***
	 * Funci—n auxiliar para la unificaci—n de ECRs internas
	 * @param union: la estructura unificada unifcamente con la etiqueta
	 * @param claves: las claves de los ragos de ambas ECRs a unificar
	 * @param rasgos1: los rasgos de la ecr1 a unificar
	 * @param rasgos2: los rasgos de la ecr2 a unificar
	 * @return
	 */
	private ECRInterna unificaAux(ECRInterna union, Set<String> claves,
			Map<String, Rasgo> rasgos1, Map<String, Rasgo> rasgos2) {
		boolean unificable = true;
		Iterator<String> it = claves.iterator();
		String clave;
		Rasgo r1, r2;
		//Mientras haya rasgos por procesar y sea unificable
		while (it.hasNext() && unificable) {
			clave = it.next();
			r1 = rasgos1.get(clave);
			r2 = rasgos2.get(clave);
			//Si rasgo no existe en ECR1, lo a–adimos
			if (r1 == null) {
				union.add(r2);
			//Si no existe en ECR2, lo a–adimos
			} else if (r2 == null) {
				union.add(r1);
			//Si existe en ambas
			} else { 
				//Si los rasgos son compatibles
				if (rasgosCompatibles(r1, r2)) {
					//Si son rasgos terminales
					if (r1 instanceof RasgoTerminalEntero
							|| r1 instanceof RasgoTerminalString)
						//Si son iguales, lo a–adimos
						if (r1.equals(r2))
							union.add(r1);
						//Si no, no es unificable
						else
							unificable = false;
					//Si no son terminales, son una estructura interna, y unificamos ambas ECR internas
					else {
						ECRInterna e1, e2, estInt;
						e1 = (ECRInterna) r1;
						e2 = (ECRInterna) r2;
						estInt = new ECRInterna(e1.getEtiqueta());
						Set<String> rasgosAUnificar = new HashSet<String>(e1
								.getRasgos().keySet());
						rasgosAUnificar.addAll(e2.getRasgos().keySet());
						estInt = unificaAux(estInt, rasgosAUnificar,
								e1.getRasgos(), e2.getRasgos());
						if (estInt.getRasgos() == null)
							unificable = false;
						else
							union.add(estInt);
					}
				//Si no son compatibles, no es unificable
				} else
					unificable = false;

			}

		}
		//Si no es unificables -> union.rasgos = null
		if (!unificable)
			union.setRasgos(null);
		return union;
	}


	//Devuelve true si r1 y r2 son del mismo tipo
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
