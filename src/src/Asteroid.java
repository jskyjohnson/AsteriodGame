package src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

public class Asteroid extends Actor {
	
	private int frame;
	
	public final Polygon polygon;
	

	public Asteroid(Dictator d) {
		super(new Position(0.0, 0.0), new Movement(0.0,0.0), 10);
		Position temp = new Position(4.0, 40.0);
		Movement tempv = new Movement(0.0,0.0);
		this.getPosition().set(temp);
		this.getVelocity().set(tempv);
		
		
		this.polygon = makeAsteroid(radius);
		rotation = 0;
		this.frame = 0;
	}

	private Polygon makeAsteroid(double radius) {
		// TODO Auto-generated method stub
		int[] xs = new int[5];
		int[] ys = new int[5];
		
		double angle = (2*Math.PI/5);
		
		for(int i = 0; i < 5; i++){
			xs[i] = (int)(radius*Math.sin(i*angle));
			ys[i] = (int)(radius*Math.cos(i*angle));
		}
		
		
		return new Polygon(xs,ys,5);
	}

	public void update(Dictator d) {
		super.update(d);
		this.frame++;
		this.getPosition().add(this.getVelocity());
		
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
	
	public void draw(Graphics2D g, Dictator d){
		g.setColor(Color.RED);
		g.translate(0, 0);
		g.drawPolygon(polygon);;
		
	}
	
}
