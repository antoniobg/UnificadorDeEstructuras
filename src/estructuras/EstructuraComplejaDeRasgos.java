package estructuras;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class EstructuraComplejaDeRasgos implements Rasgo {

	private String etiqueta;

	private Map<String, Rasgo> rasgos;

	private boolean interna;

	public EstructuraComplejaDeRasgos(String et) {
		etiqueta = et;
		rasgos = new HashMap<String, Rasgo>();
		interna = false;

	}

	public EstructuraComplejaDeRasgos(String et, boolean inter) {
		etiqueta = et;
		rasgos = new HashMap<String, Rasgo>();
		interna = inter;

	}
	
	public EstructuraComplejaDeRasgos(boolean inter){
		rasgos = new HashMap<String, Rasgo>();
		interna = inter;
	}

	public EstructuraComplejaDeRasgos() {
		this.rasgos = new HashMap<String, Rasgo>();
		interna = false;
	}

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

	public void setInterna(boolean interna) {
		this.interna = interna;
	}

	public boolean isInterna() {
		return interna;
	}

	public void add(String clave, Rasgo valor) throws IllegalArgumentException {
		if (clave == null || valor == null)
			throw new IllegalArgumentException();
		rasgos.put(clave, valor);

	}

	public void add(Rasgo rasgo) throws IllegalArgumentException {
		if (rasgo == null)
			throw new IllegalArgumentException();
		if (rasgo instanceof RasgoTerminalEntero) {
			RasgoTerminalEntero rte = (RasgoTerminalEntero) rasgo;
			rasgos.put(rte.getEtiqueta(), rte);
		} else if (rasgo instanceof RasgoTerminalString) {
			RasgoTerminalString rts = (RasgoTerminalString) rasgo;
			rasgos.put(rts.getEtiqueta(), rts);
		} else {
			EstructuraComplejaDeRasgos ecr = (EstructuraComplejaDeRasgos) rasgo;
			rasgos.put(ecr.getEtiqueta(), ecr);
		}

	}

	public boolean contains(Rasgo rasgo) throws IllegalArgumentException {
		boolean b = false;
		if (rasgo == null)
			throw new IllegalArgumentException();
		if (getRasgos().containsKey(rasgo.getEtiqueta()))
			b = true;
		return b;
	}

	public int size() {
		return rasgos.size();
	}

	@Override
	public boolean equals(Rasgo r) {
		boolean b = true;
		if (! (r instanceof EstructuraComplejaDeRasgos))
			b = false;
		else {
			Map<String, Rasgo> r2 = ((EstructuraComplejaDeRasgos) r)
					.getRasgos();
			if (r2.size() != getRasgos().size())
				b = false;
			else {
				String key;
				Rasgo e1, e2;
				Iterator<String> it = getRasgos().keySet().iterator();
				while (it.hasNext() && b) {
					key = it.next();
					e1 = rasgos.get(key);
					e2 = r2.get(key);
					if (e2.equals(e1))
						b = false;
				}
			}
		}
		return b;
	}


	public String toString() {
		String s;
		Rasgo rasgo;
		boolean first = true;
		Map.Entry<String, Rasgo> ent;
		if(!interna && rasgos == null)
			s = etiqueta.concat(" = FALLO");
		else if (!interna) {
			s = etiqueta.concat(" = (");
			Iterator<Map.Entry<String, Rasgo>> it = rasgos.entrySet().iterator();
			while (it.hasNext()) {
				ent = it.next();
				rasgo = ent.getValue();
				if (first) {
					s = s + rasgo.toString();

					first = false;
				} else
					s = s.concat(", " + rasgo.toString());
			}
			if (s != null)
				s = s.concat(")");
		} else {
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

		}
		return s;
	}
/* OLD
	public EstructuraComplejaDeRasgos unifica(EstructuraComplejaDeRasgos ecr2) {
		EstructuraComplejaDeRasgos union = new EstructuraComplejaDeRasgos(
				getEtiqueta() + "&" + ecr2.getEtiqueta());
		Map<String, Rasgo> rasgos2 = ecr2.getRasgos();
		union = unificaAux(union, getRasgos(), rasgos2);
		if (union != null)
			union = unificaAux(union, rasgos2, getRasgos());

		return union;
	}*/
	
	public EstructuraComplejaDeRasgos unifica(EstructuraComplejaDeRasgos ecr2) {
		EstructuraComplejaDeRasgos union = new EstructuraComplejaDeRasgos(getEtiqueta() + "&" + ecr2.getEtiqueta());
		Set<String> rasgosAUnificar = new HashSet<String>(getRasgos().keySet());
		rasgosAUnificar.addAll(ecr2.getRasgos().keySet());
		union = unificaAux(union, rasgosAUnificar, getRasgos(), ecr2.getRasgos());
		return union;
	}
	/***
	 * @param union
	 * @param rasgos1
	 * @param rasgos2
	 * @return
	 */
	private EstructuraComplejaDeRasgos unificaAux(EstructuraComplejaDeRasgos union, Set<String> claves, Map<String, Rasgo> rasgos1, Map<String, Rasgo> rasgos2){
		boolean unificable = true;
		Iterator<String> it = claves.iterator();
		String clave;
		Rasgo r1, r2;
		while (it.hasNext() && unificable){
			clave = it.next();
			r1 = rasgos1.get(clave);
			r2 = rasgos2.get(clave);
			if (r1 == null){ //1
				union.add(r2);
			}
			else if (r2 == null) {//2
				union.add(r1);

			}
			else{ //3
				
				if (rasgosCompatibles(r1,r2)){

					if (r1 instanceof RasgoTerminalEntero || r1 instanceof RasgoTerminalString)
						if (r1.equals(r2))
							union.add(r1);
						else
							unificable = false;
					else{
						EstructuraComplejaDeRasgos e1, e2, estInt;
						e1 = (EstructuraComplejaDeRasgos) r1;
						e2 = (EstructuraComplejaDeRasgos) r2;
						estInt = new EstructuraComplejaDeRasgos(e1.getEtiqueta(), true);
						Set<String> rasgosAUnificar = new HashSet<String>(e1.getRasgos().keySet());
						rasgosAUnificar.addAll(e2.getRasgos().keySet());
						estInt = unificaAux(estInt, rasgosAUnificar, e1.getRasgos(), e2.getRasgos());
						if (estInt.getRasgos() == null)
							unificable = false;
						else
							union.add(estInt);
					}
				} else 
					unificable = false;
					
			}
				
		}
		if (!unificable)
			union.setRasgos(null);
		return union;
	}

	/*private EstructuraComplejaDeRasgos unificaAux(
			EstructuraComplejaDeRasgos union, Map<String, Rasgo> rasgos1,
			Map<String, Rasgo> rasgos2) {
		boolean unificable = true;
		Iterator<String> it = getRasgos().keySet().iterator();
		String key;
		Rasgo r1, r2;
		while (it.hasNext()) {
			key = it.next();
			r1 = getRasgos().get(key);
			r2 = rasgos2.get(key);
			if (!union.contains(r1)) {
				if (r2 == null)
					union.add(r1);
				else {
					if (rasgosCompatibles(r1, r2)) {
						if (!(r1 instanceof EstructuraComplejaDeRasgos)) {
							if (r1.equals(r2))
								union.add(r1);
							else
								unificable = false;

						} else {
							EstructuraComplejaDeRasgos est1 = (EstructuraComplejaDeRasgos) r1;
							EstructuraComplejaDeRasgos est2 = (EstructuraComplejaDeRasgos) r2;
							EstructuraComplejaDeRasgos estInt = est1
									.unifica(est2);
							if (estInt == null)
								unificable = false;
							else
								union.add(estInt);
						}
					} else
						unificable = false;
				}
			}
		}

		if (!unificable)
			union = null;

		return union;
	}*/
	

	private boolean rasgosCompatibles(Rasgo r1, Rasgo r2) {
		boolean b = false;
		if ((r2 instanceof RasgoTerminalEntero)
				&& (r1 instanceof RasgoTerminalEntero))
			b = true;
		else if ((r2 instanceof RasgoTerminalString)
				&& (r1 instanceof RasgoTerminalString))
			b = true;
		else if ((r2 instanceof EstructuraComplejaDeRasgos)
				&& (r1 instanceof EstructuraComplejaDeRasgos))
			b = true;
		return b;
	}

}
