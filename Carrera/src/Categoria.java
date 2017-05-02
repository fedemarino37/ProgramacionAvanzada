
public class Categoria {
	private int desde;
	private int hasta;
	private int numero;
	private Sexo sexo;
	
	public Categoria(int numero, int desde, int hasta, Sexo sexo) {
		this.numero = numero;
		this.desde = desde;
		this.hasta = hasta;
		this.sexo = sexo;
	}
	
	public boolean corresponde(Competidor competidor){
		return competidor.getSexo() == sexo 
				&& competidor.getEdad() >= desde 
				&& competidor.getEdad() <= hasta;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public int getDesde() {
		return desde;
	}

	public int getHasta() {
		return hasta;
	}
	
	@Override
	public String toString() {
		return "[numero=" + numero + ", desde=" + desde + ", hasta=" + hasta + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numero;
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (numero != other.numero)
			return false;
		if (sexo != other.sexo)
			return false;
		return true;
	}
}
