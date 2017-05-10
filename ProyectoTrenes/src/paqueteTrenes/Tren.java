package paqueteTrenes;

import java.util.Comparator;
import java.util.LinkedList;

public class Tren {
	private int agresividadMax;
	private int cantVagones;
	private int agresividadAcumEnTren;
	private Animal[] animalesATransportar;
	private LinkedList<Vagon> vagonesDelTren = new LinkedList<>();

	public Tren() {
		this.agresividadMax = 100;
		this.cantVagones = 0;
		// this.agresividadAcumulada = 0;
		this.animalesATransportar = new Animal[10];
	}

	public Tren(int agresividadMax) {
		this.agresividadMax = agresividadMax;
		this.cantVagones = 0;
		// this.agresividadAcumulada = 0;
		this.animalesATransportar = new Animal[10];
	}

	public Tren(int agresividadMax, Animal[] array) {
		this.agresividadMax = agresividadMax;
		this.cantVagones = 0;
		// this.agresividadAcumulada = 0;
		this.animalesATransportar = array;
	}

	public void ordenar() {
		LinkedList<Animal> listaAux = new LinkedList<>();
		for (int i = 0; i < animalesATransportar.length; i++) {
			listaAux.add(animalesATransportar[i]);
		}
		listaAux.sort(new Comparator<Animal>() {
			@Override
			public int compare(Animal o1, Animal o2) {
				return o2.getAgresividadDeEspecie() - o1.getAgresividadDeEspecie();
			}
		});

		for (int i = 0; i < animalesATransportar.length; i++) {
			animalesATransportar[i] = listaAux.removeFirst();
		}
	}

	public boolean llenarVagones() {
		int i;
		int j;
		int posibleAgresion = 0;
		int posibleCantEspecies = 0;
		boolean flag = false;
		Animal[] posibleArrayAlVagon = animalesATransportar;
		Animal[] animalesRestantesPosible = animalesATransportar;
		for (i = 0; i < animalesATransportar.length; i++) {
			for (j = (posibleArrayAlVagon.length - 1); j >= i; j--) {

				if (this.animalesATransportar[i].getAgresividadDeEspecie()
						- this.animalesATransportar[j].getAgresividadDeEspecie() <= this.agresividadMax) {

					Animal[] subArray = new Animal[j + 1];
					Animal[] animalesRestantes = new Animal[animalesATransportar.length - (j + 1)];
					for (int y = 0; y < (j + 1); y++) {
						subArray[y] = animalesATransportar[y];
					}
					for (int y = 0, h = j; y < animalesATransportar.length - (j + 1); y++, h++) {
						animalesRestantes[y] = animalesATransportar[h+1];
					}

					if (posibleCantEspecies < (j + 1) && subArray[i].getAgresividadDeEspecie()
							- subArray[j].getAgresividadDeEspecie() <= agresividadMax) {

						posibleCantEspecies = (j + 1);
						posibleAgresion = this.animalesATransportar[i].getAgresividadDeEspecie()
								- this.animalesATransportar[j].getAgresividadDeEspecie();
						posibleArrayAlVagon = subArray;
						animalesRestantesPosible = animalesRestantes;
						flag = true;
					}
				}
			}
		}
		if (!flag) {
			return false;
		}
		Vagon v1 = new Vagon(posibleAgresion, posibleCantEspecies, posibleArrayAlVagon);
		this.vagonesDelTren.add(v1);
		this.cantVagones++;
		this.agresividadMax -= v1.getAgresividadEnVagon();
		this.agresividadAcumEnTren += v1.getAgresividadEnVagon();
		this.animalesATransportar = animalesRestantesPosible;
		return true;
	}

	public int getAgresividadMax() {
		return agresividadMax;
	}

	public void setAgresividadMax(int agresividadMax) {
		this.agresividadMax = agresividadMax;
	}

	public int getCantVagones() {
		return cantVagones;
	}

	public void setCantVagones(int cantVagones) {
		this.cantVagones = cantVagones;
	}

	public int getAgresividadAcumEnTren() {
		return agresividadAcumEnTren;
	}

	public void setAgresividadAcumEnTren(int agresividadAcumEnTren) {
		this.agresividadAcumEnTren = agresividadAcumEnTren;
	}

	public Animal[] getAnimalesATransportar() {
		return animalesATransportar;
	}

	public void setAnimalesATransportar(Animal[] animalesATransportar) {
		this.animalesATransportar = animalesATransportar;
	}

	public LinkedList<Vagon> getVagonesDelTren() {
		return vagonesDelTren;
	}

	public void setVagonesDelTren(LinkedList<Vagon> vagonesDelTren) {
		this.vagonesDelTren = vagonesDelTren;
	}
}
