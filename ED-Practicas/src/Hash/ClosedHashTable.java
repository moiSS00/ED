package Hash;

import java.util.ArrayList;

/**
 * @author Nestor
 * @version 2019-20
 *
 */
public class ClosedHashTable<T> extends AbstractHash<T> { // ¡¡¡CUIDADO CON LOS BITS DE ESTADO!!!
// IMPORTANTE Funcion hash no se puede tocar (POS INICIAL) De momento solo funcion de dispersion lineal 
//	No cambiar el nombre ni visibilidad de los atributos protected

	protected HashNode<T> associativeArray[];

	protected int hashSize; // tamaÃ±o de la tabla, debe ser un numero primo
	protected int numElems; // numero de elementos en la tabla en cada momento.

	public static final int LINEAL = 0; // Tipo de exploracion en caso de colision, por defecto sera LINEAL
	public static final int CUADRATICA = 1;
	public static final int DOBLEHASH = 2;

	protected int exploracion; // exploracion que se realizara en caso de colision (LINEAL por defecto)

	protected double fcUP; // para la funcion de redispersion directa
	protected double fcDown; // para la funcion de redispersion inversa

	/**
	 * Constructor para fijar el tamano al numero primo >= que el parametro y el
	 * tipo de exploracion al indicado.
	 * @param tam El tamaño de la tabla hash
	 * @para expl Funcion de exploracion deseada. 0 para la lineal, 1 para la 
	 * cuadratica t 2 para la de doble dispersion 
	 */
	@SuppressWarnings("unchecked") // Puede tener repetidos
	public ClosedHashTable(int tam, int expl) {
		hashSize = nextPrimeNumber(tam);// Establece un tamaÃ±o valido si tam no es primo

		associativeArray = (HashNode<T>[]) new HashNode[hashSize]; // Crea el array de HashNode's
		inicializarHash(); // Inicializa la hash con nodos
		setExploration(expl); // Establece funcion de exploracion (por defecto lineal)
		numElems = 0; // Numero de elementos a 0.
		this.fcUP = 1; // Valores por defecto
		this.fcDown = 0;

	}

	/**
	 * Inicializa la tabla Hash rellenandola de HashNode sin info.
	 */
	private void inicializarHash() {
		for (int i = 0; i < associativeArray.length; i++) {
			associativeArray[i] = new HashNode<T>();
		}
	}

	/**
	 * Establece la funcion de exploracion en caso de colision, por defecto es
	 * lineal
	 * 
	 * @param expl Numero correspodiente a la funcion de exploracion
	 */
	private void setExploration(int expl) {
		if (expl < 0 || expl > 2) {
			exploracion = LINEAL;
		} else {
			exploracion = expl;
		}
	}

	/**
	 * Constructor para fijar el tamaÃ±o al numero primo >= que el parametro
	 * 
	 * @param tam    tamaÃ±o del Hash, si no es un numero primo lo ajusta al primo
	 *               superior
	 * @param fcUP   Factor de carga limite, por encima del cual hay que redispersar
	 *               (directa)
	 * @param fcDOWN Factor de carga limite, por debajo del cual hay que redispersar
	 *               (inversa)
	 * @param expl   tipo de exploracion (LINEAL=0, CUADRATICA=1, ...), si invalido
	 *               LINEAL
	 */
	@SuppressWarnings("unchecked")
	public ClosedHashTable(int tam, double fcUP, double fcDOWN, int expl) { // Para la segunda clase
		hashSize = nextPrimeNumber(tam);
		associativeArray = (HashNode<T>[]) new HashNode[hashSize];
		inicializarHash();
		setFcUp(fcUP); // Cuidado con los valores por defecto
		setFcDown(fcDOWN);
		setExploration(expl);
	}

	/**
	 * Establece el factor de carga limite, por encima del cual hay que resispersar
	 * (directa) Si rezibe un parámtro nulo , se pondra 1 como valor por defecto.
	 * 
	 * @param fcUp el factor de carga limite, por encima del cual hay que
	 *             resispersar (directa)
	 */
	private void setFcUp(double fcUp) {
		if (fcUp < 0 || fcUp > 1) {
			this.fcUP = 1;
		} else {
			this.fcUP = fcUp;
		}
	}

