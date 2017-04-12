package figura;

public class Cuadrado extends Figura{
	private double lado;
	
	public Cuadrado(double l) {
		lado = l;
	}
	
	public double getLado(){
		return lado;
	}

	@Override
	public double area() {
		return lado * lado;
	}
}
