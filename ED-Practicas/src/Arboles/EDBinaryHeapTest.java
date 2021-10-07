package Arboles;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Clase de pruebas de los monticulos de minimos.
 *
 */
public class EDBinaryHeapTest {

	/**
	 * Pruebas del metodo add de los monticulos de minimos.
	 */
	@Test
	public void addTest() {
		EDBinaryHeap<Integer> m = new EDBinaryHeap<Integer>(10);
		
		//PRUEBAS POSITIVAS
		
		//Se añaden nodos con exito 
		assertEquals("",m.toStringAux());
		assertEquals(0,m.add(12));
		assertEquals(0,m.add(14));
		assertEquals(0,m.add(15));
		assertEquals(0,m.add(20));
		assertEquals(0,m.add(16));
		assertEquals(0,m.add(17));
		assertEquals(0,m.add(19));
		assertEquals(0,m.add(24));
		assertEquals(0,m.add(18));
		assertEquals(9,m.numElementos);
		assertEquals("12\t14\t15\t18\t16\t17\t19\t24\t20\t",m.toStringAux());
		//System.out.println(m.toString());
		
		
		//PRUEBAS NEGATIVAS.
		
		//Caso 1: Paramtro nulo 
		assertEquals(-2,m.add(null));
		
		//Caso 2: No hay espacio 
		assertEquals(0,m.add(8));
		assertEquals(-2,m.add(null));
		assertEquals(-1,m.add(9));
		
	}
	
	/**
	 * Pruebas del metodo remove de los monticulos de minimos.
	 */
	@Test 
	public void removeTest() {
		
		//PRUEBAS POSITIVAS 
		
		//Se elimina con exito 
		EDBinaryHeap<Integer> m = new EDBinaryHeap<Integer>(10);
		assertEquals(0,m.add(12));
		assertEquals(0,m.add(14));
		assertEquals(0,m.add(15));
		assertEquals(0,m.add(20));
		assertEquals(0,m.add(16));
		assertEquals(0,m.add(17));
		assertEquals(0,m.add(19));
		assertEquals(0,m.add(24));
		assertEquals(0,m.add(30));
		
		assertEquals(0,m.remove(20));
		assertEquals(0,m.remove(15));
		assertEquals(0,m.remove(12));
		assertEquals(6,m.numElementos);
		assertEquals("14\t16\t17\t24\t19\t30\t",m.toStringAux());
		m.remove(17);
		m.remove(19);
		m.remove(16);
		
		//System.out.println(m.toString());
		
		//PRUEBAS NEGATIVAS 
		
		//Caso 1: El parametro es nulo 
		assertEquals(-2,m.remove(null));
		
		//Caso 2 : El elemento que se quiere borrar no existe 
		assertEquals(-1,m.remove(500));
	}
	
	/**
	 * Pruebas de los metodos clear y isEmpty de los monticulos.
	 */
	@Test 
	public void clearEmptyTest() {
		EDBinaryHeap<Integer> m = new EDBinaryHeap<Integer>(10);
		assertTrue(m.isEmpty());
		m.add(4);
		assertFalse(m.isEmpty());
		m.clear();
		assertTrue(m.isEmpty());
		m.clear();
		assertTrue(m.isEmpty());
	}
	
	/**
	 * Pruebas del metodo getTop de los monticulos de minimos 
	 */
	@Test 
	public void getTopTest() {
		EDBinaryHeap<Integer> m = new EDBinaryHeap<Integer>(10);
		
		//PREUEBAS NEGATIVAS 
		
		//El monticulo no tiene raiz 
		assertNull(m.getTop());
		
		//PRUEBAS POSITIVAS 
		
		//El monticulo tiene raiz y se elimina correctamente 
		assertEquals(0,m.add(12));
		assertEquals(0,m.add(14));
		assertEquals(0,m.add(15));
		assertEquals(0,m.add(20));
		assertEquals(0,m.add(16));
		assertEquals(0,m.add(17));
		assertEquals(0,m.add(19));
		assertEquals(0,m.add(24));
		assertEquals(0,m.add(30));
		
		Integer aux = new Integer(12);
		assertTrue(aux.equals(m.getTop()));
		assertEquals("14\t16\t15\t20\t30\t17\t19\t24\t",m.toStringAux());		
		//System.out.println(m.toString());
	}
	

}
