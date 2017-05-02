
public class Deposito {
	int superficie;
	int profundidad;
	public Deposito(int superficie, int profundidad) {
		this.superficie = superficie;
		this.profundidad = profundidad;
	}
	@Override
	public String toString() {
		return "[superficie=" + superficie + ", profundidad=" + profundidad + "]";
	}
	
	public int getProfundidad() {
		return profundidad;
	}
	
	public int getVolumenPorMetro() {
		return superficie;
	}
}
