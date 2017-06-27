import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Subterraneo {
	private int lineas;
	private int estaciones;
	private int desde;
	private int hasta;
	private Map<Integer, Set<Integer>> recorridos;
	private boolean adj[][];
	
	public Subterraneo(File file) {
		try (Scanner scanner = new Scanner(file)) {
			String[] datos = scanner.nextLine().split(" ");
			lineas = Integer.parseInt(datos[0]);
			adj = new boolean[lineas + 1][lineas + 1];
			estaciones = Integer.parseInt(datos[1]);
			recorridos = new HashMap<>();
			for (int i = 1; i <= lineas; i++) {
				datos = scanner.nextLine().split(" ");
				int estacionesRecorrido = Integer.parseInt(datos[0]);
				Set<Integer> recorridoI = new HashSet<>();
				for (int j = 1; j <= estacionesRecorrido; j++) {
					int estacion = Integer.parseInt(datos[j]);
					recorridoI.add(estacion);
					
					// Verifico si alguna de las líneas anteriores conecta con esta
					for (Integer linea : recorridos.keySet()) {
						Set<Integer> recorrido = recorridos.get(linea);
						if (recorrido.contains(estacion)) {
							adj[i][linea] = true;
							adj[linea][i] = true;
						}
					}
				}
				recorridos.put(i, recorridoI);
			}
			datos = scanner.nextLine().split(" ");
			desde = Integer.parseInt(datos[0]);
			hasta = Integer.parseInt(datos[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void resolver() {

	}
}
