package Arboles;

/**
 * Clase que representa un monticulo de MINIMOS.
 * (Se admiten repetidos, por lo que en el filtrado ascendente/descendete no se intercambian) 
 *
 * @param <T>
 */
public class EDBinaryHeap<T extends Comparable<T>> implements EDPriorityQueue<T>{
	
	/**
	 * Vector de elementos que representa al montículo de MÍNIMOS 
	 */
	protected T [] elementos;
	
	/**
	 * Representa el número de elementos actual.
	 */
	protected int numElementos;

	
	/**
	 * Constructor. 
	 * @param numMaxElementos La capacidad máxima deseada para el montículo.
	 */
	@SuppressWarnings("unchecked")
	public EDBinaryHeap(int numMaxElementos) {
		elementos = (T[]) new Comparable[numMaxElementos];
	}

	/**
	 * Añade un elemento al montículo. Para ello lo inserta y realiza un filtrado ascendente 
	 * si es necesario.
	 * 
	 * @param info Elemento a añadir.
	 * @return Devuelve -2 en caso de que el parámetro sea nulo, -1 si el elemento no cabe o 0 si se 
	 * inserta con éxito.
	 */
	@Override
	public int add(T info) {
		
		if(info == null) {
			return -2; 
		}
		
		if(numElementos >= elementos.length) {
			return -1; 
		}

		elementos[numElementos] = info;
		filtradoAscendente(numElementos);
		numElementos++;
		return 0;
	}

	/**
	 * Devuelve la raíz y la elimina en el proceso. 
	 * @return Devuelve null si no existe el monticulo o esta vacio, o la raiz si la 
	 * operacion se realiza con exito.
	 */
	@Override
	public T getTop() {
		if(numElementos <= 0) {
			return null;
		}
		
		T aux = elementos[0];
		remove(aux);
		filtradoDescendente(0);
		return aux; 
	}

	/**
	 * Elimina un elemento del montículo. Posteriormente hace un filtrado descendente/ascendente si 
	 * es necesario.
	 * 
	 * @param info Elemento del monticulo que se quiere eliminar.
	 * @return Devuelve -2 si el parámetro es nulo,-1 si el elemento que se quiere eliminar 
	 * no esta en el monticulo , 0 si la operacion se ha realizado con exito.
	 */
	@Override
	public int remove(T info) {
		int index = search(info);
		if(index == -1) {
			return -1;
		}
		if(index == -2) {
			return -2;
		}
		
		//System.out.println(toStringAux());
		elementos[index] = elementos[numElementos -1];
		numElementos--;
		//System.out.println(toStringAux());
		filtradoAscendente(index);
		filtradoDescendente(index);
		return 0;
	}
	
