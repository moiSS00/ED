package Arboles;


/** 
* Clase que represnta un nodo de una arbol BST
* @author Prodesores ED (Español) 
* @version 2019-20 
*/ 
public class BSTNode <T extends Comparable<T>>{ 
/**
 * Informaci�n del nodo
 */
protected T info; 

/**
 * Hijo izquierdo del nodo 
 */
protected BSTNode<T> left; 

/**
 * Hijo derecho del nodo
 */
protected BSTNode<T> right; 
 
 
/** 
* Se le pasa un objeto comparable. Este se inicializa sin hijos que la respectiva info
* que se le pasa como par�metro.
* @param info El objeto comparable.
*/ 
public BSTNode (T info) {
	this.info = info;
	this.left = null;
	this.right = null;
} 
 
 
/** 
* Se le pasa la informaci�n que se quiere meter en el nodo 
* @param info Nueva informaci�n del nodo 
*/ 
protected void setInfo(T info) {
	this.info = info;
} 
 
 
/** 
* Devuelve la informaci�n que almacena el nodo 
* @return Informaci�n del nodo.
*/ 
public T getInfo() {
	return this.info;
} 
 
 
/** 
* Se le pasas el nodo que se quiere enlazar en el sub�rbol izquierdo.
* @param left Nodo que se quiere poner como hijo izquierdo.
*/ 
public void setLeft(BSTNode<T> left){
	this.left = left;
} 
 
 
/** 
* Se le pasa el nodo que se quiere enlazar en el sub�rbol derecho.
* @param right Nodo que se quiere poner como hijo derecho.
*/ 
public void setRight(BSTNode<T> right){
	this.right = right;
} 
 
 
/** 
* Devuelve el sub�rbol izquierdo 
* @return El sub�rbol izquierdo
*/ 
protected BSTNode<T> getLeft () {
	return this.left;
} 
 
 
/** 
* Devuelve el sub�rbol derecho
* @return El sub�rbol derecho 
*/ 
protected BSTNode<T> getRight () {
	return this.right;
} 
 
 
/* (non-Javadoc) 
* @see java.lang.Object#toString() 
*/ 
public String toString() { 
return info.toString(); 
}  
} 