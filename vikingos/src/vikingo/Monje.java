package vikingo;

public class Monje extends EstadoVikingo {
	private double daņoAcumulado; 

	@Override
	public int getDefensa(Vikingo vikingo) {		
		return vikingo.getDefensaInicial() * 5;
	}

	@Override
	public int getFuerza(Vikingo vikingo) {
		// TODO Auto-generated method stub
		return 1;
	}



	@Override
	public String toString() {
		return "Monje";
	}

	@Override
	public EstadoVikingo recibirDaņo(Vikingo vikingo, int daņo) {
		daņoAcumulado += daņo;
		if (daņoAcumulado / vikingo.getSaludMaxima() >= 0.05) {
			return new Normal();
		}
		return this;
	}

	@Override
	public EstadoVikingo calmarse(Vikingo vikingo) {
		return this;
	}
}
