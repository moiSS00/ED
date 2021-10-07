package Arboles;
 
/** 
* Clase derivada de BSTNode aÃ±adiendo funcionalidad de AVL 
* @author NÃ©stor 
* @version 2019-20 
*  
*/ 
public class AVLNode<T extends Comparable<T>> extends BSTNode<T>  { 
 
/** 
* Para almacenar la altura del nodo 
*/ 
protected int height; 
 
 
/** 
* Para almacenar al Factor de balance. Puede no existir y calcularse cada vez a partir de la altura de los hijos. 
*/ 
protected int balanceFactor; 
 
 
/** 
* Llama al padre y aÃ±ade la informacion propia 
* se le pasa la informacion que se mete en el nuevo nodo 
* @param nodo La información del nodo 
*/ 
public AVLNode(T nodo){
	super(nodo);
	height = 1; //Simplifica las operaciones 
	balanceFactor = 0;
} 
 
 
 
/** 
* devuelve la altura del arbol del cual es raiz el nodo en cuestion 
* @return height Altura del nodo 
*/ 
public int getHeight() {
	return height;
} 
 
 
 
/** 
* Devuelve el factor de balance segun equilibrio del arbol del cual es raiz 
* @return devuelve el factor de balance del nodo.
*/ 
public int getBF() {
	return balanceFactor;
} 
 
 
 
/** 
* Actualiza la altura del nodo en el arbol utilizando la altura de los hijos.
* En función de: 
* -El nodo evaluado no tiene hijos.
* -El nodo evaluado solo tiene un hijo 
* -El nodo evaludao tiene ambos hijos
*/ 
protected void updateHeight() { 
	

	boolean left = getLeft() == null;
	boolean right = getRight() == null;
	
	if(left && right) { //Si el nodo es una hoja (no tiene hijos)
		this.height = 1;
		this.balanceFactor = 0;
	}
	
	else if(left && !right) { //Si solo tiene hijo derecho 
		this.height = getRight().height + 1; //La altura es la inmediatamente superior a su hijo derecho 
		this.balanceFactor = getRight().height; //Su factor de balance es la altura de su hijo derecho (getRight().height - 0 )
	}
	
	else if(!left && right) { //Si solo tiene hijo izquierdo 
		this.height = getLeft().height + 1; //La altura es la inmediatamente superior a su hijo izqierdo 
		this.balanceFactor = - getLeft().height; //Su factor de balance es la altura negativa de su hijo izquierdo (0 - getLeft.height)
	}
	
	else { //Si tiene hijo izqierdo y derecho 
		this.height = 1 + Math.max(getRight().height, getLeft().height); //Comprobamos la altura máxima 
		this.balanceFactor = getRight().getHeight() - getLeft().getHeight(); //El factor de balance será la altura del subarbol derecho menos la del subarbol izquierdo.
	}
	
	} 


 
 
/* (non-Javadoc) 
* @see BSTNode#getLeft() 
*/ 
public AVLNode<T> getLeft(){ 
return (AVLNode<T>) super.getLeft(); 
} 
 
 
/* (non-Javadoc) 
* @see BSTNode#getRight() 
*/ 
public AVLNode<T> getRight() { 
return (AVLNode<T>) super.getRight(); 
} 
 
// No se necesitan los setters, valen los heredados 
 
 
/* (non-Javadoc) 
* @see BSTNode#toString() 
* AÃ±ade factor de balance 
*/ 
public String toString() { 
return super.toString()+":FB="+ getBF(); 
} 
} 
 
 