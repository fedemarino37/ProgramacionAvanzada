package punto;

public class Punto2D {

	protected double x;
	protected double y;
	
	public void print(){
		System.out.println("esto funciona" + toString());
	}
	public Punto2D(){
		
	}
	public Punto2D(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + x + ", " + y + ")";
	}
	
	/*public static void main(String[] args) {
		Punto2D punto = new Punto2D();
		System.out.println(punto);
	}*/

}
