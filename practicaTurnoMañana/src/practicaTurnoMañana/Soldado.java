package practicaTurnoMañana;

public class Soldado extends Personaje{
	private int energia;
	private final int energiaMaxima = 100;
	private final int pierdoEnergia = 10;
	
	public Soldado(int x, int y) {
		super(10, 1, 1, 200, x, y);
		
		this.energia = energiaMaxima;
	}

	@Override
	protected boolean puedeAtacar() {
		return energia >= pierdoEnergia;
	}

	@Override
	protected void postAtaque() {
		energia -= pierdoEnergia;
	}
	
	int getEnergia(){
		return energia;
	}

	@Override
	protected void recibir(PocionAgua pocion) {
		energia = energiaMaxima;
	}
	
	

}
