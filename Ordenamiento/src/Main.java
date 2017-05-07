import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int[] a = new int[] { 8, 3, 1, 6, 5, 3, 2 };
		System.out.println("SelectSort");
		System.out.println(Arrays.toString(Ordenamiento.selectSort(a.clone())));
		System.out.println("BubbleSort");
		System.out.println(Arrays.toString(Ordenamiento.bubbleSort(a.clone())));
		System.out.println("InsertionSort");
		System.out.println(Arrays.toString(Ordenamiento.insertionSort(a.clone())));
		System.out.println("ShellSort");
		System.out.println(Arrays.toString(Ordenamiento.shellSort(a.clone())));
		int[] b = new int[] { 8, 38, 4, 7, 14, 5, 2, 12, 4 };
		System.out.println("QuickSort");
		System.out.println(Arrays.toString(Ordenamiento.quickSort(a.clone(), false)));
		System.out.println(Arrays.toString(Ordenamiento.quickSort(b.clone(), false)));
		System.out.println("QuickSort con mediana");
		System.out.println(Arrays.toString(Ordenamiento.quickSort(a.clone(), true)));
		System.out.println(Arrays.toString(Ordenamiento.quickSort(b.clone(), true)));
	}

}
