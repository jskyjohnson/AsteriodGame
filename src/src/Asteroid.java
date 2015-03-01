package src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.Random;

public class Asteroid extends Actor {

	private int frame;

	public Polygon polygon;
	
	public int numberPoints;
	public double rotation;
	public double rotationspeed;
	public int size;

	public Asteroid(Dictator d) {
		super(new Position(d.rand.nextInt(d.SIZE_X), d.rand.nextInt(d.SIZE_Y)),
				new Movement(d.rand.nextDouble() * 2 - 1,
						d.rand.nextDouble() * 2 - 1), 10);
		numberPoints = d.rand.nextInt(10);
		this.rotation = d.rand.nextDouble() *(2*Math.PI);
		this.rotationspeed = d.rand.nextDouble();
		this.polygon = makeAsteroid(radius);
		rotation = 0;
		this.frame = 0;
		
	}

	private Polygon makeAsteroid(double radius) {
		// TODO Auto-generated method stub
		int[] xs = new int[numberPoints];
		int[] ys = new int[numberPoints];

		double angle = (2 * Math.PI / numberPoints);

		for (int i = 0; i < numberPoints; i++) {
			xs[i] = (int) (radius * Math.sin(i * angle));
			ys[i] = (int) (radius * Math.cos(i * angle));
		}

		return new Polygon(xs, ys, numberPoints);
	}

	public void update(Dictator d) {
		super.update(d);
		this.frame++;
		this.getPosition().add(this.getVelocity());
		rotation+= rotationspeed/10;
		rotation%=Math.PI*2;

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

	public void draw(Graphics2D g, Dictator d) {
		g.rotate(rotation);
		g.setColor(Color.RED);
		g.drawPolygon(polygon);

	}

	public void collided(Actor a, Dictator dic) {
		if (a.getClass() != Player.class) {
			if (a.getClass() != Asteroid.class) {
				split(dic);
				remove();
				dic.score += 100;
			}

		} else if (a.getClass() == Player.class) {
			remove();
			try {
				
			} catch (Exception e) {

			}
		}
	}

	private void split(Dictator dic) {
		// TODO Auto-generated method stub
		
	}

}
