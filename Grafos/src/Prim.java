import java.util.HashSet;
import java.util.Set;

public class Prim {

	private Grafo grafo;

	public Prim(Grafo grafo) {
		this.grafo = grafo;
	}

	public void getMST() {
		Set<Integer> s = new HashSet<>();
		Set<Integer> vs = new HashSet<>();
		Grafo mst = new Grafo(grafo.orden);
		int costoTotal = 0;
		for (int i = 2; i <= grafo.orden; i++) {
			vs.add(i);
		}
		s.add(1);

		while (!vs.isEmpty()) {
			Integer w = 0;
			int padreW = -1;
			int minDist = grafo.INF;
			// Busco una arista de S que una con un nodo de V-S
			// y que sea de costo mínimo
			for (Integer u : s) {
				for (Integer v : vs) {
					if (grafo.getCosto(u, v) < minDist) {
						minDist = grafo.getCosto(u, v);
						w = v;
						padreW = u;
					}
				}
			}
			s.add(w);
			vs.remove(w);
			mst.setCosto(w, padreW, minDist);
			costoTotal += minDist;
		}

		mst.print();
		System.out.println("Costo: " + costoTotal);
	}
}
