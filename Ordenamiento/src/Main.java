import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int[] a = new int[] { 8, 3, 1, 6, 5, 3, 2 };
		System.out.println(Arrays.toString(Ordenamiento.selectSort(a)));
		System.out.println(Arrays.toString(Ordenamiento.bubbleSort(a)));
	}

}
