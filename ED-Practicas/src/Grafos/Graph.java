package Grafos;

import java.text.DecimalFormat;

public class Graph <T>{   

    /**
     * Constante que representa el infinito positivo
     */
	public static final double INFINITO = Double.POSITIVE_INFINITY;
    
    /**
     * Constante en caso de no conexión
     */
	public static final int NOCONEXION = -1;
	
	protected T[] nodes; // Vector de nodos   
    protected boolean[][] edges; // Matriz de aristas   
    protected double[][] weights; // Matriz de pesos   
    protected int numNodes; // Número de nodos en un momento dado   
    protected double[][] aFloyd; //Matriz A de Floyd (pesos)
    protected int[][] pFloyd; //Matriz P de floy (caminos)
    protected String recorrido; //Recorrido en profundidad 
   

    /**   
    * Se le pasa el numero maximo de nodos del grafo   
    */   
    @SuppressWarnings("unchecked")   
    public Graph(int tam) {   
    	edges = new boolean[tam][tam]; //Crea la matriz de aristas.
    	weights = new double[tam][tam]; //Crea la matriz de pesos.
    	nodes = (T[])new Object[tam]; //Crea un array con los nodos del grafo
    	fillEdgeMatrix(edges); //Inicializa todos los valores de la matriz de aristas a false.
    	fillWeightMatrix(weights); //Inicializa la matriz de pesos 
    	numNodes = 0; //Indica el número de nodos (inicialmente 0).
    	aFloyd = null;
    	pFloyd = null;
    }
    
    public Graph (int tam, T initialNodes[], boolean[][] initialEdges, double [][] initialWeights, double [][] initialAfloyd, int [][] initialPfloyd) { 
        // Llama al constructor anterior de inicialización 
        this(tam, initialNodes,initialEdges,initialWeights);  
         
        // Pero modifica los atributos que almacenan las matrices de Floyd con los parámetros para hacer pruebas... 
         
        if (initialAfloyd!=null && initialPfloyd!=null){ 
            // Si la matriz A de floyd se llama de otra forma (distinto de "aFloyd"), cambiad el nombre en la linea de abajo 
            aFloyd=initialAfloyd; 
            // Si la matriz P de floyd se llama de otra forma (distinto de "pFloyd"), cambiad el nombre en la linea de abajo 
            pFloyd=initialPfloyd; 
        } 
    }
  
    
   
	public Graph (int tam, T initialNodes[], boolean[][] initialEdges, double [][] initialWeights) {
		// Llama al constructor original
		this(tam); 
		
		// Pero modifica los atributos con los parametros para hacer pruebas...
		numNodes = initialNodes.length;
		
		for (int i=0;i<numNodes;i++) {
			// Si el vector de nodos se llama de otra forma (distinto de "nodes"), cambiad el nombre en la linea de abajo
			nodes[i]=initialNodes[i];
			for (int j=0;j<numNodes;j++){
				// Si la matriz de aristas se llama de otra forma (distinto de "edges"), cambiad el nombre en la linea de abajo
				edges[i][j]=initialEdges[i][j];
				// Si la matriz de pesos se llama de otra forma (distinto de "weights"), cambiad el nombre en la linea de abajo
				weights[i][j]=initialWeights[i][j];
			}
		}

	} 
 
    
    /**
     * Rellena la matriz edges con false.
     * @param edges
     */
    private void fillEdgeMatrix(boolean[][] edges) {
    	for(int i=0;i<edges.length;i++) {
    		for(int j=0;j<edges.length;j++) {
    			edges[i][j] = false; 
    		}
    	}
    }
    
    /**
     * Rellena la matriz weights con -1.
     * @param edges
     */
    private void fillWeightMatrix(double[][] edges) {
    	for(int i=0;i<weights.length;i++) {
    		for(int j=0;j<weights.length;j++) {
    			weights[i][j] = NOCONEXION; 
    		}
    	}
    }
    
