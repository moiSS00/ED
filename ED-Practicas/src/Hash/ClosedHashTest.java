package Hash;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClosedHashTest {
	
	
	/**
	 * Pruebas de tablas hash cerradas sin funciones de dispersión con Integer
	 */
	@Test
	public void test1() {

		// PRUEBAS FUNCIÓN DE EXPLORACIÓN LINEAL
		ClosedHashTable<Integer> hashI = new ClosedHashTable(5, 0);
		assertEquals(0, hashI.add(7));
		assertEquals(0, hashI.add(22));
		assertEquals(0, hashI.add(1));
		assertEquals(0, hashI.add(31));
		assertEquals(0, hashI.add(31));
		assertEquals(-2, hashI.add(null));
		assertEquals(-1, hashI.add(7));
		assertEquals(0, hashI.remove(31));
		assertEquals(0, hashI.remove(22));
		assertNotNull(hashI.find(7));
		assertNull(hashI.find(null));
		assertNull(hashI.find(22));
		// assertNotNull(hashI.find(31));
		System.out.println(hashI.toString());

		// PRUEBAS FUNCIÓN DE EXPLORACIÓN CUADRÁTICA
		hashI = new ClosedHashTable<Integer>(10, 1);
		assertEquals(0, hashI.add(15));
		assertEquals(0, hashI.add(19));
		assertEquals(0, hashI.add(38));
		assertEquals(0, hashI.add(59));
		assertEquals(0, hashI.add(4));
		assertEquals(0, hashI.add(18));
		assertEquals(0, hashI.remove(15));
		assertEquals(0, hashI.remove(4));
		assertEquals(0, hashI.add(11));
		assertEquals(0, hashI.add(29));
		assertEquals(0, hashI.remove(59));
		assertEquals(0, hashI.remove(19));
		assertEquals(0, hashI.add(17));
		assertEquals(0, hashI.add(39));
		assertEquals(0, hashI.remove(38));
		assertEquals(-1, hashI.remove(7));
		assertEquals(-2, hashI.remove(null));
		assertNotNull(hashI.find(39));
		assertNull(hashI.find(null));
		assertNull(hashI.find(59));
		assertNull(hashI.find(38));
		assertEquals(0, hashI.add(17));
		assertEquals(-1, hashI.add(17));

		System.out.println(hashI.toString());

		// PRUEBAS FUNCIÓN DE EXPLORACIÓN DE DOBLE DISPERSIÇON
		hashI = new ClosedHashTable<Integer>(5, 2);
		assertEquals(0, hashI.add(7));
		assertEquals(0, hashI.add(14));
		assertEquals(0, hashI.add(22));
		assertEquals(0, hashI.add(34));
		assertEquals(0, hashI.add(36));
		assertEquals(-1, hashI.add(2));
		assertEquals(0, hashI.remove(7));
		assertEquals(0, hashI.remove(36));
		assertEquals(-1, hashI.remove(150));
		assertEquals(-2, hashI.remove(null));
		assertNull(hashI.find(null));
		assertNull(hashI.find(150));
		assertNull(hashI.find(36));
		assertNull(hashI.find(7));
		assertNotNull(hashI.find(14));
		
		hashI = new ClosedHashTable<Integer>(5, 2);
		assertEquals(0, hashI.add(7));
		assertEquals(0, hashI.add(14));
		assertEquals(0, hashI.add(22));
		assertEquals(0, hashI.add(34));
		assertEquals(0, hashI.add(36));

		
		System.out.println(hashI.toString());
	}
	
	/**
	 * Pruebas de las tablas hash cerradas con funciones de dispersion y con Integer 
	 */
	@Test
	public void test2() {
		
		// PRUEBAS FUNCIÓN DE EXPLORACIÓN LINEAL
		ClosedHashTable<Integer> hashI = new ClosedHashTable(5,0.5,0.16,0);
		assertEquals(0, hashI.add(7));
		assertEquals(0, hashI.add(22));
		assertEquals(0, hashI.add(1));
		assertEquals(0, hashI.add(31));
		assertEquals(0, hashI.add(31));
		assertEquals(-2, hashI.add(null));
		assertEquals(0, hashI.add(7));
		assertEquals(0, hashI.remove(31));
		assertEquals(0, hashI.remove(22));
		assertNotNull(hashI.find(7));
		assertNull(hashI.find(null));
		assertNull(hashI.find(22));
		assertNotNull(hashI.find(31));
		assertEquals(0, hashI.remove(7));
		System.out.println(hashI.toString());
		
		
		// PRUEBAS FUNCIÓN DE EXPLORACIÓN CUADRÁTICA
		hashI = new ClosedHashTable<Integer>(4,0.5,0.16, 1);
		System.out.println(hashI.toString());
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 5 Num.Elems.: 0]",hashI.toString());
		
		// Inserta un null
		assertEquals(-2,hashI.add(null));

		//Inserta elementos
		assertEquals(0,hashI.add(8));
		assertEquals(0,hashI.add(10));
		System.out.println(hashI.toString());
		assertEquals("{10};{_E_};{_E_};{8};{_E_};[Size: 5 Num.Elems.: 2]",hashI.toString());

		
		//Inserta y redispersión
		assertEquals(0,hashI.add(66));
		System.out.println(hashI.toString());		
		assertEquals("{66};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{8};{_E_};{10};[Size: 11 Num.Elems.: 3]",hashI.toString());
		
		//Sigue insertando elementos
		assertEquals(0,hashI.add(77));
		assertEquals(0,hashI.add(7));
		
		//Inserta y redispersión
		assertEquals(0,hashI.add(9));
		System.out.println(hashI.toString());
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{7};{77};{8};{9};{10};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{66};{_E_};{_E_};[Size: 23 Num.Elems.: 6]",hashI.toString());
		
		//Sigue insertando elementos
		assertEquals(0,hashI.add(88));
				
		//Borra un elemento que existe
		assertEquals(0,hashI.remove(8));
		System.out.println(hashI.toString());
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{7};{77};{_D_};{9};{10};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{88};{66};{_E_};{_E_};[Size: 23 Num.Elems.: 6]",hashI.toString());
		
		
		//Sigue insertando elementos
		assertEquals(0,hashI.add(13));
		assertEquals(0,hashI.add(19));
		
		//Inserta elemetos que ya existen
		assertEquals(0,hashI.add(66));
		assertEquals(0,hashI.add(88));
		
		//Borra un elemento que no existe
		assertEquals(-1,hashI.remove(2));
		
		// Borra un null
		assertEquals(-2,hashI.remove(null));

		//Borrar elementos
		assertEquals(0,hashI.remove(19));
		assertEquals(0,hashI.remove(7));
		assertEquals(0,hashI.remove(77));
		assertEquals(0,hashI.remove(9));
		System.out.println(hashI.toString());	
	
		
		//Borra 
		assertEquals(0,hashI.remove(10));
		assertEquals(0,hashI.remove(13));
		assertEquals(0,hashI.remove(88));
		System.out.println(hashI.toString());	
		assertEquals("{88};{66};{_E_};{_E_};{66};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 11 Num.Elems.: 3]",hashI.toString());
		
		assertEquals(0,hashI.add(-3));
		System.out.println(hashI.toString());	
		assertEquals("{88};{66};{_E_};{_E_};{66};{_E_};{_E_};{_E_};{-3};{_E_};{_E_};[Size: 11 Num.Elems.: 4]",hashI.toString());
		
		// PRUEBAS FUNCIÓN DE EXPLORACIÓN DE DOBLE DISPERSIÇON
		hashI = new ClosedHashTable<Integer>(5,0.5,0.16, 2);
		
	}
	
	/**
	 * Pruebas con objetos String y con dispersiones.
	 */
	@Test 
	public void test3() {
		
		//Crea una tabla del tamaño 10 (numero no primo)
		ClosedHashTable<String> hashTable = new ClosedHashTable<String>(4,0.5,0.16,1);
		//Muestra la tabla. Debera estar vacia y ser de tamaño 11
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 5 Num.Elems.: 0]",hashTable.toString());
		System.out.println(hashTable.toString());
		//Inserta elementos
		assertEquals(0,hashTable.add("Pedro")); 
		assertEquals(0,hashTable.add("Marta")); 
		System.out.println(hashTable.toString());
		assertEquals("{_E_};{Pedro};{Marta};{_E_};{_E_};[Size: 5 Num.Elems.: 2]",hashTable.toString());
		
		
		//Inserta y redispersión
		assertEquals(0,hashTable.add("Leo")); 
		System.out.println(hashTable.toString());
		assertEquals("{_E_};{_E_};{_E_};{_E_};{Leo};{_E_};{_E_};{Pedro};{Marta};{_E_};{_E_};[Size: 11 Num.Elems.: 3]",hashTable.toString());
				
		
		//Sigue insertando elementos
		assertEquals(0,hashTable.add("Lucia")); 
		assertEquals(0,hashTable.add("Eva")); 
		
		//Inserta y redispersión
		assertEquals(0,hashTable.add("Luis")); 
		System.out.println(hashTable.toString());
		assertEquals("{_E_};{Pedro};{_E_};{_E_};{_E_};{_E_};{Eva};{_E_};{Lucia};{_E_};{Leo};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{Marta};{_E_};{_E_};{Luis};{_E_};[Size: 23 Num.Elems.: 6]",hashTable.toString());
		
		
		//Sigue insertando elementos
		assertEquals(0,hashTable.add("Jose")); 	
		System.out.println(hashTable.toString());
		
		//Borra un elemento que existe
		assertEquals(0,hashTable.remove("Jose")); 
		System.out.println(hashTable.toString());
		
		//Sigue insertando elementos
		assertEquals(0,hashTable.add("Lia")); 
		assertEquals(0,hashTable.add("Eli")); 
		assertEquals(0,hashTable.add("aLi")); 
		System.out.println(hashTable.toString());
		
	
		//Borra un elemento que no existe
		assertEquals(-1,hashTable.remove("Alejandro"));
		
		//Borrar elementos
		assertEquals(0,hashTable.remove("Eli"));
		assertEquals(0,hashTable.remove("Lia"));
		assertEquals(0,hashTable.remove("Marta"));
		assertEquals(0,hashTable.remove("Luis"));
		
		//Borra y redispersión inversa
		assertEquals(0,hashTable.remove("Eva"));
		System.out.println(hashTable.toString());
		assertEquals("{_E_};{Pedro};{_E_};{_D_};{_E_};{_D_};{_D_};{_E_};{Lucia};{_E_};{Leo};{_E_};{_E_};{_D_};{_E_};{_E_};{_E_};{_E_};{_D_};{_E_};{_E_};{_D_};{aLi};[Size: 23 Num.Elems.: 4]",hashTable.toString());
		
		
		//Inserta un elemento que ya está
		assertEquals(0,hashTable.add("Lucia"));
		System.out.println(hashTable.toString());
		assertEquals("{_E_};{Pedro};{_E_};{_D_};{_E_};{_D_};{_D_};{_E_};{Lucia};{Lucia};{Leo};{_E_};{_E_};{_D_};{_E_};{_E_};{_E_};{_E_};{_D_};{_E_};{_E_};{_D_};{aLi};[Size: 23 Num.Elems.: 5]",hashTable.toString());

		//Borra Pedro y Lucia y redispensión inversa
		assertEquals(0,hashTable.remove("Pedro"));
		assertEquals(0,hashTable.remove("Lucia"));
		System.out.println(hashTable.toString());
		assertEquals("{aLi};{_E_};{Lucia};{_E_};{Leo};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 11 Num.Elems.: 3]",hashTable.toString());
		
		//Borra Leo y aLi y redispensión inversa
		assertEquals(0,hashTable.remove("Leo"));
		assertEquals(0,hashTable.remove("aLi"));
		System.out.println(hashTable.toString());
		assertEquals("{_E_};{_E_};{_E_};{_E_};{Lucia};[Size: 5 Num.Elems.: 1]",hashTable.toString());
	}
	
	
	

}
