package junit;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Test;

import ejecucion.Main;
import estructuras.AUnificar;
import estructuras.ECRExterna;
import excepciones.RasgoNoCompatibleException;

/**
 * 
 * Esta clase es utilizada para medir los tiempos de ejecuci—n
 * de la funci—n encargada de unificar las estructuras
 *
 */

public class CronometroUnificacion {
	
	Map<String, ECRExterna> estructuras = Main.procesaFicheroECR("./src/test/Test01/TEST01.ecr");
	List<AUnificar> aUnificar;
	List<ECRExterna> unificadas;
	Calendar time1, time2;
	long tpoInicio, tpoFin;
	
	@After
	public void tearDown(){
		time1 = null;
		time2 = null;
	}
	
	@Test
	public void crono10() throws RasgoNoCompatibleException {
		aUnificar  = Main.procesaFicheroUNF("./src/UNFs/10.unf");
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		unificadas = Main.unificar(estructuras, aUnificar);
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo unificar con " + aUnificar.size()+ " unificaciones a realizar: " + tpoFin + "ms");
	}
	
	@Test
	public void crono100() throws RasgoNoCompatibleException {
		aUnificar  = Main.procesaFicheroUNF("./src/UNFs/100.unf");
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		unificadas = Main.unificar(estructuras, aUnificar);
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo unificar con " + aUnificar.size()+ " unificaciones a realizar: " + tpoFin + "ms");
	}
	
	@Test
	public void crono500() throws RasgoNoCompatibleException {
		aUnificar  = Main.procesaFicheroUNF("./src/UNFs/500.unf");
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		unificadas = Main.unificar(estructuras, aUnificar);
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo unificar con " + aUnificar.size()+ " unificaciones a realizar: " + tpoFin + "ms");
	}
	@Test
	public void crono1000() throws RasgoNoCompatibleException {
		aUnificar  = Main.procesaFicheroUNF("./src/UNFs/1000.unf");
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		unificadas = Main.unificar(estructuras, aUnificar);
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo unificar con " + aUnificar.size()+ " unificaciones a realizar: " + tpoFin + "ms");
	}
	
	@Test
	public void crono2000() throws RasgoNoCompatibleException {
		aUnificar  = Main.procesaFicheroUNF("./src/UNFs/2000.unf");
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		unificadas = Main.unificar(estructuras, aUnificar);
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo unificar con " + aUnificar.size()+ " unificaciones a realizar: " + tpoFin + "ms");
	}
	
	@Test
	public void crono3000() throws RasgoNoCompatibleException {
		aUnificar  = Main.procesaFicheroUNF("./src/UNFs/3000.unf");
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		unificadas = Main.unificar(estructuras, aUnificar);
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo unificar con " + aUnificar.size()+ " unificaciones a realizar: " + tpoFin + "ms");
	}
	
	@Test
	public void crono5000() throws RasgoNoCompatibleException {
		aUnificar  = Main.procesaFicheroUNF("./src/UNFs/5000.unf");
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		unificadas = Main.unificar(estructuras, aUnificar);
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo unificar con " + aUnificar.size()+ " unificaciones a realizar: " + tpoFin + "ms");
	}
	
	@Test
	public void crono10000() throws RasgoNoCompatibleException {
		aUnificar  = Main.procesaFicheroUNF("./src/UNFs/10000.unf");
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		unificadas = Main.unificar(estructuras, aUnificar);
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo unificar con " + aUnificar.size()+ " unificaciones a realizar: " + tpoFin + "ms");
	}
	@Test
	public void crono20000() throws RasgoNoCompatibleException {
		aUnificar  = Main.procesaFicheroUNF("./src/UNFs/20000.unf");
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		unificadas = Main.unificar(estructuras, aUnificar);
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo unificar con " + aUnificar.size()+ " unificaciones a realizar: " + tpoFin + "ms");
	}
	
	@Test
	public void crono50000() throws RasgoNoCompatibleException {
		aUnificar  = Main.procesaFicheroUNF("./src/UNFs/50000.unf");
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		unificadas = Main.unificar(estructuras, aUnificar);
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo unificar con " + aUnificar.size()+ " unificaciones a realizar: " + tpoFin + "ms");
	}
	
	@Test
	public void crono100000() throws RasgoNoCompatibleException {
		aUnificar  = Main.procesaFicheroUNF("./src/UNFs/100000.unf");
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		unificadas = Main.unificar(estructuras, aUnificar);
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo unificar con " + aUnificar.size()+ " estructuras a unificar: " + tpoFin + "ms");
	}
	
	@Test
	public void crono150000() throws RasgoNoCompatibleException {
		aUnificar  = Main.procesaFicheroUNF("./src/UNFs/150000.unf");
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		unificadas = Main.unificar(estructuras, aUnificar);
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo unificar con " + aUnificar.size()+ " estructuras a unificar: " + tpoFin + "ms");
	}
	
	@Test
	public void crono200000() throws RasgoNoCompatibleException {
		aUnificar  = Main.procesaFicheroUNF("./src/UNFs/200000.unf");
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		unificadas = Main.unificar(estructuras, aUnificar);
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo unificar con " + aUnificar.size()+ " estructuras a unificar: " + tpoFin + "ms");
	}
}