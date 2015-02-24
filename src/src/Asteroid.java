package src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.Random;

public class Asteroid extends Actor {
	
	private int frame;
	
	public Polygon polygon;
	
	private Random rand = new Random();

	public Asteroid(Dictator d) {
		super(new Position(d.rand.nextInt(d.SIZE_X), d.rand.nextInt(d.SIZE_Y)), new Movement(d.rand.nextDouble()*2-1,d.rand.nextDouble()*2-1), 10);
		System.out.println(this.getPosition().toString());
		
		
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
		g.drawPolygon(polygon);;
		
	}
	
}
