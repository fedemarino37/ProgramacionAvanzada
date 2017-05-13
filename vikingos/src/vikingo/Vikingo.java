package vikingo;

public class Vikingo {
	private EstadoVikingo estado;
	private int salud;
	private final int saludMaxima = 100;
	private final int fuerzaInicial = 100;
	private final int defensaInicial = 200;
	
	public Vikingo() {
		estado = new Normal();
		this.salud = saludMaxima;
	}
	
	public int getSaludMaxima() {
		return this.saludMaxima;
	}
	
	public void calmarse() {
		this.estado = estado.calmarse(this);
	}
	
	public void recibirDaño(int daño) {
		this.estado = estado.recibirDaño(this, daño);
		salud -= daño;		
	}
	
	public EstadoVikingo getEstado() {
		return estado;
	}
	
	public int getFuerza(){
		return estado.getFuerza(this);
	}
	
	public int getDefensa(){
		return estado.getDefensa(this);
	}
	
	public int getFuerzaInicial(){
		return this.fuerzaInicial;
	}
	
	public int getDefensaInicial(){
		return this.defensaInicial;
	}
}
