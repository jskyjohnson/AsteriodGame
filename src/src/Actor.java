package src;

import java.awt.*;



public abstract class Actor {
	
	/**
	 *Movement Vector Thing
	 */
	private Movement velocity = new Movement(0.0, 0.0);
	
	private Position position = new Position(0.0, 0.0);
	/**
	 * The rotation off of north of the object
	 */
	public double radius;
	
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
	
	public void setRotation(double angle){
		rotation = angle;
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
	
	public void setPosition(Position a){
		position = a;
	}
	
	public void setMovement(Movement a){
		velocity = a;
	}
	
	public boolean removeCheck(){
		return needsRemoval;
	}
	
	public boolean remove(){
		this.needsRemoval = true;
		return true;
	}
	
	public void update(Dictator d){
		
		if(position.getX() < 0.0f){
			position.addX(d.SIZE_X);
		}
		if(position.getY() < 0.0f){
			position.addY(d.SIZE_Y);
		}
		double posx = position.getX();
		position.setX(posx %= d.SIZE_X);
		double posy = position.getY();
		position.setY( posy %= d.SIZE_Y);
	}
	
	public abstract void draw(Graphics2D g, Dictator d);
}
