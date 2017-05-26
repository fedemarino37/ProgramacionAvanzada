
public class Posicion {
	private int x;
	private int y;
	
	public Posicion(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int distanciaA(int x2, int y2) {
		return Math.abs(x - x2) + Math.abs(y - y2);
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
}
