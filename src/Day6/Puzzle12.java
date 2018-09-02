package Day6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Puzzle12 {

	public static void main(String[] args) {
		
		String userInput = "";
		do {
			userInput = getUserInput();
		}while(userInput == "");
		
		String[] inputArray = userInput.split(",");
		
		ArrayList<String> seenBefore = new ArrayList<String>();
		int steps = 0;
		
		ArrayList<Integer> inputNumbers = new ArrayList<Integer>();
		for(String in : inputArray) {
			inputNumbers.add(Integer.parseInt(in));
		}
		
		while(seenBefore.indexOf(userInput) == -1) {
			seenBefore.add(userInput); 
			final int maxNumber = Collections.max(inputNumbers);
			int maxNumberIndex = inputNumbers.indexOf(maxNumber);
			for(int i = 1; i <= maxNumber;i++) {
				int distanceFromMaxNumberIndex = maxNumberIndex + i;
				int index = distanceFromMaxNumberIndex % inputNumbers.size();
				int existingValue = inputNumbers.get(index);
				inputNumbers.set(index, existingValue + 1);
				int existingMaxNumberIndexValue = inputNumbers.get(maxNumberIndex);
				inputNumbers.set(maxNumberIndex, existingMaxNumberIndexValue - 1);
			}
			StringBuilder sb = new StringBuilder();
			for(Integer num: inputNumbers) {
				sb.append(num.toString()).append(",");
			}
			userInput = sb.deleteCharAt(sb.length() - 1).toString();
			steps++;
		}
		int loopSize = steps - (seenBefore.indexOf(userInput));
		System.out.println("Steps: " + steps);
		System.out.println("Loop size: " + loopSize);
	}

	private static String getUserInput() {
		try {
			Scanner userInput = new Scanner( System.in );
			System.out.print("Please enter input: ");
			String input = userInput.nextLine();
			userInput.close();
			return input;
		}
		catch(Exception ex) {
			return "";
		}
	}
}
