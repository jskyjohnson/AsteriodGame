

import java.awt.*;



public abstract class Actor {
	
	/**
	 *Movement Vector Thing
	 */
	protected Movement velocity;
	
	protected Position position;
	/**
	 * The rotation off of north of the object
	 */
	private double radius;
	
	protected double speed;
	
	protected double rotation;
	
	/*
	 * Boolean check for if needs to be removed
	 */
	private boolean needsRemoval;
	
	public Actor(Position position, Movement velocity, double radius){
		this.velocity = velocity;
		this.position = position;
		this.radius = radius;
		this.needsRemoval = false;
		
		
	}
	
	/**
	 * @param amount
	 */
	public void rotate(double amount){
		this.rotation += amount;
		this.rotation %= Math.PI*2;
	}
	
	public double getRotation(){
		return rotation;
	}
	
	public Position getPosition(){
		return position;
	}
	
	public Movement getVelocity(){
		return velocity;
	}
	
	public boolean removeCheck(){
		return needsRemoval;
	}
	
	public boolean remove(){
		this.needsRemoval = true;
		return true;
	}
	
	public void update(Dictator d){
		
		if(position.x < 0.0f){
			position.x += d.SIZE_X;
		}
		if(position.y < 0.0f){
			position.y += d.SIZE_Y;
		}
		position.x %= d.SIZE_X;
		position.y %= d.SIZE_Y;
	}
	
	public abstract void draw(Graphics2D g, Dictator d);
}
