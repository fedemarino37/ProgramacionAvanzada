import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class JuegoConLetras {
	private char[][] matriz;
	private int dimension;
	private List<String> palabras;
	private int cantPalabras;
	
	public JuegoConLetras(String path) {
		try (Scanner scanner = new Scanner(new File(path))){
			String[] atributos = scanner.nextLine().split(" ");
			this.dimension = Integer.parseInt(atributos[0]);
			this.cantPalabras = Integer.parseInt(atributos[1]);
			matriz = new char[dimension][dimension];
			palabras = new LinkedList<String>();
			for (int i = 0; i < dimension; i++) {
				matriz[i] = scanner.nextLine().toCharArray();
			}
			
			for (int i = 0; i < cantPalabras; i++) {
				palabras.add(scanner.next());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void resolver() {
		int nroPalabra = 1;
		for (String palabra : this.palabras) {
			int largo = palabra.length();
			boolean encontrada = false;
			String orientacion = "";
			for (int i = 0; i < dimension && !encontrada; i++) {
				for (int j = 0; j < dimension && !encontrada; j++) {
					boolean entraH = largo <= (dimension - j);
					boolean entraV = largo <= (dimension - i);
					if (entraH) orientacion = chequearHorizontal(i, j, palabra, largo);
					if (orientacion.isEmpty() && entraV) orientacion = chequearVertical(i, j, palabra, largo);
					encontrada = !orientacion.isEmpty();
				}
			}
			if (encontrada) {
				System.out.println(nroPalabra + " " + orientacion);
			}
			++nroPalabra;
		}
	}
	
	private String chequearHorizontal(int i, int j, String palabra, int largo) {
		boolean este = true;
		boolean oeste = true;
		for (int k = 0; k < largo && (este || oeste); k++) { 
			este &= (this.matriz[i][j + k] == palabra.charAt(k)); 
			oeste &= (this.matriz[i][j + k] == palabra.charAt(largo - 1 - k));
		}
		return este ? "E" : oeste ? "O" : "";
	}
	
	private String chequearVertical(int i, int j, String palabra, int largo) {
		boolean sur = true;
		boolean norte = true;
		for (int k = 0; k < largo && (sur || norte); k++) {
			sur &= (this.matriz[i + k][j] == palabra.charAt(k));
			norte &= (this.matriz[i + k][j] == palabra.charAt(largo - 1 - k));
		}
		return sur ? "S" : norte ? "N" : "";
	}	
}