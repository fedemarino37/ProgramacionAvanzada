package punto;

public class Punto3D extends Punto2D{

	private double z;	

	public Punto3D(){
		
	}
	public Punto3D(double x, double y, double z) {
		super(x, y);
		this.z = z;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Punto3D other = (Punto3D) obj;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}
	
	@Override
	public Punto3D clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new Punto3D(x, y, z);
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	
	@Override
	public String toString() {
		return "Punto3D [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	
}
