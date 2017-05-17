import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class MovimientoAlfil {
	private int[][] tablero;
	private int[][] distanciaA;
	private int n;
	private final int INF = 999999999;
	
	private Posicion a;
	private Posicion b;
	
	public MovimientoAlfil(String path) {
		try (Scanner scanner = new Scanner(new File(path))){
			this.n = Integer.parseInt(scanner.nextLine());
			this.tablero = new int[n+1][n+1];
			this.distanciaA = new int[n+1][n+1];
			for (int i = 1; i <= n; i++) {
				String[] fila = scanner.nextLine().split(" ");
				for (int j = 1; j <= n; j++) {
					this.tablero[i][j] = Integer.parseInt(fila[j - 1]);
					this.distanciaA[i][j] = INF;
				}
			}
			String[] posAlfil = scanner.nextLine().split(" ");
			a = new Posicion(Integer.parseInt(posAlfil[0]), Integer.parseInt(posAlfil[1]));
			String[] posDestino = scanner.nextLine().split(" ");
			b = new Posicion(Integer.parseInt(posDestino[0]), Integer.parseInt(posDestino[1]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public int resolver() {
		if (!alcanzable(a, b)) return -1;
		return BFS();
	}
	
	private int BFS() {
		Queue<Posicion> queue = new LinkedList<Posicion>();
		queue.add(this.a);
		this.distanciaA[a.getX()][a.getY()] = 0;
		
		while (!queue.isEmpty()) {
			Posicion v = queue.poll();
			int distanciaHastaV = distanciaA[v.getX()][v.getY()];
			if (v.equals(b)) {
				return distanciaHastaV; 
			}
			
			for (Posicion posicion : getPosicionesAlcanzables(v)) {
				// Si todavía no visité el nodo, lo visito
				if (distanciaA[posicion.getX()][posicion.getY()] == INF) {
					queue.add(posicion);
					distanciaA[posicion.getX()][posicion.getY()] = distanciaHastaV + 1;
				}
			}
		}
		
		return -1;
	}
	
	private List<Posicion> getPosicionesAlcanzables(Posicion p) {
		List<Posicion> posicionesAlcanzables = new LinkedList<Posicion>();
		int i = p.getX(), j = p.getY();
		// hacia arriba a la derecha
		while (--i >= 1 && ++j <= n && !esObstaculo(i, j)) {
			posicionesAlcanzables.add(new Posicion(i, j));
		}
		
		i = p.getX(); j = p.getY();
		// hacia abajo a la izquierda
		while (++i <= n && --j >= 0 && !esObstaculo(i, j)) {
			posicionesAlcanzables.add(new Posicion(i, j));
		}
		
		i = p.getX(); j = p.getY();
		// Hacia abajo a la derecha
		while (++i <= n && ++j <= n && !esObstaculo(i, j)) {
			posicionesAlcanzables.add(new Posicion(i, j));
		}
		
		i = p.getX(); j = p.getY();
		// Hacia arriba a la izquierda
		while (--i >= 0 && --j >= 0 && !esObstaculo(i, j)) {
			posicionesAlcanzables.add(new Posicion(i, j));
		}
		
		return posicionesAlcanzables;
	}
	
	// Si las dos posiciones están en el mismo "color",
	// entonces se puede llegar de a hasta b
	// El color está definido según la paridad de la celda
	private boolean alcanzable(Posicion a, Posicion b) {
		boolean paridadA = (a.getX() - a.getY()) % 2 == 0;
		boolean paridadB = (b.getX() - b.getY()) % 2 == 0;
		return paridadA == paridadB;
	}
	
	private boolean esObstaculo(int x, int y) {
		return tablero[x][y] == 1;
	}
}
