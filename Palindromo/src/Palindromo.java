
public class Palindromo {
	private String palabra;
	
	public Palindromo(String palabra){
		this.palabra = palabra;
	}
	
	public String resolver(){
		String salida = "";
		int len = palabra.length();
		if (len >= 4) {
			for (int j = 2; j < len - 1; j++){
				String primeraPalabra = palabra.substring(0, j);
				String segundaPalabra = palabra.substring(j, len);
				String palindromosPrimera = getPalindromos(primeraPalabra);
				String palindromosSegunda = getPalindromos(segundaPalabra);
				if (!palindromosPrimera.isEmpty() && !palindromosSegunda.isEmpty()){
					salida += palindromosPrimera + "\n" + palindromosSegunda + "\n\n";
				}
			}
		}
		return salida.isEmpty() ? "No se puede" : salida;
	}
	
	private String getPalindromos(String palabra){
		String resultado = "";
		if (esIPalindromo(palabra)) resultado += " i-palindromo";
		if (esDPalindromo(palabra)) resultado += " d-palindromo";
		if (esPalindromo(palabra)) resultado += " palindromo";
		return resultado.isEmpty() ? resultado : palabra + resultado;
	}
	
	private boolean esIPalindromo(String palabra){
		return esPalindromo(palabra, 1, palabra.length() - 1);
	}
	
	private boolean esDPalindromo(String palabra){
		return esPalindromo(palabra, 0, palabra.length() - 2);
	}
	
	private boolean esPalindromo(String palabra){
		return esPalindromo(palabra, 0, palabra.length() - 1);
	}
	
	private boolean esPalindromo(String palabra, int i, int j){
		if ((j - i) < 2) return false;
		while (i < j){
			if (palabra.charAt(i) != palabra.charAt(j))
				return false;
			i++; j--;
		}
		return true;
	}

}
