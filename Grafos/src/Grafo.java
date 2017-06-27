import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Grafo {
	
	private int[][] adj;
	protected int orden; 
	protected final int INF = 987654321;
	
	public Grafo(int orden) {
		this.orden = orden;
		this.adj = new int[orden + 1][orden + 1];
	}
	
	public Grafo(File file) {
		try (Scanner scanner = new Scanner(file)) {
			String[] datosGrafo = scanner.nextLine().split(" ");
			this.orden = Integer.parseInt(datosGrafo[0]);
			int cantAristas = Integer.parseInt(datosGrafo[1]);
			this.adj = new int[orden + 1][orden + 1];
			for (int i = 0; i < cantAristas; i++) {
				String[] nodo = scanner.nextLine().split(" ");
				int u = Integer.parseInt(nodo[0]);
				int v = Integer.parseInt(nodo[1]);
				int w = Integer.parseInt(nodo[2]);
				this.adj[u][v] = w;
				this.adj[v][u] = w;
			}
			for (int i = 1; i <= this.orden; i++) {
				for (int j = 1; j <= this.orden; j++) {
					if (this.adj[i][j] == 0) {
						if (i != j) 
							this.adj[i][j] = INF;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getCosto(int u, int v) {
		return this.adj[u][v];
	}
	
	public void setCosto(int u, int v, int w) {
		this.adj[u][v] = w;
		this.adj[v][u] = w;
	}
	
	public void print() {
		for (int i = 1; i <= orden; i++) {
			for (int j = 1; j <= orden; j++) {
				System.out.print(adj[i][j] + " ");
			}
			System.out.println();
		}
	}
}