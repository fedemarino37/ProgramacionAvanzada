package polinomio;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
//		int grado = 4;
//		double[] coeficientes = new double[]{ 3, 2, 0, 1, 5 };
//		int x = 2;
//		Polinomio pol = new Polinomio(grado, coeficientes);
//		
//		System.out.println(pol.evaluarMSucesivas(x));
//		System.out.println(pol.evaluarRecursiva(x));
//		System.out.println(pol.evaluarRecursivaPar(x));
//		System.out.println(pol.evaluarPow(x));
//		System.out.println(pol.evaluarProgDinamica(x));
//		System.out.println(pol.evaluarHorner(x));
		
		int n = 11500;
		double valorX = 0.8;
		Random r = new Random();
		double[] coef = new double[n + 1];
		for (int i = 0; i < n + 1; i++) {
			coef[i] = r.nextDouble() + i;
		}
		
		Polinomio pol2 = new Polinomio(n, coef);
		Calendar cInicio = new GregorianCalendar();
		pol2.evaluarMSucesivas(valorX);
		Calendar cFin = new GregorianCalendar();
		System.out.println(cFin.getTimeInMillis() - cInicio.getTimeInMillis());
		
		cInicio = new GregorianCalendar();
		pol2.evaluarRecursiva(valorX);
		cFin = new GregorianCalendar();
		System.out.println(cFin.getTimeInMillis() - cInicio.getTimeInMillis());
		
		cInicio = new GregorianCalendar();
		pol2.evaluarRecursivaPar(valorX);
		cFin = new GregorianCalendar();
		System.out.println(cFin.getTimeInMillis() - cInicio.getTimeInMillis());
		
		cInicio = new GregorianCalendar();
		pol2.evaluarPow(valorX);
		cFin = new GregorianCalendar();
		System.out.println(cFin.getTimeInMillis() - cInicio.getTimeInMillis());
		
		cInicio = new GregorianCalendar();
		pol2.evaluarProgDinamica(valorX);
		cFin = new GregorianCalendar();
		System.out.println(cFin.getTimeInMillis() - cInicio.getTimeInMillis());
		
		cInicio = new GregorianCalendar();
		pol2.evaluarHorner(valorX);
		cFin = new GregorianCalendar();
		System.out.println(cFin.getTimeInMillis() - cInicio.getTimeInMillis());
	}
}
