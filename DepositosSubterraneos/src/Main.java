
public class Main {

	public static void main(String[] args) {
		String path = "in/entrada.in";
		SistemaAlmacenamiento sistemaAlmacenamiento = new SistemaAlmacenamiento(path);
		System.out.println(sistemaAlmacenamiento.resolver());
	}

}
