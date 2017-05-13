package polinomio;
import java.lang.Math;

public class Polinomio {

	private int grado;
	private double[] coeficientes;
	
	public Polinomio(int grado, double[] coeficientes) {
		this.grado = grado;
		this.coeficientes = coeficientes;
	}
	
	public double evaluarMSucesivas(double x) { 
		double result = 0;
		for (int i = 0; i <= grado; i++) {
			result += (coeficientes[i] * potenciaMSucesivas(x, grado - i));
		}
		return result; 
	}
	
	private double potenciaMSucesivas(double x, int pow) {
		double result = 1; 
		for (int i = 0; i < pow; i++) {
			result *= x;
		}
		return result;
	}
	
	public double evaluarRecursiva(double x) { 
		double result = 0;
		for (int i = 0; i <= grado; i++) {
			result += (coeficientes[i] * potenciaRecursiva(x, grado - i));
		}
		return result; 
	}
	
	private double potenciaRecursiva(double x, int pow) {
		if (pow == 0) return 1;
		return x * potenciaRecursiva(x, pow - 1);
	}
	
	public double evaluarRecursivaPar(double x) { 
		double result = 0;
		for (int i = 0; i <= grado; i++) {
			result += (coeficientes[i] * potenciaRecursivaPar(x, grado - i));
		}
		return result; 
	}
	
	private double potenciaRecursivaPar(double x, int pow) {
		if (pow == 0) return 1;
		if (pow % 2 == 0) return potenciaRecursivaPar(x * x, pow / 2);
		return x * potenciaRecursivaPar(x, pow - 1);
	}
	
	public double evaluarProgDinamica(double x) { 
		double result = 0;
		double potencia = 1;
		for(int i = grado; i >= 0; i--) {
			result += (coeficientes[i] * (potencia));
			potencia *= x;
		}
		return result; 
	}
	
	public double evaluarMejorada(double x) { 
		return x; 
	}
	
	public double evaluarPow(double x) { 
		double result = 0;
		for (int i = 0; i <= grado; i++) {
			result += (coeficientes[i] * Math.pow(x, grado - i));
		}
		return result; 
	}
	
	public double evaluarHorner(double x) {
        double result = coeficientes[0];
	    for (int i = 1; i <= grado; i++)
	        result = result * x + coeficientes[i];
	    return result;
    }
	
}
