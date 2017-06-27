
public class Warshall {

	private Grafo grafo;
	
	public Warshall(Grafo grafo) {
		this.grafo = grafo;
	}
	
	public void resolver() {
		int orden = grafo.orden;
		boolean a[][] = new boolean[orden + 1][orden + 1];
		for (int i = 1; i <= orden; i++) {
			for (int j = 1; j <= orden; j++) {
				a[i][j] = grafo.getCosto(i, j) != grafo.INF;
			}
		}
		
		for (int k = 1; k <= orden; k++) {
			for (int i = 1; i <= orden; i++) {
				for (int j = 1; j <= orden; j++) {
					a[i][j] = a[i][j] || (a[i][k] && a[k][j]);
				}
			}
		}
		
		for (int i = 1; i <= orden; i++) {
			for (int j = 1; j <= orden; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}
}
