import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class Dijsktra {
	
	private Grafo grafo;
	
	public Dijsktra(Grafo grafo) {
		this.grafo = grafo;
	}
	
	public void resolver(int s) {
		int orden = grafo.orden;
		Set<Integer> vs = new TreeSet<>();
		int[] d = new int[orden + 1];
		int[] p = new int[orden + 1];
		for (int i = 1; i <= orden; i++) {
			d[i] = grafo.INF;
			p[i] = -1;
			vs.add(i);
		}
		d[s] = 0;
		
		while (!vs.isEmpty()) {
			// Busco w E V-S / d[w] sea mínima
			Integer w = 0;
			int minDist = grafo.INF;
			for (Integer vertice : vs) {
				if (d[vertice] < minDist) {
					minDist = d[vertice];
					w = vertice;
				}
			}
			vs.remove(w);
			
			for (Integer i : vs) {
				int distCandidata = d[w] + grafo.getCosto(w, i);
				if (d[i] > distCandidata) {
					d[i] = distCandidata;
					p[i] = w;
				}
			}
		}
		System.out.println("Distancias: " + Arrays.toString(d));
		System.out.println("Predecesores: " + Arrays.toString(p));
	}
	
	public void resolverPQ(int s) {
		int orden = grafo.orden;
		int[] d = new int[orden + 1];
		int[] p = new int[orden + 1];
		PriorityQueue<Pair> queue = new PriorityQueue<>();
		
		for (int i = 1; i <= orden; i++) {
			d[i] = grafo.INF;
			p[i] = -1;
		}
		d[s] = 0;
		queue.add(new Pair(s, 0));
		
		while (!queue.isEmpty()) {
			Pair w = queue.poll();
			if (w.distancia > d[w.vertice]) continue; // como un visited[w] - Evito volver a procesar nodos ya terminados
			for (int i = 1; i <= orden; i++) {
				int distCandidata = d[w.vertice] + grafo.getCosto(w.vertice, i);
				if (d[i] > distCandidata) {
					d[i] = distCandidata;
					queue.add(new Pair(i, d[i]));
					p[i] = w.vertice;
				}
			}
		}
		System.out.println("Distancias: " + Arrays.toString(d));
		System.out.println("Predecesores: " + Arrays.toString(p));
	}
	
	private class Pair implements Comparable<Pair> {
        Integer vertice;
        Integer distancia;

        public Pair(Integer vertice, Integer distancia) {
            this.vertice = vertice;
            this.distancia = distancia;
        }

        @Override
        public int compareTo(Pair p) {
            return (this.distancia - p.distancia);
        }
    }
}
