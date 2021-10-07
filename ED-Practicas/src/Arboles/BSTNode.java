package Arboles;


/** 
* Clase que represnta un nodo de una arbol BST
* @author Prodesores ED (EspaÃ±ol) 
* @version 2019-20 
*/ 
public class BSTNode <T extends Comparable<T>>{ 
/**
 * Información del nodo
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
* que se le pasa como parámetro.
* @param info El objeto comparable.
*/ 
public BSTNode (T info) {
	this.info = info;
	this.left = null;
	this.right = null;
} 
 
 
/** 
* Se le pasa la información que se quiere meter en el nodo 
* @param info Nueva información del nodo 
*/ 
protected void setInfo(T info) {
	this.info = info;
} 
 
 
/** 
* Devuelve la información que almacena el nodo 
* @return Información del nodo.
*/ 
public T getInfo() {
	return this.info;
} 
 
 
/** 
* Se le pasas el nodo que se quiere enlazar en el subárbol izquierdo.
* @param left Nodo que se quiere poner como hijo izquierdo.
*/ 
public void setLeft(BSTNode<T> left){
	this.left = left;
} 
 
 
/** 
* Se le pasa el nodo que se quiere enlazar en el subárbol derecho.
* @param right Nodo que se quiere poner como hijo derecho.
*/ 
public void setRight(BSTNode<T> right){
	this.right = right;
} 
 
 
/** 
* Devuelve el subárbol izquierdo 
* @return El subárbol izquierdo
*/ 
protected BSTNode<T> getLeft () {
	return this.left;
} 
 
 
/** 
* Devuelve el subárbol derecho
* @return El subárbol derecho 
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