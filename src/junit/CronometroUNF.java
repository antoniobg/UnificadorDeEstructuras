package junit;

import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import ejecucion.Main;
import estructuras.AUnificar;

/**
 * 
 * Esta clase es utilizada para medir el tiempo que tarda en ser procesado
 * un fichero UNF
 *
 */

public class CronometroUNF {
	
	List<AUnificar> aUnificar;
	Calendar time1, time2;
	long tpoInicio, tpoFin;
	
	@After
	public void tearDown(){
		aUnificar = null;
		time1 = null;
		time2 = null;
	}
	
	@Test
	public void crono10(){
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		aUnificar = Main.procesaFicheroUNF("./src/UNFs/10.unf");
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo procesaFicheroUnf con " + 2*aUnificar.size()+ " estructuras a unificar: " + tpoFin + "ms");
	}
	
	@Test
	public void crono100(){
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		aUnificar = Main.procesaFicheroUNF("./src/UNFs/100.unf");
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo procesaFicheroUnf con " + 2*aUnificar.size()+ " estructuras a unificar: " + tpoFin + "ms");
	}
	
	@Test
	public void crono500(){
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		aUnificar = Main.procesaFicheroUNF("./src/UNFs/500.unf");
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo procesaFicheroUnf con " + 2*aUnificar.size()+ " estructuras a unificar: " + tpoFin + "ms");
	}
	
	@Test
	public void crono1000(){
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		aUnificar = Main.procesaFicheroUNF("./src/UNFs/1000.unf");
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo procesaFicheroUnf con " + 2*aUnificar.size()+ " estructuras a unificar: " + tpoFin + "ms");
	}
	
	@Test
	public void crono2000(){
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		aUnificar = Main.procesaFicheroUNF("./src/UNFs/2000.unf");
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo procesaFicheroUnf con " + 2*aUnificar.size()+ " estructuras a unificar: " + tpoFin + "ms");
	}
	
	@Test
	public void crono3000(){
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		aUnificar = Main.procesaFicheroUNF("./src/UNFs/3000.unf");
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo procesaFicheroUnf con " + 2*aUnificar.size()+ " estructuras a unificar: " + tpoFin + "ms");
	}
	
	@Test
	public void crono5000(){
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		aUnificar = Main.procesaFicheroUNF("./src/UNFs/5000.unf");
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo procesaFicheroUnf con " + 2*aUnificar.size()+ " estructuras a unificar: " + tpoFin + "ms");
	}
	
	@Test
	public void crono10000(){
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		aUnificar = Main.procesaFicheroUNF("./src/UNFs/10000.unf");
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo procesaFicheroUnf con " + 2*aUnificar.size()+ " estructuras a unificar: " + tpoFin + "ms");
	}
	
	@Test
	public void crono20000(){
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		aUnificar = Main.procesaFicheroUNF("./src/UNFs/20000.unf");
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo procesaFicheroUnf con " + 2*aUnificar.size()+ " estructuras a unificar: " + tpoFin + "ms");
	}
	
	@Test
	public void crono50000(){
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		aUnificar = Main.procesaFicheroUNF("./src/UNFs/50000.unf");
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo procesaFicheroUnf con " + 2*aUnificar.size()+ " estructuras a unificar: " + tpoFin + "ms");
	}
	
	@Test
	public void crono100000(){
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		aUnificar = Main.procesaFicheroUNF("./src/UNFs/100000.unf");
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo procesaFicheroUnf con " + 2*aUnificar.size()+ " estructuras a unificar: " + tpoFin + "ms");
	}
	
	@Test
	public void crono150000(){
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		aUnificar = Main.procesaFicheroUNF("./src/UNFs/150000.unf");
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo procesaFicheroUnf con " + 2*aUnificar.size()+ " estructuras a unificar: " + tpoFin + "ms");
	}
	
	@Test
	public void crono200000(){
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		aUnificar = Main.procesaFicheroUNF("./src/UNFs/200000.unf");
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo procesaFicheroUnf con " + 2*aUnificar.size()+ " estructuras a unificar: " + tpoFin + "ms");
	}
}