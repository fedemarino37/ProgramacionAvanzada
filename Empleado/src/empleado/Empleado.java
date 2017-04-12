package empleado;

public class Empleado {
	private final String nombre;
	private double sueldo;
	private static int cont = 0;
	private int id = 0;
	
	public Empleado(String nombre, double sueldo) {
		this.nombre = nombre;
		this.sueldo = sueldo;
		cont++;
		id = cont;
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	public String dameNombre(){
		return nombre;
	}
	
	public double dameSueldo(){
		return sueldo;
	}
	
	public int dameId(){
		return id;
	}
}
