
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
	private int group;
	
	
	private boolean rup;
	private boolean gup;
	private boolean bup;
	public Star(double mx, double my, double mbrightness, double s, int group){
		x = mx;
		y = my;
		brightness = mbrightness;
		color = new Color(group, group, group);
		size = s;
		this.group = group;
		
		rup = false;
		gup = false;
		bup = false;
	}
	void drawStar(Graphics2D gg){
		update();
		gg.setColor(color);
		gg.drawOval((int)x,(int) y, (int) size,(int) size);
	}
	void update(){
		twinkle();
	}
	private void twinkle(){
		Color k = color;
		int r = k.getRed();
		if(rup){
			if(r+1 >= 255){
			r -= 1;
			rup = false;
			}else{
				r+=1;
			}
		}else if(!rup){
			if(r-1 <= 0){
				r += 1;
				rup = true;
				}else{
					r-=1;
				}
		}
		
		int g = k.getGreen();
		if(gup){
			if(g+1 >= 255){
			g -= 1;
			gup = false;
			}else{
				g+=1;
			}
		}else if(!gup){
			if(g-1 <= 0){
				g += 1;
				gup = true;
				}else{
					g-=1;
				}
		}
		int b = k.getRed();
		if(bup){
			if(b+1 >= 255){
			b -= 1;
			bup = false;
			}else{
				b+=1;
			}
		}else if(!bup){
			if(b-1 <= 0){
				b += 1;
				bup = true;
				}else{
					b-=1;
				}
		}
		Color f = new Color(r,g,b);
		color = f;
	}
	
}
