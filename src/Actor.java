import java.awt.*;



public abstract class Actor {
	
	/*/
	 *Movement Vector Thing
	 */
	private Movement vector;
	/**
	 * The rotation off of north of the object
	 */
	private double radius;
	
	private double rotation;
	
	/*
	 * Boolean check for if needs to be removed
	 */
	private boolean needsRemoval;
	
	public Actor(){
		this.vector = new Movement(0,0);
		this.needsRemoval = false;
	}
	
	public void rotate(double amount){
		this.rotation += amount;
		this.rotation %= Math.PI*2;
	}
	
	public double getRotation(){
		return rotation;
	}
	
	public Movement getPosition(){
		return vector;
	}
	
	public boolean remove(){
		this.needsRemoval = true;
		return true;
	}
	
	public void update(Dictator d){
		
	}
	
	public abstract void draw(Graphics2D g, Dictator d);
}
