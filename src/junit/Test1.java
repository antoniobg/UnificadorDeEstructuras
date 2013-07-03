package junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import estructuras.EstructuraComplejaDeRasgos;
import estructuras.RasgoTerminalEntero;
import estructuras.RasgoTerminalString;

public class Test1 {
	RasgoTerminalEntero rte1, rte2, rte3;
	RasgoTerminalString rts1, rts2, rts3, rts4;
	EstructuraComplejaDeRasgos ecr1, ecr2, ecr3, ecr4;
	
	@Before
	public void setup(){
		rte1 = new RasgoTerminalEntero("head", 1);
		rte3 = new RasgoTerminalEntero("head", 1);
		rte2 = new RasgoTerminalEntero("back", 2);
		rts1 = new RasgoTerminalString("finger", "three");
		rts3 = new RasgoTerminalString("finger", "three");
		rts2 = new RasgoTerminalString("arm", "four");
		rts4 = new RasgoTerminalString("head", "one");
		ecr1 = new EstructuraComplejaDeRasgos();
		ecr1.setEtiqueta("ecr1");
		ecr1.add(rte1.getEtiqueta(), rte1);
		ecr1.add(rts1.getEtiqueta(), rts1);
		ecr2 = new EstructuraComplejaDeRasgos("ecr2");
		ecr2.add(rte2.getEtiqueta(), rte2);
		ecr2.add(rts2.getEtiqueta(), rts2);
		ecr2.add(ecr1.getEtiqueta(), ecr1);
		ecr3 = new EstructuraComplejaDeRasgos("ecr3");
		ecr3.add(rts4);
		ecr4 = new EstructuraComplejaDeRasgos("ecr4");
		ecr4.add(rte2);
		
	}
	/*
	@Test
	public void test1 (){
		System.out.println(ecr2);
		
	}*/
	
	/*@Test
	public void test2(){
		String s = "[ wsWrfaS = asdierjfas, asdad";
		int fin = s.indexOf('=');
		String et = s.substring(2, fin - 1);
		System.out.println(et);
	}

	@Test
	public void test3(){
		String s = "[ wasgjieERf = (head: e, uasd: asd, easdas: (asad: asdae, ae2e: 1)) ]";
		int ppio = s.indexOf("(");
		int fin = s.indexOf("]");
		String propiedades = s.substring(ppio, fin - 1);
		System.out.println(propiedades);
	}*/
	
	@Test
	public void test4(){
		assertTrue(rte1.equals(rte3));
		assertTrue(rts1.equals(rts3));
		assertFalse(rte1.equals(rte2));
		assertFalse(rts1.equals(rts2));
	}
	
	@Test
	public void test5(){
		System.out.println(ecr1);
		System.out.println(ecr2);
		EstructuraComplejaDeRasgos e = ecr1.unifica(ecr2);
		System.out.println(e);
		System.out.println(ecr1);
		System.out.println(ecr4);
		e = ecr1.unifica(ecr4);
		System.out.println(e);
		System.out.println(ecr1);
		System.out.println(ecr3);
		e = ecr1.unifica(ecr3);
		System.out.println(e);
	}
}
