package Day1;

import java.util.ArrayList;
import java.util.Scanner;

public class Puzzle2 {

	public static void main(String[] args) {
		Scanner userInput = new Scanner( System.in );
		String input;
		do {
			System.out.print("Please enter a captcha with an even number of digits: ");
			input = userInput.next();
		} while(input.length() % 2 > 0);
		userInput.close();
		ArrayList<Integer> inputDigits = getInputDigits(input);
		System.out.println("Captcha result for " + input + ": " + calculateCaptcha(inputDigits));
	}
	
	private static int calculateCaptcha(ArrayList<Integer> inputDigits) {
		int sum = 0;
		int listLength = inputDigits.size();
		for(int i = 0; i < listLength; i++) {
			int digit1 = inputDigits.get(i);
			int digit2Index = (i + (listLength /2)) % listLength;
			int digit2 = inputDigits.get(digit2Index);
			if(digit1 == digit2) {
				sum = sum + digit1;
			}
		}
		return sum;
	}

	private static ArrayList<Integer> getInputDigits(String input){
		ArrayList<Integer> inputDigits = new ArrayList<Integer>();
		for(int i = 0; i < input.length(); i++) {
			String sub = input.substring(i, i+1);
			try {
				Integer digit = Integer.parseInt(sub);
				inputDigits.add(digit);
			}
			catch(Exception ex) {
				System.out.println("Exception parsing substring!!" + ex);
			}
		}
		return inputDigits;
	}
}
