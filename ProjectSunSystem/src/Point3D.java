public class Point3D {
	double	x;
	double	y;
	double	z;

	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Point3D() {}

	protected double getX() {
		return x;
	}

	protected void setX(double x) {
		this.x = x;
	}

	protected double getY() {
		return y;
	}

	protected void setY(double y) {
		this.y = y;
	}

	protected double getZ() {
		return z;
	}

	protected void setZ(double z) {
		this.z = z;
	}

	protected void setAll(double x, double y, double z) {
		setX(x);
		setY(y);
		setZ(z);
	}

}
