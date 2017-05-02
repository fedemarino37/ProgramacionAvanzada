import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;

import vectorMath.*;

public class Main {

	public static void main(String[] args) throws Exception {
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
		
		String path = "archivos_in/04_caso2x2cCasiLDsimple.in";
		String fileName = Paths.get(path).getFileName().toString();
		if (fileName.indexOf(".") > 0)
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		String pathSalida = "archivos_out/" + fileName + ".out";
		
		try (PrintWriter printWriter = new PrintWriter(new FileWriter(pathSalida, false))){
			SEL sel = new SEL(path);
			VectorMath resultado = sel.resolver();
			if (resultado != null){
				printWriter.println(resultado.toStringVertical());
				printWriter.println();
				printWriter.println(sel.calcularErrorSolucion());
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}

}
