package Grafos;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class GrapthTest {

	/**
	 * Pruebas del constructor 
	 */
	@Test
	public void testConstructor() { 
		Graph<Integer> grapth = new Graph<Integer>(5);
		assertEquals(0,grapth.numNodes);
		assertEquals(5,grapth.edges.length);
		assertEquals(5,grapth.weights.length);
		assertNull(grapth.pFloyd);
		assertNull(grapth.aFloyd);
	}
	
	/**
	 * Pruebas del método para añadir un nodo 
	 */
	@Test 
	public void testAddNode() { 
		Graph<Integer> grapth = new Graph<Integer>(5);
		
		//PRUEBAS POSITIVAS 
		assertEquals(0,grapth.addNode(0));
		assertEquals(0,grapth.addNode(1));
		assertEquals(2,grapth.numNodes);
		
		//PRUEBAS NEGATIVAS 
		
		//Caso 1: Le pasamos un nodo nulo
		assertEquals(-4,grapth.addNode(null));
		
		//Caso 2: Le pasamos un nodo que ya existe
		assertEquals(-1,grapth.addNode(0));
		
		//Caso 3: El nodo no cabe
		grapth.addNode(2);
		grapth.addNode(3);
		grapth.addNode(4);
		assertEquals(-2,grapth.addNode(5));
		
		//Caso 4: Pasamos un nodo nulo y no cabe.
		assertEquals(-6,grapth.addNode(null));
		
		//Caso 5: El nodo ya existe y no cabe 
		assertEquals(-3,grapth.addNode(0));
		
		
		//System.out.println(grapth.toString());
	}
	
	/**
	 * Pruebas del método para comprobar si existe o no un nodo 
	 */
	@Test 
	public void testExistsNode() { 
		Graph<Integer> grapth = new Graph<Integer>(5);
		
		//PRUEBAS POSITIVAS 
		
		//Caso 1: El nodo existe.
		grapth.addNode(0);
		grapth.addNode(1);
		assertTrue(grapth.existsNode(0));
		
		//Caso 2: El nodo no existe 
		assertFalse(grapth.existsNode(2));
		grapth.addNode(2);
		assertTrue(grapth.existsNode(2));
		
		//PRUEBAS NEGATIVAS 
		
		//Caso 1: El nodo es inválido
		assertFalse(grapth.existsNode(null));
	}
	
	/**
	 * Pruebas del método para eliminar un nodo.
	 */
	@Test 
	public void testRemoveNode() { 
		Graph<Integer> grapth = new Graph<Integer>(5);
		Graph<Integer> grapth2 = new Graph<Integer>(5);
		
		//PRUEBAS POSITIVAS 
		
		//Caso 1: Se borra al principio sin arsitas
		assertEquals(0,grapth.numNodes);
		grapth.addNode(0);
		assertEquals(1,grapth.numNodes);
		grapth.removeNode(0);
		assertEquals(0,grapth.numNodes);
		assertFalse(grapth.existsNode(0));
		
		//Caso 2: Se borra entre posiciones sin aristas
		grapth.addNode(0);
		grapth.addNode(1);
		grapth.addNode(2);
		assertEquals(3,grapth.numNodes);
		assertEquals(0,grapth.removeNode(1));
		assertEquals(2,grapth.numNodes);
		assertFalse(grapth.existsNode(1));
		
		//Caso 3: Lo eliminas al final sin aristas 
		grapth.removeNode(2);
		assertEquals(1,grapth.numNodes);
		assertFalse(grapth.existsNode(2));
		
		//Caso 4. Lo eliminas con aristas 
		grapth2.addNode(0);
		grapth2.addNode(1);
		grapth2.addNode(2);
		grapth2.addNode(3);
		grapth2.addNode(4);
		grapth2.addEdge(2, 3, 1);
		grapth2.addEdge(2, 0, 3);
		
		assertTrue(grapth2.existEdge(2, 3));
		assertTrue(grapth2.existEdge(2, 0));
		grapth2.removeNode(2);
		assertFalse(grapth2.existEdge(2, 3));
		assertFalse(grapth2.existEdge(2, 0));
		
		//System.out.println(grapth2.toString());
		
		//PRUEBAS NEGATIVAS 
		
		//Caso 1: Parámetro nulo y no existe
		assertEquals(-5,grapth.removeNode(null));
		
		//Caso 2: El nodo no existe
		assertEquals(-1,grapth.removeNode(6));
		 
	}
	
	/**
	 * Pruebas del método para añadir una arista.
	 */
	@Test
	public void testAddEdge() { 
		Graph<Integer> grapth = new Graph<Integer>(5);
		
		//PRUEBAS POSITIVAS 
		
		//Caso 1: Se añade la arista 
		grapth.addNode(0);
		grapth.addNode(1);
		assertEquals(0,grapth.addEdge(0, 1, 2));
		assertTrue(grapth.edges[0][1]);
		
		//PRUEBAS NEGATIVAS 
		
		//Caso 1: Parámetros nulos
		assertEquals(-1,grapth.addEdge(null, 1, 2));
		assertEquals(-2,grapth.addEdge(0, null, 2));
		assertEquals(-3,grapth.addEdge(null, null, 2));
		
		
		//Caso 2: Peso erroneo
		assertEquals(-8,grapth.addEdge(1, 0, -4));
		assertEquals(-9,grapth.addEdge(null, 1, 0));
		assertEquals(-10,grapth.addEdge(0, null, 0));
		assertEquals(-11,grapth.addEdge(null, null, 0));
		
		//Caso 3: La arista ya existe 
		assertEquals(-4,grapth.addEdge(0, 1, 3));
		assertEquals(-12,grapth.addEdge(0, 1, -2));
	}
	
	/**
	 * Pruebas del método para obtener el peso de una arista.
	 */
	@Test
	public void testGetEdge() { 
		Graph<Integer> grapth = new Graph<Integer>(5);
		
		//PRUEBAS POSITIVAS 
		
		//Caso 1: Se crea la arista 
		grapth.addNode(0);
		grapth.addNode(1);
		assertEquals(0,grapth.addEdge(0, 1, 1));
		assertTrue(grapth.edges[0][1]);
		assertTrue(1==grapth.getEdge(0, 1));
		
		//PRUEBAS NEGATIVAS 
		
		//Caso 1: Parámetros nulos
		assertTrue(-7==grapth.getEdge(null,null));
		assertTrue(-5==grapth.getEdge(null,1));
		assertTrue(-6==grapth.getEdge(0,null));
		
		//Caso 2: La arista no existe 
		grapth.addNode(2);
		assertTrue(-4==grapth.getEdge(0,2));
	}

	/**
	 * Pruebas del método para comprobar si existe o no una arista.
	 */
	@Test 
	public void testExistEdge() {
		Graph<Integer> grapth = new Graph<Integer>(5);
		
		//PRUEBAS POSITIVAS 
		
		//Caso 1: El nodo existe.
		grapth.addNode(0);
		grapth.addNode(1);
		assertEquals(0,grapth.addEdge(0, 1, 10));
		assertTrue(grapth.existEdge(0, 1));
		assertFalse(grapth.existEdge(1, 0));
		
		//PRUEBAS NEGATIVAS 
		
		//Caso 1: El nodo no existe
		assertFalse(grapth.existEdge(1, 7));
		assertFalse(grapth.existEdge(null, 1));
		assertFalse(grapth.existEdge(1, null));
		assertFalse(grapth.existEdge(null, null));
		//System.out.println(grapth.toString());
	}
	
	/**
	 * Pruebas del método para eliminar una arista 
	 */
	@Test
	public void testRemoveEdge() { 
		Graph<Integer> grapth = new Graph<Integer>(5);
		
		//PRUEBAS POSITIVAS 
		
		//Caso 1: Se elimina la arista 
		grapth.addNode(0);
		grapth.addNode(1);
		grapth.addEdge(0, 1, 1);
		assertTrue(grapth.existEdge(0, 1));
		assertEquals(0,grapth.removeEdge(0, 1));
		assertFalse(grapth.existEdge(0, 1));
		
		//PRUEBAS NEGATIVAS 
		
		//Caso 1: Parámetros nulos
		assertEquals(-7,grapth.removeEdge(null, null));
		assertEquals(-5,grapth.removeEdge(null, 1));
		assertEquals(-6,grapth.removeEdge(1, null));
		
		//Caso 2: La arista no existe
		assertEquals(-4,grapth.removeEdge(0, 1));
	}
	
	/**
	 * Pruebas del método Dijkstra.
	 */
	@Test 
	public void testDijkstra() { 
		Graph<Integer> grapth = new Graph<Integer>(5);
		Graph<Integer> grapth2 = new Graph<Integer>(8);
		double[] solucion = {0,1,5,3,6};
		
		grapth.addNode(0);
		grapth.addNode(1);
		grapth.addNode(2);
		grapth.addNode(3);
		grapth.addNode(4);
		

		grapth.addEdge(0,1,1);
		grapth.addEdge(0,3,3);
		grapth.addEdge(0,4,10);
		
		grapth.addEdge(1,2,5);
		
		grapth.addEdge(2,4,1);
		
		grapth.addEdge(3,2,2);
		grapth.addEdge(3,4,6);
		
		double[] resultado = grapth.dijkstra(0);
		assertArrayEquals(solucion, resultado, 0);;
		
		//System.out.println(grapth.toString());
		
		/////////////////////////////////////////////////////////////////////////////
		
		grapth2.addNode(0);
		grapth2.addNode(1);
		grapth2.addNode(2);
		grapth2.addNode(3);
		grapth2.addNode(4);
		grapth2.addNode(5);
		grapth2.addNode(6);
		grapth2.addNode(7);
		
		grapth2.addEdge(0, 3, 4);

		grapth2.addEdge(1, 2, 4);
		grapth2.addEdge(1, 5, 2);
		grapth2.addEdge(1, 6, 8);
		
		grapth2.addEdge(2, 0, 5);
		grapth2.addEdge(2, 6, 5);
		
		grapth2.addEdge(4, 2, 8);
		grapth2.addEdge(4, 6, 7);
		grapth2.addEdge(4, 7, 7);
		
		grapth2.addEdge(5, 1, 7);
		grapth2.addEdge(5, 3, 6);
		grapth2.addEdge(5, 6, 9);
		grapth2.addEdge(5, 7, 7);
		
		grapth2.addEdge(6, 2, 4);
		grapth2.addEdge(6, 5, 9);
		
		grapth2.addEdge(7, 5, 7);
		grapth2.addEdge(7, 6, 7);
		
		double[] solucion2 = {5,21,0,9,grapth.INFINITO,14,5,21};
		double[] resultado2 = grapth2.dijkstra(2);
		assertArrayEquals(solucion2, resultado2, 0);;
	}
	
	/**
	 * Pruebas del método Floyd y minCostPath.
	 */
	@Test
	public void testFloyd() {
		
		Graph<Integer> grapth = new Graph<Integer>(6);
		assertEquals(-1,grapth.floyd());
		double[][] a = {{0,3,4,12,7,10},{grapth.INFINITO,0,grapth.INFINITO,10,5,8},
				{grapth.INFINITO,grapth.INFINITO,0,8,3,6},{grapth.INFINITO,grapth.INFINITO,grapth.INFINITO,0,grapth.INFINITO,grapth.INFINITO},
				{grapth.INFINITO,grapth.INFINITO,grapth.INFINITO,5,0,3},{grapth.INFINITO,grapth.INFINITO,grapth.INFINITO,2,grapth.INFINITO,0}};
		int[][] p =	{{-1,-1,-1,5,2,4},{-1,-1,-1,5,-1,4},{-1,-1,-1,5,-1,4},
				{-1,-1,-1,-1,-1,-1},{-1,-1,-1,5,-1,-1},{-1,-1,-1,-1,-1,-1}};
		
		
		grapth.addNode(0);
		grapth.addNode(1);
		grapth.addNode(2);
		grapth.addNode(3);
		grapth.addNode(4);
		grapth.addNode(5);
		
		grapth.addEdge(0, 1, 3);
		grapth.addEdge(0, 2, 4);
		grapth.addEdge(0, 4, 8);
		
		grapth.addEdge(1, 4, 5);
		
		grapth.addEdge(2, 4, 3);
		
		grapth.addEdge(4, 3, 7);
		grapth.addEdge(4, 5, 3);
		
		grapth.addEdge(5, 3, 2);
		
		assertNull(grapth.getAFloyd());
		assertNull(grapth.getPFloyd());
		
		assertEquals(0,grapth.floyd());
		
		assertNotNull(grapth.getAFloyd());
		assertNotNull(grapth.getPFloyd());
		
		//System.out.println(grapth.toString());
		assertArrayEquals(grapth.getAFloyd(),a);
		assertArrayEquals(grapth.getPFloyd(),p);
		//System.out.println(grapth.toString());

		/////////////////////////////////////////////////////////////////////////////////
		
		//PRUEBAS POSITIVAS 
		
		//Caso 1: Afloyd y Pfloyd están generadas con éxito 
		assertEquals(5,grapth.minCostPath(4, 3));
		assertEquals(0,grapth.minCostPath(0, 0));
		assertEquals(12,grapth.minCostPath(0, 3));
		assertEquals(grapth.INFINITO,grapth.minCostPath(1, 0));
		
		//Caso 2: Las matrices no estás generadas 
		grapth.aFloyd = null;
		grapth.pFloyd = null;
		assertEquals(5,grapth.minCostPath(4, 3));
		assertEquals(0,grapth.minCostPath(0, 0));
		assertEquals(12,grapth.minCostPath(0, 3));
		assertEquals(grapth.INFINITO,grapth.minCostPath(1, 0));
		
		//PRUEBAS NEGATIVAS 
		
		//Caso 1: Parámetros nulos 
		assertEquals(-1,grapth.minCostPath(null, 3));
		assertEquals(-1,grapth.minCostPath(0, null));
		assertEquals(-1,grapth.minCostPath(null, null));
		
		//Caso 2: No hay grafo 
		grapth.removeNode(0);
		grapth.removeNode(1);
		grapth.removeNode(2);
		grapth.removeNode(3);
		grapth.removeNode(4);
		grapth.removeNode(5);
		assertEquals(-1,grapth.minCostPath(0, 3));
	

	}
	
	/**
	 * Pruebas del método que encuentra el recorrido profundo de un grafo.
	 */
	@Test
	public void testRecorridoProfundidad() {
		
		Graph<Integer> grapth = new Graph<Integer>(8);
		String aux ="";
		
		grapth.addNode(0);
		grapth.addNode(1);
		grapth.addNode(2);
		grapth.addNode(3);
		grapth.addNode(4);
		grapth.addNode(5);
		grapth.addNode(6);
		grapth.addNode(7);
		
		grapth.addEdge(0, 1, 1);
		grapth.addEdge(0, 2, 1);
		grapth.addEdge(0, 4, 1);
		grapth.addEdge(1, 5, 1);
		grapth.addEdge(2, 4, 1);
		grapth.addEdge(2, 5, 1);
		grapth.addEdge(5, 6, 1);
		grapth.addEdge(6, 7, 1);
		
		assertTrue(aux.equals(grapth.recorridoProfundidad(23)));
		grapth.floyd();
		System.out.println(grapth.recorridoProfundidad(0));		
	}
	
	/**
	 * Pruebas del método path.
	 */
	@Test 
	public void testPath() {
		Graph<Integer> grapth = new Graph<Integer>(6);
		
		grapth.addNode(1);
		grapth.addNode(2);
		grapth.addNode(3);
		grapth.addNode(4);
		grapth.addNode(5);
		grapth.addNode(6);
		
		grapth.addEdge(1, 2, 3);
		grapth.addEdge(1, 3, 4);
		grapth.addEdge(1, 5, 8);
		
		grapth.addEdge(2, 5, 5);
		
		grapth.addEdge(3, 5, 3);
		
		grapth.addEdge(5, 4, 7);
		grapth.addEdge(5, 6, 3);
		
		grapth.addEdge(6, 4, 2);
		
		
		assertTrue("".equals(grapth.path(null, 1)));
		assertTrue("".equals(grapth.path(1, null)));
		assertTrue("".equals(grapth.path(null, null)));
		assertTrue("1".equals(grapth.path(1, 1)));
		
		System.out.println(grapth.path(2, 1));
		System.out.println(grapth.path(1, 3));
		System.out.println(grapth.path(3, 6));
	}
	

	
}
