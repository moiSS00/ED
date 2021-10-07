package Recursividad;

public class Algorithms {
	private static final long SLEEP_TIME = 250;
	

	/**
	 * Método que hace esperar x milisegundos. Siendo x >= 0. 
	 */
	public static void doNothing() {
		try {
			Thread.sleep(SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Realiza el factorial de un número de forma recursiva.
	 * Caso general: factorial(0) = 1.
	 * @param n Número del que queremos el factorial.
	 * @return Factorial de n.
	 */
	public long factorial (int n) {
		
		doNothing();
		if (n == 0) {
			return 1;
		}
	
		return n*factorial(n-1); 
	}
	
		
	/**
	 * Realiza la sucesión de fibonacci de forma recursiva.
	 * Caso general: fibonacci(0) = 0  y fibonacci(1) = 1.
	 * @param n Posición dentro de la sucesión de fibonacci que nos interesa.
	 * @return Término n de la sucesión de fibonacci.
	 */
	public int fibonacci (int n) {
		
		doNothing();
		if ( n == 0 || n== 1) {
			return n;
		}
		
		return fibonacci(n-1)+fibonacci(n-2);
	}

	
	/**
	 * Realiza la potencia de forma recursiva: a^b.
	 * Caso general: potenciaRec(a,0) = 1.
	 * @param a Es la base de la potencia 
	 * @param b Es el exponente de la potencia
	 * @return Resultado de a^b.
	 */
	public long potenciaRec (int a, int b) {
		
		if(b==0) {
			return 1;
		}
		
		return a*potenciaRec(a, b-1);
	}

	/**
	 * Obtiene el resto de dividir a entre b de forma recursiva.
	 * Caso general: Si a es menor que b, restoDivRec(a,b) = a.
	 * @param a Numerador.
	 * @param b Denominador.
	 * @return Resto.
	 */
	public int restoDivRec (int a, int b) {
		
		
		if(a<b) {
			return a;
		}
		
		return restoDivRec(a-b,b);
	}
	
	/**
	 * Invierte un número entero de forma recursiva.
	 * Caso general.Si el número es menor que 10, invertirEnteroRec(n) = n.
	 * @param n Entero a invertir.
	 * @return Entero invertido.
	 */
	public int invertirEnteroRec(int n) {
		
		doNothing();
		if (n < 10) {
			return n;
		}
		String aux = ""+n;
		
		return n%10 * (int)Math.pow(10, aux.length()-1)+invertirEnteroRec(n/10);
	}
	
	/**
	 * Invierte un String de forma recursiva.
	 * Caso general: Si l.length es 1, invertirStringRec(l) = l.
	 * @param l String que se desea invertir.
	 * @return El String invertido.
	 */
	public String invertirStringRec(String l) {

		if (l.length() == 1) {
			return l;
		}
		
		
		return l.charAt(l.length()-1)+invertirStringRec(l.substring(0,l.length()-1));
	}
	
	/**
	 * Comprueba si una matriz es simétrica o no, mediante el uso de un método privado.
	 * @param m Matriz que se desea comprobar.
	 * @return true si la matriz es simétrica, false en caso contrario.
	 */
	public boolean esSimetrica(int[][] m) {
		return comprobador(m, 0, 1);
	}
	
	/**
	 * Método privado para comprobar si una matriz es simétrica de forma recursiva.
	 * @param m Matriz a comprobar.
	 * @param i Fila deseada.
	 * @param j Columna deseada.
	 * @return true si la matriz es simétrica, false en caso contrario.
	 */
	private boolean comprobador(int[][] m, int i,int j) {
		if (i == m.length -1) {
			return true;  //Es simétrica 
		}
		
		else {
			if (m[i][j] != m[j][i]) {
				return false;  //No coincide , no simétrica
			}
			else {
				if (j == m.length -1) {
					return comprobador(m, i+1,i+2); //Incremento fila
				}
				else {
					return comprobador(m, i, j+1); //Incremento columna
				}
			}
		}
	}
	
	/**
	 * Realiza la potencia de 2 de forma iterativa.
	 * @param n Exponente de la potencia.
	 * @return Dos elevado al exponente.
	 */
	public int pow2Iter(int n) { 
		
		int aux=1;
		
    	for(int i=1;i<=n;i++) {
			doNothing();
    		aux = 2*aux;
		}
    	
    	return aux;
	}
	
	/**
	 * Realiza la potencia de 2 de forma recursiva (modelo1).
	 * Caso general: pow2Rec1(0) = 1.
	 * @param n Exponente de la potencia.
	 * @return Dos elevado al exponente.
	 */
	public long pow2Rec1 (int n) {
		
		doNothing();
		if(n==0) {
			return 1;
		}
		
		return 2*pow2Rec1(n-1);
	}

	/**
	 * Realiza la potencia de 2 de forma recursiva (modelo2).
	 * Caso general: pow2Rec2(0) = 1.
	 * @param n Exponente de la potencia.
	 * @return Dos elevado al exponente.
	 */
	public int pow2Rec2(int n) {
		
		doNothing();
		
		if (n == 0) {
			return 1;
		}
		
		return pow2Rec2(n-1)+pow2Rec2(n-1);
	}
	
	
	/**
	 * Realiza la potencia de 2 de forma recursiva (modelo3).
	 * Caso general: pow2Rec3(0) = 1.
	 * @param n Exponente de la potencia.
	 * @return Dos elevado al exponente.
	 */
	public int pow2Rec3(int n) {
		
		doNothing();
		
		if (n == 0) {
			return 1;
		}
		
		if(n%2 == 0) { // Si es par 
			return pow2Rec3(n/2) * pow2Rec3(n/2);
		}
		
		return 2 * pow2Rec3(n/2) * pow2Rec3(n/2); //Si es impar 
	}
	
	
	/**
	 * Realiza la potencia de 2 de forma recursiva (modelo4).
	 * Caso general: pow2Rec4(0) = 1.
	 * @param n Exponente de la potencia.
	 * @return Dos elevado al exponente.
	 */
	public int pow2Rec4(int n) {
		
		doNothing();
		
		if (n == 0) {
			return 1;
		}
		
		int aux = pow2Rec4(n/2);  
		
		if(n%2 == 0) {
			return aux * aux;
		}
		
		return 2 * aux * aux;
	}
	
	/**
	 * Algoritmo de complejidad lineal O(n).
	 * @param n Valor de carga.
	 */
	public void linear(int n) {

		for(int i=0;i<n;i++) {
			doNothing();

		}
	}
	
	/**
	 * Algoritmo de complejidad cuadrática O(n^2).
	 * @param n Valor de carga.
	 */
	public void quadratic(int n) {

		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				doNothing();
			}
		}

	}
	
	/**
	 * Algoritmo de complejidad cúbica O(n^3).
	 * @param n Valor de carga.
	 */
	public void cubic(int n) {
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				for(int k=0;k<n;k++) {
					doNothing();
				}
			}
		}

	}
	
	/**
	 * Algoritmo de complejidad logaritmica O(log n).
	 * @param n Valor de carga.
	 */
	public void logarithmic(int n) {
		
		int i = 1;
		while (i<n) {
			doNothing();
			i =i*2;
		}
	}
	

}
