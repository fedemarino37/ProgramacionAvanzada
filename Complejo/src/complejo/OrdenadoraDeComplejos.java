package complejo;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrdenadoraDeComplejos {
	
	public static void ordenarPorModulo(List<Complejo> complejos){
		Collections.sort(complejos, new Comparator<Complejo>() {
			@Override
			public int compare(Complejo c1, Complejo c2) {
				if (c1.modulo() > c2.modulo()) return 1;
				if (c1.modulo() < c2.modulo()) return -1;
				return 0;
			}
		});
		
	}
	
	public static void ordenarPorParteReal(List<Complejo> complejos){
		Collections.sort(complejos, new ComparatorParteReal());
	}

	public static void ordenarPorParteImaginaria(List<Complejo> complejos){
		Collections.sort(complejos, new ComparatorParteImaginaria());
	}
}

class ComparatorParteReal implements Comparator<Complejo> {

	@Override
	public int compare(Complejo c1, Complejo c2) {
		if (c1.getReal() > c2.getReal()) return 1;
		if (c1.getReal() < c2.getReal()) return -1;
		return 0;
	}
	
}

class ComparatorParteImaginaria implements Comparator<Complejo> {

	@Override
	public int compare(Complejo c1, Complejo c2) {
		if (c1.getImaginario() > c2.getImaginario()) return 1;
		if (c1.getImaginario() < c2.getImaginario()) return -1;
		return 0;
	}
	
}
