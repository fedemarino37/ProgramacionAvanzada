package vikingo;

public class Normal extends EstadoVikingo {
	private double da�oAcumulado; 
	
	@Override
	public EstadoVikingo recibirDa�o(Vikingo vikingo, int da�o) {
		da�oAcumulado += da�o;
		int salud = vikingo.getSaludMaxima();
		if (da�oAcumulado / salud >= 0.15) {
			return new Berserker();
		}
		return this;
	}
	
	@Override 
	public EstadoVikingo calmarse(Vikingo vikingo) {
		return new Monje();
	}
	
	@Override
	public String toString() {
		return "Normal";
	}
}
