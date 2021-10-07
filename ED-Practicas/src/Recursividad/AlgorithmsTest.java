package Recursividad;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlgorithmsTest {

	/**
	 * Método de prueba del factorial.
	 */
	@Test
	public void testFactorial() {
		Algorithms al = new Algorithms();
		assertEquals(1,al.factorial(0));
		assertEquals(1,al.factorial(1));
		assertEquals(120,al.factorial(5));
	}

	/**
	 * Método de prueba de fibonacci.
	 */
	@Test
	public void testFib() {
		Algorithms al = new Algorithms();
		assertEquals(0,al.fibonacci(0));
		assertEquals(1,al.fibonacci(1));
		assertEquals(1,al.fibonacci(2));
		assertEquals(89,al.fibonacci(11));
	}
	
	/**
	 * Método de prueba de la potencia recursiva con 2 parámetros.
	 */
	@Test
	public void testPotenciaRec() { 
		Algorithms al = new Algorithms();
		assertEquals(1,al.potenciaRec(10,0));
		assertEquals(1,al.potenciaRec(0,0));
		assertEquals(64,al.potenciaRec(4,3));
	}
	
	/**
	 * Método de prueba de invertir un entero.
	 */
	@Test
	public void testInvertirEnteroRec() {
		Algorithms al = new Algorithms();
		assertEquals(12,al.invertirEnteroRec(21));
		assertEquals(0,al.invertirEnteroRec(0));
		assertEquals(2874,al.invertirEnteroRec(4782));
	}
	
	/**
	 * Método de prueba de invertir un String.
	 */
	@Test
	public void testInvertirString() {
		Algorithms al = new Algorithms();
		assertEquals("123456",al.invertirStringRec("654321"));
		assertEquals("1",al.invertirStringRec("1"));
		assertEquals("cba",al.invertirStringRec("abc"));
	}

	/**
	 * Método de prueba del resto de la división.
	 */
	@Test
	public void testRestoDivRec() {
		Algorithms al=new Algorithms();
		assertEquals(3,al.restoDivRec(13,10));
		assertEquals(0,al.restoDivRec(16,8));
		assertEquals(3,al.restoDivRec(28,5));
	}
	
	/**
	 * Método de prueba del comprobador de la simetría de una matriz.
	 */
	@Test
	public void testEsSimetrica() { 
		Algorithms al=new Algorithms();
		assertTrue(al.esSimetrica(new int[][] {{1,2},{2,1}}));
		assertTrue(al.esSimetrica(new int[][] {{1,2,3,4},{2,1,2,3},{3,2,1,2},{4,3,2,1}}));
	}
	
	/**
	 * Método de prueba de la potencia de 2 iterativa.
	 */
	@Test
	public void pow2Iter() {
		Algorithms al=new Algorithms();
		assertEquals(1,al.pow2Iter(0));
		assertEquals(8,al.pow2Iter(3));
		assertEquals(16,al.pow2Iter(4));
	}
	
	/**
	 * Método de prueba de la potencia de 2 recursiva 1.
	 */
	@Test
	public void testPow2Rec1() { 
		Algorithms al = new Algorithms();
		assertEquals(1,al.pow2Rec1(0));
		assertEquals(2,al.pow2Rec1(1));
		assertEquals(16,al.pow2Rec1(4));
	}

	/**
	 * Método de prueba de la potencia de 2 recursiva 2.
	 */
	@Test
	public void testPow2Rec2() {
		Algorithms al=new Algorithms();
		assertEquals(1,al.pow2Rec2(0));
		assertEquals(8,al.pow2Rec2(3));
		assertEquals(16,al.pow2Rec2(4));
	}
	
	/**
	 * Método de prueba de la potencia de 2 recursiva 3.
	 */
	@Test
	public void testPow2Rec3() {
		Algorithms al=new Algorithms();
		assertEquals(1,al.pow2Rec3(0));
		assertEquals(8,al.pow2Rec3(3));
		assertEquals(16,al.pow2Rec3(4));
	}
	
	/**
	 * Método de prueba de la potencia de 2 recursiva 4.
	 */
	@Test
	public void testPow2Rec4() {
		Algorithms al=new Algorithms();
		assertEquals(1,al.pow2Rec4(0));
		assertEquals(8,al.pow2Rec4(3));
		assertEquals(16,al.pow2Rec4(4));
	}
	
}
