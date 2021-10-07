package Arboles;

import static org.junit.Assert.*;

import org.junit.Test;

/** 
* Clase de prueba de los arboles BST 
* @version 2019-20 
*  
*/ 
public class BSTreeTest {

	/**
	 * Pruebas del método addNode del BSTTree 
	 */
	@Test
	public void addNodetest() {
		
		//Se añaden nodos al arbol de Integer 
		
		BSTree<Integer> arbol = new BSTree<Integer>();
		
		assertEquals(0,arbol.addNode(6));
		assertEquals(0,arbol.addNode(2));
		assertEquals(0,arbol.addNode(1));
		assertEquals(0,arbol.addNode(4));
		assertEquals(0,arbol.addNode(3));
		assertEquals(0,arbol.addNode(5));
		assertEquals(0,arbol.addNode(8));
		assertEquals(0,arbol.addNode(7));
		assertEquals(0,arbol.addNode(9));
		assertEquals(-1,arbol.addNode(9));
		assertEquals(-2,arbol.addNode(null));
		//System.out.println(arbol.toString());
		
		//Se añaden nodos al arblo de Character
		
		BSTree<Character> arbol2 = new BSTree<Character>();
		
		assertEquals(0,arbol2.addNode('a'));
		assertEquals(0,arbol2.addNode('b'));
		assertEquals(0,arbol2.addNode('z'));
		assertEquals(0,arbol2.addNode('2'));
		assertEquals(0,arbol2.addNode('6'));
		assertEquals(0,arbol2.addNode('g'));
		assertEquals(-1,arbol2.addNode('g'));
		assertEquals(-2,arbol2.addNode(null));
		//System.out.println(arbol2.toString());
	
	}
	
	
	
	/**
	 * Pruebas del método searchNode 
	 */
	@Test 
	public void searchNodeTest() {
		
		//Se añaden nodos al arbol de Integer 
		
		BSTree<Integer> arbol = new BSTree<Integer>();
		assertNull(arbol.searchNode(3));
		arbol.addNode(5);
		assertNull(arbol.searchNode(null));
		arbol.addNode(1);
		arbol.addNode(6);
		arbol.addNode(3);
		
		assertTrue(arbol.searchNode(5).equals(5));
		assertTrue(arbol.searchNode(3).equals(3));
		assertTrue(arbol.searchNode(6).equals(6));
		assertNull(arbol.searchNode(2));
		assertNull(arbol.searchNode(26));
		assertNull(arbol.searchNode(45));
		assertNull(arbol.searchNode(7));
		
		//Se añaden nodos al arblo de Character
		
		BSTree<Character> arbol2 = new BSTree<Character>();
		assertNull(arbol2.searchNode('a'));
		assertNull(arbol2.searchNode(null));
		
		arbol2.addNode('a');
		arbol2.addNode('b');
		arbol2.addNode('c');
		arbol2.addNode('f');
		arbol2.addNode('i');
		arbol2.addNode('h');
		arbol2.addNode('j');
		arbol2.addNode('m');
		arbol2.addNode('1');
		
		assertNull(arbol2.searchNode(null));
		assertTrue(arbol2.searchNode('a').equals('a'));
		assertTrue(arbol2.searchNode('h').equals('h'));
		assertTrue(arbol2.searchNode('1').equals('1'));
		
				
	}
	
	
	/**
	 * Pruebas del método removeNode del BSTTree 
	 */
	@Test 
	public void removeNode() {
		
		BSTree<Integer> arbol = new BSTree<Integer>();
		assertEquals(-2,arbol.removeNode(null));
		assertEquals(-2,arbol.removeNode(4));
		arbol.addNode(6);
		assertEquals(-1,arbol.removeNode(7));
		arbol.addNode(2);
		arbol.addNode(1);
		arbol.addNode(4);
		arbol.addNode(3);
		arbol.addNode(5);
		arbol.addNode(8);	
		arbol.addNode(7);
		arbol.addNode(9);
		
		//Caso 1: Nodos sin hijos 
		assertEquals(0,arbol.removeNode(3));		
		assertEquals(0,arbol.removeNode(9));
		
		//Caso 2: Nodos con un solo hijo por la izq o por la derec
		assertEquals(0,arbol.removeNode(4));
		assertEquals(0,arbol.removeNode(8));
		
		//Caso 3: Nodos con hijos por la izq y por la derec
		assertEquals(0,arbol.removeNode(6));
		
		//System.out.println(arbol.toString());
		
		BSTree<Character> arbol2 = new BSTree<Character>();

		arbol2.addNode('a');
		arbol2.addNode('b');
		arbol2.addNode('c');
		arbol2.addNode('f');
		arbol2.addNode('i');
		arbol2.addNode('h');
		arbol2.addNode('j');
		arbol2.addNode('m');
		arbol2.addNode('1');
		
		//System.out.println(arbol2.toString());
		arbol2.removeNode('j');
		arbol2.removeNode('a');
		arbol2.removeNode('1');
		//System.out.println(arbol2.toString());
		
		BSTree<Integer> aux = new BSTree<Integer>();
		aux.addNode(4);
		aux.removeNode(4);
		//System.out.println(aux.toString());
	}

	
	
	/**
	 * Pruebas de los métodos preOrden,postOrden y inOrder 
	 */
	@Test 
	public void recorridosTest() {
		
		BSTree<Integer> arbol = new BSTree<Integer>();
		assertEquals("",arbol.inOrder());
		assertEquals("",arbol.preOrder());
		assertEquals("",arbol.postOrder());
		
		arbol.addNode(6);
		arbol.addNode(2);
		arbol.addNode(1);
		arbol.addNode(4);
		arbol.addNode(3);
		arbol.addNode(5);
		arbol.addNode(8);	
		arbol.addNode(7);
		arbol.addNode(9);
		
		//System.out.println(arbol.preOrder()); //Recorrido preOrden
		//System.out.println(arbol.postOrder());	// Recorrido postOrden
		//System.out.println(arbol.inOrder());	// Recorrido inOrden 
		
		
		BSTree<Character> arbol2 = new BSTree<Character>();
		assertEquals("",arbol2.inOrder());
		assertEquals("",arbol2.preOrder());
		assertEquals("",arbol2.postOrder());
		arbol2.addNode('a');
		arbol2.addNode('b');
		arbol2.addNode('c');
		arbol2.addNode('f');
		arbol2.addNode('i');
		arbol2.addNode('h');
		arbol2.addNode('j');
		arbol2.addNode('m');
		arbol2.addNode('1');
		
		
		//System.out.println(arbol2.preOrder()); //Recorrido preOrden
		//System.out.println(arbol2.postOrder());	// Recorrido postOrden
		//System.out.println(arbol2.inOrder());	// Recorrido inOrden 
	}
	
		
}
