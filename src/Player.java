import java.awt.*;

/*
 * Controlling Entities of ships in the game, or the actual person "Playing" the game.
 * 
 */
public class Player extends Actor {

	private final double ACCELERATION = 0.09;

	private boolean thrustingUp;

	private boolean thrustingDown;

	private boolean thrustingLeft;

	private boolean thrustingRight;

	private int frame;

	public Player(Dictator d) {
		super(new Position(d.SIZE_X / 2.0, d.SIZE_Y / 2.0), new Movement(0.0,
				0.0), 10.0);
		speed = 0;
		rotation = 0;
		this.frame = 0;

		thrustingUp = false;
		thrustingDown = false;
		thrustingLeft = false;
		thrustingRight = false;
	}

	public void update(Dictator d) {
		this.frame++;
		checkAcceleration();
		position.add(velocity);
		rotationization(d.mouse.getPoint());
		super.update(d);

	}
	public void rotationization(Point e){
		double firstx = position.x;
		double firsty = position.y;
		
		double tox = e.getX();
		double toy = e.getY();
		
		double ydiff = toy-firsty;
		double xdiff = tox-firstx;
		if(xdiff<0){
			super.setRotation(Math.atan(ydiff/xdiff)-Math.PI);
		}else{
			super.setRotation(Math.atan((ydiff/xdiff)));
		}
		
		
	}

	public String toString() {
		String stringy = super.getPosition().toString();

		return stringy;
	}

	public Position getPosition() {
		return super.getPosition();
	}

	public int getFrame() {
		return frame;
	}

	public void thrustingLeft(boolean inc) {
		// TODO Auto-generated method stub
		this.thrustingLeft = inc;

	}

	public void thrustingRight(boolean inc) {
		// TODO Auto-generated method stub
		this.thrustingRight = inc;

	}

	public void thrustingDown(boolean inc) {
		// TODO Auto-generated method stub
		this.thrustingDown = inc;

	}

	public void thrustingUp(boolean inc) {
		// TODO Auto-generated method stub
		this.thrustingUp = inc;

	}

	public boolean getThrustingLeft() {
		// TODO Auto-generated method stub
		return thrustingLeft;

	}

	public boolean getThrustingRight() {
		// TODO Auto-generated method stub
		return thrustingRight;

	}

	public boolean getThrustingDown() {
		// TODO Auto-generated method stub
		return thrustingDown;

	}

	public boolean getThrustingUp() {
		// TODO Auto-generated method stub
		return thrustingUp;

	}

	public void checkAcceleration() {
		if (thrustingUp) {
			velocity.addY(-ACCELERATION);
		}
		if (thrustingDown) {
			velocity.addY(ACCELERATION);
		}
		if (thrustingLeft) {
			velocity.addX(-ACCELERATION);
		}
		if (thrustingRight) {
			velocity.addX(ACCELERATION);
		}

		velocity.scale(0.993);

	}

	public void draw(Graphics2D g, Dictator dic) {

		// TODO add check for pause, or thrust, or what ever, i don't really
		// know
		if (true) {
			g.drawLine(-10, -8, 10, 0);
			g.drawLine(-10, 8, 10, 0);
			g.drawLine(-6, -6, -6, 6);
		}
	}

}
