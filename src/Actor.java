import java.awt.*;



public abstract class Actor {
	
	/*
	 * Speed of object
	 */
	private double speed;
	/*
	 * X
	 */
	private double x;
	/*
	 * Y
	 */
	private double y;
	
	/**
	 * The rotation off of north of the object
	 */
	private double angel;
	/*
	 * The distance from edge to center of the object
	 */
	private double radius;
	/*
	 * Boolean check for if needs to be removed
	 */
	private boolean needsRemoval;
	
	public Actor(){
		
		
	}
}
