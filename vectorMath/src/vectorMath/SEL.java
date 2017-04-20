package vectorMath;

public class SEL {
	private VectorMath b; // Términos independientes
	private MatrizMath matriz;
	
	public SEL(MatrizMath matriz, VectorMath b){
		this.matriz = matriz;
		this.b = b;
	}
	
	public VectorMath resolver(){
		VectorMath resultado = null;
		try {
			MatrizMath inversa = matriz.inversa();
			resultado = inversa.producto(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
}
