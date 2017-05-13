package vikingo;

public abstract class EstadoVikingo {
	
	public abstract EstadoVikingo recibirDaño(Vikingo vikingo, int daño);
	
	public abstract EstadoVikingo calmarse(Vikingo vikingo);

	@Override
	public String toString() {
		return "EstadoVikingo []";
	}
	
	public int getFuerza(Vikingo vikingo){
		return vikingo.getFuerzaInicial();
	}
	
	public int getDefensa(Vikingo vikingo){
		return vikingo.getDefensaInicial();
	}
}
