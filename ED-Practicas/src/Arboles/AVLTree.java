package Arboles;

/** 
* Clase derivada de BSTree aÃ±adiendo funcionalidad de AVL 
* @author Nestor 
* @version 2019-20 
*/ 
public class AVLTree <T extends Comparable<T> >extends BSTree <T>{ 
 
/** 
* Constructor.La raíz null por defecto.  
*/ 
public AVLTree() {} 
 
 
/* (non-Javadoc) 
* @see BSTree#addNode(java.lang.Comparable) 
* Redefine inserciÃ³n para funcionalidad AVL 
*/ 
public int addNode (T obj){
	if(obj == null) { //El nodo que se le pasa como parámetro es nulo
		return -2; 
	}
	if(searchNode(obj) != null) { //El nodo a añadir ya está en el árbol 
		return -1;
	}
	raiz = addNodeRec((AVLNode<T>)this.raiz,obj);
	return 0; //Se completa con éxito 
} 

/**
 * Método recursivo auxiliar del método addNode.
 * @param raiz La raiz de la cual parte el metodo.
 * @para obj Objeto que se quiere añadir en el arbol 
 * @return La raiz del arbol ya balanceada 
 */
private AVLNode<T> addNodeRec(AVLNode<T> raiz, T obj) {
	if(raiz == null) { //Siún no se creño el árbol 
		return new AVLNode<T>(obj);
	}
	if(obj.compareTo(raiz.getInfo()) < 0) { //Si es menor, va a la izquierda. 
		raiz.setLeft(addNodeRec(raiz.getLeft(), obj));
	}
	if(obj.compareTo(raiz.getInfo()) > 0) { //Si es mayor, va a la derecha.
		raiz.setRight(addNodeRec(raiz.getRight(), obj));
	}
	return updateAndBalanceIfNecesary(raiz);
	
}


  
 
/** 
* se le pasa el arbol que se quiere actualizar Height, BF  
*      y balancear si fuese necesario 
* y devuelve la raiz del arbol por si ha cambiado 
* @param node Nodo sobre el que se quiere balancear si es necesario.
* @return raiz del arbol 
*/ 
private AVLNode<T> updateAndBalanceIfNecesary (AVLNode<T> node){
	
	if(node == null){
		return null;
	}
	
	node.updateHeight();
	
	if(node.getBF() == -2) {
		if(node.getLeft().getBF() == 1) {
			node = doubleLeftRotation(node);
		}
		else {
			node = singleLeftRotation(node);
		}
	}
	if(node.getBF() == 2) {
		if(node.getRight().getBF() == -1) {
			node = doubleRightRotation(node);
		}
		else {
			node = singleRightRotation(node);
		}
	}
	return node; 

} 
 
 
/** 
* Se le pasa la raiz del arbol a balancear con rotacion simple a la izquierda  
* devuelve la raiz del nuevo arbol que ha cambiado 
*/ 
private AVLNode<T> singleLeftRotation (AVLNode<T> node){ 
	//System.out.println("Rotación simple a la izquierda sobre el nodo "+ node.toString());
	AVLNode<T> aux = node.getLeft();
	node.setLeft(aux.getRight());
	aux.setRight(node);
	node.updateHeight();
	if(node == this.raiz) { //Si el nodo es la raíz 
		this.raiz = aux;
	}
	node = aux;
	aux.updateHeight();
	return node; 
} 
 
 
/** 
* Se le pasa la raiz del arbol a balancear con rotacion doble a la izquirda 
* devuelve la raiz del nuevo arbol que ha cambiado 
*/ 
private AVLNode<T> doubleLeftRotation(AVLNode<T> node) {
	//System.out.println("Rotación doble a la izquierda sobre el nodo "+ node.toString());
	node.setLeft(singleRightRotation(node.getLeft()));
	AVLNode<T> aux = singleLeftRotation(node);
	return aux;
} 
 
 
/** 
* Se le pasa la raiz del arbol a balancear con rotacion simple a la derecha
* devuelve la raiz del nuevo arbol que ha cambiado 
*/ 
private AVLNode<T> singleRightRotation (AVLNode<T> node){
	//System.out.println("Rotación simple a la derecha sobre el nodo "+ node.toString());
	AVLNode<T> aux = node.getRight();
	node.setRight(aux.getLeft());
	aux.setLeft(node);
	node.updateHeight();
	if(node == this.raiz) { //Si el nodo es la raíz 
		this.raiz = aux;
	}
	node = aux;
	aux.updateHeight();
	return node; 
}
 
 
/** 
* Se le pasa la raiz del arbol a balancear con rotacion doble a la derecha 
* Devuelve la raiz del nuevo arbol que ha cambiado 
*/ 
private AVLNode<T> doubleRightRotation(AVLNode<T> node) {
	//System.out.println("Rotación doble a la derecha sobre el nodo "+ node.toString());
	node.setRight(singleLeftRotation(node.getRight()));
	AVLNode<T> aux = singleRightRotation(node);
	return aux;
}

 
 
///* (non-Javadoc) 
//* @see BSTree#removeNode(java.lang.Comparable) 
//* RedefiniciÃ³n para incluir caracterÃ­sticas AVL 
//*/ 
public int removeNode (T node){
	if(raiz == null || node == null) { //Si no hay arbol o el objeto es nulo 
		return -2;
	}
	if(searchNode(node) == null) { //Si el objeto a eliminar no esta en el arbol / NO EXISTE 
		return -1; 
	}
	if (searchNode(node).equals(node)) { //Se comprueba para asegurarse con el equals.
		raiz = removeNodeRec(getRoot(), node);
		return 0;
	}
	
	return -2;
} 
private AVLNode<T> removeNodeRec(AVLNode<T> theRoot,T node){
	if(theRoot == null) {
		error = true; //El nodo no existe.
	}
	
	if (!error) {
		if (node.compareTo(theRoot.getInfo()) < 0) {
			theRoot.setLeft(removeNodeRec(theRoot.getLeft(), node));
		}
		else if (node.compareTo(theRoot.getInfo()) > 0) {
			theRoot.setRight(removeNodeRec(theRoot.getRight(), node));
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
	
	return updateAndBalanceIfNecesary(theRoot);
}

/**
 * Busca el nodo máximo en el árbol 
 * @param theRoot Raíz 
 * @return Nodo máximo 
 */
private T getMax(AVLNode<T> theRoot) {
	while(theRoot.getRight() != null) {
		theRoot = theRoot.getRight();
	}
	return theRoot.getInfo();
}


/**
 * Devulve la raíz del árbol en forma de AVLNode<T>.
 * @return raiz Raíz del árbol 
 */
public AVLNode<T> getRoot(){
	return (AVLNode<T>) raiz;
}

}


 
 