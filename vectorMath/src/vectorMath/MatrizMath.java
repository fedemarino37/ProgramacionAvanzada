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
			this.columnas = Integer.parseInt(dimensiones[1]);
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
}
