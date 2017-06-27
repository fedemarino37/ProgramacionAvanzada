
public class Floyd {
	
	private Grafo grafo;
	
	public Floyd(Grafo grafo) {
		this.grafo = grafo;
	}
	
	public void resolver() {
		int orden = grafo.orden;
		int a[][] = new int[orden + 1][orden + 1];
		for (int i = 1; i <= orden; i++) {
			for (int j = 1; j <= orden; j++) {
				a[i][j] = i == j ? 0 : grafo.getCosto(i, j);
			}
		}
		
		for (int k = 1; k <= orden; k++) {
			for (int i = 1; i <= orden; i++) {
				for (int j = 1; j <= orden; j++) {
					if (a[i][k] + a[k][j] < a[i][j])
						a[i][j] = a[i][k] + a[k][j]; 
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