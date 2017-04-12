package vehiculos;
import personas.*;

public class Motocicleta extends Vehiculo {
	private Persona acompañante;
	
	public Motocicleta(Persona chofer) {
		super(chofer);
		
	}
	
	public void setAcompañante(Persona acompañante){
		this.acompañante = acompañante;
	}

	@Override
	public void cambiarChofer(Persona chofer) {
		if(acompañante == null)
			setChofer(chofer);
		else
			System.out.println("no se puede cambiar el chofer de la moto");
	}

}
