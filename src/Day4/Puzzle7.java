package Day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Puzzle7 {

	public static void main(String[] args) {
		
		ArrayList<String[]> input = readFromTextFile("C:/Users/mattc/Documents/adventofcode7.txt");
		int valid = 0;
		for(int i=0; i< input.size(); i++) {
			String[] phrase = input.get(i);
			ArrayList<String> dict = new ArrayList<String>();
			boolean isValid = true;
			for(int j=0; j < phrase.length; j++) {
				String word = phrase[j];
				int index = dict.indexOf(word);
				dict.add(word);
				if(index != -1) {
					isValid = false;
				}
			}
			if(isValid) {
				valid++;
			}
		}
		System.out.println("Total number of valid passphrases:" + valid);
	}
	
	private static ArrayList<String[]> readFromTextFile(String filename) {
		
		Path pathToFile = Paths.get(filename);
		ArrayList<String[]> rows = new ArrayList<String[]>();
		
		try(BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)){
			String line = br.readLine();
			while(line != null) {
				String[] cells = line.split(" ");
				String[] words = new String[cells.length];
				for(int i = 0; i < cells.length; i++) {
					words[i] = cells[i];
				}
				rows.add(words);
				line = br.readLine();
			}
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		return rows;
	}

}
