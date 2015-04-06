package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SeedListener {

	public String seed;

	public String seedIntString;

	public BigInteger seedtoint;

	public Dictator dictator = null;

	PrintStream out = null;

	private ArrayList<String> level;

	public SeedListener(String levelIncomeing, Dictator dic) {
		// TODO Auto-generated constructor stub
		this.level = new ArrayList<String>();
		this.seed = levelIncomeing;
		this.dictator = dic;
		seedIntString = "133769101";

		for (int i = 0; i < levelIncomeing.length(); i++) {
			seedIntString += (int) levelIncomeing.charAt(i);
		}
		seedtoint = new BigInteger(seedIntString);
		seedtoint = seedtoint.multiply(seedtoint).multiply(seedtoint);
		dictator.rand = new Random(seedtoint.longValue());

		generate();

	}

	// this comes into gen from a hash
	public void generate() {
		int levelLength = dictator.rand.nextInt(200) + 50;

		
		for (int spawnerLevelLength = 0; spawnerLevelLength < levelLength; spawnerLevelLength++) {
			
			String addString = "";
			
			int randspawncheck1 = dictator.rand.nextInt(2) - 1;
			int randspawncheck2 = dictator.rand.nextInt(2) - 1;
			
			if (randspawncheck1 * randspawncheck2  != 0) {
				int AsteroidsToSpawn = dictator.rand.nextInt(3)+1;
				addString += "AsteroidsToSpawn:"
						+ Integer.toString(AsteroidsToSpawn);

				for (int i = 0; i < AsteroidsToSpawn; i++) {
					int AsteroidSize = dictator.rand.nextInt(30)+10;
					int Posx = dictator.rand.nextInt(dictator.SIZE_X);
					int Posy = dictator.rand.nextInt(dictator.SIZE_Y);
					int Movx = dictator.rand.nextInt(4);
					int Movy = dictator.rand.nextInt(4);

					addString += "AsteroidCall:" + (i + 1) + "AsteroidSize:"
							+ AsteroidSize + ";Position:" + Posx + ",P" + Posy
							+ ";Movement:" + Movx + ",M" + Movy
							+ ";AsteroidCallEnd:" + (i + 1);
				}
			}
			level.add(addString);
		}
	}

	// returns the level
	public ArrayList<String> getLevel() {
		return this.level;
	}

	public boolean endSong() {
		// TODO Auto-generated method stub
		return false;
	}

}
