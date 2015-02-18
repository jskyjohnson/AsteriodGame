
import java.awt.Color;
import java.awt.Graphics2D;


public class Star {
	private double x;
	private double y;
	private double brightness;
	private Color color;
	private int colorDir = 1;
	private double size;
	private double frame;
	public Star(double mx, double my, double mbrightness, double s){
		x = mx;
		y = my;
		brightness = mbrightness;
		color = Color.WHITE;
		size = s;
	}
	void drawStar(Graphics2D gg){
		update();
		gg.setColor(color);
		gg.drawOval((int)x,(int) y, (int) size,(int) size);
	}
	void update(){
		twinkle();
	}
	private Color twinkle(){
		Color k = color;
		int r = k.getRed();
		if(r+colorDir>=255){
			colorDir = -1;
			r = r+colorDir;
		}else if(r+colorDir<=0){
			r = r-colorDir;
			colorDir = 1;
		}
		
		int g = k.getGreen();
		g = g+colorDir;
		int b = k.getRed();
		b = b+colorDir;
		Color f = new Color(r,g,b);
		return f;
	}
	
}
