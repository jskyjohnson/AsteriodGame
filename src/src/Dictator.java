package src;

import java.time.Clock;
import java.util.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;

/**
 * Dictator of all game aspects, and manager of inputs and the what not.
 * 
 * @author Sky Johnson
 * 
 */
public class Dictator extends JFrame {
	// serialID

	/*
	 * Serial Version
	 */
	private static final long serialVersionUID = -3535839203565039672L;

	// Threads
	/*
	 * Initialization thread
	 */
	Thread initThread;
	/*
	 * Looping thread
	 */
	Thread loopThread;

	// Game Constants
	/*
	 * Frames per second limit
	 */
	private static final int FRAMES = 60;

	private static final int GUN_CAP = 1;

	public final int BULLET_MAX = 100;

	public double bulletSpeed;
	
	public final int BULLET_REGEN = 1;

	private int NUMBER_STARS = 100;

	// Game States
	/*
	 * If paused boolean
	 */
	public boolean paused;
	/*
	 * if game is exiting
	 */
	private boolean exit;
	/*
	 * If restart process boolean
	 */
	private boolean restart;

	/*
	 * If game is over boolean
	 */
	public boolean gameOver;
	/*
	 * If game is in session
	 */
	private boolean isGame;

	// Game Values
	/*
	 * Current Score
	 */

	public int lives = 3;
	public int score;

	public int bulletCount;
	/*
	 * High Score
	 */
	public int highScore;
	// Game Objects and others
	/*
	 * ArrayList of Actor Objects in the game
	 */
	private List<Actor> actor;
	/*
	 * ArrayList of Actor Objects that need to be added to the game
	 */
	private List<Actor> toAddActor;

	public String thissong;

	protected ArrayList<Star> starlist;

	public ArrayList<Actor> asteroids;
	/*
	 * Clock
	 */
	private Watch StarTimer;

	/*
	 * Size of jframe x
	 */
	public final int SIZE_X = 600;
	/*
	 * Size of jframe y
	 */
	public final int SIZE_Y = 600;

	public Mouse mouse;

	public boolean mouseDown;

	public Point mousePoint;

	/*
	 * Player(s?)
	 */
	protected Player StarCaptain;

	/*
	 * World Map entitiy
	 */

	private SpaceMap Constellation;

	public Random rand = new Random();

	public int framesSoFar;
	
	public SpawnController spawner;

	/**
	 * Constructor for objects of class Dictator
	 */
	public Dictator() {
		// initialize instance variables

		super();
		

		bulletCount = 0;
		framesSoFar = 0;
		score = 0;
		bulletSpeed = 5;
		// SONG Analyzer Now!!
		thissong = "test.mp3";
		
		mouse = new Mouse();
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		// create SpaceMap and set Window and jazz
		add(this.Constellation = new SpaceMap(this), BorderLayout.CENTER);
		setContentPane(Constellation);

		addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				mouse.update(e.getPoint());
			}

