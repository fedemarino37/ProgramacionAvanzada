package practicaTurnoMañana;

public class Caballero extends Personaje {
	
	private int caballo = 0;
	
	public Caballero(int x, int y) {
		super(50, 1, 2, 200, x, y);
	}

	@Override
	public void atacar(Personaje p2) {
		
		super.atacar(p2);
	}

	@Override
	protected void recibir(PocionAgua pocion) {
		caballo = 0;
	}

	@Override
	protected boolean puedeAtacar() {
		if(caballo < 3){
			return super.puedeAtacar();
		}
		return false;
	}

	@Override
	protected void postAtaque() {
		caballo++;
	}
	
}
