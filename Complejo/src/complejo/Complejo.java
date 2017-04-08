package complejo;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Complejo implements Comparable<Complejo> {
	private double real;
	private double imaginario;
	public Complejo(double real, double imaginario){
		this.real = real;
		this.imaginario = imaginario;
	}
	
	public Complejo(){
		this(0, 0);
	}
	
	public double getReal(){
		return this.real;
	}
	
	public double getImaginario(){
		return this.imaginario;
	}
	
	@Override
	public String toString() {
		return "(" + real + ", " + imaginario + ")";
	}

	public Complejo sumar(Complejo c2)
	{
		return new Complejo(this.real + c2.real, this.imaginario + c2.imaginario);
	}
	
	public Complejo sumar(int c2)
	{
		return new Complejo(this.real + c2, this.imaginario);
	}

	public Complejo restar(Complejo c2)
	{
		return new Complejo(this.real - c2.real, this.imaginario - c2.imaginario);
	}

	public Complejo restar(int c2)
	{
		return new Complejo(this.real - c2, this.imaginario);
	}
	
	public Complejo multiplicar(Complejo c2)
	{
		return new Complejo((this.real * c2.real) - (this.imaginario * c2.imaginario), 
							(this.real * c2.imaginario) + (this.imaginario * c2.real));
	}
	
	public Complejo multiplicar(int num)
	{
		return new Complejo(this.real * num, this.imaginario * num);
	}
	
	public double modulo(){
		return Math.sqrt((this.real * this.real) + (this.imaginario * this.imaginario));
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(imaginario);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(real);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Complejo other = (Complejo) obj;
		if (Double.doubleToLongBits(imaginario) != Double.doubleToLongBits(other.imaginario))
			return false;
		if (Double.doubleToLongBits(real) != Double.doubleToLongBits(other.real))
			return false;
		return true;
	}


	@Override
	protected Object clone() {
		return new Complejo(this.real, this.imaginario);
	}

	@Override
	public int compareTo(Complejo c2) {
		if (this.modulo() > c2.modulo()) return 1;
		if (this.modulo() < c2.modulo()) return -1;
		return 0;
	}
	
	public static void main(String[] args) {
		Complejo z1 = new Complejo();
		Complejo z2 = new Complejo(2,2);
		Complejo z3 = new Complejo(1.1,1.1);
		System.out.println(z1); 
		System.out.println(z2);
		System.out.println(z3);
		System.out.println(z2.multiplicar(z3));
		z3 = z1.sumar(z2);
		System.out.println(z3); // muestra 2,2
		z2 = (Complejo)z1.clone();
		System.out.println(z2);
		
		int TAM = 10;
		Complejo[] vComplejo = new Complejo[TAM];
				
		for (int i = 0; i < TAM; i++)
			vComplejo[i] = new Complejo(Math.sin(i+10),Math.cos(i+20));
		
		for (int i = 0; i < TAM; i++)
			System.out.println(vComplejo[i].modulo());

		Arrays.sort(vComplejo);
		System.out.println("Ahora ordenado");
		
		for (int i = 0; i < TAM; i++)
			System.out.println(vComplejo[i].modulo());
	}

}
