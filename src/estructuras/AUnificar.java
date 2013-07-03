package estructuras;

public class AUnificar {
	
	private String est1, est2;
	
	public AUnificar (){
	
	}
	public AUnificar (String e1, String e2){
		est1 = e1;
		est2 = e2;
	}

	public String getEst1() {
		return est1;
	}

	public void setEst1(String est1) {
		this.est1 = est1;
	}

	public String getEst2() {
		return est2;
	}

	public void setEst2(String est2) {
		this.est2 = est2;
	}
	

	public boolean equals(AUnificar aunf){
		boolean b = false;
		if (getEst1().equals(aunf.getEst1()) && getEst2().equals(aunf.getEst2()))
				b = true;
		return b;
	}
	
	public String toString(){
		return "Unificar: "+getEst1()+" & "+getEst2();
	}
}
