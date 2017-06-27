import java.io.File;

public class Main {

	public static void main(String[] args) {
		File entrada = new File("in/entrada.in");
		Subterraneo sub = new Subterraneo(entrada);
		sub.resolver();
	}

}
