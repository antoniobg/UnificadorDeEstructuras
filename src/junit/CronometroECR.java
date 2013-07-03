package junit;

import java.util.Calendar;
import java.util.Map;

import org.junit.After;
import org.junit.Test;

import ejecucion.Main;
import estructuras.ECRExterna;

/**
 * 
 * Esta clase es utilizada para medir el tiempo que tarda en ser procesado
 * un fichero ECR
 *
 */

public class CronometroECR {
	
	Map<String, ECRExterna> estructuras;
	Calendar time1, time2;
	long tpoInicio, tpoFin;
	
	@After
	public void tearDown(){
		estructuras = null;
		time1 = null;
		time2 = null;
	}
	
	@Test
	public void crono10(){
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		estructuras = Main.procesaFicheroECR("./src/ECRs/10.ecr");
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo procesaFicheroEcr con " + estructuras.size()+ " estructuras: " + tpoFin + "ms");
	}
	@Test
	public void crono100(){
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		estructuras = Main.procesaFicheroECR("./src/ECRs/100.ecr");
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo procesaFicheroEcr con " + estructuras.size() + " estructuras: " + tpoFin + "ms");
	}
	
	@Test
	public void crono500(){
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		estructuras = Main.procesaFicheroECR("./src/ECRs/500.ecr");
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo procesaFicheroEcr con " + estructuras.size()+ " estructuras: " + tpoFin + "ms");
	}

	@Test
	public void crono1000(){
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		estructuras = Main.procesaFicheroECR("./src/ECRs/1000.ecr");
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo procesaFicheroEcr con " + estructuras.size()+ " estructuras: " + tpoFin + "ms");
	}
	
	@Test
	public void crono2000(){
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		estructuras = Main.procesaFicheroECR("./src/ECRs/2000.ecr");
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo procesaFicheroEcr con " + estructuras.size()+ " estructuras: " + tpoFin + "ms");
	}
	
	@Test
	public void crono3000(){
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		estructuras = Main.procesaFicheroECR("./src/ECRs/3000.ecr");
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo procesaFicheroEcr con " + estructuras.size()+ " estructuras: " + tpoFin + "ms");
	}
	
	@Test
	public void crono5000(){
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		estructuras = Main.procesaFicheroECR("./src/ECRs/5000.ecr");
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo procesaFicheroEcr con " + estructuras.size()+ " estructuras: " + tpoFin + "ms");
	}
	
	@Test
	public void crono10000(){
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		estructuras = Main.procesaFicheroECR("./src/ECRs/10000.ecr");
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo procesaFicheroEcr con " + estructuras.size()+ " estructuras: " + tpoFin + "ms");
	}
	
	@Test
	public void crono11000(){
		time1 = Calendar.getInstance();
		tpoInicio = time1.getTimeInMillis();
		estructuras = Main.procesaFicheroECR("./src/ECRs/11000.ecr");
		time2 = Calendar.getInstance();
		tpoFin = time2.getTimeInMillis();
		tpoFin -= tpoInicio;
		System.out.println("Tpo procesaFicheroEcr con " + estructuras.size()+ " estructuras: " + tpoFin + "ms");
	}
	
}

