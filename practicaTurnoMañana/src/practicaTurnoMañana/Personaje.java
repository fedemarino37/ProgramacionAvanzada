package practicaTurnoMañana;
import java.lang.Math;

public abstract class Personaje {
	protected int dañoAtaque;
	protected int minDistAtaque;
	protected int maxDistAtaque;
	protected int salud;
	protected int x, y;
	protected final int saludMaxima;
	
	
	public Personaje(int daño, int distanciaMinima, int distanciaMaxima, int saludMaxima, int x, int y){
		this.dañoAtaque = daño;
		this.minDistAtaque = distanciaMinima;
		this.maxDistAtaque = distanciaMaxima;
		this.salud = this.saludMaxima = saludMaxima;
		this.x = x;
		this.y  = y;
	}
	
	public void atacar(Personaje p2){
		if(salud == 0) return;
		if(distanciaAtaque(p2) && puedeAtacar()){
			p2.serAtacado(this.dañoAtaque);
			postAtaque();
		}
	}
	
	protected void recibir(PocionAgua pocion){
		
	}
	
	protected void recibir(PaqueteFlechas paquete){
		
	}
	
	private boolean distanciaAtaque(Personaje p2){
		double distancia = Math.sqrt(Math.pow((this.x - p2.x), 2) + Math.pow((this.y - p2.y), 2) );
		
		return distancia >= minDistAtaque && distancia <= maxDistAtaque;
	}
	
	protected boolean puedeAtacar(){
		return true;
	}
	
	protected void postAtaque(){
		
	}
	
	private void serAtacado(int daño){
		if (daño > 0)
		{
			if (this.salud > daño)
				this.salud -= daño;
			else 
				this.salud = 0;
		}
	}
	
	protected int getSalud(){
		return salud;
	}
}
