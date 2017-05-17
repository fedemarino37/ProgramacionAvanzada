public class Posicion {
	private int x;
	private int y;
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Posicion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Posicion pos2) {
		return this.x == pos2.x && this.y == pos2.y;
	}
	
}