    /**
     * Inicializa la matriz aFloyd.
     */
    private void incialiceA() {
    	this.aFloyd = new double[numNodes][numNodes];
    	for(int i=0;i<aFloyd.length;i++) {
    		for(int j=0;j<aFloyd.length;j++) {
    			if(edges[i][j]) {
    				aFloyd[i][j] = weights[i][j];
    			}
    			else {
    				aFloyd[i][j] = INFINITO;
    			}
    		}
    	}
    	for(int i=0;i<numNodes;i++) {
    		aFloyd[i][i] = 0;
    	}
    	

    }
    
    /**
     * Inicializa la matriz pFloyd
     */
    private void incialiceP(){
    	this.pFloyd = new int[numNodes][numNodes];
    	for(int i=0;i<pFloyd.length;i++) {
    		for(int j=0;j<pFloyd.length;j++) {
    			pFloyd[i][j] = NOCONEXION; 
    		}
    	}
    }
    
    
    /**  
    * Inserta un nuevo nodo que se le pasa como parametro  
    * si ya existe, no lo inserta y devuelve -1, (implementado mas tarde)  
    * si no cabe, no lo inserta y devuelve -2,  
    * Si se le pasa un nodo inválido -4 
    * si lo inserta devuelve 0.  
    *   
    */   
	public int addNode(T node) {
		
		int error = 0;
		
		if(!existNode(node) && numNodes<nodes.length && node != null) {
			nodes[numNodes] = node;
			for(int i=0;i<=numNodes;i++) {
				edges[numNodes][i] = false;
				edges[i][numNodes] = false;
				weights[numNodes][i] = 0;
				weights[i][numNodes] = 0;
			}
			++numNodes;
			return error;
		}
		
		if(existsNode(node)) {
			error = error -1;
		}
		
		if(!(numNodes<nodes.length)) {
			error = error -2;
		}
		
		if(node==null) {  
			error = error - 4;			
		}
		
		
		return error;
	} 

	/**  
    * Obtiene el índice de un nodo en el vector de nodos, y –1 si no existe  
    *  
    * ¡¡¡ OJO que es privado porque depende de la implementación !!!  
    */  
    private int getNode(T node) {  
    	for(int i=0;i<numNodes;i++) {
    		if(nodes[i].equals(node)){ //Si encuentra el nodo buscado 
    			return i;
    		}
    	}
    	
    	return -1; //Si no lo encuentra 
    }  
    
    /**  
	* indica si existe o no el nodo en el grafo  
	*/  
	public boolean existsNode(T node) {
		int aux = getNode(node);
		if(aux==-1) {  //Si no existe el nodo.
			return false;
		}
		
		return true;  //Si existe el nodo.
	}


	/**  
	* indica si existe o no el nodo en el grafo  
	*/  
	public boolean existNode(T node) {
		return this.existsNode(node);
	}


	/**  
	* Borra el nodo deseado del vector de nodos así como las aristas de las que  
	* forma parte, devolviendo 0 si lo hace y –1 si no existe el nodo y –4 si 
	* el nodo no es valido  
	*/  
	public int removeNode(T node) { 
		
		int error = 0;
		
		if(node!=null && existNode(node)) {
			int index = getNode(node);
			if(index>=0) {
				--numNodes;
				if(index != numNodes+1) {
					nodes[index] = nodes[numNodes];
					for(int j=0;j<=numNodes;j++) {
						edges[j][index] = edges[j][numNodes];
						edges[index][j] = edges[numNodes][j];
						weights[index][j] = weights[numNodes][j];
						weights[j][index] = weights[j][numNodes];
					}
					edges[index][index] = edges[numNodes][numNodes];
					weights[index][index] = weights[numNodes][numNodes];
				}
			}
			return error; //Si se realizó con éxito.
		}
		
		if(node==null) {
			error = error -4;				//Si el nodo es nulo 
		}
		
		if(!existsNode(node)) {
			error = error -1;				//Si no existe el nodo.
		}
		
		return error;
	}


