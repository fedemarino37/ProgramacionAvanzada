package vikingo;

public class Berserker extends EstadoVikingo {
	private int cantCalmas;
	
	@Override
	public String toString() {
		return "Berserker";
	}


	@Override
	public int getFuerza(Vikingo vikingo) {
		return vikingo.getFuerzaInicial() * 3;
	}

	@Override
	public int getDefensa(Vikingo vikingo) {
		return 1;
	}


	@Override
	public EstadoVikingo calmarse(Vikingo vikingo) {
		if (++cantCalmas >= 3)
			return new Normal();
		return this;
	}


	@Override
	public EstadoVikingo recibirDaño(Vikingo vikingo, int daño) {
		return this;
	}
}