	/**
	 * Establece el factor de carga limite, por encima del cual hay que resispersar
	 * (inversa) Si rezibe un parámtro nulo , se pondra 0 como valor por defecto.
	 * 
	 * @param fcUp el factor de cargar limite, por encima del cual hay que
	 *             resispersar (inversa)
	 */
	private void setFcDown(double fcDown) {
		if (fcDown < 0 || fcDown > 1) {
			this.fcUP = 0;
		} else {
			this.fcDown = fcDown;
		}
	}

	/**
	 * Funcion de exploracion lineal
	 * 
	 * @param elem      Elemento
	 * @param iteracion El numero de la iteracion actual 
	 * @return Nueva posicion 
	 */
	private int fLineal(T elem, int iteracion) {
		return (fHash(elem) + iteracion) % getSize();
	}

	/**
	 * Funcion de exploracion cuadratica 
	 * @param elem Elemento 
	 * @param iteracion El numero de iteracion actual 
	 * @return Nueva posicion 
	 */
	private int fCuadratic(T elem, int iteracion) {
		return (fHash(elem) + iteracion * iteracion) % getSize();
	}

	/**
	 * Funcion de exploracion doble dispersion  
	 * @param elem Elemento 
	 * @param iteracion El numero de iteracion actual 
	 * @return Nueva posicion 
	 */
	private int fDouble(T elem, int iteracion) {
		return (fHash(elem) + iteracion * h(elem)) % getSize();
	}

	/**
	 * Metodo privado auxiliar para calcular la nueva posicion con funcion de exploracion de doble dispersion.
	 * @param elem Elemento 
	 * @return
	 */
	private int h(T elem) {
		int r = previousPrimeNumber(getSize() - 1); // CUIDADO
		int positiveValue = elem.hashCode() % r;
		if (positiveValue < 0) {
			positiveValue += r;
		}
		return r - positiveValue;

	}

	/**
	 * Devuelve el numero de elementos de la tablas hash 
	 */
	@Override
	public int getNumOfElems() {
		return numElems;
	}

	/**
	 * Devuelve el tamaño de la tabla hash
	 */
	@Override
	public int getSize() {
		return hashSize;
	}

	/**
	 * Devuelve la funcion de exploracion que se esta utilizando 
	 * @return
	 */
	private int getExploration() {
		return exploracion;
	}

	/**
	 * Añade y realiza una redispersion si es necesario 
	 * @param elem Elemento a añadir 
	 * @return 0 si se realiza on exito , -1 si no cabe , -2 si el elemento es invalido.
	 */
	@Override
	public int add(T elem) {
		int expl = getExploration();
		int aux = -2;
		if (expl == LINEAL) {
			aux = addLineal(elem);
		}
		if (expl == CUADRATICA) {
			aux = addCuadratic(elem);
		}
		if (expl == DOBLEHASH) {
			aux = addDouble(elem);
		}
		reDispersion();
		return aux;
	}

	/**
	 * Metodo auxiliar para añadir mediante la funcion de exploracion lineal
	 * @param elem Elemento a añadir 
	 * @return 0 si se realiza on exito , -1 si no cabe , -2 si el elemento es invalido.
	 */
	private int addLineal(T elem) {
		if (elem == null) {
			return -2;
		}
	
		int it = 0;
		int pos = fLineal(elem, it);
	
		while (associativeArray[pos].getStatus() == 1) {
			if (it == getSize() - 1) {
				return -1;
			}
			++it;
			pos = fLineal(elem, it);
		}
		associativeArray[pos].setInfo(elem);
		++numElems;
		return 0;
	}

	/**
	 * Metodo auxiliar para añadir mediante la funcion de exploracion cuadratica
	 * @param elem Elemento a añadir 
	 * @return 0 si se realiza on exito , -1 si no cabe , -2 si el elemento es invalido.
	 */
	private int addCuadratic(T elem) {
		if (elem == null) {
			return -2;
		}

		int it = 0;
		int pos = fCuadratic(elem, it);
		double nElem = getNumOfElems();
		double size = getSize();
		double fc = nElem / size;

		if (!(fc < 0.5 && isPositivePrime(getSize()))) {
			return -1;
		}

		while (associativeArray[pos].getStatus() == 1) {
			++it;
			pos = fCuadratic(elem, it);
		}
		associativeArray[pos].setInfo(elem);
		++numElems;
		return 0;
	}

