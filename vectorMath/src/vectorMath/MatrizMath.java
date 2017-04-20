package vectorMath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class MatrizMath {

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
	
	// Intercambia dos filas
	private void swap(int i, int j){
		for (int k = 0; k < columnas; k++)
		{
			double temp = matriz[i][k];
			matriz[i][k] = matriz[j][k];
			matriz[j][k] = temp;
		}
	}
	
	
	public void inversa() throws Exception{
		if (filas != columnas) 
			throw new Exception("No se puede calcular la inversa. La matriz debe ser cuadrada");
		
		// QUIQUE: Todo esto hay que meterlo en la clase MatrizExtendida (constructor con MatrizMath, método swap, subirPivot, etc.)
		// Matriz extendida (A | I)
		MatrizMath resultado = new MatrizMath(filas, columnas * 2);
		
		// Lleno la matriz extendida
		for (int i = 0; i < filas; i++){
			for (int j = 0; j < columnas * 2; j++){
				if (j < columnas)
					resultado.matriz[i][j] = this.matriz[i][j];
				else if (j % columnas == i)
					resultado.matriz[i][j] = 1;
			}
		}
		
		// Eliminación bajo la diagonal principal
		// Para cada columna:
		for (int i = 0; i < columnas; i++){
			// Busco el pivot
			int p_idx = i;
			double p_val = resultado.matriz[i][i];
			
			// Busco el máximo y su posición
			for (int j = i + 1; j < resultado.filas; j++) {
				double val = resultado.matriz[j][i];
				if (Math.abs(val) > p_val){
					p_idx = j; 
					p_val = val;
				}
			}			
			if (p_val == 0) {
				throw new Exception("No hay solución porque la matriz es singular");
			}
			if (p_idx != i) resultado.swap(p_idx, i);
			
			// Eliminación para cada fila bajo la diagonal principal
			for (int j = i + 1; j < resultado.filas; j++){ 
				double d = resultado.matriz[j][i] / resultado.matriz[i][i];
				for (int k = i; k < resultado.columnas; k++){
					resultado.matriz[j][k] -= d * resultado.matriz[i][k];
				}
			}
		}
		
		// Eliminación sobre la diagonal principal
		// Para cada columna:
		for (int i = columnas - 1; i > 0; i--){
			for (int j = i - 1; j >= 0; j--){ 
				double d = resultado.matriz[j][i] / resultado.matriz[i][i];
				for (int k = i; k < resultado.columnas; k++){
					resultado.matriz[j][k] -= d * resultado.matriz[i][k];
				}
			}
		}
		
		// Divido para hacerla identidad
		for (int i = 0; i < filas; i++)
	    {
	        double d = resultado.matriz[i][i];
	        for (int j = 0; j < resultado.columnas; j++)
	        	resultado.matriz[i][j] = resultado.matriz[i][j] / d; 
	    }
		
		System.out.println(resultado);
	}
}
