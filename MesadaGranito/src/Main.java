import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		List<Mesada> mesadas = new LinkedList<Mesada>(); 
	    mesadas.add(new Mesada(2, 1)); 
	    mesadas.add(new Mesada(3, 1)); 
	    mesadas.add(new Mesada(2, 2)); 
	    mesadas.add(new Mesada(12, 1)); 
	    mesadas.add(new Mesada(7, 2)); 
	    mesadas.add(new Mesada(5, 4)); 
	    mesadas.add(new Mesada(9, 3)); 
	    
	    for (Stack<Mesada> pila : minCantidadPilas(mesadas)) {
	    	System.out.println(pila);
	    }
	}
	
	public static List<Stack<Mesada>> minCantidadPilas(List<Mesada> mesadas){
		List<Stack<Mesada>> pilas = new LinkedList<Stack<Mesada>>();
		Collections.sort(mesadas);
		for (Mesada mesada : mesadas) {
			boolean pudoApilar = false;
			for (Stack<Mesada> pila : pilas) {
				if (pila.peek().puedeApilar(mesada)) {
					pila.push(mesada);
					pudoApilar = true;
					break;
				}
			}
			if (!pudoApilar) {
				// Creo una nueva pila con esta mesada como base
				Stack<Mesada> nuevaPila = new Stack<Mesada>();
				nuevaPila.push(mesada);
				pilas.add(nuevaPila);
			}
		}
		return pilas;
	}
}
