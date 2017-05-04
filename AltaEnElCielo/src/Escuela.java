import java.util.LinkedList;
import java.util.List;

public class Escuela {
	private List<Integer> retazos; 
	private int nro;
	// Estas variables evitan recorrer la lista 
	private int largoTotal;
	private int cantRetazos;
	
	public Escuela(int primerRetazo, int nro) {
		retazos = new LinkedList<Integer>();
		this.agregarRetazo(primerRetazo);
		this.nro = nro;
	}
	
	public void agregarRetazo(int retazo) {
		this.largoTotal += retazo;
		this.cantRetazos++;
		retazos.add(retazo);
	}
	
	public int getLargoSiguiente() {
		int largoUltimo = this.getLargoUltimo();
		int largoSiguiente = largoUltimo;
		while (largoUltimo > 0) {
			largoSiguiente += (largoUltimo % 10);
			largoUltimo /= 10;
		}
		return largoSiguiente;
	}
	
	public int getLCS(Escuela otraEscuela) {
		return LCS.deIterativo(this.retazos, otraEscuela.retazos, this.cantRetazos, otraEscuela.cantRetazos);
	}
	
	public int getLargoUltimo() {
		return this.retazos.get(cantRetazos - 1);
	}
	
	public int getCantRetazos() {
		return this.cantRetazos;
	}
	
	public int getCantCosturas() {
		return this.cantRetazos - 1;
	}
	
	public int getLargoTotal() {
		return largoTotal; 
	}
	
	public int getNumero() {
		return this.nro;
	}

	@Override
	public String toString() {
		return "Escuela [retazos=" + retazos + "]";
	}
}
