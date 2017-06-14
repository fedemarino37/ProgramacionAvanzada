import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class Grafo {
	int cantNodos;
	int cantAristas;
	int porcentajeAdyacencia;
	int gradoMinimo;
	int gradoMaximo;
	
	int[][] grafo;
	
	public Grafo(String path) {
		try (Scanner scanner = new Scanner(new File(path))) {
			String[] datosGrafo = scanner.nextLine().split(" ");
			
			cantNodos = Integer.parseInt(datosGrafo[0]);
			cantAristas = Integer.parseInt(datosGrafo[1]);
			porcentajeAdyacencia = Integer.parseInt(datosGrafo[2]);
			gradoMinimo = Integer.parseInt(datosGrafo[3]);
			gradoMaximo = Integer.parseInt(datosGrafo[4]);
			
			grafo =  new int[cantNodos][cantNodos];
			
			for(int i = 0; i < cantAristas; i++) {
				String[] nodo = scanner.nextLine().split(" ");
				int x = Integer.parseInt(nodo[0]) - 1;
				int y = Integer.parseInt(nodo[1]) - 1;
				grafo[x][y] = 1;
				grafo[y][x] = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void print() {
		for(int i = 0; i < cantNodos; i++) {
			System.out.println(Arrays.toString(grafo[i]));
		}
	}
}