	/**
	 * Metodo auxiliar para añadir mediante la funcion de exploracion de doble dispersion
	 * @param elem Elemento a añadir 
	 * @return 0 si se realiza on exito , -1 si no cabe , -2 si el elemento es invalido.
	 */
	private int addDouble(T elem) {
		if (elem == null) {
			return -2;
		}
	
		int it = 0;
		int pos = fDouble(elem, it);
	
		while (associativeArray[pos].getStatus() == 1) {
			if (it == getSize() * getSize()) { // Para fijar un limite
				return -1;
			}
			++it;
			pos = fDouble(elem, it);
		}
		associativeArray[pos].setInfo(elem);
		++numElems;
		return 0;
	}

	/**
	 * Busca un elemento en la tabla hash
	 * @param elem Elemento a buscar 
	 * @return El elemento encontrado en la tabla o si no lo encuenta , devuelve null.
	 */
	@Override
	public T find(T elem) { // Mirar si estan borrados y cuidado con el numElm
		int expl = getExploration();
		T aux = null;
		if (expl == LINEAL) {
			aux = findLineal(elem);
		}
		if (expl == CUADRATICA) {
			aux = findCuadratic(elem);
		}
		if (expl == DOBLEHASH) {
			aux = findDouble(elem);
		}
		return aux;
	}

	/**
	 * Metodo auxiliar para buscar mediante la funcion de exploracion lineal
	 * @param elem Elemento a añadir 
	 * @return El objeto encontrado o null si no lo encuentra.
	 */
	private T findLineal(T elem) {
		if (elem == null) {
			return null;
		}

		int it = 0;
		int pos = fLineal(elem, it);

		while (it != getSize() - 1) {
			if (associativeArray[pos].getStatus() == 1 && associativeArray[pos].getInfo().equals(elem)) {
				return associativeArray[pos].getInfo();
			}
			++it;
			pos = fLineal(elem, it);
		}

		return null;
	}

	/**
	 * Metodo auxiliar para buscar mediante la funcion de exploracion cuadratica
	 * @param elem Elemento a añadir 
	 * @return El objeto encontrado o null si no lo encuentra.
	 */
	private T findCuadratic(T elem) {
		if (elem == null) {
			return null;
		}

		int it = 0;
		int pos = fCuadratic(elem, it);

		while (it != getSize() * getSize()) {
			if (associativeArray[pos].getStatus() == 1 && associativeArray[pos].getInfo().equals(elem)) {
				return associativeArray[pos].getInfo();
			}
			++it;
			pos = fCuadratic(elem, it);
		}

		return null;
	}

	/**
	 * Metodo auxiliar para buscar mediante la funcion de exploracion de doble dispersion
	 * @param elem Elemento a añadir 
	 * @return El objeto encontrado o null si no lo encuentra.
	 */
	private T findDouble(T elem) {
		if (elem == null) {
			return null;
		}

		int it = 0;
		int pos = fDouble(elem, it);

		while (it != getSize() * getSize()) {
			if (associativeArray[pos].getStatus() == 1 && associativeArray[pos].getInfo().equals(elem)) {
				return associativeArray[pos].getInfo();
			}
			++it;
			pos = fDouble(elem, it);
		}

		return null;
	}

	/**
	 * Elimina un elemento de la tabla hash
	 * @param Elemento a eliminar 
	 * @return 0 si lo elimina con exito, -1 si no existe, -2 si el parametro es invalido 
	 */
	@Override
	public int remove(T elem) {
		int expl = getExploration();
		int aux = -2;
		if (expl == LINEAL) {
			aux = removeLineal(elem);
		}
		if (expl == CUADRATICA) {
			aux = removeCuadratic(elem);
		}
		if (expl == DOBLEHASH) {
			aux = removeDouble(elem);
		}
		inverseReDispersion();
		return aux;
	}

