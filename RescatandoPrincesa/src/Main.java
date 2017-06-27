import java.io.File;

public class Main {

	public static void main(String[] args) {
		File entrada = new File("in/ejemplo-camino.in"); // ejemplo-sincamino.in , ejemplo-interceptado.in
		Grafo g = new Grafo(entrada);
		g.resolver();
	}

}
