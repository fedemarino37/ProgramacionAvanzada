package vectorMath;

public class SEL {
	private VectorMath b; // Términos independientes
	private MatrizMath matriz;
	private MatrizMath inversa;
	private static final double EPSILON = 1e-8;
	
	public SEL(MatrizMath matriz, VectorMath b){
		this.matriz = matriz;
		this.b = b;
	}
	
	public SEL(String path){
		this.matriz = new MatrizMath(path);
		this.b = new VectorMath(path, this.matriz.getDimension());
	}
	
	public MatrizMath getMatriz(){
		return this.matriz;
	}
	
	public VectorMath getB(){
		return this.b;
	}
	
	public VectorMath resolver(){
		VectorMath resultado = null;
		try {
			this.inversa = matriz.inversa();
			resultado = inversa.producto(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public double calcularErrorSolucion() throws Exception{
		MatrizMath identidadPrima = this.matriz.producto(this.inversa);
		double maxError = 0;
		for (int i = 0; i < identidadPrima.getFilas(); i++){
			double error = Math.abs(identidadPrima.getMatriz()[i][i] - 1);
			if (error > maxError)
				maxError = error;
		}
		if (maxError > EPSILON)
			throw new Exception("La solución encontrada tiene un error mayor a " + EPSILON);
		return maxError;
	}
}
