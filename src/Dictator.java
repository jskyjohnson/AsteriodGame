

import java.time.Clock;
import java.util.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.*;

/**
 * Dictator of all game aspects, and manager of inputs and the what not.
 * 
 * @author Sky Johnson
 * 
 */
public class Dictator extends JFrame{
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

	// Game States
	/*
	 * Made boolean, if finished loading
	 */
	private boolean made = false;
	/*
	 * If paused boolean
	 */
	private boolean paused;
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
	private boolean gameOver;
	/*
	 * If game is in session
	 */
	private boolean isGame;

	// Game Values
	/*
	 * Current Score
	 */
	private int score;
	/*
	 * High Score
	 */
	private int highScore;
	// Game Objects and others
	/*
	 * ArrayList of Actor Objects in the game
	 */
	public List<Actor> actor;
	/*
	 * ArrayList of Actor Objects that need to be added to the game
	 */
	public List<Actor> toAdActor;
	/*
	 * Clock
	 */
	private Watch StarTimer;
	/*
	 * Universal Random
	 */
	private Random Party = new Random();
	
	/*
	 * Size of jframe x
	 */
	public final int SIZE_X = 600;
	/*
	 * Size of jframe y
	 */
	public final int SIZE_Y = 600;
	
	public Mouse mouse;

	/*
	 * Player(s?)
	 */
	protected Player StarCaptain;

	/*
	 * World Map entitiy
	 */

	private SpaceMap Constellation;

	/**
	 * Constructor for objects of class Dictator
	 */
	public Dictator() {
		// initialize instance variables
		super();
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
		    
		});
		
		// Add Key Listener, only modifies player
		
		addKeyListener(new KeyAdapter() {

			// key mapping for press
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyChar()=='a'){
					if (!checkForRestart()&& !StarCaptain.getThrustingLeft()) {
						StarCaptain.thrustingLeft(true);
					}else{
						StarCaptain.thrustingLeft(false);
					}
				}
				if(e.getKeyChar()=='d'){
					if (!checkForRestart() && !StarCaptain.getThrustingRight()) {
						StarCaptain.thrustingRight(true);
					}else{
						StarCaptain.thrustingRight(false);
					}
				}
				if(e.getKeyChar()=='s'){
					if (!checkForRestart() && !StarCaptain.getThrustingDown()) {
						StarCaptain.thrustingDown(true);
					}else{
						StarCaptain.thrustingDown(false);
					}
				}
				if(e.getKeyChar()=='w'){
					if (!checkForRestart() && !StarCaptain.getThrustingUp()) {
						StarCaptain.thrustingUp(true);
						
					}else{
						StarCaptain.thrustingUp(false);
					}
				}
				if(e.getKeyChar()=='p'){
					if (!checkForRestart()) {
						if (!paused) {
							pause();
						}
					}
				}
			
			}

			// key mapping for release
			public void keyReleased(KeyEvent e) {
				
				if(e.getKeyChar()=='a'){
					StarCaptain.thrustingLeft(false);
				}
				if(e.getKeyChar()=='w'){
					StarCaptain.thrustingUp(false);
				}
				if(e.getKeyChar()=='s'){
					StarCaptain.thrustingDown(false);
				}
				if(e.getKeyChar()=='d'){
					StarCaptain.thrustingRight(false);
				}
				
				
				
			}

		});

		// Resize
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	/**
	 * Start Method, to initialize threads.
	 */
	public void startThreads() {
		if (loopThread == null) {
			loopThread = new Thread((Runnable) this);
			loopThread.start();
		}
		if (!made && initThread == null) {
			initThread = new Thread((Runnable) this);
			initThread.start();
		}
	}

	/**
	 * Stop Method, to stop threads.
	 */
	@SuppressWarnings("deprecation")
	public void stopThreads() {
		if (loopThread != null) {
			loopThread.stop();
			loopThread = null;
		}
		if (initThread != null) {
			initThread.stop();
			initThread = null;

		}

	}

	/*
	 * Check to see if restart has been pressed or not
	 * 
	 * @return If the key to restart has been pressed or not
	 */
	private boolean checkForRestart() {
		boolean restartgame = gameOver;
		if (restartgame) {
			restart = true;
		}
		return restartgame;
	}

	/*
	 * Check to see if the exit button has been pressed or not
	 * 
	 * @return if the key to end the game has been pressed or not
	 */
	private boolean checkForExit() {
		boolean exitgame = exit;
		if (exitgame) {
			exit = true;
		}
		return exitgame;
	}

	private void pause() {
		this.paused = true;
	}

	/*
	 * Starts the game, including game loop and update system.
	 */
	private void startGame() {
		this.Party = new Random();
		this.actor = new ArrayList();
		this.toAdActor = new ArrayList();
		this.StarCaptain = new Player(this);

		restartGame();

		// Set star timer to refresh at every frame value
		this.StarTimer = new Watch(FRAMES);
		// this.Constellation = new SpaceMap(this);
		isGame = true;
		gameOver = false;
		// Game Loop
		while (isGame) {
			long start = System.nanoTime();

			// Tick
			StarTimer.update();
			for (int i = 0; i < 5 && StarTimer.hasPassedTicks(); i++) {
				updateGame();
			}
			// Render
			Constellation.repaint();

			/*
			 * Frame Checker
			 */
			long frameerror = FRAMES - (System.nanoTime() - start);
			if (frameerror > 0) {
				try {
					Thread.sleep(frameerror / 1000000L,
							(int) frameerror % 1000000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

	}

	/*
	 * when dies, stops everything and then waits for restart
	 */
	private void endGame() {

	}

	private void resetActorLists(){
		toAdActor.clear();
		actor.clear();
		actor.add(StarCaptain);
	}
	
	/*
	 * the update loop for the game
	 */
	private void updateGame() {
		
		resetActorLists();
		
		for(Actor i : actor){
			i.update(this);
		}
		StarCaptain.update(this);
	}

	/*
	 * restart loop for the game,
	 */
	private void restartGame() {
		resetActorLists();
	}

	/*
	 * main methods, starts the entire program
	 */
	public static void main(String[] args) {

		Dictator hitlerAndy = new Dictator();
		hitlerAndy.startGame();

	}


}
