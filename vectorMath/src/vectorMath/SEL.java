package vectorMath;

public class SEL {
	private VectorMath b; // Términos independientes
	private MatrizMath matriz;
	
	public SEL(MatrizMath matriz, VectorMath b){
		this.matriz = matriz;
		this.b = b;
	}
	
	public SEL(String path){
		this.matriz = new MatrizMath(path);
		this.b = new VectorMath(path, this.matriz.getDimension());
		System.out.println(this.matriz);
		System.out.println(this.b);
		System.out.println();
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
