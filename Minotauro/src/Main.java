import java.io.File;

public class Main {

	/*
	 * Como hay una sola forma de llegar de un nodo al otro, el grafo es
	 * ac�clico -> es un �rbol. Teniendo como entrada la matriz de costos, puedo
	 * usar Prim o Kruskal para generar el arbol abarcador de costo m�nimo
	 */
	public static void main(String[] args) {
		File entrada = new File("in/minotauro.in");
		Grafo grafo = new Grafo(entrada);
		grafo.resolverPrim();
		grafo.resolverKruskal();
	}

}
