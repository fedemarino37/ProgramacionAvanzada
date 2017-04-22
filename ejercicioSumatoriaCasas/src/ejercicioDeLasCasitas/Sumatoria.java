package ejercicioDeLasCasitas;
import java.lang.Math;

public class Sumatoria {
	private int[] casas;
	private int n;
	
	public Sumatoria(int n){
		this.n = n;
		casas = new int[n+1];
		for(int i = 1; i <= n; i++){
			casas[i] = i;
		}
	}
	
	public int posicionIntermedia(){
		if (n < 8) return -1;
		int S3 = ((n * n) + n) / 2; // Sumatoria desde 1 hasta n
		for (int i = n/2; i < n; i++){
			// Sumatorias excluyendo I
			int S1 = 0;
			int S2 = 0;
			S1 = (i * (i - 1)) / 2;
			S2 = S3 - S1 - i;
			if (S1 == S2) return i;
		}
		return -1;
	}
	
	public int posicionIntermedia2(){
		if (n < 8) return -1;
		
		int i = (int)Math.sqrt( ( n*(n-1) ) / 2);
		if(i != 0 ) return i;
		return -1;
	}
	
}
