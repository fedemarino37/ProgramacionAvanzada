package vikingo;

public class Normal extends EstadoVikingo {
	private double daņoAcumulado; 
	
	@Override
	public EstadoVikingo recibirDaņo(Vikingo vikingo, int daņo) {
		daņoAcumulado += daņo;
		int salud = vikingo.getSaludMaxima();
		if (daņoAcumulado / salud >= 0.15) {
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
