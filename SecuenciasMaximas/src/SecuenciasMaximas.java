import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SecuenciasMaximas {
	private int[] numeros;
	
	public SecuenciasMaximas(String path){
		try (Scanner scanner = new Scanner(new File(path))){
			numeros = new int[scanner.nextInt()];
			int i = 0;
			while (scanner.hasNextLine()){
				numeros[i++] = scanner.nextInt();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void resolver(){
		int longMax = 0;
		int longActual = 0;
		int cantValidos = 0;
		for (int n : numeros){
			if (esValorValido(n)) {
				++cantValidos;
				++longActual;
			}
			else {
				if (longActual > longMax) longMax = longActual;
				longActual = 0;
			}
		}
		System.out.println("Cantidad válidos: " + cantValidos);
		System.out.println("Longitud máxima: " + longMax);
	}
	
	private boolean esValorValido(int n){
		return (n % 2 != 0) && (n % 3 != 0) && (n % 5 != 0);
	}
}
