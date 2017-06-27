import java.io.File;

public class Main {

	public static void main(String[] args) {
//		Grafo grafo = new Grafo(new File("in/floyd.in"));
//		Dijsktra d = new Dijsktra(grafo);
//		d.resolver(1);
//		d.resolverPQ(1);
//		BFS b = new BFS(grafo);
//		b.recorrer(1);
//		DFS dfs = new DFS(grafo);
//		dfs.recorrer(1);
//		Floyd floyd = new Floyd(grafo);
//		floyd.resolver();
		Grafo grafoMST = new Grafo(new File("in/prim.in"));
		Prim prim = new Prim(grafoMST);
		prim.getMST();
		Kruskal kruskal = new Kruskal(grafoMST);
		kruskal.getMST();
	}
}