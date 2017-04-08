package luchadores;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Torneo {

	private int [] cantidadDominados;
	private Luchador[] luchadores;
	
	public Torneo(String path)
	{
		try (Scanner scanner = new Scanner(new File(path));){
			int cantidadLuchadores = Integer.parseInt(scanner.nextLine());
			luchadores = new Luchador[cantidadLuchadores];
			cantidadDominados = new int[cantidadLuchadores];
			int peso, altura, i = 0;
			while (scanner.hasNextLine()){
				String[] atributos = scanner.nextLine().split(" ");
				peso = Integer.parseInt(atributos[0]);
				altura = Integer.parseInt(atributos[1]);
				luchadores[i++] = new Luchador(peso, altura);
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public void printLuchadores(){
		for (int i = 0; i < luchadores.length; ++i)
			System.out.println(luchadores[i]);
	}
	
	public void evaluarLuchadores(){
		for (int i = 0; i < luchadores.length; ++i){
			for (int j = i + 1; j < luchadores.length; ++j){
				if (luchadores[i].dominaA(luchadores[j])){
					cantidadDominados[i]++;
				}
				else if (luchadores[j].dominaA(luchadores[i])){
					cantidadDominados[j]++;
				}
				
			}
		}
	}
	
	public void printDominaciones(){
		for (int i = 0; i < luchadores.length; ++i){
			System.out.println(cantidadDominados[i]);
		}
	}
}
