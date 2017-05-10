package paqueteTrenes;

public class Animal {
	String especie;
	int agresividadDeEspecie;
	
	public Animal(String especie, int agresividadDeEspecie) {
		this.especie = especie;
		this.agresividadDeEspecie = agresividadDeEspecie;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public int getAgresividadDeEspecie() {
		return agresividadDeEspecie;
	}
	public void setAgresividadDeEspecie(int agresividadDeEspecie) {
		this.agresividadDeEspecie = agresividadDeEspecie;
	}
}
