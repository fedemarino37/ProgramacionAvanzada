import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CuantosCaballos extends EjercicioOIA {
	private int f, c, m;
	private char[][] matriz;
	private char[] caballo = "CABALLO".toCharArray();
	
	public CuantosCaballos(File entrada, File salida) {
		super(entrada, salida);
		try (Scanner scanner = new Scanner(entrada)) {
			String[] atributos = scanner.nextLine().split(" ");
			f = Integer.parseInt(atributos[0]);
			c = Integer.parseInt(atributos[1]);
			m = Integer.parseInt(atributos[2]);
			matriz = new char[f][c];
			for (int i = 0; i < f; i++) {
				matriz[i] = scanner.nextLine().toCharArray();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void resolver() {
		int cantFormas = 0;
		for (int i = 0; i < f; i++) {
			for (int j = 0; j < c; j++) {
				cantFormas += buscar(i, j, 0);
			}
		}
		System.out.println(cantFormas);
	}
	
	private int buscar(int x, int y, int pos) {
		if (pos == caballo.length - 1 && matriz[x][y] == caballo[pos]) {
			return 1;
		}
		int cantFormas = 0;
		if (matriz[x][y] == caballo[pos]) {
			for (Posicion posicion : getPosicionesAlcanzables(x, y)) {
				int x2 = posicion.getX(), y2 = posicion.getY();
				cantFormas += buscar(x2, y2, pos + 1);
			}
		}
		return cantFormas;
	}
	
	private List<Posicion> getPosicionesAlcanzables(int x, int y) {
		List<Posicion> resultado = new LinkedList<Posicion>();
		if (x - 2 >= 0 ) {
			if(y - 1 >= 0) resultado.add(new Posicion(x-2, y-1));
			if(y + 1 < c ) resultado.add(new Posicion(x-2, y+1));
		}
		if (x + 2 < f ) {
			if(y - 1 >= 0) resultado.add(new Posicion(x+2, y-1));
			if(y + 1 < c ) resultado.add(new Posicion(x+2, y+1));
		}
		if(y -2 >= 0) {
			if(x-1 >= 0) resultado.add(new Posicion(x-1, y-2));
			if(x+1 < f) resultado.add(new Posicion(x+1, y-2));
		}
		if(y +2 < c) {
			if(x-1 >= 0) resultado.add(new Posicion(x-1, y+2));
			if(x+1 < f) resultado.add(new Posicion(x+1, y+2));
		}
		return resultado;
	}
}
