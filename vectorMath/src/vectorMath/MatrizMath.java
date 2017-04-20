package vectorMath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class MatrizMath {

	private static final double EPSILON = 1e-8;
	private double [][]matriz;
	private int columnas;
	private int filas;
				
	public MatrizMath(String path){
		try (Scanner scanner = new Scanner(new File(path));){
			String[] dimensiones = scanner.nextLine().split(" ");
			this.filas = Integer.parseInt(dimensiones[0]);
			if (dimensiones.length > 1)
				this.columnas = Integer.parseInt(dimensiones[1]);
			else 
				this.columnas = this.filas; // Es cuadrada
			
			this.matriz = new double[filas][columnas];
			
			int i, j;
			while (scanner.hasNextLine()){
				String[] componentes = scanner.nextLine().split(" ");
				i = Integer.parseInt(componentes[0]);
				j = Integer.parseInt(componentes[1]);
				this.matriz[i][j] = Double.parseDouble(componentes[2]);
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public MatrizMath(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		matriz = new double [filas][columnas];
	}

	public MatrizMath(int filas, int columnas, double [][] matriz) {
		this.filas = filas;
		this.columnas = columnas;
		this.matriz = matriz;
	}
	
	@Override
	public String toString() {
		String resultado = "";
		for (int i = 0; i < this.filas; i++){
			resultado += Arrays.toString(matriz[i]) + "\n";
		}
		return resultado;
	}
	
	public MatrizMath suma(MatrizMath m2) throws DistDimException{
		if (filas != m2.filas || columnas != m2.columnas){
			throw new DistDimException(" Distinta Dimension ");
		}
		
		MatrizMath resultado = new MatrizMath(filas, columnas);
		
		for(int i = 0; i < filas; i++){
			for(int j = 0; j < columnas; j++){
				resultado.matriz[i][j] = matriz[i][j] + m2.matriz[i][j];
			}
		}
		return resultado;
	}
	
	public MatrizMath resta(MatrizMath m2) throws DistDimException{
		if (filas != m2.filas || columnas != m2.columnas){
			throw new DistDimException(" Distinta Dimension ");
		}
		
		MatrizMath resultado = new MatrizMath(filas, columnas);
		
		for(int i = 0; i < filas; i++){
			for(int j = 0; j < columnas; j++){
				resultado.matriz[i][j] = matriz[i][j] - m2.matriz[i][j];
			}
		}
		return resultado;
	}
	
	public MatrizMath producto(MatrizMath m2){
		if (columnas != m2.filas)
			  throw new RuntimeException("No se pueden multiplicar las matrices");
		
		MatrizMath m_res = new MatrizMath(filas, m2.columnas);
		
		for(int i = 0; i < filas; i++){
			for(int j = 0; j < m2.columnas; j++){
				for(int k = 0; k < columnas; k++){
					m_res.matriz[i][j] += matriz[i][k] * m2.matriz[k][j];
				}
			}
		}
		return m_res;
	}
	
	public VectorMath producto(VectorMath v2){
		if (columnas != v2.dimension)
			  throw new RuntimeException("No se puede multiplicar la matriz con el vector");
		
		VectorMath v_res = new VectorMath(filas);
		
		for(int i = 0; i < filas; i++){
			for(int j = 0; j < columnas; j++){				
				v_res.coordenadas[i] += matriz[i][j] * v2.coordenadas[j];				
			}
		}
		return v_res;
	}
	
	public MatrizMath producto(float k){
		MatrizMath res = new MatrizMath(filas, columnas);
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++){
				res.matriz[i][j] = this.matriz[i][j] * k;
			}
		}
		return res;
	}
	
	private void swap(int row1, int row2){
		double[] temp = matriz[row1];
		matriz[row1] = matriz[row2];
		matriz[row2] = temp;
	}
	
	public MatrizMath inversa() throws Exception{
		if (filas != columnas) 
			throw new Exception("No se puede calcular la inversa. La matriz no es cuadrada");
		
		// TODO: Chequear por determinante
		
		MatrizMath resultado = new MatrizMath(filas, columnas * 2);
		
		// Armo la matriz extendida (A | I)
		for (int i = 0; i < filas; i++){
			for (int j = 0; j < columnas; j++){
				resultado.matriz[i][j] = this.matriz[i][j];
			}
		}
		for (int i = 0; i < filas; i++){
			resultado.matriz[i][filas + i] = 1; 
		}
		
		// Pivoteo para cada columna
		for (int p = 0; p < columnas; p++){
			// Busco el pivot
			int p_idx = p;
			double p_val = Math.abs(resultado.matriz[p][p]);
			
			for (int j = p + 1; j < resultado.filas; j++) {
				double val = Math.abs(resultado.matriz[j][p]);
				if (val > p_val){
					p_idx = j; 
					p_val = val;
				}
			}
			if (p_val <= EPSILON) {  
				throw new Exception("No hay solución porque la matriz es singular o está muy cercana a serlo");
			}
			resultado.swap(p_idx, p);
			resultado.pivot(p, p);
		}
		
		MatrizMath inversa = new MatrizMath(filas, columnas);
		for (int i = 0; i < filas; i++){
			for (int j = 0; j < columnas; j++){
				inversa.matriz[i][j] = resultado.matriz[i][j + columnas];
			}
		}
		return inversa;
	}
	
	// Eliminación de Gauss-Jordan pivoteando en (p, q)
	private void pivot(int p, int q){
		for (int i = 0; i < filas; i++){
			double d = matriz[i][q] / matriz[p][q];
			if (i != p){
				for (int j = q; j < columnas; j++){ // Hago 0 desde la columna del pivot en adelante, porque lo anterior ya es 0
					matriz[i][j] -= d * matriz[p][j]; // Fi = Fi - d*Fp
				}
			}
		}
		double d = matriz[p][q];
		for (int j = q; j < columnas; j++)
            matriz[p][j] /= d;
	}
}
