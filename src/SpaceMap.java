import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

/**
 * 
 */

/**
 * This is the world map actor
 * @author Sky Johnson
 */
public class SpaceMap extends JPanel {
	
	/*
	 * Serial Version
	 */
	private static final long serialVersionUID = -3535839203384839672L;
	
	/*
	 * Title Font
	 */
	private static final Font TITLE_FONT = new Font("Dialog", Font.PLAIN, 25);
	/*
	 * Text font
	 */
	private static final Font SUBTITLE_FONT = new Font("Dialog", Font.PLAIN, 15);
	/*
	 * Size of jframe x
	 */
	private static final int SIZE_X = 600;
	/*
	 * Size of jframe y
	 */
	private static final int SIZE_Y = 600;
	/*
	 * Number of stars to be rendered in the frame
	 */
	private int NUMBER_STARS = 60;
	
	/*
	 * Stars arraylist
	 */
	private ArrayList<Star> starlist;
	/*
	 * Random
	 */
	private Random rand = new Random();
	/*
	 * dictator
	 */
	private Dictator dictator;
	
	/*
	 * Constructor for SpaceMap
	 */
	SpaceMap(Dictator dic){
		this.dictator = dic;
		// set window
		setPreferredSize(new Dimension(SIZE_X, SIZE_Y));
		setBackground(Color.BLACK);
		
		//Generates Stars
		starlist = new ArrayList<Star>();
		for(int i = 0; i < NUMBER_STARS; i++){		
			double a = (rand.nextDouble()*SIZE_X);
			double b = (rand.nextDouble()*SIZE_Y);
			double c = rand.nextDouble();
			Color d = Color.WHITE;
			double e = 1;
			
			Star startemp = new Star(a,b,c,d,e);
			starlist.add( startemp);
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D gg = (Graphics2D) g;
		gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gg.setColor(Color.WHITE);
	
		//draws stars
		for(Star i : starlist){
			i.drawStar(gg);
		}
		for(Actor i : dictator.actor){
			Movement currPosition =i.getPosition();
			drawActor(gg, i, currPosition.getX(), currPosition.getY());
		}
		
	}
	private void drawActor(Graphics2D g2d, Actor actor,double x, double y)
	{
		//DRAWING STUFF NOT DONE
		g2d.translate(x, y);
		double rotation = actor.getRotation();
		if(rotation != 0.0f){
			g2d.rotate(actor.getRotation());
		}
		actor.draw(g2d, dictator);
		
	}
}
