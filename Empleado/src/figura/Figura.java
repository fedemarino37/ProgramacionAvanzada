package figura;

public abstract class Figura {
	private String color;
	
	public Figura(){
		color = "azul";
	}
	
	public String dameColor(){
		return color;
	}
	
	public abstract double area();
}
