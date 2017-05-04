import java.util.List;

public class LCS {
	public static int de(int[] x, int[] y, int m, int n){
		if (m == 0 || n == 0) return 0;
		if (x[m - 1] == y[n - 1]) return 1 +  de(x, y, m-1, n-1);
		return Integer.max(de(x, y, m-1, n), de(x, y, m, n-1));
	}
	
	public static int de(char[] x, char[] y, int m, int n){
		if (m == 0 || n == 0) return 0;
		if (x[m - 1] == y[n - 1]) return 1 +  de(x, y, m-1, n-1);
		return Integer.max(de(x, y, m-1, n), de(x, y, m, n-1));
	}
	
	public static int deIterativo(char[] x, char[] y){
		int m = x.length;
		int n = y.length;
		int L[][] = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (x[i - 1] == y[j - 1]) 
					L[i][j] = L[i-1][j-1] + 1;
				else 
					L[i][j] = Integer.max(L[i-1][j], L[i][j-1]);
			}
		}
		return L[m][n];
	}
	
	public static int deIterativo(int[] x, int[] y){
		int m = x.length;
		int n = y.length;
		int L[][] = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (x[i - 1] == y[j - 1]) 
					L[i][j] = L[i-1][j-1] + 1;
				else 
					L[i][j] = Integer.max(L[i-1][j], L[i][j-1]);
			}
		}
		return L[m][n];
	}
	
	public static int deIterativo(List<Integer> x, List<Integer> y, int m, int n) {
		int L[][] = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (x.get(i - 1) == y.get(j - 1)) 
					L[i][j] = L[i-1][j-1] + 1;
				else 
					L[i][j] = Integer.max(L[i-1][j], L[i][j-1]);
			}
		}
		return L[m][n];
	}
}
