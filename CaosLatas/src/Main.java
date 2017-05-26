import java.io.File;

public class Main {

	public static void main(String[] args) {
		File entrada = new File("in/05_SecuenciasIncrementales.in");
		File salida = new File("out/salida.out");
		EjercicioOIA ejercicio = new Latas(entrada, salida);
		ejercicio.resolver();
	}

}
