import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Carrera {
	private Categoria[] categorias;
	private Competidor[] competidores;
	private int[] ganadores;
	private Map<Categoria, LinkedList<Competidor>> ganadoresPorCategoria;
	
	public Carrera(String path) {
		try (Scanner scanner = new Scanner(new File(path))){
			ganadoresPorCategoria = new Hashtable<Categoria, LinkedList<Competidor>>();
			String[] partes = scanner.nextLine().split(" ");
			competidores = new Competidor[Integer.parseInt(partes[0])];
			int cf = Integer.parseInt(partes[1]);
			int cm = Integer.parseInt(partes[2]);
			categorias = new Categoria[cf + cm];
			ganadores = new int[Integer.parseInt(partes[3])];
			int i = 0;
			while (i < cf){ 
				String[] cotas = scanner.nextLine().split(" ");
				int desde = Integer.parseInt(cotas[0]);
				int hasta = Integer.parseInt(cotas[1]);
				categorias[i++] = new Categoria(i, desde, hasta, Sexo.F);
			}
			i = 0;
			while (i < cm){ 
				String[] cotas = scanner.nextLine().split(" ");
				int desde = Integer.parseInt(cotas[0]);
				int hasta = Integer.parseInt(cotas[1]);
				categorias[cf + i++] = new Categoria(i, desde, hasta, Sexo.M);			 
			}
			i = 0;
			while (i < competidores.length){ 
				String[] atributos = scanner.nextLine().split(" ");
				int edad = Integer.parseInt(atributos[0]);
				Sexo sexo = Sexo.valueOf(atributos[1]);
				Competidor competidor = new Competidor(i + 1, edad, sexo);
				establecerCategoria(competidor);
				competidores[i++] = competidor;
			}
			i = 0;
			while (i < ganadores.length){ 
				ganadores[i++] = scanner.nextInt();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void establecerCategoria(Competidor competidor){
		for (Categoria categoria : this.categorias) {
			if (categoria.corresponde(competidor)) {
				competidor.setCategoria(categoria);
				break;
			}
		}
	}
	
	public void resolver() {
		for (int nroGanador : ganadores) {
			Competidor competidor = getCompetidor(nroGanador);
			Categoria categoriaCompetidor = competidor.getCategoria();
			if (!ganadoresPorCategoria.containsKey(categoriaCompetidor)) {
				ganadoresPorCategoria.put(categoriaCompetidor, new LinkedList<Competidor>());
			}
			ganadoresPorCategoria.get(categoriaCompetidor).add(competidor);
		}
		
		for (Categoria categoria : this.categorias) {
			String salida = categoria.getNumero() + "";
			List<Competidor> ganadoresCategoria = ganadoresPorCategoria.get(categoria);
			for (int i = 0; i < 3; i++) {
				int nroCompetidor = 0;
				if (ganadoresCategoria != null && ganadoresCategoria.size() > i)
					nroCompetidor = ganadoresCategoria.get(i).getNumero();
				salida += " " + nroCompetidor;
			}
			System.out.println(salida);
		}
	}
	
	private Competidor getCompetidor(int numero) {
		for (Competidor competidor : this.competidores) {
			if (competidor.getNumero() == numero)
				return competidor;
		}
		return null;
	}
}
