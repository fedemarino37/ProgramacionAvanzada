package paqueteTrenes;

public class Vagon {

	private Animal[] animalesEnVagon;
	private int agresividadEnVagon;
	private int cantEspeciesEnVagon;
	
	public Vagon(){
		this.agresividadEnVagon = 0;
		this.cantEspeciesEnVagon = 0;
	}
	public Vagon(int agresividadDeVagon, int cantEspecies, Animal[] animalesEnvagon){
		this.agresividadEnVagon = agresividadDeVagon;
		this.cantEspeciesEnVagon = cantEspecies;
		this.animalesEnVagon = animalesEnvagon;
	}
	public Animal[] getAnimalesEnVagon() {
		return animalesEnVagon;
	}
	public void setAnimalesEnVagon(Animal[] animalesEnVagon) {
		this.animalesEnVagon = animalesEnVagon;
	}
	public int getAgresividadEnVagon() {
		return agresividadEnVagon;
	}
	public void setAgresividadEnVagon(int agresividadEnVagon) {
		this.agresividadEnVagon = agresividadEnVagon;
	}
	public int getCantEspeciesEnVagon() {
		return cantEspeciesEnVagon;
	}
	public void setCantEspeciesEnVagon(int cantEspeciesEnVagon) {
		this.cantEspeciesEnVagon = cantEspeciesEnVagon;
	}
}
