import java.io.File;

public class Main {

	public static void main(String[] args) {
		File entrada = new File("in/05_SinLugar.in");
		File salida = new File("");
		EjercicioOIA ejercicio = new EsquinaIdeal(entrada, salida);
		ejercicio.resolver();
	}

}
