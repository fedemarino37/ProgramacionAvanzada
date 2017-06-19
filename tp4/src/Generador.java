import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generador {
	public static GrafoNDNP generarNPartito(int orden, int particion) {
		GrafoNDNP grafo = new GrafoNDNP(orden);
		int grupo = 1;
		int vecGrupos[] = new int[orden + 1]; // el cero lo voy a ignorar

		// armo los grupos
		for (int i = 1; i <= orden; i++) {
			vecGrupos[i] = grupo;
			if (grupo < particion) {
				grupo++;
			} else {
				grupo = 1;
			}
		}

		// Si los nodos partenecen a grupos distintos los uno
		for (int i = 1; i < orden; i++) {
			for (int j = i; j <= orden; j++) {
				if (vecGrupos[i] != vecGrupos[j]) {
					grafo.setArista(i, j);
				}
			}
		}
		return grafo;
	}

	public static GrafoNDNP generarRandomPorcentaje(int orden, int porcentaje) {
		GrafoNDNP grafo = new GrafoNDNP(orden);
		List<int[]> lista = new ArrayList<int[]>();
		Random rnm = new Random();
		// armo una lista con todas las aristas posibles
		for (int i = 1; i < orden; i++) {
			for (int j = i; j <= orden; j++) {
				lista.add(new int[] { i, j });
			}
		}

		// saco de la lista, en forma aleatoria, x% de aristas
		int tamaIni = lista.size();
		for (int i = 1; i <= tamaIni * porcentaje / 100; i++) {
			int randomIdx = rnm.nextInt(lista.size());
			int[] coord = lista.remove(randomIdx);
			grafo.setArista(coord[0], coord[1]);
		}
		return grafo;
	}

	public static GrafoNDNP generarRandomProbabilidad(int orden, double probabilidad) {
		GrafoNDNP grafo = new GrafoNDNP(orden);
		for (int i = 1; i < orden; i++) {
			for (int j = i; j <= orden; j++) {

				double rand = Math.random();
				if (rand <= probabilidad) {
					grafo.setArista(i, j);
				}
			}
		}
		return grafo;
	}

	public static GrafoNDNP generarRegular(int orden, int grado) throws Exception {
		GrafoNDNP grafo = new GrafoNDNP(orden);
		if (grado >= orden) {
			throw new Exception("No se puede generar un grafo de grado mayor a orden - 1");
		}

		if ((orden % 2) == 1 && (grado % 2) == 1) {
			throw new Exception("No se puede generar un grafo de grado y orden impar");
		}

		if (grado % 2 == 1) {
			adyacenciaGrado1(grafo);
		}

		for (int gradAux = 2; gradAux <= grado; gradAux += 2) {
			for (int i = 1; i <= orden; i++) {
				grafo.setArista(i, sumaCircular(orden, i, gradAux / 2));
			}
		}
		return grafo;
	}

	public static GrafoNDNP generarRegularPorcentaje(int orden, int porcentaje) throws Exception {
		GrafoNDNP grafo = new GrafoNDNP(orden);
		int grado = Math.round(porcentaje * orden / 100);
		if (grado >= orden) {
			throw new Exception("No se puede generar un grafo de grado mayor a orden - 1");
		}

		if ((orden % 2) == 1 && (grado % 2) == 1) {
			grado--;
		}

		if (grado % 2 == 1) {
			adyacenciaGrado1(grafo);
		}

		for (int gradAux = 2; gradAux <= grado; gradAux += 2) {
			for (int i = 1; i <= orden; i++) {
				grafo.setArista(i, sumaCircular(orden, i, gradAux / 2));
			}
		}
		return grafo;
	}
	
	private static void adyacenciaGrado1(GrafoNDNP grafo) {
		int orden = grafo.getOrden();
		for(int i = 1; i <= orden / 2; i++) {
			grafo.setArista(i, i + orden / 2);
		}
	}

	private static int sumaCircular(int orden, int inicio, int adicion) {
		int aux = inicio + adicion;
		if (aux > orden) {
			return aux - orden;
		}
		return aux;
	}
}
