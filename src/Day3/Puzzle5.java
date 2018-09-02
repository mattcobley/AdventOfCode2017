package Day3;

import java.util.Scanner;

public class Puzzle5 {

	public static void main(String[] args) {
		
		int userNumber = 0;
		do {
			userNumber = getUserInput();
		}while(userNumber == 0);
		
		int highestValueInSpiral = 0;
		int endSpiralIndex = 0;
		int endSpiralWidth = 0;
		for(int i = 0; highestValueInSpiral < userNumber; i++) {
			int spiralWidth = (2*i) + 1;
			highestValueInSpiral = spiralWidth*spiralWidth;
			endSpiralIndex = i;
			endSpiralWidth = spiralWidth;
		}
		int spiralNumber = endSpiralIndex + 1;
	
		int previousSpiralWidth = Math.max(0, (endSpiralWidth - 2));
		
		int spiralLowNumber = ((previousSpiralWidth) * (previousSpiralWidth)) + 1;
		
		int stepsToSpiral = spiralNumber - 1;
		int distanceFromMidPoint = getDistanceFromMidPoint(userNumber, highestValueInSpiral, spiralLowNumber, endSpiralWidth);
		int finalSteps = stepsToSpiral + distanceFromMidPoint;
		System.out.println("Total number of steps: " + finalSteps);
	}
	
	private static int getDistanceFromMidPoint(int userNumber, int spiralHighNumber, int spiralLowNumber, int spiralWidth) {
		int distanceToNextCorner = spiralWidth -1;
		int sectionHigh = 0;
		for(int i = spiralHighNumber; i > spiralLowNumber; i = i - distanceToNextCorner) {
			int low = i - distanceToNextCorner;
			if(userNumber < i && userNumber > low) {
				sectionHigh = i;
				break;
			}
		}
		int midPoint = sectionHigh - (distanceToNextCorner/2);
		return midPoint == 0 ? 0 : Math.abs(userNumber - midPoint);
	}
	
	private static int getUserInput() {
		try {
			Scanner userInput = new Scanner( System.in );
			System.out.print("Please enter number: ");
			String input = userInput.next();
			userInput.close();
			return Integer.parseInt(input);
		}
		catch(Exception ex) {
			return 0;
		}
	}

}
