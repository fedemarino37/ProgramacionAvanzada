package paqueteTrenes;

public class MainTrenes {

	public static void main(String[] args) {
		Animal a1 = new Animal("elefante", 200);
		Animal a2 = new Animal("leon", 1500);
		Animal a3 = new Animal("liebre", 20);
		Animal a4 = new Animal("gato", 1950);
		Animal a5 = new Animal("pantera", 2000);
		Animal a6 = new Animal("zorro", 50);
		Animal[] animales = new Animal[6];
		animales[0] = a1;
		animales[1] = a2;
		animales[2] = a3;
		animales[3] = a4;
		animales[4] = a5;
		animales[5] = a6;
		Tren t1 = new Tren(100, animales);
		t1.ordenar();
		while(t1.llenarVagones()) {
	
		}
		System.out.println("Vagones llenos: " + t1.getCantVagones() + "\n Agresividad Acumulada: " + t1.getAgresividadAcumEnTren());

	}

}
