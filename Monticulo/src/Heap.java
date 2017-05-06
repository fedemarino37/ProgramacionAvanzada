
public class Heap {
	private int[] elements;
	private int tope;
	private final int DEFAULT_SIZE = 30;

	public Heap() {
		this.elements = new int[DEFAULT_SIZE];
		this.tope = -1;
	}

	public void insert(int n) {
		elements[++tope] = n;
		int i = tope;
		while (hasParent(i) && elements[parent(i)] > elements[i]) {
			swap(parent(i), i);
			i = parent(i);
		}
	}

	@Override
	public String toString() {
		String result = elements[0] + "";
		for (int i = 1; i <= tope; i++) {
			result += ", " + elements[i];
		}
		return result;
	}

	public int extractMin() {
		if (tope == -1)
			return Integer.MAX_VALUE;
		if (tope == 0) {
			return elements[tope--];
		}
		int min = elements[0];
		elements[0] = elements[tope--]; // Mando el último como nueva raíz y
										// reduzco el tamaño
		// Y acomodo de arriba hacia abajo
		int i = 0;
		while (hasLeftChild(i)) {
			// Subo el menor de los dos hijos
			int minChild = left(i);
			if (hasRightChild(i) && elements[right(i)] < elements[left(i)]) {
				minChild = right(i);
			}
			if (elements[i] > elements[minChild]) {
				swap(i, minChild);
			} else {
				break;
			}
			i = minChild;
		}

		return min;
	}

	private void swap(int i, int j) {
		int aux = elements[i];
		elements[i] = elements[j];
		elements[j] = aux;
	}

	private boolean hasLeftChild(int i) {
		return left(i) <= tope;
	}

	private boolean hasRightChild(int i) {
		return right(i) <= tope;
	}

	private boolean hasParent(int i) {
		return i > 0; // No es la raíz
	}

	private int parent(int i) {
		return (i - 1) / 2;
	}

	private int left(int i) {
		return (2 * i) + 1;
	}

	private int right(int i) {
		return (2 * i) + 2;
	}
}
