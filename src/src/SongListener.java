package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class SongListener {

	PrintStream out = null;

	private ArrayList<String> song = null;

	public SongListener(String thissong) {
		// TODO Auto-generated constructor stub

		song = new ArrayList<String>();
		try {
			out = new PrintStream("temp.t");
			
			out.println("asdfasdfasdf");
			out.println("asdfasdfasdf");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<String> generate() {
		// TODO Auto-generated method stub
		File songFile = new File("temp.t");
		Scanner adder;
		try {
			adder = new Scanner(songFile);

			while (adder.hasNextLine()) {

				String k = adder.nextLine();
				song.add(k);

			}
			adder.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return song;
	}

	public boolean endSong() {
		// TODO Auto-generated method stub
		return false;
	}

}
