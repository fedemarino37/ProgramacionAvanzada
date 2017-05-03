package comparadorLetras;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		JuegoConLetras jl = new JuegoConLetras("in/motumbo.in");
		Calendar cInicio = new GregorianCalendar();
		Map<Palabra, String> resultados = jl.resolver();
		Calendar cFin = new GregorianCalendar();
		for (Palabra palabra : resultados.keySet()) {
			System.out.println(palabra.getNumero() + " " + resultados.get(palabra));
		}
		System.out.println("Tiempo: " + (cFin.getTimeInMillis() - cInicio.getTimeInMillis()) + "ms");
		//JuegoConLetras.generarAleatorio("in/motumbo.in");
	}
}
