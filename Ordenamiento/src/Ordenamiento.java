import java.util.Arrays;
import java.util.Random;

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
		return bubbleSort(a, 0, a.length - 1);
	}
	
	public static int[] bubbleSort(int[] a, int begin, int end) {
		for (int i = begin; i < end; i++) {
			boolean huboIntercambio = false;
			for (int j = begin; j < end - i; j++) {
				if (a[j] > a[j + 1]) {
					swap(a, j, j + 1);
					huboIntercambio = true;
				}
			}
			if (!huboIntercambio)
				break; // Ya está ordenado
		}
		return a;
	}

	public static int[] insertionSort(int[] a) {
		// Para cada elem de la parte desordenada
		for (int i = 1; i < a.length; i++) {
			int aux = a[i];
			int j = i - 1;
			// Le hace lugar en la parte ordenada
			while (j >= 0 && a[j] > aux) {
				a[j + 1] = a[j];
				j--;
			}
			// Lo inserta
			a[j + 1] = aux;
		}
		return a;
	}

	public static int[] shellSort(int[] a) {
		int h = 1;
		while ((3 * h) + 1 <= a.length) {
			h = (3 * h) + 1;
		}
		while (h >= 1) {
			for (int i = h; i < a.length; i++) {
				int aux = a[i];
				int j = i - h;
				// Le hace lugar en la parte ordenada
				while (j >= 0 && a[j] > aux) {
					a[j + h] = a[j];
					j -= h;
				}
				// Lo inserta
				a[j + h] = aux;
			}
			h = (h - 1) / 3;
		}
		return a;
	}

	public static int[] quickSort(int[] a, boolean pivotMediana) {
		if (pivotMediana) 
			quickSortMediana(a, 0, a.length - 1);
		else 
			quickSortRec(a, 0, a.length - 1);
		return a;
	}

	private static void quickSortRec(int[] a, int begin, int end) {
		if (begin < end) {
			int pivotIdx = particion(a, begin, end);
			quickSortRec(a, begin, pivotIdx - 1);
			quickSortRec(a, pivotIdx + 1, end);
		}
	}
	
	private static int particion(int[] a, int begin, int end) {
		int pivotIdx = (begin + end) / 2;
		int pivot = a[pivotIdx];
		swap(a, pivotIdx, end);
		int i = begin, j = end - 1;
		while (i <= j) {
			while ((i <= end - 1) && (a[i] <= pivot)) 
				i++;
			while ((j >= begin) && (a[j] > pivot)) 
				j--;
			if (i < j) 
				swap(a, i++, j--);
		}
		swap(a, i, end);
		return i;
	}

	private static void quickSortMediana(int[] a, int begin, int end) {
		if (begin < end) {
			int pivotIdx = particionMediana(a, begin, end);
			quickSortMediana(a, begin, pivotIdx - 1);
			quickSortMediana(a, pivotIdx + 1, end);
		}
	}
	
	private static int particionMediana(int[] a, int begin, int end) {
		int pivot = mediana(a, begin, end);
		// Evito los que ya quedaron en su lugar por la mediana
		int i = begin + 1, j = end - 2;
		while (i <= j) {
			while ((i <= end - 2) && (a[i] <= pivot)) 
				i++;
			while ((j >= begin + 1) && (a[j] > pivot)) 
				j--;
			if (i < j) 
				swap(a, i++, j--);
		}
		if ((end - begin) > 2) // si son más de tres elementos
			swap(a, i, end - 1);
		return i;
	}

	private static int mediana(int[] a, int i, int j) {
		int m = (i + j) / 2;
		if (a[i] > a[m])
			swap(a, i, m);
		if (a[i] > a[j])
			swap(a, i, j);
		if (a[m] > a[j])
			swap(a, m, j);

		swap(a, m, j - 1);
		return a[j - 1];
	}

	private static void swap(int[] a, int i, int j) {
		int aux = a[i];
		a[i] = a[j];
		a[j] = aux;
	}
}
