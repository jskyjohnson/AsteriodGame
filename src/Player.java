import java.awt.*;


/*
 * Controlling Entities of ships in the game, or the actual person "Playing" the game.
 * 
 */
public class Player extends Actor {
	
	private boolean thrustingUp;
	
	private boolean thrustingDown;
	
	private boolean rotateingLeft;
	
	private boolean rotatingRight;
	
	private Movement position;
	
	private Movement velocity;
		
	public Player(){
		position = new Movement(300, 300);
		
	}
	
	public void update(Dictator d){
		super.update(d);
		
		
	}
	
	public String toString(){
		String stringy = position.toString(); 
		
		return stringy;
	}

	public Movement getPosition(){
		return position;	
	}
	
	public void rotateLeft(boolean inc) {
		// TODO Auto-generated method stub
		this.rotateingLeft = inc;
	}

	public void rotateRight(boolean inc) {
		// TODO Auto-generated method stub
		this.rotatingRight = inc;
	}

	public void thrustDown(boolean inc) {
		// TODO Auto-generated method stub
		this.thrustingDown = inc;
	}

	public void thrustUp(boolean inc) {
		// TODO Auto-generated method stub
		this.thrustingUp = inc;
	}
	
	public void draw(Graphics2D g, Dictator dic){
		
		// TODO add check for pause, or thrust, or what ever, i don't really know
		if(true){
			g.drawLine(-10,  -8,  10,  0);
			g.drawLine(-10, 8, 10, 0);
			g.drawLine(-6, -6, -6, 6);
		}
	}

	
	
}
