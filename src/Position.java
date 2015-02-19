



public class Position {
	public double x;
	
	public double y;
	
	public Position(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void add(Movement a){
		this.x += a.x;
		this.y += a.y;
	}
	
}
