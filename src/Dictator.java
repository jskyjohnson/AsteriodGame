import java.time.Clock;
import java.util.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
	//private static final long serialVersionUID = -3535839203565039672L;

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
	 * Player(s?)
	 */
	private Player StarCaptain;
	
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
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		//create SpaceMap and set Window and jazz
		add(this.Constellation = new SpaceMap(this), BorderLayout.CENTER);
		setContentPane(Constellation);
		//Add Key Listener, only modifies player
		addKeyListener(new KeyAdapter(){
			
			
			//key mapping for press
			public void keyPressed(KeyEvent e){
				
			}
			// key mapping for release
			public void keyReleased(KeyEvent e){
				
			}
			
		});
		
		//Resize
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

	/**	
	*  Start Method, to initialize threads.
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
	*  Stop Method, to stop threads.
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
	 * @return If the key to restart has been pressed or not
	 */
	private boolean checkForRestart(){
		boolean restartgame = gameOver;
		if(restartgame){
			restart= true;
		}
		return restartgame;
	}
	/*
	 * Check to see if the exit button has been pressed or not
	 *@return if the key to end the game has been pressed or not
	 */
	private boolean checkForExit(){
		boolean exitgame = exit;
		if(exitgame){
			exit = true;
		}
		return exitgame;
	}
	

	/*
	 * Starts the game, including game loop and update system.
	 */
	private void startGame(){
		this.Party = new Random();
		this.actor = new ArrayList();
		this.toAdActor = new ArrayList();
		this.StarCaptain = new Player();
		
		restartGame();
		
		//Set star timer to refresh at every frame value
		this.StarTimer = new Watch(FRAMES);
		//this.Constellation = new SpaceMap(this);
		isGame= true;
		gameOver = false;
		//Game Loop
		while(isGame){
			long start = System.nanoTime();
			
			StarTimer.update();
			
			Constellation.repaint();
			
		}
		
	}
	/*
	 * when dies, stops everything and then waits for restart
	 */
	private void endGame(){
		
	}
	/*
	 * the update loop for the game
	 */
	private void updateGame(){
		
	}
	/*
	 * restart loop for the game, 
	 */
	private void restartGame(){
		
	}
	/*
	 * main methods, starts the entire program
	 */
	public  static void main(String[] args){

		Dictator gurf = new Dictator();
		
		gurf.startGame();
		
	}
	/**
	 * An example of a method - replace this comment with your own
	 * 
	 * @param y
	 *            a sample parameter for a method
	 * @return the sum of x and y
	 */
	public int sampleMethod(int y) {
		// put your code here
		return 4;
	}

}
