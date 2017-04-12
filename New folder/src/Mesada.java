
public class Mesada implements Comparable<Mesada> {
	private int largo;
	private int ancho;
	
	public Mesada(int largo, int ancho){
		this.largo = largo;
		this.ancho = ancho;
	}
	
	public void rotar(){
		int temp = largo;
		largo = ancho;
		ancho = temp;
	}

	@Override
	public int compareTo(Mesada m2) {
		if ((this.largo >= m2.largo && this.ancho >= m2.ancho) || 
			 (this.largo >= m2.ancho && this.ancho >= m2.largo))
			return -1; // Se puede apilar m2 sobre this
		return 1;
	}

	@Override
	public String toString() {
		return "Mesada [largo=" + largo + ", ancho=" + ancho + "]";
	}
	
}
