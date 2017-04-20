import vectorMath.*;

public class Main {

	public static void main(String[] args) throws DistDimException {
//		String path = "archivos_in/myVector.in";
//	
//		VectorMath vector = new VectorMath(path);
//		VectorMath vector2 = new VectorMath(path);
//		
//		System.out.println(vector);
//		System.out.println(vector.suma(vector2));
//		System.out.println(vector.producto(2));
//		System.out.println(vector.resta(vector2));
//		System.out.println((VectorMath)vector.clone());
//		System.out.println(vector.equals(vector.clone()));
//		System.out.println(vector.normaUno());
//		System.out.println(vector.normaDos());
//		System.out.println(vector.normaInfinito());
//		VectorMath v3 = new VectorMath(3, new double []{1,2,3});
//		VectorMath v4 = new VectorMath(3, new double []{3, 2, 1});
//		System.out.println(v3.productoVectorial(v4));
		
		String pathMatriz = "archivos_in/myMath.in";
		String pathMatriz2 = "archivos_in/myMath2.in";
		String pathMatrizSEL = "archivos_in/myMathSEL.in";
		String pathVectorSEL = "archivos_in/myVectorSEL.in";
//		MatrizMath m1 = new MatrizMath(pathMatriz);
//		MatrizMath m2 = new MatrizMath(pathMatriz);
//		MatrizMath m3 = new MatrizMath(pathMatriz2);
//		
//		System.out.println(new MatrizMath(pathMatriz));
//		System.out.println(m1.suma(m2));
//		System.out.println(m1.resta(m2));
//		System.out.println(m1.producto(m3));
//		
//		System.out.println(m1.producto(new VectorMath(2, new double []{3, 2})));
//		System.out.println(m1.producto((float)1.5));
		
		MatrizMath mSEL = new MatrizMath(pathMatrizSEL);
		VectorMath vSEL = new VectorMath(pathVectorSEL);
		SEL sel = new SEL(mSEL, vSEL);
		System.out.println(sel.resolver());
	}

}
