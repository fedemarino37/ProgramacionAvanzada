package luchadores;

import java.util.List;

public class Torneo {
	private List<Luchador> luchadores;
	
	public Torneo(){
		luchadores = new List<Luchador>();
	}
	
	public void agregarLuchador(Luchador luchador)
	{
		luchadores.add(luchador);
	}
}
