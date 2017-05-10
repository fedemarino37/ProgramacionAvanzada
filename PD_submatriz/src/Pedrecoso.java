
public class Pedrecoso {
	private int[][] matriz;
	private int filas;
	private int columnas;
	private int filasCasa;
	private int columnasCasa;
	int [][] auxMatriz;
	
	public Pedrecoso(int[][] matriz, int filasCasa, int columnasCasa) {
		this.matriz = matriz;
		this.filas = matriz.length;
		this.columnas = matriz[0].length;
		this.filasCasa = filasCasa;
		this.columnasCasa = columnasCasa;
	}
	
	public void resolver() {
		auxMatriz = new int [filas][columnas];
		
		for(int i = 0; i < filas; i++) {
			for(int j = 0; j < columnas; j++) {
				auxMatriz[i][j] = matriz[i-1][j] + matriz[i][j-1] - matriz[i-1][j-1];
			}
		}
		
		for(int i = 0; i < filas; i++) {
			for(int j = 0; j < columnas; j++) {
				
			}
		}
	}
}
