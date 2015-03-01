package src;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends Actor {
	private int frame;
	
	public Bullet(Dictator d){
		super(new Position(d.StarCaptain.getPosition()), new Movement(d.bulletSpeed*Math.cos(d.StarCaptain.rotation), d.bulletSpeed*Math.sin(d.StarCaptain.rotation)),1);
		edgeCollision = true;
		frame = 0;
		setColor(Color.WHITE);
	}
	
	public void update(Dictator d){
		super.update(d);
		this.frame++;
		this.getPosition().add(this.getVelocity());
		if(frame > 140){
			d.score--;
			remove();
		}
	}

	public void collided(Actor a, Dictator dic) {
		// TODO Auto-generated method stub
		if(a.getClass() == Asteroid.class){
			remove();
		}
	}

	public void draw(Graphics2D g, Dictator d) {
		// TODO Auto-generated method stub
		g.setColor(getColor());
		g.drawOval(0, 0, 2, 4);
	}
}
