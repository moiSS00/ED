package Hash;

/**
 * @author Néstor
 * @version 2019-20
 *
 * @param <T>
 */
public class HashNode<T> {
	private T info;
	private byte status;

	
	public static final byte BORRADO = -1;
	public static final byte VACIO = 0;
	public static final byte LLENO = 1;

	/**
	 * Constructor por defecto de un nodo de la tabla hash. Este se inicializa como vacio 
	 */
	public HashNode () {
		info = null;
		status=VACIO;
	}
	
	/**
	 * Devuelve ele elemento que hay en el nodo
	 * @return Elemento contenido 
	 */
	public T getInfo() {
		return info;
	}
	
	/**
	 * Borra el elemento, poniendo su status a borrado 
	 */
	public void remove (){
		status=BORRADO;
	}
	
	/**
	 * Actualiza la info de el nodo 
	 * @param elem Nueva info 
	 */
	public void setInfo(T elem){
		info=elem;
		status=LLENO;
	}
	
	/**
	 * Devuelve el estado del nodo 
	 * @return Estado del nodo 
	 */
	public byte getStatus() {
		return status;
	}

	/**
	 * Devuelve una cadena representando al nodo segun su status.
	 */
	public String toString (){
		StringBuilder cadena=new StringBuilder("{");
		switch (getStatus()){
		case LLENO:
			cadena.append(info);
			break;
		case VACIO:
			cadena.append("_E_");
			break;
		case BORRADO:
			cadena.append("_D_");
		}
		cadena.append("}");
		return cadena.toString();
	}

}
