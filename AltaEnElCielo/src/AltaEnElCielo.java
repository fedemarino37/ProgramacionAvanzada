import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AltaEnElCielo {
	private Escuela[] escuelas;
	private int carretel;
	
	public AltaEnElCielo(String path) {
		try (Scanner scanner = new Scanner(new File(path))){
			String[] atributos = scanner.nextLine().split(" ");
			this.carretel = Integer.parseInt(atributos[0]);
			this.escuelas = new Escuela[Integer.parseInt(atributos[1])];
			
			for (int i = 0; i < this.escuelas.length; i++) {
				int retazo = scanner.nextInt();
				this.carretel -= retazo;
				escuelas[i] = new Escuela(retazo, i + 1);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void resolver() {
		// Asigno los retazos
		boolean pudoAsignar;
		do {
			pudoAsignar = false;
			for (int i = 0; i < this.escuelas.length; i++) {
				int largoRetazo = this.escuelas[i].getLargoSiguiente();
				if (this.carretel >= largoRetazo) {
					this.carretel -= largoRetazo;
					this.escuelas[i].agregarRetazo(largoRetazo);
					pudoAsignar = true;
				}
			}
		}
		while (pudoAsignar);
		
		// Calculo lo que piden
		int maxLargo = 0;
		int nro_maxLargo = 0;
		
		int maxCantCosturas = 0;
		
		int maxLCS = 0;
		int[] escuelasLCS = new int[2];
		
		for (int i = 0; i < this.escuelas.length; i++) {
			Escuela escuela = this.escuelas[i];
			// Maximo largo
			int largoTotal = escuela.getLargoTotal();
			if (largoTotal > maxLargo) {
				maxLargo = largoTotal;
				nro_maxLargo = escuela.getNumero();
			}
			
			int cantCosturas = escuela.getCantCosturas();
			if (cantCosturas > maxCantCosturas) {
				maxCantCosturas = cantCosturas;
			}
			
			for (int j = i + 1; j < this.escuelas.length; j++) {
				Escuela otraEscuela = this.escuelas[j];
				int lcs = escuela.getLCS(otraEscuela);
				if (lcs > maxLCS) {
					maxLCS = lcs;
					escuelasLCS[0] = escuela.getNumero();
					escuelasLCS[1] = otraEscuela.getNumero();
				} 
			}
		}
		
		System.out.println(nro_maxLargo + " " + maxLargo);
		System.out.println(maxCantCosturas);
		System.out.println(carretel);
		String salidaLCS = maxLCS + " " + escuelasLCS[0] + " " + escuelasLCS[1];
		System.out.println(salidaLCS);
	}
}
