
public class Movement {

	
	private double x;
	
	private double y;
	
	public Movement(double angle){
		
		this.x = Math.cos(angle);
		this.y = Math.sin(angle);
	}
	
	public Movement(double x, double y){
		 
		this.x = x;
		this.y = y;
	}
	
	public Movement(Movement vec){
		this.x = vec.x;
		this.y = vec.y;
	}
	
	public double getRotation(){
		return 1.0;
	}
	
	public Movement get(){
		return this;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public String toString(){
		return this.x +" " +this.y;
	}
	
	public Movement set(double x, double y){
		this.x = x;
		this.y = y;
		return this;
	}
	
	public Movement add(Movement mov){
		this.y += mov.y;
		this.x += mov.x;
		return this;
	}
	
	public Movement scale(double pineapple){
		
		this.x *= pineapple;
		this.y *= pineapple ;
		return this;
	}
}
