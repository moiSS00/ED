package Arboles;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

/** 
* Clase de pruebas de árboles AVL   
* @version 2019-20 
*  
*/ 
public class AVLTreeTest {
	
	/**
	 * Pruebas del metodo add del AVSTree, comprobandolo en el caso de todas las rotaciones posibles.
	 */
	@Test
	public void addTest() 
	{
		AVLTree<Integer> avl = new AVLTree<Integer>();
		
		//RSD SOBRE 75 
		assertEquals(0,avl.addNode(50));
		assertEquals("50:FB=0\t",avl.inOrder());
		assertEquals(0,avl.addNode(25));
		assertEquals(0,avl.addNode(75));
		assertEquals(0,avl.addNode(10));
		assertEquals(0,avl.addNode(30));
		assertEquals(0,avl.addNode(90));
		assertEquals("10:FB=0\t25:FB=0\t30:FB=0\t50:FB=0\t75:FB=1\t90:FB=0\t",avl.inOrder());
		avl.addNode(95);
		assertEquals("10:FB=0\t25:FB=0\t30:FB=0\t50:FB=0\t75:FB=0\t90:FB=0\t95:FB=0\t",avl.inOrder());
		
		
		//RSI SOBRE 75
		avl = new AVLTree<Integer>();
		assertEquals(0,avl.addNode(50));
		assertEquals(0,avl.addNode(25));
		assertEquals(0,avl.addNode(75));
		assertEquals(0,avl.addNode(10));
		assertEquals("10:FB=0\t25:FB=-1\t50:FB=-1\t75:FB=0\t",avl.inOrder());
		avl.addNode(5);
		assertEquals("5:FB=0\t10:FB=0\t25:FB=0\t50:FB=-1\t75:FB=0\t",avl.inOrder());
		System.out.println(avl.toString());
		
		
		//RDD SOBRE EL 25 
		avl = new AVLTree<Integer>();
		assertEquals(0,avl.addNode(50));
		assertEquals(0,avl.addNode(25));
		assertEquals(0,avl.addNode(75));
		assertEquals(0,avl.addNode(30));
		assertEquals("25:FB=1\t30:FB=0\t50:FB=-1\t75:FB=0\t",avl.inOrder());
		avl.addNode(26);
		assertEquals("25:FB=0\t26:FB=0\t30:FB=0\t50:FB=-1\t75:FB=0\t",avl.inOrder());
		
		
		//RDI SOBRE EL 75
		avl = new AVLTree<Integer>();
		assertEquals(0,avl.addNode(50));
		assertEquals(0,avl.addNode(25));
		assertEquals(0,avl.addNode(75));
		assertEquals(0,avl.addNode(60));
		assertEquals("25:FB=0\t50:FB=1\t60:FB=0\t75:FB=-1\t",avl.inOrder());
		avl.addNode(65);
		assertEquals("25:FB=0\t50:FB=1\t60:FB=0\t65:FB=0\t75:FB=0\t",avl.inOrder());
		
		
	}
	
