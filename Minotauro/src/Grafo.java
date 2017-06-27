import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Grafo {
	private int[][] adj;
	private int descansos;
	
	public Grafo(File entrada) {
		try (Scanner scanner = new Scanner(entrada)) {
			descansos = Integer.parseInt(scanner.nextLine());
			this.adj = new int[descansos + 1][descansos + 1];
			
			for (int i = 1; i <= descansos; i++) {
				String[] datos = scanner.nextLine().split(" ");
				for (int j = 1; j <= descansos; j++) {
					this.adj[i][j] = Integer.parseInt(datos[j - 1]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resolverPrim() {
		List<Arista> mst = new ArrayList<>();
		Set<Integer> s = new HashSet<>();
		Set<Integer> vs = new HashSet<>();
		for (int i = 2; i <= descansos; i++) {
			vs.add(i);
		}
		s.add(1);
		while (!vs.isEmpty()) {
			int minCosto = Integer.MAX_VALUE / 2;
			int verticeVS = -1;
			int verticeS = -1;
			for (Integer u : s) {
				for (Integer v : vs) {
					if (adj[u][v] < minCosto) {
						minCosto = adj[u][v];
						verticeS = u;
						verticeVS = v;
					}
				}
			}
			s.add(verticeVS);
			vs.remove(verticeVS);
			mst.add(new Arista(verticeS, verticeVS, minCosto));
		}
		
		System.out.println(mst.size());
		int costoTotal = 0;
		for (Arista arista : mst) {
			System.out.println(arista);
			costoTotal += arista.w;
		}
		System.out.println("Costo del árbol: " + costoTotal);
	}
	
	public void resolverKruskal() {
		List<Arista> mst = new ArrayList<>();
		UnionFind uf = new UnionFind(descansos);
		PriorityQueue<Arista> pq = new PriorityQueue<>();
		for (int i = 1; i <= descansos; i++) {
			for (int j = 1; j <= descansos; j++) {
				pq.add(new Arista(i, j, adj[i][j]));
			}
		}
		
		while (!pq.isEmpty()) {
			Arista arista = pq.poll();
			if (!uf.connected(arista.u, arista.v)) {
				mst.add(arista);
				uf.union(arista.u, arista.v);
			}
		}
		
		System.out.println(mst.size());
		int costoTotal = 0;
		for (Arista arista : mst) {
			System.out.println(arista);
			costoTotal += arista.w;
		}
		System.out.println("Costo del árbol: " + costoTotal);
	}
	
	private class Arista implements Comparable<Arista> {
		private int u;
		private int v;
		private int w;
		
		public Arista(int desde, int hasta, int costo) {
			u = desde;
			v = hasta;
			w = costo;
		}

		@Override
		public String toString() {
			return u + " " + v + " " + w;
		}

		@Override
		public int compareTo(Arista otra) {
			return (this.w - otra.w);
		}
	}
}
