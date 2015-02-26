package src;

public class Position {
	private double x;
	
	private double y;
	
	public Position(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public Position(Position position) {
		this.x = position.getX();
		this.y = position.getY();
	}

	public void add(Movement a){
		this.x += a.getX();
		this.y += a.getY();
	}
	
	public void set(Position a){
		this.x = a.getX();
		this.y = a.getY();
	}

	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}
	
	public double getY(){
		
		return y;
	}
	public void setX(double a) {
		// TODO Auto-generated method stub
		 x = a;
	}
	
	public void setY(double a){
		
		y = a;
	}
	
	public void addX(double a) {
		// TODO Auto-generated method stub
		 x += a;
	}
	
	public void addY(double a){
		
		y += a;
	}
	public String toString(){
		
		return x+" "+y;
	}
	public double getDistanceToSquared(Position a){
		
		double dx = this.x - a.x;
		double dy = this.y - a.y;
		return (dx * dx + dy * dy);
		
	}

	
}