	/**
	 * Pruebas del método remove del AVLTree 
	 */
	@Test
	public void removeTest()
	{
		AVLTree<Integer> tree = new AVLTree<>();
		
		tree.addNode(6);
		tree.addNode(3);
		tree.addNode(9);
		tree.addNode(1);
		tree.addNode(5);
		tree.addNode(8);
		tree.addNode(11);
		tree.addNode(2);
		tree.addNode(4);
		tree.addNode(7);
		tree.addNode(10);
		tree.addNode(13);
		tree.addNode(12);
		
		assertEquals("1:FB=1\t2:FB=0\t3:FB=0\t4:FB=0\t5:FB=-1\t6:FB=1\t7:FB=0\t8:FB=-1\t9:FB=1\t10:FB=0\t11:FB=1\t12:FB=0\t13:FB=-1\t", tree.inOrder());

		//Prueba 1: Borrar un nodo sin hijos
		assertEquals(0,tree.removeNode(10));
		assertEquals("1:FB=1\t2:FB=0\t3:FB=0\t4:FB=0\t5:FB=-1\t6:FB=0\t7:FB=0\t8:FB=-1\t9:FB=0\t11:FB=0\t12:FB=0\t13:FB=0\t", tree.inOrder());
		
		//Prueba 2: Borrar un nodo con un hijo
		assertEquals(0,tree.removeNode(8));
		assertEquals("1:FB=1\t2:FB=0\t3:FB=0\t4:FB=0\t5:FB=-1\t6:FB=0\t7:FB=0\t9:FB=1\t11:FB=0\t12:FB=0\t13:FB=0\t", tree.inOrder());
		
		//Prueba 3: Borrar un nodo con dos hijos
		assertEquals(0,tree.removeNode(6));
		assertEquals("1:FB=1\t2:FB=0\t3:FB=-1\t4:FB=0\t5:FB=0\t7:FB=0\t9:FB=1\t11:FB=0\t12:FB=0\t13:FB=0\t", tree.inOrder());
		
		//Pruebas Negativas
		assertEquals(-2,tree.removeNode(null));
		assertEquals(-1,tree.removeNode(-5));
		
		//PRUEBAS INTENSIVAS 
			
		tree = new AVLTree<Integer>();
		assertEquals(0,tree.addNode(10));
		assertEquals(0,tree.addNode(100));
		assertEquals(0,tree.addNode(60));
		assertEquals(0,tree.addNode(30));
		assertEquals(0,tree.addNode(2));
		assertEquals(0,tree.addNode(1));
		assertEquals(0,tree.addNode(70));
		assertEquals(0,tree.addNode(90));
		assertEquals(0,tree.addNode(23));
		assertEquals(0,tree.addNode(43));
		assertEquals(0,tree.addNode(65));
		assertEquals(0,tree.addNode(13));
		assertEquals(0,tree.addNode(230));
		assertEquals(0,tree.addNode(110));
		assertEquals(0,tree.addNode(49));
		assertEquals(0,tree.addNode(7));
		assertEquals(0,tree.addNode(40));
		assertEquals(0,tree.addNode(50));
		assertEquals(0,tree.addNode(20));
		assertEquals(0,tree.addNode(15));
		assertEquals(0,tree.addNode(3));
			
		//Borra un elemento que no existe
		assertEquals(-1,tree.removeNode(500));
			
		//Borro una hoja
		assertEquals(0,tree.removeNode(3));
		assertEquals("1:FB=0\t2:FB=0\t7:FB=0\t10:FB=1\t13:FB=1\t15:FB=0\t20:FB=-1\t23:FB=0\t30:FB=0\t40:FB=0\t43:FB=1\t49:FB=1\t50:FB=0\t60:FB=0\t65:FB=0\t70:FB=-1\t90:FB=0\t100:FB=0\t110:FB=0\t230:FB=0\t",tree.inOrder());
			
		//Borra un elemento con un hijo
		assertEquals(0,tree.removeNode(110));
		assertEquals("1:FB=0\t2:FB=0\t7:FB=0\t10:FB=1\t13:FB=1\t15:FB=0\t20:FB=-1\t23:FB=0\t30:FB=0\t40:FB=0\t43:FB=1\t49:FB=1\t50:FB=0\t60:FB=0\t65:FB=0\t70:FB=-1\t90:FB=0\t100:FB=1\t230:FB=0\t",tree.inOrder());
			
		//Borra un elemento con dos hijos
		assertEquals(0,tree.removeNode(90));
		assertEquals("1:FB=0\t2:FB=0\t7:FB=0\t10:FB=1\t13:FB=1\t15:FB=0\t20:FB=-1\t23:FB=0\t30:FB=0\t40:FB=0\t43:FB=1\t49:FB=1\t50:FB=0\t60:FB=0\t65:FB=0\t70:FB=1\t100:FB=1\t230:FB=0\t",tree.inOrder());
			
			
		//Borra la raiz (30)
		assertEquals(0,tree.removeNode(30));
		assertEquals("1:FB=0\t2:FB=0\t7:FB=0\t10:FB=0\t13:FB=0\t15:FB=0\t20:FB=0\t23:FB=1\t40:FB=0\t43:FB=1\t49:FB=1\t50:FB=0\t60:FB=0\t65:FB=0\t70:FB=1\t100:FB=1\t230:FB=0\t",tree.inOrder());
			
		//Borro 1\t7\t13
		assertEquals(0,tree.removeNode(1));
		assertEquals(0,tree.removeNode(7));
		assertEquals(0,tree.removeNode(13));
		assertEquals("2:FB=0\t10:FB=1\t15:FB=1\t20:FB=0\t23:FB=1\t40:FB=0\t43:FB=1\t49:FB=1\t50:FB=0\t60:FB=0\t65:FB=0\t70:FB=1\t100:FB=1\t230:FB=0\t",tree.inOrder());
			
		//Borro 20. RSD 2,0
		assertEquals(0,tree.removeNode(20));
		assertEquals("2:FB=0\t10:FB=0\t15:FB=0\t23:FB=1\t40:FB=0\t43:FB=1\t49:FB=1\t50:FB=0\t60:FB=-1\t65:FB=0\t70:FB=1\t100:FB=1\t230:FB=0\t",tree.inOrder());
			
		//Borro el 230. RDI  -2, 1
		assertEquals(0,tree.removeNode(230));
		assertEquals("2:FB=0\t10:FB=0\t15:FB=0\t23:FB=-1\t40:FB=0\t43:FB=0\t49:FB=1\t50:FB=0\t60:FB=0\t65:FB=0\t70:FB=0\t100:FB=0\t",tree.inOrder());
			
		//Borro 23
		assertEquals(0,tree.removeNode(23));
		assertEquals("2:FB=0\t10:FB=-1\t15:FB=-1\t40:FB=0\t43:FB=0\t49:FB=1\t50:FB=0\t60:FB=0\t65:FB=0\t70:FB=0\t100:FB=0\t",tree.inOrder());
			
		//Borro 43 (raiz) y RSI sobre el nodo 15
		assertEquals(0,tree.removeNode(43));
		assertEquals("2:FB=0\t10:FB=0\t15:FB=0\t40:FB=1\t49:FB=1\t50:FB=0\t60:FB=0\t65:FB=0\t70:FB=0\t100:FB=0\t",tree.inOrder());
		
		//Se necesitan dos rotaciones para equilibrar el arbol 
		AVLTree<Integer> aux = new AVLTree<Integer>();
		
		aux.addNode(5);
		aux.addNode(2);
		aux.addNode(8);
		aux.addNode(1);
		aux.addNode(3);
		aux.addNode(7);
		aux.addNode(10);
		aux.addNode(4);
		aux.addNode(6);
		aux.addNode(9);
		aux.addNode(12);
		aux.addNode(13);
		
		aux.removeNode(1);
		//System.out.printLn(aux.toString());
		}
	}
	
	