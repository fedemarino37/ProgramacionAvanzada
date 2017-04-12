import empleado.*;
import personas.Persona;
import vehiculos.*;

public class Main {

	public static void main(String[] args) {
		Empleado [] misEmpleados = new Empleado[4];
		//Jefatura jefe_RRHH = new Jefatura("pedro", 35000);
				
		misEmpleados[0] = new Empleado("jose", 15000);
		misEmpleados[1] = new Empleado("maria", 23000);
		misEmpleados[2] = new Empleado("pablo", 8500);
		misEmpleados[3] = new Jefatura("pedro", 35000);
		
		for(Empleado e : misEmpleados){
			System.out.println(e.dameNombre() + "\t" + e.dameSueldo() + "\t" + e.dameId());
		}	

		Motocicleta moto = new Motocicleta(new Persona("Manolo", "Perolo", 35));
		
		moto.avanzarKilometro(10);
		System.out.println(moto.getKilometros());
		moto.setAcompañante(new Persona("kikin", "Perolo", 22));
		//moto.cambiarChofer(new Persona("nicolas", "Perolo", 30));
		moto.setAcompañante(null);
		moto.cambiarChofer(new Persona("nicolas", "Perolo", 30));
		
		Autobus bus = new Autobus(new Persona("matias", "manibela", 55));
		bus.agregarPasajero(new Persona("nicolas", "Perolo", 30));
		bus.agregarPasajero(new Persona("martin", "lala", 22));
		bus.agregarPasajero(new Persona("kike", "Perolo", 23));
		bus.agregarPasajero(new Persona("fede", "Perolo", 24));
		
		bus.cambiarChofer(new Persona("elpela", "Perolo", 23));
	}
}
