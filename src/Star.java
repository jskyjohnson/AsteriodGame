import java.awt.Color;
import java.awt.Graphics2D;


public class Star {
	private double x;
	private double y;
	private double brightness;
	private Color color;
	private double size;
	
	public Star(double mx, double my, double mbrightness, Color mcolor, double s){
		x = mx;
		y = my;
		brightness = mbrightness;
		color = mcolor;
		size = s;
	}
	void drawStar(Graphics2D gg){
		
		gg.setColor(color);
		gg.drawOval((int)x,(int) y, (int) size,(int) size);
	}
	void update(){
		
	}
	
}