	/**  
	* Inserta una arista con el peso indicado (> 0) entre dos nodos, uno origen y  
	* otro destino.   
	* Devuelve 0 si la inserta  
	* Devuelve –1 y -2 si no existen nodos origen y destino respectivamente  
	* Devuelve –4 si ya existe y –8 si el peso no es válido  
	* Si se dan varios errores se suman 
	*/ 
	public int addEdge(T source, T target, double edgeWeight) { 
		
		boolean sourceN =existsNode(source);
		boolean targetN = existsNode(target);
		
		int error = 0;
		
		if(!existEdge(source, target) && edgeWeight>0 && sourceN && targetN) {
			int i = getNode(source);
			int j = getNode(target);
			edges[i][j] = true;
			weights[i][j] = edgeWeight;
			return error;
		}
		
		if(!sourceN) { 
			error = error-1;
		}
		
		if(!targetN) {
			error = error-2;
		}
		
		if(edgeWeight<=0) {
			error = error-8;
		}
		
		if(existEdge(source, target)) {
			error = error-4;
		}
		
		
		return error;
	
	}


	/**  
    * Devuelve el peso de la arista que conecta dos nodos,  
    * devuelve –1, -2 Y –3 si no existe origen, destino, ambos 
    * (notese que –3 es la suma de –1 y –2) 
    * devuelve –4 si no existe la arista  
    */  
    public double getEdge(T source, T target) { 
    	boolean sourceN = existNode(source);
    	boolean targetN = existNode(target);
    	int error = 0;
    	
    	if(sourceN && targetN &&existsEdge(source, target)) {
    		int i = getNode(source);
        	int j = getNode(target);
        	return this.weights[i][j];
    	}

    	if(!sourceN) {
    		error = error -1;
    	}
    	if(!targetN) {
    		error = error -2;
    	}
    	if(!existEdge(source, target)) {
    		error = error -4;
    	}
    	
    	return error;
    }  

      
    /**  
	* Comprueba si existe una arista entre dos nodos que se pasan como parámetro  
	* si alguno de los nodos no existe, no existe la arista evidentemente  
	*/  
	public boolean existEdge(T source, T target) { 
		int i = getNode(source);
		int j = getNode(target);
		if(i>=0 && j>=0) {
			return edges[i][j];
		}
		return false;
	}  
	
	/**  
	* Comprueba si existe una arista entre dos nodos que se pasan como parámetro  
	* si alguno de los nodos no existe, no existe la arista evidentemente  
	*/  
	public boolean existsEdge(T source, T target) { 
		return this.existEdge(source, target);
	}  
	
	//getEdge();
	
	
	/**  
	* Borra la arista del grafo que conecta dos nodos   
	* devuelve –1, -2 Y –3 si no existe origen, destino, ambos 
	* (notese que –3 es la suma de –1 y –2) 
	* devuelve –4 si no existe la arista  
	* devuelve 0 si la borra  
	*/  
	public int removeEdge(T source, T target) {
		boolean sourceN = existsNode(source);
		boolean targetN = existsNode(target);
		int error = 0;
		
		if(sourceN && targetN &&existEdge(source, target)) {
			int i = getNode(source);
			int j = getNode(target);
			edges[i][j] = false;
			weights[i][j] = -1; //Representando peso nulo 
			
			return error;
		}
		if(!sourceN) {
			error = error -1;
		}
		
		if(!targetN) {
			error = error -2;
		}
		
		if(!existEdge(source, target)) {
			error = error -4;
		}
		
		return error;
	}
	