	/**
	 * Busca y devuelve la posicion de un nodo especifico perteneciente al monticulo.
	 * 
	 * @param info Nodo que se quiere buscar 
	 * @return Devuelve -2 si el parametro es nulo,-1 si no lo encuentra o la posicion del nodo 
	 * si lo encuentra.
	 */
	private int search(T info) {
		if(info == null) {
			return -2;
		}
		for(int i=0;i<numElementos;i++) {
			if(elementos[i].equals(info)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Devuelve un valor booleano , true si el montículo está vacío o false en caso contrario.
	 */
	@Override
	public boolean isEmpty() {
		return numElementos == 0;
	}

	/**
	 * Vacia el monticulo.
	 */
	@Override
	public void clear() {
		numElementos = 0;
	}

	/**  
	 * Devuelve una cadena con la informacion de los elementos que contiene el  
	 * monticulo en forma visible (recomendado inorden-derecha-izquierda tabulado)  
	*/
	public String toString() {
	// Por ejemplo el arbol tumbado...  
		StringBuilder cadena = new StringBuilder();  
		cadena.append(inOrdenDerechaTabulado(0,0));  
		return cadena.toString();   
	}


    // el arbol que empieza en indice p tumbado con esp tabulaciones...
    private String inOrdenDerechaTabulado(int p,int esp) {
        String cadena="";
        if (p<numElementos) {
            int izq = Math.abs(2*p+1);
            int der = Math.abs(2*p+2);
            cadena+=inOrdenDerechaTabulado(der,esp+1);
            for(int i = 0;i<esp;i++)
                cadena+="\t";
            cadena += elementos[p]+"\n";
            cadena+=inOrdenDerechaTabulado(izq,esp+1);
        }
        return cadena;
    }

    /**
     * Realiza una filtrado ascendente de minimos en el monticulo
     * 
     * Se le pasa el indice del elemento a filtrar
     * @param index Indice del elemento desde el que se quiere hacer un filtrado ascendente.
     */
    protected void filtradoAscendente(int index) {
    	int padreIndex = (index-1)/2;
    	if(padreIndex >= 0) { //Comprobacion indice del padre 
    		if(elementos[padreIndex].compareTo(elementos[index]) > 0) { //Comprueba si el padre es mayor que el hijo 
    			T aux = elementos[index];
    			elementos[index] = elementos[padreIndex];
    			elementos[padreIndex] = aux; 
    			filtradoAscendente(padreIndex);
    		}
    	}
	}

    /**
     * Realiza una filtrado descendente de minimos en el monticulo
     * 
     * Se le pasa el indice del elemento a filtrar
     * @param index Indice del elemento desde el que se quiere hacer un filtrado descendente.
     */
    protected void filtradoDescendente(int index) { //Revisar mínimos 
    	
    	
    	int indexHijoL = index*2+1;  //Posición de los hijos izq y dere
    	int indexHijoR = index*2+2; 
    	
    	if(indexHijoR < numElementos && indexHijoL < numElementos) { //Ambos hijos existen 
    		if(elementos[index].compareTo(elementos[indexHijoL]) > 0 && elementos[index].compareTo(elementos[indexHijoR]) > 0) { //Los 2 hijos son mayores que el padre 
    			if(elementos[indexHijoL].compareTo(elementos[indexHijoR])<0) { //El hijo izquierdo es mayor que el derecho  
        			T aux = elementos[index];
        			elementos[index] = elementos[indexHijoL];
        			elementos[indexHijoL] = aux; 
        			filtradoDescendente(indexHijoL);
    			}
    			else if (elementos[indexHijoL].compareTo(elementos[indexHijoR])>0){ //El hijo derecho es mayor que el menor 
        			T aux = elementos[index];
        			elementos[index] = elementos[indexHijoR];
        			elementos[indexHijoR] = aux; 
        			filtradoDescendente(indexHijoR);
    			}
    		}
    		else if(elementos[index].compareTo(elementos[indexHijoL]) > 0) { //Si solo el hijo izquierdo es menor que el padre 
    			T aux = elementos[index];
    			elementos[index] = elementos[indexHijoL];
    			elementos[indexHijoL] = aux; 
    			filtradoDescendente(indexHijoL);
    		}
    		else if(elementos[index].compareTo(elementos[indexHijoR]) > 0) { //Si solo el hijo derecho es menor que el padre 
    			T aux = elementos[index];
    			elementos[index] = elementos[indexHijoR];
    			elementos[indexHijoR] = aux; 
    			filtradoDescendente(indexHijoR);
    		}
    	}
    	else if (indexHijoL < numElementos) { //Solo tiene hijo izquierdo
    		if(elementos[index].compareTo(elementos[indexHijoL]) > 0) { //El hijo es menor que el padre 
    			T aux = elementos[index];
    			elementos[index] = elementos[indexHijoL];
    			elementos[indexHijoL] = aux; 
    			filtradoDescendente(indexHijoL);
    		}
    	}
    	else if (indexHijoR < numElementos) { //Solo tiene hijo derecho 
    		if(elementos[index].compareTo(elementos[indexHijoR]) > 0) { //El hijo es menor que el padre 
    			T aux = elementos[index];
    			elementos[index] = elementos[indexHijoR];
    			elementos[indexHijoR] = aux; 
    			filtradoDescendente(indexHijoR);
    		}
    	}
    	
    	//System.out.println(toStringAux());
	}
    
    /**
     * Devuelve un String que recorre de forma lineal el vector de elementos y los separa mediante tabuladores.
     */
    protected String toStringAux() {
    	String aux = "";
    	for(int i=0;i<numElementos;i++) {
    		aux = aux + elementos[i].toString() + "\t";
    	}
    	return aux;
    }

}
