import java.io.File;

public class Main {

	private static String pathEntrada = "in/";
	private static String pathSalida = "out/";

	public static void main(String[] args) throws Exception {
		// generarEntradas();
		String[] nombres = new String[] { "aleatorio40", "aleatorio60", "aleatorio90", "regular50", "regular75" };
		for (String nombre : nombres) {
			System.out.println("Arranca coloreo de " + nombre);
			colorear(nombre);
			System.out.println("Finaliz� el coloreo de " + nombre);
		}
//		File in = new File("in/ejemplo.in");
//		File out = new File("out/ejemplo-powell.out");
//		System.out.println(ProgramaProbador.evaluarColoreo(in, out));
	}

	private static void colorear(String nombreArchivo) {
		File in = new File(pathEntrada + nombreArchivo + ".in");
		GrafoNDNP grafo = new GrafoNDNP(in);
		int cantIteraciones = 10000;
		int minCantColores = grafo.getOrden() * 2;
		GrafoNDNP grafoColoreado = null;
		for (int i = 0; i < cantIteraciones; i++) {
			grafo.colorearAleatorio();
			int cantColores = grafo.getCantColores();
			if (cantColores < minCantColores) {
				minCantColores = cantColores;
				grafoColoreado = (GrafoNDNP) grafo.clone();
			}
		}
		if (grafoColoreado != null)
			grafoColoreado.guardar(new File(pathSalida + nombreArchivo + "-aleatorio.out"), true);

		grafoColoreado = null;
		minCantColores = grafo.getOrden() * 2;
		for (int i = 0; i < cantIteraciones; i++) {
			grafo.colorearMatula();
			int cantColores = grafo.getCantColores();
			if (cantColores < minCantColores) {
				minCantColores = cantColores;
				grafoColoreado = (GrafoNDNP) grafo.clone();
			}
		}
		if (grafoColoreado != null)
			grafoColoreado.guardar(new File(pathSalida + nombreArchivo + "-matula.out"), true);

		grafoColoreado = null;
		minCantColores = grafo.getOrden() * 2;
		for (int i = 0; i < cantIteraciones; i++) {
			grafo.colorearWelshPowell();
			int cantColores = grafo.getCantColores();
			if (cantColores < minCantColores) {
				minCantColores = cantColores;
				grafoColoreado = (GrafoNDNP) grafo.clone();
			}
		}
		if (grafoColoreado != null)
			grafoColoreado.guardar(new File(pathSalida + nombreArchivo + "-powell.out"), true);
	}

	private static void generarEntradas() throws Exception {
		int cantNodos = 600;

		// aleatorios
		GrafoNDNP grafo = Generador.generarRandomPorcentaje(cantNodos, 40);
		grafo.guardar(new File(pathEntrada + "aleatorio40.in"));
		grafo = Generador.generarRandomPorcentaje(cantNodos, 60);
		grafo.guardar(new File(pathEntrada + "aleatorio60.in"));
		grafo = Generador.generarRandomPorcentaje(cantNodos, 90);
		grafo.guardar(new File(pathEntrada + "aleatorio90.in"));

		// regulares
		cantNodos = 1000;
		grafo = Generador.generarRegularPorcentaje(cantNodos, 50);
		grafo.guardar(new File(pathEntrada + "regular50.in"));
		grafo = Generador.generarRegularPorcentaje(cantNodos, 75);
		grafo.guardar(new File(pathEntrada + "regular75.in"));
	}
}
