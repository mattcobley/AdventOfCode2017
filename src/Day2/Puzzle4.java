package Day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Puzzle4 {

	public static void main(String[] args) {
		
		String spreadsheetPath = "C:/Users/mattc/Documents/adventofcode4.csv";
		ArrayList<int[]> fileContents = readFromCsvFile(spreadsheetPath);
		int sum = 0;
		for(int[] row: fileContents) {
			sum = sum + getRowValue(row);
		}
		System.out.println(sum);
	}
	
	private static int getRowValue(int[] row) {
		Arrays.sort(row);
		int[] divisibleNumbers = getDivisibleNumbers(row);
		return divisibleNumbers[0] / divisibleNumbers[1];
	}
	
	private static int[] getDivisibleNumbers(int[] row) {
		int[] result = new int[2];
		for(int i = 0; i < row.length; i++) {
			int num1  = row[i];
			for(int j = 0; j < row.length; j++) {
				int num2 = row[j];
				if(i == j) {
					continue;
				}
				if(num1 % num2 == 0) {
					result[0] = num1;
					result[1] = num2;
					return result;
				}
			}
		}
		return null;
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
