
public class Main {
	public static void main(String[] args) {
		int[] a = new int[] { 8, 5, 20, 3, 4, 8, 4, 10, 8, 6, 5, 6, 2, 4, 11, 7 };
		System.out.println(LIS(a));
	}
	
	public static int LIS(int[] a){
		int n = a.length;
		int[] aux = new int[n];
		for (int i = 0; i < n; i++) { aux[i] = 1; }
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (a[j] < a[i] && 1 + aux[j] > aux[i])
					aux[i] = aux[j] + 1;
			}
		}
		int max = 0;
		for (int i = 0; i < n; i++) {
			if (aux[i] > max)
				max = aux[i];
		}
		return max;
	}
	
//	public static int LISRecursivo(int[] a, int n) {
//		if (n == 0) return 1;
//		int max = -1;
//		for (int i = 1; i < n; i++) {
//			int valor = LISRecursivo(a, n - i);
//			if (valor > max) max = valor;
//		}
//	}
}
