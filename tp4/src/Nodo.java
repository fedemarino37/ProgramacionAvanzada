
public class Nodo implements  Comparable<Nodo> {
	int id;
	int grado = -1;
	int color;
	
	public Nodo(int id, int grado) {
		this.id = id;
		this.grado = grado;
	}
	
	@Override
	public int compareTo(Nodo n) {
		if(this.grado < n.grado)
			return -1;
		if(this.grado > n.grado)
			return 1; 
		return 0;
	}
	
	public int getId() {
		return id;
	}
	
	public void setColor(int n){
		color = n;
	}
	
	public int getColor(){
		return color;
	}
}
