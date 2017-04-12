package vehiculos;
import personas.*;

public abstract class Vehiculo {
	private int kilometros;
	private Persona chofer;
	
	public abstract void cambiarChofer(Persona chofer);
	
	public Vehiculo(Persona chofer){
		this.chofer = chofer;
	}
	
	protected void setChofer(Persona chofer){
		this.chofer = chofer;
	}
	
	public void avanzarKilometro(int km){
		kilometros += km;
	}
	
	public int getKilometros(){
		return kilometros;
	}
}
