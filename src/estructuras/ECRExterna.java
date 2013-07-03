package estructuras;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Esta clase representa una estructura externa
 */
public class ECRExterna {

	//Etiqueta (nombre) de la estructura
	private String etiqueta;

	//Lista de definiciones
	private List<Definicion> definiciones;

	//Constructores 
	public ECRExterna(String et) {
		etiqueta = et;
		definiciones = new LinkedList<Definicion>();
	}

	public ECRExterna() {
		this.definiciones = new LinkedList<Definicion>();
	}


	//Getters y Setters
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	
	public String getEtiqueta() {
		return etiqueta;
	}

	public List<Definicion> getDefiniciones() {
		return definiciones;
	}

	public void setDefiniciones(List<Definicion> definiciones) {
		this.definiciones = definiciones;
	}


	//A–ade una definici—n
	public void add(Definicion def) throws IllegalArgumentException {
		if (def == null)
			throw new IllegalArgumentException();
		definiciones.add(def);

	}

	//Devuelve true en caso de que la definici—n 'def' estŽ en el atributo definiciones
	public boolean contains(Definicion def) throws IllegalArgumentException {
		boolean b = false;
		if (def == null)
			throw new IllegalArgumentException();
		if (getDefiniciones().contains(def))
			b = true;
		return b;
	}

	//Devuelve el n¼ de definiciones
	public int size() {
		return definiciones.size();
	}

	//Imprime una ECR externa
	public String toString() {
		String s = "##\t->" ;
		Definicion def;
		if(definiciones.isEmpty())
			s = "()";
		else {
			s = "";
			Iterator<Definicion> it = definiciones.iterator();
			while (it.hasNext()) {
				def = it.next();
				s = s.concat("(" + def.toString() + ")\n");
			}
		}
		return s;
	}

	/**
	 * @param ecr2: ECRExterna a unificar
	 * @return una ECRExterna unificada, si definiciones del objeto que devuelve 
	 * est‡ vac’o, no es unificable
	 */
	public ECRExterna unifica(ECRExterna ecr2) {
		boolean fin = false;
		ECRExterna union = new ECRExterna(getEtiqueta() + "&" + ecr2.getEtiqueta());
		Definicion def1, def2, defUnion;
		Iterator<Definicion> it1, it2;
		it1 = getDefiniciones().iterator();
		it2 = ecr2.getDefiniciones().iterator();
		while (it1.hasNext() && !fin){
			def1 = it1.next();
			while (it2.hasNext() && !fin){
				def2 = it2.next();
				defUnion = def1.unifica(def2);
				if (defUnion != null){
					fin = true;
					union.add(defUnion);
				}
			}
		}

		return union;
	}

}
