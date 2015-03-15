package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
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
	private static final Font SUBTITLE_FONT = new Font("Dialog",
			Font.ROMAN_BASELINE, 15);

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
		
		if(!dictator.isGenerated()){
			drawTextCenter("Welcome to Asteroids!", TITLE_FONT, graphics, 0);
			drawTextCenter("Please type in a seed", SUBTITLE_FONT, graphics, 0);
			
			drawTextCenter(dictator.seed, SUBTITLE_FONT, graphics, 0);
			
			if(dictator.entered){
				dictator.setGenerated(true);
			}
		}

		if (!dictator.checkForRestart() && dictator.isGenerated()) {
			// DrawScores
			graphics.setFont(SUBTITLE_FONT);
			graphics.drawString("SCORE: " + dictator.getScore(), 40,
					dictator.SIZE_Y - 40);

			// draw Lives
			graphics.translate(dictator.SIZE_X - 100, dictator.SIZE_Y - 40);
			for (int i = 0; i < dictator.lives; i++) {
				graphics.drawLine(-8, 10, 0, -10);
				graphics.drawLine(8, 10, 0, -10);
				graphics.drawLine(-6, 6, 6, 6);
				graphics.translate(30, 0);
			}

			// draw Bullets
			graphics.setTransform(identity);
			graphics.translate(dictator.SIZE_X - 110, dictator.SIZE_Y - 80);
			for (int i = 0; i < dictator.BULLET_MAX - dictator.bulletCount; i++) {
				graphics.setColor(Color.WHITE);
				graphics.drawOval(0, 0, 2, 4);
				graphics.translate(10, 0);
			}
			// draw Stars Update
			for (Star i : dictator.starlist) {
				graphics.setTransform(identity);
				i.drawStar(graphics);
			}

			// Draw Actors Update
			for (int i = 0; i < dictator.getActor().size(); i++) {
				drawActor(graphics, dictator.getActor().get(i), dictator
						.getActor().get(i).getPosition());
				graphics.setTransform(identity);
			}

			// Situational and Menus

			// Paused
			if (dictator.paused) {
				drawTextCenter("PAUSED", TITLE_FONT, graphics, 0);
				drawTextCenter("Press Esc to Exit", SUBTITLE_FONT, graphics,
						-50);
				drawTextCenter("Press P to Unpause", SUBTITLE_FONT, graphics,
						-90);
				drawTextCenter("Press R to Restart", SUBTITLE_FONT, graphics,
						-70);

			}
			// End Game
			if (dictator.gameOver) {
				drawTextCenter("GAME OVER", TITLE_FONT, graphics, 0);
				drawTextCenter("Press Esc to Exit", SUBTITLE_FONT, graphics,
						-50);
				drawTextCenter("Press R to Restart", SUBTITLE_FONT, graphics,
						-70);
			}
		}

	}

	// Rotates and translates the 2dGraphics to oppropriate position object
	private void drawActor(Graphics2D graphics, Actor actor, Position lookingat) {
		// DRAWING STUFF NOT DONE
		graphics.translate(lookingat.getX(), lookingat.getY());
		double rotation = actor.getRotation();
		if (rotation != 0.0f) {
			graphics.rotate(actor.getRotation());
		}
		actor.draw(graphics, dictator);
	}

	private void drawTextCenter(String string, Font font, Graphics2D g,
			int downspace) {
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString(string, dictator.SIZE_X / 2
				- g.getFontMetrics().stringWidth(string) / 2, dictator.SIZE_Y
				/ 2 - g.getFontMetrics().stringWidth(string) / 2 + downspace);
	}
}
