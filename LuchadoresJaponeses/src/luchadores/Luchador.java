package luchadores;

public class Luchador {
	private int peso;
	private int altura;
	
	public Luchador(){}
	public Luchador(int peso, int altura){
		this.altura = altura;
		this.peso = peso;
	}
	
	public Boolean dominaA(Luchador luch){
		if(this.peso > luch.peso && this.altura > luch.altura)
		{
			return true;
		}
		if(this.peso == luch.peso && this.altura > luch.altura){
			return true;
		}
		if(this.peso > luch.peso && this.altura == luch.altura){
			return true;
		}
		return false;
	}
	
}
