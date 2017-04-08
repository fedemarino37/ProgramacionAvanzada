package luchadores;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Torneo {

	private ArrayList<Luchador>luchadores;
	public Torneo(){
		luchadores = new ArrayList<Luchador>();
	}
	
	public void agregarLuchador(Luchador luchador)
	{
		luchadores.add(luchador);
	}
	
	public void leerArchivo(String path) throws FileNotFoundException
	{
		Scanner scanner = new Scanner(new File(path));
		int cant = scanner.nextInt();
		scanner.nextLine();
		String linea;
		int peso, altura;
		peso = 0;
		altura = 0;
		for(int i=0; i < cant; ++i){
			linea = scanner.nextLine().toString();
			String[] array = linea.split(" ");
			peso = Integer.parseInt(array[0]);
			altura = Integer.parseInt(array[1]);
			System.out.println("peso: "+ peso + "  altura: " + altura);
			agregarLuchador(new Luchador(peso, altura));
		}
	}
}