			public void mouseDragged(MouseEvent e) {
				mouse.update(e.getPoint());
				mousePoint = e.getPoint();
				mouseDown = true;
			}

		});

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				mouse.update(e.getPoint());
				mousePoint = e.getPoint();
				mouseDown = true;
			}

			public void mouseReleased(MouseEvent e) {
				mouseDown = false;
				mouse.update(e.getPoint());
			}
		});

		// Add Key Listener, only modifies player

		addKeyListener(new KeyAdapter() {

			// key mapping for press
			public void keyPressed(KeyEvent e) {

				// Controlling Keys
				if (e.getKeyCode() == KeyEvent.VK_A) {
					if (!checkForRestart()) {
						StarCaptain.thrustingLeft(true);
					} else {
						StarCaptain.thrustingLeft(false);
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_D) {
					if (!checkForRestart()) {
						StarCaptain.thrustingRight(true);
					} else {
						StarCaptain.thrustingRight(false);
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					if (!checkForRestart()) {
						StarCaptain.thrustingDown(true);
					} else {
						StarCaptain.thrustingDown(false);
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_W) {
					if (!checkForRestart()) {
						StarCaptain.thrustingUp(true);

					} else {
						StarCaptain.thrustingUp(false);
					}
				}

				// Menu Keys
				if (e.getKeyCode() == KeyEvent.VK_P) {
					if (!checkForRestart()) {
						pause();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_R) {
					if (!checkForRestart()) {
						restart();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					if (!checkForRestart()) {
						exit = true;
					}
				}

			}

			// key mapping for release
			public void keyReleased(KeyEvent e) {

				if (e.getKeyChar() == 'a') {
					StarCaptain.thrustingLeft(false);
				}
				if (e.getKeyChar() == 'w') {
					StarCaptain.thrustingUp(false);
				}
				if (e.getKeyChar() == 's') {
					StarCaptain.thrustingDown(false);
				}
				if (e.getKeyChar() == 'd') {
					StarCaptain.thrustingRight(false);
				}

			}

		});

		
		
		//Create Spawn Object on init, to be changed at Start Game methods
		spawner = new SpawnController(this, thissong);
		
		
		// Resize
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	/*
	 * Check to see if restart has been pressed or not
	 * 
	 * @return If the key to restart has been pressed or not
	 */
	public boolean checkForRestart() {
		return restart;
	}

	// Restarts the game(Literrally runs threw startup again)
	private void restart() {
		bulletCount = 0;
		framesSoFar = 0;
		bulletSpeed = 5;
		score = 0;

		lives = 3;
		mousePoint = new Point();
		mouseDown = false;

		spawner.reset();
		this.setActor(new ArrayList<Actor>());
		this.toAddActor = new ArrayList<Actor>();
		this.StarCaptain = new Player(this);
		this.starlist = new ArrayList<Star>();
		this.asteroids = new ArrayList<Actor>();
		restartGame();

		// Set star timer to refresh at every frame value
		this.StarTimer = new Watch(FRAMES);
		// this.Constellation = new SpaceMap(this);
		isGame = true;
		gameOver = false;
		restart = false;

		// Starlist Generation
		for (int i = 0; i < NUMBER_STARS; i++) {
			double a = (rand.nextDouble() * SIZE_X);
			double b = (rand.nextDouble() * SIZE_Y);
			double c = rand.nextDouble();
			int group = rand.nextInt(16);

			Star startemp = new Star(a, b, c, group);
			starlist.add(startemp);
		}

		toAddActor.add(StarCaptain);

		// Asteroid Generation (MUST CHANGE)
		for (int i = 0; i < 0; i++) {
			Asteroid asteroid = new Asteroid(this, 30,new Position(rand.nextInt(SIZE_X), rand.nextInt(SIZE_Y)), new Movement(rand.nextDouble() * 2 - 1, rand.nextDouble() * 2 - 1));
			asteroids.add(asteroid);
		}

		toAddActor.addAll(asteroids);

		restart = false;

	}

	private void pause() {
		if (paused == false) {
			this.paused = true;
			isGame = false;
		} else if (paused == true) {
			this.paused = false;
			isGame = true;
		}
	}

	/*
	 * Starts the game, including game loop and update system.
	 */
	private void startGame() {

		score = 0;

		lives = 3;
		mousePoint = new Point();
		mouseDown = false;

		this.setActor(new ArrayList<Actor>());
		this.toAddActor = new ArrayList<Actor>();
		this.StarCaptain = new Player(this);
		this.starlist = new ArrayList<Star>();
		this.asteroids = new ArrayList<Actor>();
		restartGame();

		// Set star timer to refresh at every frame value
		this.StarTimer = new Watch(FRAMES);
		// this.Constellation = new SpaceMap(this);
		isGame = true;
		gameOver = false;
		restart = false;

		// Starlist Generation
		for (int i = 0; i < NUMBER_STARS; i++) {
			double a = (rand.nextDouble() * SIZE_X);
			double b = (rand.nextDouble() * SIZE_Y);
			double c = rand.nextDouble();
			int group = rand.nextInt(16);

			Star startemp = new Star(a, b, c, group);
			starlist.add(startemp);
		}

		toAddActor.add(StarCaptain);

		// Asteroid Generation (MUST CHANGE)
		for (int i = 0; i < 0; i++) {
			Asteroid asteroid = new Asteroid(this, 30,new Position(rand.nextInt(SIZE_X), rand.nextInt(SIZE_Y)), new Movement(rand.nextDouble() * 2 - 1, rand.nextDouble() * 2 - 1));
			asteroids.add(asteroid);
		}

		toAddActor.addAll(asteroids);

		// game Loop
		while (true) {
			long start = System.nanoTime();

			// Tick
			StarTimer.update();
			for (int i = 0; i < 5 && StarTimer.hasPassedTicks(); i++) {
				updateGame();
			}

			Constellation.repaint();
			// Renders SpaceMap Class

			/*
			 * Waits for tick to finish
			 */
			long frameerror = FRAMES - (System.nanoTime() - start);
			if (frameerror > 0) {
				try {
					Thread.sleep(frameerror / 10000L, (int) frameerror % 10000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// Game Over Sequence / menus and stuff

		}
	}

	/*
	 * when dies, stops everything and then waits for restart
	 */
	private void endGame() {
		isGame = false;
	}

	private void resetLists() {

	}

	/*
	 * the update loop for the game
	 */
	private void updateGame() {
		// Pre-Game / Post game Options manager?
		
		
		//Updates Spawner
		spawner.update();
		
		getActor().addAll(toAddActor);
		toAddActor.clear();
		
		if (exit) {
			System.exit(0);
		}

		// For In Game

		if (isGame && !paused && !restart) {

			checkMouseDown();
			// Time based stuff

			// Score Counter every second
			if (StarTimer.getSinceStart() % 60 == 0) {
				score += 10;
			}

			// Bullet Regeneration
			if (StarTimer.getSinceStart() % BULLET_REGEN == 0) {
				if (BULLET_MAX - bulletCount < BULLET_MAX) {
					bulletCount--;
				}

			}
			StarTimer.addSinceStart();

			

			framesSoFar++;
			// ADD FRAMES SO FAR CHECKER HERE, SO THAT IF PAST SONG LIMIT GAME
			// STOPS

			// Update Methods for Stars (Twinckles
			for (Star i : starlist) {
				i.update(this);
			}

			// Updates All actors Players,Astroids, Bullets,

			for (Actor i : actor) {
				i.update(this);
				
			}

			// Scans threw all Actor Objects and checks for collisions, then
			// does
			// the handle collision method if true
			for (int i = 0; i < getActor().size(); i++) {
				Actor temp1 = getActor().get(i);
				for (int i2 = i + 1; i2 < getActor().size(); i2++) {
					Actor temp2 = getActor().get(i2);
					if (i != i2 && temp1.colliding(temp2)) {
						temp1.collided(temp2, this);
						temp2.collided(temp1, this);

					}
				}
			}

			Iterator<Actor> iter = actor.iterator();
			while (iter.hasNext()) {
				Actor lookingat = iter.next();
				if (lookingat.getRemoval()) {
					iter.remove();
				}
			}
		}
	}

	private void checkMouseDown() {
		// TODO Auto-generated method stub
		if (mouseDown) {
			if (framesSoFar % GUN_CAP == 0)
				shoot();

		}
	}

	private void shoot() {
		if (bulletCount < BULLET_MAX) {
			if (!gameOver) {
				Bullet bullet = new Bullet(this);
				bulletCount++;
				toAddActor.add(bullet);
			}
		}
	}

	/*
	 * restart loop for the game,
	 */
	private void restartGame() {
		resetLists();
	}

	/*
	 * main methods, starts the entire program
	 */
	public static void main(String[] args) {

		Dictator StalinMussoliniHitlerAndyMao = new Dictator();
		StalinMussoliniHitlerAndyMao.startGame();

	}

	public String getScore() {
		return Integer.toString(score);
	}

	public List<Actor> getActor() {
		return actor;
	}

	public void setActor(List<Actor> actor) {
		this.actor = actor;
	}

	public void addToAddActors(Actor actor) {
		toAddActor.add(actor);
	}

	
	public int getTime(){
		return StarTimer.getSinceStart();
	}
	public void crash() {
		// TODO Auto-generated method stub
		if (lives == 0) {
			endGame();
			gameOver = true;
		} else {
			lives--;
			StarCaptain.center(this);

		}
	}

	public Position getPlayerPosition() {
		// TODO Auto-generated method stub
		Position a = StarCaptain.getPosition();
		return a;
	}

}
