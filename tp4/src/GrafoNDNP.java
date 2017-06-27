import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GrafoNDNP {
	private MatrizSimetrica grafo;
	private ArrayList<Nodo> nodos;
	private int orden;
	private int porcentajeAdyacencia = -1;
	private int cantAristas = -1;
	private int gradoMaximo = -1;
	private int gradoMinimo = -1;

	public GrafoNDNP(int orden) {
		this.orden = orden;
		this.grafo = new MatrizSimetrica(this.orden);
	}

	public GrafoNDNP(int orden, MatrizSimetrica grafo, ArrayList<Nodo> nodos) {
		this.orden = orden;
		this.grafo = grafo;
		this.nodos = nodos;
	}

	public GrafoNDNP(File file) {
		try (Scanner scanner = new Scanner(file)) {
			String[] datosGrafo = scanner.nextLine().split(" ");

			this.orden = Integer.parseInt(datosGrafo[0]);
			this.cantAristas = Integer.parseInt(datosGrafo[1]);
			this.porcentajeAdyacencia = Integer.parseInt(datosGrafo[2]);
			this.gradoMaximo = Integer.parseInt(datosGrafo[3]);
			this.gradoMinimo = Integer.parseInt(datosGrafo[4]);

			this.grafo = new MatrizSimetrica(this.orden);

			for (int i = 0; i < cantAristas; i++) {
				String[] nodo = scanner.nextLine().split(" ");
				int x = Integer.parseInt(nodo[0]);
				int y = Integer.parseInt(nodo[1]);
				setArista(x, y);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void guardar(File file) {
		guardar(file, false);
	}

	public void guardar(File file, boolean coloreado) {
		try (PrintWriter printWriter = new PrintWriter(file)) {
			printWriter.println(getOrden() + " " + (coloreado ? getCantColores() + " " : "") + getCantAristas() + " "
					+ getPorcAdyacencia() + " " + getGradoMax() + " " + getGradoMin());
			for (int i = 1; i <= orden; i++) {
				if (coloreado) {
					Nodo nodo = getNodos().get(i - 1);
					printWriter.println(nodo.getId() + " " + nodo.getColor());
				} else {
					for (int j = i + 1; j <= orden; j++) {
						if (sonAdyacentes(i, j)) {
							printWriter.println(i + " " + j);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getOrden() {
		return this.orden;
	}

	public int getGradoMax() {
		if (this.gradoMaximo == -1) {
			int max = 0;
			for (Nodo nodo : getNodos()) {
				if (nodo.getGrado() > max) {
					max = nodo.getGrado();
				}
			}
			this.gradoMaximo = max;
		}
		return this.gradoMaximo;
	}

	public int getGradoMin() {
		if (this.gradoMinimo == -1) {
			int min = this.orden * 2;
			for (Nodo nodo : getNodos()) {
				if (nodo.getGrado() < min) {
					min = nodo.getGrado();
				}
			}
			this.gradoMinimo = min;
		}
		return this.gradoMinimo;
	}

	public int getPorcAdyacencia() {
		if (porcentajeAdyacencia == -1)
			porcentajeAdyacencia = (200 * getCantAristas()) / (orden * (orden - 1));
		return porcentajeAdyacencia;
	}

	public int getCantAristas() {
		if (cantAristas == -1) {
			ArrayList<Nodo> nodos = getNodos();
			int cant = 0;
			for (int i = 1; i <= orden; i++) {
				for (int j = i + 1; j <= orden; j++) {
					if (sonAdyacentes(i, j)) {
						cant++;
					}
				}
			}
			cantAristas = cant;
		}
		return cantAristas;
	}

	public void setArista(int x, int y) {
		this.grafo.setValorArista(x, y, 1);
	}

	public ArrayList<Nodo> getNodos() {
		if (nodos == null) {
			nodos = new ArrayList<Nodo>();
			for (int i = 0; i < this.orden; i++) {
				nodos.add(new Nodo(i + 1, getGradoNodo(i + 1)));
			}
		}
		return this.nodos;
	}

	private int getGradoNodo(int i) {
		int grado = 0;
		for (int j = 1; j <= this.orden; j++) {
			if (sonAdyacentes(i, j)) {
				grado++;
			}
		}
		return grado;
	}

	private void reiniciarColores() {
		for (Nodo nodo : this.nodos) {
			nodo.setColor(0);
		}
	}

	public int getCantColores() {
		int cantColores = 0;
		for (Nodo nodo : this.nodos) {
			if (nodo.getColor() > cantColores) {
				cantColores = nodo.getColor();
			}
		}
		return cantColores;
	}

	private void colorear() {
		reiniciarColores();
		for (Nodo nodo : this.nodos) {
			int color = 1;
			for (Nodo nodoAdy : this.nodos) {
				if (sonAdyacentes(nodo, nodoAdy) && nodoAdy.getColor() == color)
					color++;
			}
			nodo.setColor(color);
		}
	}

	public void colorearAleatorio() {
		Collections.shuffle(getNodos());
		colorear();
	}

	public void colorearWelshPowell() {
		Collections.shuffle(getNodos());
		Collections.sort(getNodos(), Collections.reverseOrder());
		colorear();
	}

	public void colorearMatula() {
		Collections.shuffle(getNodos());
		Collections.sort(getNodos());
		colorear();
	}

	private boolean sonAdyacentes(Nodo a, Nodo b) {
		return this.sonAdyacentes(a.getId(), b.getId());
	}

	private boolean sonAdyacentes(int i, int j) {
		return this.grafo.getValorArista(i, j) != 0;
	}

	@Override
	protected Object clone() {
		return (Object) new GrafoNDNP(this.orden, this.grafo, this.nodos);
	}

}
