package vectorMath;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;


public class VectorMath {
	protected int dimension;
	protected double [] coordenadas;
		
	public VectorMath(int dimension) {
		this.dimension = dimension;
		this.coordenadas = new double [dimension];
	}
	
	public VectorMath(int dimension, double [] coordenadas) {
		this.dimension = dimension;
		this.coordenadas = coordenadas;
	}
	
	public VectorMath(String path){
		this(path, 0);
	}
	
	public VectorMath(String path, int leerDesde) {
		try (Scanner scanner = new Scanner(new File(path));){
			dimension = Integer.parseInt(scanner.nextLine());
			coordenadas = new double[dimension];
			
			int i = 0, nroLinea = 0;
			while (scanner.hasNextLine()){
				String linea = scanner.nextLine();
				if (nroLinea++ >= leerDesde)
					coordenadas[i++] = Double.parseDouble(linea);
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}

	public static VectorMath getAleatorio(int n){
		VectorMath v = new VectorMath(n);
		Random r = new Random();
		for (int i = 0; i < n; i++){
			v.coordenadas[i] = r.nextInt((i + 1) * 2) + 1;
		}
		return v;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(coordenadas);
	}
	
	public String toStringVertical() {
		String resultado = this.dimension + "";
		for (double coord : this.coordenadas){
			resultado += "\r\n" + coord;
		}
		return resultado;
	}
	
	public VectorMath suma(VectorMath v2) throws DistDimException{
		if (dimension != v2.dimension){
			throw new DistDimException(" Distinta Dimension ");
		}
		
		VectorMath aux = new VectorMath(dimension);
		
		for (int i = 0; i < dimension; i++){
			aux.coordenadas[i] = this.coordenadas[i]+ v2.coordenadas[i];
		}
		
		return aux;
	}
	
	
	public VectorMath resta(VectorMath v2) throws DistDimException{
		if (dimension != v2.dimension){
			throw new DistDimException(" Distinta Dimension ");
		}
		
		VectorMath aux = new VectorMath(dimension);
		
		for (int i = 0; i < dimension; i++){
			aux.coordenadas[i] = this.coordenadas[i] - v2.coordenadas[i];
		}
		
		return aux;
	}
	
	public VectorMath producto(double num){
		VectorMath aux = new VectorMath(dimension);
		
		for (int i = 0; i < dimension; i++){
			aux.coordenadas[i] = this.coordenadas[i] * num;
		}
		
		return aux;
	}


	
	@Override
	public Object clone(){
		return new VectorMath(dimension, coordenadas);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(coordenadas);
		result = prime * result + dimension;
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
		VectorMath other = (VectorMath) obj;
		if (!Arrays.equals(coordenadas, other.coordenadas))
			return false;
		if (dimension != other.dimension)
			return false;
		return true;
	}

	public double normaUno(){
		double suma = 0; 
		for (int i = 0; i < dimension; i++){
			suma += Math.abs(coordenadas[i]);
		}
		
		return suma;
	}
	
	public double normaDos(){
		double suma = 0; 
		for (int i = 0; i < dimension; i++){
			suma += coordenadas[i] * coordenadas[i];
		}
		
		return Math.sqrt(suma);
	}
	
	public double normaInfinito(){
		double [] aux = coordenadas;
 
		for (int i = 0; i < dimension; i++){
			aux[i] = Math.abs(aux[i]);
		}
		
		return Arrays.stream(aux).max().getAsDouble();
	}
	
	public double productoVectorial(VectorMath v2){
		double resultado = 0;
		
		for (int i = 0; i < dimension; i++){
			resultado += coordenadas[i] * v2.coordenadas[i];
		}
		
		return resultado;
	}
	
}
