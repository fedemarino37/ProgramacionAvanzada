package comparadorLetras;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class JuegoConLetras {

	private char [][]matriz;
	private int dimension;
	private int cantpalabras;
	private List<Palabra> palabras;
	
	public JuegoConLetras(String path) {
		try (Scanner scanner = new Scanner(new File(path));){
			String[] primeraLinea = scanner.nextLine().split(" ");
			this.dimension  = Integer.parseInt(primeraLinea[0]);
			this.cantpalabras = Integer.parseInt(primeraLinea[1]);
			palabras = new LinkedList<Palabra>();
			
			this.matriz = new char[dimension][dimension];
			for (int k = 0; k < dimension; k++){
				matriz[k] = scanner.nextLine().toCharArray();				
			}
			
			for (int k = 0; k < cantpalabras; k++){
				String palabra = scanner.nextLine();
				palabras.add(new Palabra(palabra, k + 1));
			}
			
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public static void generarAleatorio(String path) {
		int d = 100;
		int P = 10000;
		int largoPalabra = 20;
		
		Random r = new Random();
		char[] abecedario = new char[26];
		for(int i = 0; i < abecedario.length; i++){
			abecedario[i] = (char)(i + 97);
		}
		
		try (PrintWriter printWriter = new PrintWriter(new FileWriter(path))) {
			printWriter.println(d + " " + P);
			for (int i = 0; i < d; i++) {
				String aux = "";
				for (int j = 0; j < d; j++) {
					aux += abecedario[r.nextInt(abecedario.length)];
				}
				printWriter.println(aux);
			}
			String palabra = "";
			for (int i = 0; i < 20; i++) {
				palabra += "a";
			}
			for (int i = 0; i < P; i++) {
				printWriter.println(palabra);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Map<Palabra, String> resolver() {
		Map<Palabra, String> resultados = new Hashtable<Palabra, String>();
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				String textoH = this.getPalabraHorizontal(i, j);
				String textoV = this.getPalabraVertical(i, j);
				int largoH = textoH.length();
				int largoV = textoV.length();
				
				for (Palabra palabra : palabras) {
					if (resultados.containsKey(palabra)) continue;
					int largoPalabra = palabra.getPalabra().length();
					String sentido = "";
					// Horizontal
					if (largoPalabra <= largoH) {
						if (textoH.startsWith(palabra.getPalabra())) { 
							sentido = "E";
						} else if (textoH.startsWith(palabra.getInversa())) {
							sentido = "O";
						}
					}
					// Vertical
					if (sentido.isEmpty() && largoPalabra <= largoV) {
						if (textoV.startsWith(palabra.getPalabra())) { // S
							sentido = "S";
						} else if (textoV.startsWith(palabra.getInversa())) {
							sentido = "N";
						}
					}
					if (!sentido.isEmpty()) {
						resultados.put(palabra, sentido);
					}
				}
			}
		}
		return resultados;
	}
	
	private String getPalabraVertical(int filaDesde, int columna) {
		String resultado = "";
		for (int i = filaDesde; i < dimension; i++) {
			resultado += matriz[i][columna];
		}
		return resultado;
	}
	
	private String getPalabraHorizontal(int fila, int columnaDesde) {
		String resultado = "";
		for (int j = columnaDesde; j < dimension; j++) {
			resultado += matriz[fila][j];
		}
		return resultado;
	}
}
