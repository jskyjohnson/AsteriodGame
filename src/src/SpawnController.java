package src;

import java.util.ArrayList;

public class SpawnController {

	private Dictator dictator;

	private int spawnCap;

	private SongListener songlistener;

	private ArrayList<String> song;

	private int currentLine;

	private int songRateSpeed;

	private String currentString;
	
	public boolean endsong;

	public SpawnController(Dictator dic, String thissong) {
		this.dictator = dic;
		endsong = false;
		spawnCap = 100;
		currentLine = 0;
		songlistener = new SongListener(thissong);
		currentString = "asdf";
		songlistener.generate();
		song = songlistener.getSong();
		songRateSpeed = 30;

	}

	public void set(int spawnCap, int spawnFreq, int spawn) {
		this.spawnCap = spawnCap;

	}
	
	public void reset(){
		currentString = "asdf";
		currentLine = 0;
		
	}

	public void update() {
		if (dictator.getTime() % songRateSpeed == 0) {
			try {
				currentString = song.get(currentLine);
				currentLine++;
			} catch (IndexOutOfBoundsException e) {
				endsong = true;
			} catch (Exception e) {

			}
		}
		
		if (currentString.contains("a")) {
			int offsetInt = currentString.indexOf("a")+2;
			if (dictator.getTime() % spawnCap == 0) {
				int numberAsteroidSpawn = Integer.parseInt(currentString.substring(offsetInt, offsetInt+1));
				for (int i = 0; i < numberAsteroidSpawn; i++) {
					Asteroid new1 = new Asteroid(dictator, 30, new Position(
							dictator.rand.nextInt(dictator.SIZE_X),
							dictator.rand.nextInt(dictator.SIZE_Y)),
							new Movement(dictator.rand.nextDouble() * 2 - 1,
									dictator.rand.nextDouble() * 2 - 1));

					dictator.addToAddActors(new1);
				}
			}
		}
		
		for(Star star : dictator.starlist){
			if(star.getGroup() == 0){
				star.setToSize(5);
				
			}else{
				star.setToSize(1);
			}
		}
	}
}
