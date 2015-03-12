package src;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class LevelGenerator {

		PrintStream out = null;

		private ArrayList<String> level = null;

		public LevelGenerator(String levelIncomeing) {
			// TODO Auto-generated constructor stub
			this.level = new ArrayList<String>();

		}

		//this comes into gen from a hash
		public void generate(String a) {
			// TODO Auto-generated method stub
			File levelFile = new File("temp.t");
			Scanner adder;
			try {
				
				adder = new Scanner(levelFile);
				while (adder.hasNextLine()) {
					String k = adder.nextLine();
					this.level.add(k);
				}
				adder.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public ArrayList<String> getSong(){
			return this.level;
		}
		public boolean endSong() {
			// TODO Auto-generated method stub
			return false;
		}

	}

