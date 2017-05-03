package comparadorLetras;

public class Palabra {
	private String palabra;
	private String inversa;
	private int numero;
	public Palabra(String palabra, int numero) {
		this.palabra = palabra;
		StringBuilder sb = new StringBuilder(palabra);
		this.inversa = sb.reverse().toString();
		this.numero = numero;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numero;
		return result;
	}
	public String getPalabra() {
		return palabra;
	}
	public String getInversa() {
		return inversa;
	}
	public int getNumero() {
		return numero;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Palabra other = (Palabra) obj;
		if (numero != other.numero)
			return false;
		return true;
	} 
}
