
public class Mesada implements Comparable<Mesada> { 
	  private int largo; 
	  private int ancho; 
	   
	  public Mesada(int largo, int ancho){ 
	    this.largo = largo; 
	    this.ancho = ancho; 
	  }
	  
	  public int getArea(){
	    return this.largo * this.ancho;
	  }
	  
	  public boolean puedeApilar(Mesada m2){
		  return (this.largo >= m2.largo && this.ancho >= m2.ancho) || 
				 (this.ancho >= m2.largo && this.largo >= m2.ancho);
	  }
	 
	  @Override 
	  public int compareTo(Mesada m2) { 
	    if (this.getArea() > m2.getArea()) return -1;
	    return 1;
	    // Ordeno de mayor a menor por superficie
	  } 
	 
	  @Override 
	  public String toString() { 
	    return "[largo=" + largo + ", ancho=" + ancho + "]"; 
	  }
}
