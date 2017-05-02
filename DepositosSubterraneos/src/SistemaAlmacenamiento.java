import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SistemaAlmacenamiento {
	private Deposito[] depositos;
	private int volumen;
	
	public SistemaAlmacenamiento(String path) {
		try (Scanner scanner = new Scanner(new File(path))){
			int cantDepositos = Integer.parseInt(scanner.nextLine());
			this.depositos = new Deposito[cantDepositos];
			int i = 0;
			// Vienen ordenados por profundidad decreciente
			while (scanner.hasNextLine() && i < cantDepositos){ 
				String[] partes = scanner.nextLine().split(" ");
				int superficie = Integer.parseInt(partes[0]);
				int profundidad = Integer.parseInt(partes[1]);
				this.depositos[i++] = new Deposito(superficie, profundidad); 
			}
			this.volumen = scanner.nextInt();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String resolver() {
		int metrosLibres = this.depositos[0].getProfundidad();
		int volumenRestante = this.volumen;
		String salida = "";
		int cantDepositosUsados = 0;
		// Voy llenando de a 1 metro
		while (metrosLibres > 0 && volumenRestante > 0) {
			int i = 0;
			for (Deposito deposito : depositos) {
				if (deposito.getProfundidad() < metrosLibres) break; // Todavía no lo puedo llenar
				if (volumenRestante <= 0) break;
				volumenRestante -= deposito.getVolumenPorMetro();
				i++;
			}
			metrosLibres--;
			if (i > cantDepositosUsados) cantDepositosUsados = i;
		}
		
		if (volumenRestante > 0) {
			salida = "Rebasan " + volumenRestante;
		} else {
			salida = cantDepositosUsados + "\n" + metrosLibres; 
		}
		
		return salida;
	}
}
