import java.io.File;

public class Main {

	/*
	 * Como hay una sola forma de llegar de un nodo al otro, el grafo es
	 * acíclico -> es un árbol. Teniendo como entrada la matriz de costos, puedo
	 * usar Prim o Kruskal para generar el arbol abarcador de costo mínimo
	 */
	public static void main(String[] args) {
		File entrada = new File("in/minotauro.in");
		Grafo grafo = new Grafo(entrada);
		grafo.resolverPrim();
		grafo.resolverKruskal();
	}

}
