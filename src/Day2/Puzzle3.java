package Day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Puzzle3 {

	public static void main(String[] args) {
		
		String spreadsheetPath = "C:/Users/mattc/Documents/adventofcode3.csv";
		ArrayList<int[]> fileContents = readFromCsvFile(spreadsheetPath);
		int sum = 0;
		for(int[] row: fileContents) {
			sum = sum + getRowValue(row);
		}
		System.out.println(sum);
	}
	
	private static int getRowValue(int[] row) {
		Arrays.sort(row);
		int max = row[row.length -1];
		int min = row[0];
		return max - min;
	}

	private static ArrayList<int[]> readFromCsvFile(String filename) {
		
		Path pathToFile = Paths.get(filename);
		ArrayList<int[]> rows = new ArrayList<int[]>();
		
		try(BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)){
			String line = br.readLine();
			while(line != null) {
				String[] cells = line.split(",");
				int[] numbers = new int[cells.length];
				for(int i = 0; i < cells.length; i++) {
					numbers[i] = Integer.parseInt(cells[i]);
				}
				rows.add(numbers);
				line = br.readLine();
			}
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		return rows;
	}
}
