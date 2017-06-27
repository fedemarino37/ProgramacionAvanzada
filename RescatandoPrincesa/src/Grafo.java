import java.io.File;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Grafo {

	private int[] posDragones;
	private int claros;
	private int posPrincesa;
	private int posPrincipe;
	private int[][] adj;
	private final int INF = Integer.MAX_VALUE / 2; 
	
	public Grafo(File entrada) {
		try (Scanner scanner = new Scanner(entrada)) {
			String[] datos = scanner.nextLine().split(" ");
			claros = Integer.parseInt(datos[0]);
			int senderos = Integer.parseInt(datos[1]);
			int dragones = Integer.parseInt(datos[2]);
			this.adj = new int[claros + 1][claros + 1];
			
			datos = scanner.nextLine().split(" ");
			this.posPrincesa = Integer.parseInt(datos[0]);
			this.posPrincipe = Integer.parseInt(datos[1]);
			
			datos = scanner.nextLine().split(" ");
			posDragones = new int[dragones];
			for (int i = 0; i < datos.length; i++) {
				posDragones[i] = Integer.parseInt(datos[i]);
			}
			
			for (int i = 0; i < senderos; i++) {
				datos = scanner.nextLine().split(" ");
				int desde = Integer.parseInt(datos[0]);
				int hasta = Integer.parseInt(datos[1]);
				int largo = Integer.parseInt(datos[2]);
				setCosto(desde, hasta, largo);
			}
			for (int i = 1; i <= claros; i++) {
				for (int j = 1; j <= claros; j++){
					if (this.adj[i][j] == 0 && i != j) { 
						this.adj[i][j] = INF;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void resolver() {
		int[] d = new int[this.claros + 1];
		int[] p = new int[this.claros + 1];
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		
		for (int i = 1; i <= this.claros; i++) {
			d[i] = INF;
			p[i] = -1;
		}
		d[posPrincesa] = 0;
		pq.add(new Pair(0, posPrincesa));
		
		while (!pq.isEmpty()) {
			Pair w = pq.poll();
			if (w.distancia > d[w.vertice]) continue;
			for (int i = 1; i <= claros; i++) {
				int distCandidata = d[w.vertice] + getCosto(w.vertice, i);
				if (d[i] > distCandidata) {
					d[i] = distCandidata;
					pq.add(new Pair(d[i], i));
					p[i] = w.vertice;
				}
			}
		}
		
		if (d[posPrincipe] == INF) {
			System.out.println("No hay camino");
			return;
		}
		for (int posDragon : posDragones) {
			if (d[posDragon] < d[posPrincipe]) {
				System.out.println("Interceptado");
				return;
			}
		}
		// Busco el camino
		int i = posPrincipe;
		String camino = i + " ";
		while (i != posPrincesa) {
			i = p[i];
			camino += i + " ";
		}
		System.out.println(camino);
	}
	
	private void setCosto(int u, int v, int w) {
		this.adj[u][v] = w;
		this.adj[v][u] = w;
	}
	
	private int getCosto(int u, int v) {
		return this.adj[u][v];
	}
	
	private class Pair implements Comparable<Pair> {
        int distancia;
        int vertice;

        public Pair(int distancia, int vertice) {
            this.distancia = distancia;
            this.vertice = vertice;
        }

        @Override
        public int compareTo(Pair p) {
            return (this.distancia - p.distancia);
        }
    }
}
