package vikingo;

public class Normal extends EstadoVikingo {
	private double dañoAcumulado; 
	
	@Override
	public EstadoVikingo recibirDaño(Vikingo vikingo, int daño) {
		dañoAcumulado += daño;
		int salud = vikingo.getSaludMaxima();
		if (dañoAcumulado / salud >= 0.15) {
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
