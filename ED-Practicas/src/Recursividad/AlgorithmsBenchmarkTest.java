package Recursividad;

import org.junit.Test;

public class AlgorithmsBenchmarkTest {
	
	/**
	 * Método que mide los tiempos de ejecución en milisegundos de diversos algoritmos y los guarda en 
	 * un fichero de texto.
	 */
	@Test
	public void times() { 
		
		AlgorithmsBenchmark bench = new AlgorithmsBenchmark();
		
		
		bench.testFinal("Invertir.txt", 0, 10, 5, "p1Algoritmia.Algorithms", "invertirEnteroRec");
	
		bench.testFinal("Pow2Iter.txt", 0, 9, 5, "p1Algoritmia.Algorithms", "pow2Iter");
		bench.testFinal("Pow2Rec1.txt", 0, 9, 5, "p1Algoritmia.Algorithms", "pow2Rec1");
		bench.testFinal("Pow2Rec2.txt", 0, 5, 5, "p1Algoritmia.Algorithms", "pow2Rec2");
	    bench.testFinal("Pow2Rec3.txt", 0, 9, 5, "p1Algoritmia.Algorithms", "pow2Rec3");
		bench.testFinal("Pow2Rec4.txt", 0, 12, 5, "p1Algoritmia.Algorithms", "pow2Rec4");
		
		bench.testFinal("Linear.txt", 0, 12, 5, "p1Algoritmia.Algorithms", "linear");
		bench.testFinal("Quadratic.txt", 0, 10, 5, "p1Algoritmia.Algorithms", "quadratic");
		bench.testFinal("Cubic.txt", 0, 5, 5, "p1Algoritmia.Algorithms", "cubic");
		bench.testFinal("Logarithmic.txt", 0, 20, 5, "p1Algoritmia.Algorithms", "logarithmic");
		bench.testFinal("Factorial.txt", 0, 12, 5, "p1Algoritmia.Algorithms", "factorial");
	    bench.testFinal("Fibonacci.txt", 0, 9, 5, "p1Algoritmia.Algorithms", "fibonacci");
	}
}
