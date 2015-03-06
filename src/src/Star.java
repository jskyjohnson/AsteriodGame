package src;

import java.awt.Color;
import java.awt.Graphics2D;


public class Star {
	private double x;
	private double y;
	private Color color;
	private int size;
	private int tosize;
	private boolean sizedir;
	private boolean rup;
	private boolean gup;
	private boolean bup;
	public Star(double mx, double my, double mbrightness, int group){
		x = mx;
		y = my;
		size = 1;
		tosize = 20;
		color = new Color(group*255/16, group*255/16, group*255/16);
		sizedir = true;
		
		rup = false;
		gup = false;
		bup = false;
	}
	void drawStar(Graphics2D gg){
		
		gg.setColor(color);
		gg.drawOval((int)x-(size/2),(int) y-(size/2), (int) size ,(int) size);
	}
	
	public void setColor(Color a){
		this.color = a;
	}
	public void setToSize(int toasize){
		tosize = toasize;
	}
	void update(Dictator dic){
		x+=.1*dic.StarCaptain.getVelocity().getX();
		y+=.1*dic.StarCaptain.getVelocity().getY();
		
		if(size!=tosize&& dic.getTime()%60 == 0){
			if(size>tosize){
				size--;
			}else if(tosize>size){
				size++;
			}
		}
		
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
		
		Color f = new Color(r,g,b);
		color = f;
	}
	
}