	/**  
	*  Algoritmo de Dijkstra para encontrar el camino de coste mínimo desde nodoOrigen   
	*  hasta el resto de nodos. Devuelve el vector D de Dijkstra  
	*/  
	public double[] dijkstra(T node) { 
		
		//INICIALIZACIÓN
		double[] d = new double[this.numNodes];
		Integer[] p = new Integer[this.numNodes];
		boolean[] s = new boolean[this.numNodes];
		int aux = getNode(node);
		
		for(int i=0;i<numNodes;i++) {
			if(edges[aux][i]) { //Si hay arista 
				p[i] = aux;
				d[i] = weights[aux][i];
			}
			else { //Si no hay arista 
				p[i] = NOCONEXION;
				d[i] = INFINITO;
			}
		}
		
		s[aux] = true;
		
		for(int i=0;i<numNodes;i++) { //Se recorre el grafo 
			int minCost = minCost(d, s);
			if(minCost!=-1) {
				s[minCost] = true;
				for(int j=0;j<numNodes;j++) { 
					if(edges[minCost][j] && !s[j]) {
						if(d[minCost] + weights[minCost][j] < d[j]) {
							p[j] = minCost;
							d[j] = weights[minCost][j] + d[minCost]; //Camino actualizado
						}
					}
				}
			}
		}
		
		d[aux] = 0; //El coste al propio nodo es 0.
		return d;
	}    
	
	
	/**  

	* Busca el nodo con distancia minima en D al resto de nodos, se le pasa el vector D de dijkstra y 
	* el conjunto de visitados (como un vector de booleanos) y devuelve el indice del nodo buscado. 
	*/  
	private int minCost(double[] d, boolean[] s ) { 
		double mCost = INFINITO;
		int nNode = NOCONEXION;
		
		for(int i=0;i<numNodes;i++) { //Busca el menor 
			if(d[i] < mCost && !s[i]) {
				mCost = d[i];
				nNode = i;
			}
		}
		
		return nNode;
	}  

    /** 
     * Devuelve un String con la informacion del grafo (incluyendo matrices de Floyd) 
     */ 
    public String toString() { 
        DecimalFormat df = new DecimalFormat("#.##"); 
        String cadena = ""; 
  
        cadena += "NODES\n"; 
        for (int i = 0; i < numNodes; i++) { 
            cadena += nodes[i].toString() + "\t"; 
        } 
        cadena += "\n\nEDGES\n"; 
        for (int i = 0; i < numNodes; i++) { 
            for (int j = 0; j < numNodes; j++) { 
                if (edges[i][j]) 
                    cadena += "T\t"; 
                else 
                    cadena += "F\t"; 
            } 
            cadena += "\n"; 
        } 
        cadena += "\nWEIGHTS\n"; 
        for (int i = 0; i < numNodes; i++) { 
            for (int j = 0; j < numNodes; j++) { 
  
                cadena += (edges[i][j]?df.format(weights[i][j]):"-") + "\t"; 
            } 
            cadena += "\n"; 
        } 
  
        double[][] aFloyd = getAFloyd(); 
        if (aFloyd != null) { 
            cadena += "\nAFloyd\n"; 
            for (int i = 0; i < numNodes; i++) { 
                for (int j = 0; j < numNodes; j++) { 
                    cadena += df.format(aFloyd[i][j]) + "\t"; 
                } 
                cadena += "\n"; 
            } 
        } 
  
        int[][] pFloyd = getPFloyd(); 
        if (pFloyd != null) { 
                cadena += "\nPFloyd\n"; 
            for (int i = 0; i < numNodes; i++) { 
                for (int j = 0; j < numNodes; j++) { 
                    cadena += (pFloyd[i][j]>=0?df.format(pFloyd[i][j]):"-") + "\t"; 
                } 
                cadena += "\n"; 
            } 
        } 
        return cadena; 
    } 
	
    /** 
    * Aplica el algoritmo de Floyd al grafo y devuelve 0 si lo aplica y genera matrices A y P; y –1 si no lo hace 
    */ 
    public int floyd() {
    	if(numNodes == 0) {
    		return -1;
    	}
    	
    	incialiceA();
    	incialiceP(); //Se inicializan las matrices 
    	
    	for(int k=0;k<numNodes;k++) { //Se aplica el algoritmo 
    		for(int i=0;i<numNodes;i++) {
    			for(int j=0;j<numNodes;j++) {
    				if(aFloyd[i][k] + aFloyd[k][j] < aFloyd[i][j]) {
    					aFloyd[i][j] = aFloyd[i][k] + aFloyd[k][j];
    					pFloyd[i][j] = k;
    				}
    			}
    		}
    	}
    	return 0;
    } 

	 

	/**  
	* Devuelve la matriz A de Floyd, con infinito si no hay camino  
	* Si no se ha invocado a Floyd debe devolver null  
	*/  
	protected double[][] getAFloyd() { 
		return this.aFloyd;
	}  

	  

	 

