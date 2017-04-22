package practicaTurnoMañana;

public class Arquero extends Personaje{
	private int cantidadaFlechas;
	private final int flechasMaximas = 20;

	public Arquero(int x, int y) {
		super(5, 2, 5, 50, x, y);
		
		this.cantidadaFlechas = flechasMaximas;
	}

	@Override
	protected boolean puedeAtacar() {
		return cantidadaFlechas > 0;
	}

	@Override
	protected void postAtaque() {
		cantidadaFlechas -= 1;
	}
	

	@Override
	protected void recibir(PaqueteFlechas p) {
		cantidadaFlechas += 6;
	}
}
