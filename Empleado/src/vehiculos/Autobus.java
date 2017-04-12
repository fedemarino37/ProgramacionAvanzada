package vehiculos;

import java.util.ArrayList;
import personas.*;

public class Autobus extends Vehiculo {
	ArrayList<Persona> pasajeros;
	
	public Autobus(Persona chofer){
		super(chofer);
		pasajeros= new ArrayList<Persona>();
	}

	@Override
	public void cambiarChofer(Persona chofer) {
		// TODO Auto-generated method stub
		if(pasajeros.size() == 0){
			setChofer(chofer);
		}else{
			System.out.println("no puedo cambiar chofer");
		}
	}
	
	public void agregarPasajero(Persona pasajero){
		pasajeros.add(pasajero);
	}
	
	public void bajarTodosPasajeros(){
		pasajeros.clear();
	}
}
