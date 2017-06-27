import java.util.LinkedList;
import java.util.Stack;

public class DFS {
	
	private Grafo grafo;
	
	public DFS(Grafo grafo) {
		this.grafo = grafo;
	}
	
	public void recorrer(int s) {
		int orden = grafo.orden;
		boolean[] visited = new boolean[orden + 1];
		Stack<Integer> stack = new Stack<>();
		
		stack.push(s);
		visited[s] = true;
		
		while (!stack.isEmpty()) {
			int u = stack.pop();
			System.out.println(u + " ");
			for (int v = 1; v <= orden; v++) {
				if (grafo.getCosto(u, v) == grafo.INF) continue; // No son adyacentes
				if (!visited[v]) {
					stack.push(v);
					visited[v] = true;
				}
			}
		}
	}
}
