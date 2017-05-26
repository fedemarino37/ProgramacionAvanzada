import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class EsquinaIdeal extends EjercicioOIA {
	private int[][] matriz;
	private int v, h;
	private Posicion[] casasFamiliares;
	
	public EsquinaIdeal(File entrada, File salida) {
		super(entrada, salida);
		try (Scanner scanner = new Scanner(entrada)) {
			String[] atributos = scanner.nextLine().split(" ");
			this.v = Integer.parseInt(atributos[0]);
			this.h = Integer.parseInt(atributos[1]);
			this.matriz = new int[v][h];
			int cantCasas = Integer.parseInt(scanner.nextLine());
			this.casasFamiliares = new Posicion[cantCasas];
			for (int i = 0; i < cantCasas; i++) {
				String[] pos = scanner.nextLine().split(" ");
				int x = Integer.parseInt(pos[0]), y = Integer.parseInt(pos[1]);
				this.casasFamiliares[i] = new Posicion(x, y);
				this.matriz[x][y] = -1; // Casa de familiar.
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void resolver() {
		// Voy buscando. Si encuentro un nuevo max, inicializo el vector de posiciones y lo voy cargando.
		List<Posicion> mejoresPosiciones = null;
		int mejorDistancia = 999999999;
		int minMaximaDistancia = 9999999;
		
		for (int i = 0; i < this.v; i++) {
			for (int j = 0; j < this.h; j++) {
				if (matriz[i][j] == -1) continue;
				int maxDistancia = -1; 
				for (Posicion casaFliar : this.casasFamiliares) {
					int distancia = casaFliar.distanciaA(i, j);
					matriz[i][j] += distancia;
					if (distancia > maxDistancia) maxDistancia = distancia;
				}
				
				if (matriz[i][j] < mejorDistancia) {
					mejorDistancia = matriz[i][j];
					if (maxDistancia < minMaximaDistancia) minMaximaDistancia = maxDistancia;
					mejoresPosiciones = new LinkedList<Posicion>();
					mejoresPosiciones.add(new Posicion(i, j));
				} else if (matriz[i][j] == mejorDistancia) {
					if (maxDistancia < minMaximaDistancia){
						minMaximaDistancia = maxDistancia;
						mejoresPosiciones = new LinkedList<Posicion>();
						mejoresPosiciones.add(new Posicion(i, j));						
					} else if (maxDistancia == minMaximaDistancia) {
						mejoresPosiciones.add(new Posicion(i, j));	
					}
				}
			}
		}
		
		if (mejoresPosiciones != null) {
			for (Posicion posicion : mejoresPosiciones) {
				System.out.println(posicion);
			}
		}
		else {
			System.out.println("NO PODES METERTE AHI PAPU");
		}
	}
}