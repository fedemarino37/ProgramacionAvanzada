
public class Competidor {
	private int numero;
	private int edad;
	private Sexo sexo;
	private Categoria categoria;
	
	public Competidor(int numero, int edad, Sexo sexo) {
		this.numero = numero;
		this.edad = edad;
		this.sexo = sexo;
	}
	
	public Categoria getCategoria(){
		return this.categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public int getNumero() {
		return numero;
	}

	public int getEdad() {
		return edad;
	}

	public Sexo getSexo() {
		return sexo;
	}

	@Override
	public String toString() {
		return "[numero=" + numero + ", edad=" + edad + ", sexo=" + sexo + "]";
	}
}
