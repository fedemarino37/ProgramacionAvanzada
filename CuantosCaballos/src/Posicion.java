
public class Posicion {
	private int x, y;

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

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
	
	
}
