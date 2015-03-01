package src;

import java.awt.Color;
import java.awt.Graphics2D;


public class Star {
	private double x;
	private double y;
	private Color color;
	private double size;
	private boolean sizedir;
	private boolean rup;
	private boolean gup;
	private boolean bup;
	public Star(double mx, double my, double mbrightness, int group){
		x = mx;
		y = my;
		size = group*20/16;
		color = new Color(group*255/16, group*255/16, group*255/16);
		sizedir = true;
		
		rup = false;
		gup = false;
		bup = false;
	}
	void drawStar(Graphics2D gg){
		
		gg.setColor(color);
		gg.drawOval((int)x,(int) y, (int) 1 ,(int) 1);
	}
	void update(Dictator dic){
		x+=.1*dic.StarCaptain.getVelocity().getX();
		y+=.1*dic.StarCaptain.getVelocity().getY();
		
		if(x < 0.0f){
			x += dic.SIZE_X;
		}
		if(y < 0.0f){
			y += dic.SIZE_Y;
		}
		x %= dic.SIZE_X;
		y %= dic.SIZE_Y;
		
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
		if(sizedir){
			size++;
			if(size>=7){
				sizedir=false;
			}
		}else{
			size--;
			if(size<=0){
				sizedir=true;
			}
		}
		
		Color f = new Color(r,g,b);
		color = f;
	}
	
}