	/**  
	* Devuelve la matriz P de Floyd, con -1 en las posiciones en las que no hay nodo intermedio  
	* Si no se ha invocado a Floyd debe devolver null  
	*/  
	protected int[][] getPFloyd() {
		return this.pFloyd;
	} 
	
	/** 
	* Devuelve el coste del camino de coste mínimo entre origen y destino según Floyd 
	* Si no están generadas las matrices de Floyd, las genera. 
	* Si no puede obtener el valor por alguna razón, devuelve –1 (OJO que es distinto de infinito) 
	**/ 
	public double minCostPath(T source, T target ) {
		
		
		if(source==null || target==null || numNodes == 0) {
			return -1;
		}
		
		if(getAFloyd()==null || getPFloyd()==null) {
			floyd();
		}
		
		int i = getNode(source);
		int j = getNode(target);
		
		return this.aFloyd[i][j];
	} 
	
	/** 
	 * Lanza el recorrido en profundidad de un grafo desde un nodo determinado, 
	 * No necesariamente recorre todos los nodos. 
	 * Al recorrer cada nodo añade el toString del nodo y un tabulador 
	 * Se puede usar un método privado recursivo... 
	 * Se recorren vecinos empezando por el principio del vector de nodos (antes índices bajos)
	 * Si no existe el nodo devuelve una cadena vacia 
	 */ 
	public String recorridoProfundidad(T source) {
		this.recorrido = "";
		boolean[] v = new boolean[numNodes];
		int index = getNode(source);
		if(!existNode(source)) {
			return "";
		}
		recorridoProfundoRec(v,recorrido, index);
		return this.recorrido;
	}

	/**
	 * Método privado auxiliar que modifica el string del recorrido en profundidad de 
	 * manera recursiva.
	 * @param v Vector de visitados 
	 * @param recorrido String que representa el recorrido en profundidad del grafo. 
	 * @param index índice del nodo.
	 */
	private void recorridoProfundoRec(boolean[] v, String recorrido, int index) {
		if(!v[index]) {
			v[index] = true;
			this.recorrido = this.recorrido + nodes[index].toString() +"\t";
			
			for(int i=0;i<numNodes;i++) {
				if(edges[index][i]) {
					recorridoProfundoRec(v, recorrido, i);
				}
			}
		}
		
			
	}

	 /** 
	* Indica el camino entre los nodos que se le pasan como parámetros en un String de esta forma: 
	* Origen<tab>(coste0)<tab>Intermedio1<tab>(coste1)….IntermedioN<tab>(costeN) Destino 
	* Si no hay camino: Origen<tab>(Infinity)<tab>Destino 
	* Si Origen y Destino coinciden: Origen 
	* Si no existen los 2 nodos devuelve una cadena vacía 
	* 
	*/ 
	public String path(T source , T target  ) {
		
		if(getAFloyd() == null || getPFloyd() == null) { //Si no existen las matrices de floyd 
			floyd();
		}
		
		if(!existNode(source) || !existNode(target)) { //Alguno de los nodos no existe 
			return "";
		}
		if(source.equals(target)) { //El nodo fuente es igual al nodo destino 
			return source.toString();
		}
		
		int indexS = getNode(source);
		int indexT = getNode(target);
		String str = source.toString() + "\t";
		
		if(aFloyd[indexS][indexT] == INFINITO) { //No hay camino 
			return  source.toString() + "\t(" + INFINITO + ")\t" + target.toString()+"\t";
		}
		
		if(pFloyd[indexS][indexT] == -1) { //Hay camino directo 
			str = str + "(" + aFloyd[indexS][indexT] + ")\t" + target.toString();
		}
		
		else { //Hay nodo intermedio 
			int aux = pFloyd[indexS][indexT];
			if(aux!=-1) {
				str = str + "(" + aFloyd[indexS][aux] + ")\t" ;
				str = str + path(nodes[aux],nodes[indexT]);
			}
			
		}
		return str;


	}

}
