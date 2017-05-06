
public class Ordenamiento {

	// Busca el mínimo y lo pone en su lugar
	public static int[] selectSort(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			int minIdx = i;
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] < a[minIdx])
					minIdx = j;
			}
			swap(a, i, minIdx);
		}
		return a;
	}

	public static int[] bubbleSort(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			boolean huboIntercambio = false;
			for (int j = 0; j < a.length - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					swap(a, j, j+1);
					huboIntercambio = true;
				}
			}
			if (!huboIntercambio) break; // Ya está ordenado
		}
		return a;
	}

	private static void swap(int[] a, int i, int j) {
		int aux = a[i];
		a[i] = a[j];
		a[j] = aux;
	}
}
