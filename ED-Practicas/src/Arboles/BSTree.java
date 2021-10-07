package Arboles;


/** 
* Clase que representa un arbol BST
* @author Prodesores ED (Español) 
* @version 2019-20 
*/ 
public class BSTree <T extends Comparable<T>>{ //No constructor, arbol vacio 
 
 
	protected BSTNode<T> raiz; //Representa la ra�z del �rbol 
	protected boolean error;
	 



	/** 
	 * Se le pasa el objeto comparable que hay que insertar. Comprueba que es
	 * un par�metro correcto y se lo pasa al respectivo m�todo recusivo.En este
	 * se reccorre el �rbol BST existente comprobando si el par�metro es menor o 
	 * mayor que el nodo del arbol que estamos evaluando.Esto hasta llegar al final del �rbol 
	 * y as� insertarlo.Si el �rbol no ha sido creado, el objeto que se quiere a�adir pasar� 
	 * a ser la ra�z.
	 * 
	 * @param obj Objeto comparable a a�adir.
	 * @return Si lo inserta devolveria 0, y un codigo de error negativo  
	 * si no lo hace 
	 * (-1 si ya existe, -2 otra causa(el objeto es nulo)) 
	 */ 
	public int addNode(T obj) {
		if(obj == null) { //Se comprueba si el parametro es nulo 
			return -2;
		}
		if(searchNode(obj) != null) { //Se comprueba si ya estaba en el arbol 
			return -1;
		}
		raiz = addNodeRec(raiz,obj);
		return 0;
		//BTSNode nuevo,necesito metodo interno q le pase el arbol donde quiero insertarlo(recursivo).
	} 

	/**
	 * M�todo para modificar la ra�z del �rbol 
	 * @param nodo Nueva ra�z 
	 */
	protected void setRoot(BSTNode<T> nodo) {
		raiz = nodo;
	}
	

	/**
	 * M�todo recursivo auxiliar del addNode 
	 * @param raiz Nodo que se est� evaluando 
	 * @param obj Objeto comparable que se quiere a�adir 
	 * @return raiz Nodo que se est� comparando  
	 */
	private BSTNode<T> addNodeRec(BSTNode<T> raiz, T obj) {
		if(raiz == null) {
			return new BSTNode<T>(obj); 
		}

		if(obj.compareTo(raiz.getInfo()) <0){
			raiz.setLeft(addNodeRec(raiz.getLeft(),obj));
		}
		
		if(obj.compareTo(raiz.getInfo()) > 0) {
			raiz.setRight(addNodeRec(raiz.getRight(),obj));
		}

		return raiz;
	}


	/** 
	* Se le pasa un objeto comparable que se busca 
	* Devuelve el objeto encontrado que cumple que es "equals" con el 
	* buscado, null si no lo encuentra por cualquier motivo. Esto lo hace 
	* llamando a su respectivo m�todo recursivo que recorre todo el �rbol en 
	* busca del nodo.
	* @param obj Objeto a buscar 
	* @return El objeto buscado
	*/ 
	public T searchNode(T obj) {
		if (raiz == null || obj == null) {
			return null;
		}

		return searchNodeRec(raiz,obj);

	} 
	
	/**
	 * M�todo recursivo auxiliar de searchNode.
	 * @param theRoot El padre 
	 * @param obj El objeto a buscar 
	 * @return El objeto encontrado 
	 */
	private T searchNodeRec(BSTNode<T> theRoot,T obj) {
		
		if(theRoot == null) {
			return null;
		}
		
		if(!theRoot.equals(obj)) {
			if(obj.compareTo(theRoot.getInfo()) < 0) {
				return searchNodeRec(theRoot.getLeft(), obj);
			}
			
			if(obj.compareTo(theRoot.getInfo()) > 0) {
				return searchNodeRec(theRoot.getRight(), obj);
			}
		}
		
		return theRoot.getInfo();
	}


	
	/** 
	* Genera un String con el recorrido en pre-orden (izquierda-derecha).
	* El patr�n es: Ra�z - Hijo.izquierdo - Hijo.derecho.
	* Si el �rbol est� vac�o se devuelve la cadena vac�a 
	* Este recorrido se hace con su respectivo m�todo recursivo. 
	* (toString de los nodos separados por tabuladores) 
	*/ 
	public String preOrder() {
		return preOrderRec(raiz);
	} 
	 
	 
	/**
	 * M�todo recursivo privado que realiza el recorrido preOrden del arbol (raiz - izq - der ) 
	 * @param theRoot El padre 
	 * @return String El recorrido preOrden 
	 */
	private String preOrderRec(BSTNode<T> theRoot) {
		if(theRoot == null) {
			return "";
		}
		return theRoot.toString() + "\t" + preOrderRec(theRoot.getLeft()) + preOrderRec(theRoot.getRight());
	}

	/** 
	* Genera un String con el recorrido en post-orden (izquierda-derecha).
	* El patr�n es: Hijo.izquierdo - Hijo.derecho - Ra�z.
	* Si el �rbol est� vac�o se devuelve la cadena vac�a 
	* Este recorrido se hace con su respectivo m�todo recursivo. 
	* (toString de los nodos separados por tabuladores) 
	*/ 
	public String postOrder() {
		return postOrderRec(raiz);
	} 
	 
	 
	/**
	 * M�todo recursivo privado que realiza el recorrido postOrden del arbol (izq - der - raiz ) 
	 * @param theRoot El padre 
	 * @return String El recorrido postOrden 
	 */
	private String postOrderRec(BSTNode<T> theRoot) {
		if(theRoot == null) {
			return "";
		}
		return postOrderRec(theRoot.getLeft())  + postOrderRec(theRoot.getRight()) + theRoot.toString() + "\t";
	}


