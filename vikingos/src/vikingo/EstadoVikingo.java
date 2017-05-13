package vikingo;

public abstract class EstadoVikingo {
	
	public abstract EstadoVikingo recibirDa�o(Vikingo vikingo, int da�o);
	
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
