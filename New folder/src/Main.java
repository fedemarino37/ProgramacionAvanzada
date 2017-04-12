import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
	
		List<Mesada> mesadas = new LinkedList<Mesada>();
		mesadas.add(new Mesada(1, 2));
		mesadas.add(new Mesada(4, 5));
		mesadas.add(new Mesada(2, 2));
		Collections.sort(mesadas);
		for (Mesada mesada : mesadas)
			System.out.println(mesada);
	}

}
