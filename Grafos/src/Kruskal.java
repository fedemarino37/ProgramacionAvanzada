import java.util.PriorityQueue;

public class Kruskal {
	
	private Grafo grafo;
	private UnionFind unionFind;
	
	public Kruskal(Grafo grafo) {
		this.grafo = grafo;
		this.unionFind = new UnionFind(grafo.orden);
	}
	
	public void getMST() {
		int orden = grafo.orden;
		int costo = 0;
		Grafo mst = new Grafo(grafo.orden);
		PriorityQueue<Arista> pq = new PriorityQueue<>();
		for (int i = 1; i <= orden; i++) {
			for (int j = 1; j <= orden; j++) {
				int w = grafo.getCosto(i, j); 
				if (w != grafo.INF)
					pq.add(new Arista(i, j, w));
			}
		}
		while (!pq.isEmpty()) {
			Arista arista = pq.poll();
			if (!unionFind.connected(arista.u, arista.v)) {
				costo += arista.w;
				mst.setCosto(arista.u, arista.v, arista.w);
				unionFind.union(arista.u, arista.v);
			}
		}
		mst.print();
		System.out.println("Costo: " + costo);
	}
	
	private class Arista implements Comparable<Arista> {
        private int u;
        private int v;
        private int w;

        public Arista(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Arista a) {
            return (this.w - a.w);
        }
    }
}
