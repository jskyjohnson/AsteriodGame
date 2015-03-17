package src;

import java.util.ArrayList;

public class SpawnController {

	private Dictator dictator;

	private int spawnCap;

	private SongListener songlistener;

	private SeedListener seedListener;

	private ArrayList<String> level;

	private int currentLine;

	private int listenRateSpeed;

	private String currentString;

	public boolean endgame;

	public String thisString;

	public SpawnController(Dictator dic) {
		this.dictator = dic;
		endgame = false;
		spawnCap = 100;
		currentLine = 0;
		currentString = "asdf";
		listenRateSpeed = 30;

	}

	public void set(int spawnCap, int spawnFreq, int spawn) {
		this.spawnCap = spawnCap;

	}

	public void setThisString(String thisString) {
		this.thisString = thisString;
	}

	public void reset() {
		currentString = "asdf";
		currentLine = 0;

	}

	public void update2() {
		if (dictator.selectDecision) {
			if (dictator.musicgame) {
				
				thisString = dictator.song;
				
				songlistener = new SongListener(thisString);
				songlistener.generate();
				level = songlistener.getSong();

			} else if (dictator.seedgame) {

				thisString = dictator.seed;
				
				seedListener = new SeedListener(thisString);
				seedListener.generate();
				level = seedListener.getSong();

			}
			dictator.setGenerated(true);
		}
	}

	public void update() {

		if (dictator.getTime() % listenRateSpeed == 0) {
			try {
				currentString = level.get(currentLine);
				currentLine++;
			} catch (IndexOutOfBoundsException e) {
				endgame = true;
			} catch (Exception e) {

			}

			if (currentString.contains("a")) {
				int offsetInt = currentString.indexOf("a") + 2;
				
				if (dictator.getTime() % spawnCap == 0) {
					int numberAsteroidSpawn = Integer.parseInt(currentString
							.substring(offsetInt, offsetInt + 1));
					for (int i = 0; i < numberAsteroidSpawn; i++) {
						Asteroid new1 = new Asteroid(
								dictator,
								30,
								new Position(dictator.rand
										.nextInt(dictator.SIZE_X),
										dictator.rand.nextInt(dictator.SIZE_Y)),
								new Movement(
										dictator.rand.nextDouble() * 2 - 1,
										dictator.rand.nextDouble() * 2 - 1));

						dictator.addToAddActors(new1);
					}
				}
			}

			for (Star star : dictator.starlist) {
				if (star.getGroup() == 0) {
					star.setToSize(5);

				} else {
					star.setToSize(1);
				}
			}
		}
	}
}
