import java.io.File;
import java.util.Scanner;

public class Latas extends EjercicioOIA{
	private String[] latas; 
	
	public Latas(File entrada, File salida) {
		super(entrada, salida);
		try (Scanner scanner = new Scanner(entrada)){
			latas = scanner.nextLine().split(" ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void resolver() {
		int max = 0;
		int max2 = 0;
		int posMax = -1;
		int posMax2 = -1;
		int cant = 0;
		
		for (int i = 0; i < latas.length; i++) {
			if (latas[i].equals("G")) {
				cant = 1;
				for (int j = i + 1; j < latas.length && latas[j].equals("G"); j++) {
					cant++;
				}

				if (cant > max) {
					max2 = max;
					posMax2 = posMax;
					max = cant;
					posMax = i;
				} else if (cant > max2) {
					max2 = cant;
					posMax2 = i;
				}
				
				i += cant - 1;
			}
		}
		
		if (posMax2 > -1) {
			int distancia = getDistancia(posMax, posMax2, max, max2);
			System.out.println(latas.length + "\n" + max + "\n" + max2 + "\n" + distancia);
		} else {
			System.out.println(latas.length + "\n" + max + "\n" + max + "\n" + 0);
		}
		
	}
	
	private int getDistancia(int a, int b, int largoA, int largoB) {
		if (a > b) {
			b += largoB;
		} else {
			a += largoA;
		}
		return Math.abs(a - b);
	}

}
