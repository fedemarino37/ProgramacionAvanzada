import java.io.File;
import java.util.Scanner;

public class ProgramaProbador {
	public static boolean evaluarColoreo(File grafo, File coloreo) {
		try (Scanner scannerGrafo = new Scanner(grafo); Scanner scannerColoreo = new Scanner(coloreo)) {
			// Coloreo
			String[] datos = scannerColoreo.nextLine().split(" ");
			int n = Integer.parseInt(datos[0]);
			int[] color = new int[n + 1];
			for (int i = 0; i < n; i++) {
				datos = scannerColoreo.nextLine().split(" ");
				color[Integer.parseInt(datos[0])] = Integer.parseInt(datos[1]);
			}

			// Grafo
			datos = scannerGrafo.nextLine().split(" ");
			int aristas = Integer.parseInt(datos[1]);
			for (int i = 0; i < aristas; i++) {
				datos = scannerGrafo.nextLine().split(" ");
				int nodo1 = Integer.parseInt(datos[0]);
				int nodo2 = Integer.parseInt(datos[1]);
				if (color[nodo1] == color[nodo2])
					return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
