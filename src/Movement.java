
public class Movement {

	protected double x;

	protected double y;

	public Movement(double angle) {

		this.x = Math.cos(angle);
		this.y = Math.sin(angle);
	}

	public Movement(double x, double y) {

		this.x = x;
		this.y = y;
	}

	public Movement(Movement vec) {
		this.x += vec.x;
		this.y += vec.y;
	}

	public double getRotation() {
		return 1.0;
	}

	public Movement get() {
		return this;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public String toString() {
		return this.x + " " + this.y;
	}

	public Movement set(double x, double y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public Movement add(Movement mov) {
		this.y += mov.y;
		this.x += mov.x;
		return this;
	}

	public Movement normalize() {
		double length = getLengthSquared();
		if (length != 0.0f && length != 1.0f) {
			length = Math.sqrt(length);
			this.x /= length;
			this.y /= length;
		}
		return this;
	}

	public double getLengthSquared() {
		return (x * x + y * y);
	}

	public void addX(double h) {
		this.x += h;
	}

	public void addY(double h) {
		this.y += h;
	}

	public Movement scale(double pineapple) {

		this.x *= pineapple;
		this.y *= pineapple;
		return this;
	}

	public double getSpeed() {
		// TODO Auto-generated method stub
		return Math.sqrt((x * x) + (y * y));
	}
}
