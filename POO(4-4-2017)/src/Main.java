import punto.*;

public class Main {

	public static void main(String[] args) {
		Punto3D punto = new Punto3D(3, 1, 5);
		System.out.println(punto);
		
		Punto3D punto2 = new Punto3D(3, 3, 3);
		punto = punto2;
		
		System.out.println(punto);
		
		
	}
}
