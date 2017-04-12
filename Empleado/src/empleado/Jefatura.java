package empleado;

public class Jefatura  extends Empleado{

	private double incentivo;
	
	public Jefatura(String nombre, double sueldo) {
		super(nombre, sueldo);
		this.incentivo = 1000;
	}
	
	@Override
	public double dameSueldo(){
		return super.dameSueldo() + incentivo;
	}

}