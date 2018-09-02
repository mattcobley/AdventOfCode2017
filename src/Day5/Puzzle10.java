package Day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Puzzle10 {

	public static void main(String[] args) {
		int steps = 0;
		ArrayList<Integer> numbers = readFromTextFile("C:/Users/mattc/Documents/adventofcode9.txt");
		
		int index = 0;
		while(index >=0 && index < numbers.size()) {
			int value = numbers.get(index);
			int nextIndex = index + value;
			int offsetChange = value >= 3 ? -1 : 1;
			numbers.set(index, value + offsetChange);
			index = nextIndex;
			steps++;
		}
		System.out.println("Steps: " + steps);
	}

	private static ArrayList<Integer> readFromTextFile(String filename) {
			
		Path pathToFile = Paths.get(filename);
		ArrayList<Integer> rows = new ArrayList<Integer>();
		
		try(BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)){
			String line = br.readLine();
			while(line != null) {
				Integer number= Integer.parseInt(line);
				rows.add(number);
				line = br.readLine();
			}
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		return rows;
	}
}