	/** 
	* Genera un String con el recorrido en in-orden (izquierda-derecha).
	* El patr�n es: Hijo.izquierdo - Ra�z - Hijo.derecho.
	* Si el �rbol est� vac�o se devuelve la cadena vac�a 
	* Este recorrido se hace con su respectivo m�todo recursivo. 
	* (toString de los nodos separados por tabuladores) 
	*/ 
	public String inOrder() {
		return inOrderRec(raiz);
	} 
	
	/**
	 * M�todo recursivo privado que realiza el recorrido inOrden del arbol (izq - raiz - der ) 
	 * @param theRoot El padre 
	 * @return String El recorrido inOrden 
	 */
	private String inOrderRec(BSTNode<T> theRoot) {
		if(theRoot == null) {
			return "";
		}
		return inOrderRec(theRoot.getLeft()) + theRoot.toString() + "\t" + inOrderRec(theRoot.getRight());
	}

	/** 
	* Se le pasa el objeto que se quiere borrar que coincida con equals.
	* Se comprueba si este par�metro es correcto y si existe el �rbol.
	* Luego, mediante un m�todo recursivo privado se elimina el nodo seg�n los 
	* hijos que tenga. Hay varios casos a tener en cuenta: 
	* -Que no tenga hijos.
	* -Que solo tenga hijo izquierdo o solo hijo derecho.
	* -Que tenga ambos hijos.
	* 
	* @param obj Objeto comparable a eliminar.
	* @return Si lo inserta devolveria 0, y un codigo de error negativo  
	* si no lo hace 
	* (-1 si no existe, -2 otra causa(el objeto es nulo o el �rbol no existe)) 
	*/ 
	public int removeNode (T obj){
		if(raiz == null || obj == null) { //Si no hay arbol o el objeto es nulo 
			return -2;
		}
		if(searchNode(obj) == null) { //Si el objeto a eliminar no esta en el arbol / NO EXISTE 
			return -1; 
		}
		if (searchNode(obj).equals(obj)) { //Se comprueba para asegurarse con el equals.
			raiz = removeNodeRec(raiz, obj);
			return 0;
		}
		
		return -2;
	} 
	 
	 

	/**
	 * M�todo recursivo auxiliar de removeNode.
	 * @param theRoot El padre 
	 * @param obj El objeto a eliminar 
	 * @return El padre 
	 */
	private BSTNode<T> removeNodeRec (BSTNode<T> theRoot, T obj){
		error = false;
		
		if(theRoot == null) {
			error = true; //El nodo no existe.
		}
		
		if (!error) {
			if (obj.compareTo(theRoot.getInfo()) < 0) {
				theRoot.setLeft(removeNodeRec(theRoot.getLeft(), obj));
			}
			else if (obj.compareTo(theRoot.getInfo()) > 0) {
				theRoot.setRight(removeNodeRec(theRoot.getRight(), obj));
			} else {
				boolean leftN = theRoot.getLeft() == null;
				boolean rightN = theRoot.getRight() == null;
				if (leftN && rightN) { //Si no tiene hijos 
					return null;
				} else if (!leftN && rightN) {
					return theRoot.getLeft();
				} else if (leftN && !rightN) {
					return theRoot.getRight();
				} else {
					theRoot.setInfo(getMax(theRoot.getLeft()));
					theRoot.setLeft(removeNodeRec(theRoot.getLeft(), theRoot.getInfo()));
				}
			} 
		}
		return theRoot;
	}

	
	/**
	 * M�todo privado que nos devuelve el mayor nodo de un �rbol 
	 * @param theRoot El padre 
	 * @return El mayor nodo del �rbol ( su contenido ) 
	 */
	private T getMax(BSTNode<T> theRoot) {
		while(theRoot.getRight() != null) {
			theRoot = theRoot.getRight();
		}
		return theRoot.getInfo();
	}
	
	/**
	 * Devuelve un toString del �rbol tumbado.
	 */
	public String toString() { 
		return tumbarArbol(raiz, 0); 
	} 



	/** 
	 * Genera un String con el arbol "tumbado" (la raiz a la izquierda y las ramas hacia la derecha) 
	 * Es un recorrido InOrden-Derecha-Izquierda, tabulando para mostrar los distintos niveles 
	 * Utiliza el toString de la informacion almacenada 
	 *   
	 * @param p La raiz del arbol a mostrar tumbado 
	 * @param esp El espaciado en numero de tabulaciones para indicar la profundidad 
	 * @return El String generado 
	 */ 
	protected String tumbarArbol(BSTNode<T> p, int esp) { 
		StringBuilder cadena = new StringBuilder(); 

		if (p != null) { 
			cadena.append(tumbarArbol(p.getRight(), esp + 1)); 
			for (int i = 0; i < esp; i++) 
				cadena.append("\t"); 
			cadena.append(p + "\n"); 
			cadena.append(tumbarArbol(p.getLeft(), esp + 1)); 
		} 
		return cadena.toString(); 
	} 
	
	protected BSTNode<T> getRoot(){
		return raiz;
	}

} 