	/**
	 * Metodo auxiliar para eliminar mediante la funcion de exploracion lineal
	 * @param elem Elemento a añadir 
	 * @return 0 si lo elimina con exito, -1 si no existe, -2 si el parametro es invalido 
	 */
	private int removeLineal(T elem) {
		if (elem == null) {
			return -2;
		}

		if (find(elem) == null) {
			return -1;
		}

		int it = 0;
		int pos = fLineal(elem, it);

		while (it != getSize() - 1) {
			if (associativeArray[pos].getStatus() == 1 && associativeArray[pos].getInfo().equals(elem)) {
				associativeArray[pos].remove();
				// System.out.println(associativeArray[pos].getStatus());
				--numElems;
				return 0;
			}
			++it;
			pos = fLineal(elem, it);
		}
		return -2;
	}

	/**
	 * Metodo auxiliar para eliminar mediante la funcion de exploracion cuadratica
	 * @param elem Elemento a añadir 
	 * @return 0 si lo elimina con exito, -1 si no existe, -2 si el parametro es invalido 
	 */
	private int removeCuadratic(T elem) {

		if (elem == null) {
			return -2;
		}

		if (find(elem) == null) {
			return -1;
		}

		int it = 0;
		int pos = fCuadratic(elem, it);

		while (it != getSize() * getSize()) {
			if (associativeArray[pos].getStatus() == 1 && associativeArray[pos].getInfo().equals(elem)) {
				associativeArray[pos].remove();
				// System.out.println(associativeArray[pos].getStatus());
				--numElems;
				return 0;
			}
			++it;
			pos = fCuadratic(elem, it);
		}
		return -2;
	}

	/**
	 * Metodo auxiliar para eliminar mediante la funcion de exploracion de doble dispersion 
	 * @param elem Elemento a añadir 
	 * @return 0 si lo elimina con exito, -1 si no existe, -2 si el parametro es invalido 
	 */
	private int removeDouble(T elem) {

		if (elem == null) {
			return -2;
		}

		if (find(elem) == null) {
			return -1;
		}

		int it = 0;
		int pos = fDouble(elem, it);

		while (it != getSize() * getSize()) {
			if (associativeArray[pos].getStatus() == 1 && associativeArray[pos].getInfo().equals(elem)) {
				associativeArray[pos].remove();
				// System.out.println(associativeArray[pos].getStatus());
				--numElems;
				return 0;
			}
			++it;
			pos = fDouble(elem, it);
		}
		return -2;
	}

	/**
	 * Realiza una dispersion directa si el factor de carga actual es mayor que el
	 * fcUp. 
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected boolean reDispersion() { 

		double nElem = getNumOfElems();
		double size = getSize();
		double fc = nElem / size;
		// System.out.println(fc);

		if (fc <= fcUP) {
			return false;
		}

		int nuevoTam = nextPrimeNumber(getSize() * 2); // Se calcula nuevo tamaño
		hashSize = nuevoTam; // Se le asigna a la hash
		HashNode<T> aux[] = associativeArray.clone(); // Se copia el vector de elementos
		associativeArray = (HashNode<T>[]) new HashNode[nuevoTam]; // Se inicializa con el nuevo tamaño
		numElems = 0; // Inicializa numero de elementos
		inicializarHash();
		for (int i = 0; i < aux.length; i++) { // Se van añadiendo a la nueva hash con el tamaño cambiado
			if (aux[i].getStatus() == 1) {
				add(aux[i].getInfo());
				// System.out.println(toString());
			}

		}
		return true;
	}

	/**
	 * Realiza una dispersion inversa si el factor de carga actual es menor que el
	 * fcDown.
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected boolean inverseReDispersion() { 
		double nElem = getNumOfElems();
		double size = getSize();
		double fc = nElem / size;

		if (fc >= fcDown) {
			return false;
		}

		int nuevoTam = previousPrimeNumber(getSize() / 2);
		hashSize = nuevoTam;
		HashNode<T> aux[] = associativeArray.clone();
		associativeArray = (HashNode<T>[]) new HashNode[nuevoTam];
		inicializarHash();
		numElems = 0;
		for (int i = 0; i < aux.length; i++) {
			if (aux[i].getStatus() == 1) {
				add(aux[i].getInfo());
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder();
		for (int i = 0; i < getSize(); i++) {
			cadena.append(associativeArray[i]);
			cadena.append(";");
		}
		cadena.append("[Size: ");
		cadena.append(getSize());
		cadena.append(" Num.Elems.: ");
		cadena.append(getNumOfElems());
		cadena.append("]");
		return cadena.toString();
	}
}
