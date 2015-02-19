

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;



public class Mouse {
	protected double x;
	
	protected double y;
	
	protected Point mouseAt;
	
	public Mouse(){
		PointerInfo currentMouse = MouseInfo.getPointerInfo();
		
		mouseAt = currentMouse.getLocation();
		
		x = mouseAt.getX();
		y = mouseAt.getY();
		
	}
	
	public void update(){
		PointerInfo currentMouse = MouseInfo.getPointerInfo();
		mouseAt = currentMouse.getLocation();
		x = mouseAt.getX();
		y = mouseAt.getY();
		
	}
	
	public void update(double x, double y){
		this.x=x;
		this.y=y;
		
	}
	
	public void update(Point a){
		this.x = a.getX();
		this.y = a.getY();
	}
	
	public void updatePoint(){
		this.mouseAt.x = (int) this.x;
		this.mouseAt.y = (int) this.y;
		
	}
	public Point getPoint(){
		updatePoint();
		return mouseAt;
	}
	public String toString(){
		return x+" "+y;
	}
}
