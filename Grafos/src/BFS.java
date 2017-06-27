import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
	private Grafo grafo;
	
	public BFS(Grafo grafo) {
		this.grafo = grafo;
	}
	
	public void recorrer(int s) {
		int orden = grafo.orden;
		boolean[] visited = new boolean[orden + 1];
		int[] d = new int[orden + 1];
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(s);
		visited[s] = true;
		
		while (!queue.isEmpty()) {
			int u = queue.poll();
			System.out.println(u + " ");
			for (int v = 1; v <= orden; v++) {
				if (grafo.getCosto(u, v) == grafo.INF) continue; // No son adyacentes
				if (!visited[v]) {
					d[v] = d[u] + 1;
					queue.add(v);
					visited[v] = true;
				}
			}
		}
		System.out.println("Distancias: " + Arrays.toString(d));
	}
}
