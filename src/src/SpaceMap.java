package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JPanel;

/**
 * 
 */

/**
 * This is the world map actor
 * 
 * @author Sky Johnson
 */
public class SpaceMap extends JPanel 
	
{

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
	 * Random
	 */

	/*
	 * dictator
	 */
	private Dictator dictator;

	/*
	 * Constructor for SpaceMap
	 */
	SpaceMap(Dictator dic) {
		this.dictator = dic;
		// set window
		setPreferredSize(new Dimension(dic.SIZE_X, dic.SIZE_Y));
		setBackground(Color.BLACK);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setColor(Color.WHITE);

		AffineTransform identity = graphics.getTransform();
		graphics.drawString(dictator.getScore(), 40, dictator.SIZE_Y - 40);
		// draws stars

		for (Star i : dictator.starlist) {
			graphics.setTransform(identity);
			i.drawStar(graphics);
		}
		
		
		
		for(int i = 0; i < dictator.getActor().size(); i++){
			drawActor(graphics, dictator.getActor().get(i), dictator.getActor().get(i).getPosition());
			graphics.setTransform(identity);
		}
		//Interates threw all Actor Objects
//		Iterator<Actor> iter = dictator.getActor().iterator();
//		while(iter.hasNext()){
//			Actor current = iter.next();
//			drawActor(graphics, current, current.getPosition());
//			graphics.setTransform(identity);
//		}
	}
		
	//Rotates and translates the 2dGraphics to oppropriate position object
	private void drawActor(Graphics2D graphics, Actor actor, Position lookingat) {
		// DRAWING STUFF NOT DONE
		graphics.translate(lookingat.getX(), lookingat.getY());
		double rotation = actor.getRotation();
		if (rotation != 0.0f) {
			graphics.rotate(actor.getRotation());
		}
		actor.draw(graphics, dictator);
	}

}
