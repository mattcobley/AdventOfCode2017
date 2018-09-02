package Day3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Puzzle6 {

	public static void main(String[] args) {
		
		int userNumber = 0;
		do {
			userNumber = getUserInput();
		}while(userNumber == 0);
		
		int latestValue = 0;
		ArrayList<Puzzle6Point> points = new ArrayList<Puzzle6Point>();
		
		int x = 0;
		int y = 0;
		points.add(new Puzzle6Point(x, y, 1));
		
		for(int i = 0;latestValue < userNumber; i++) {
			int numberOfSameSteps = 1 + (2*i);
			
			for(int j = 0; j< numberOfSameSteps; j++) {
				++x;
				latestValue = getValue(x, y, points);
				points.add(new Puzzle6Point(x, y, latestValue));
			}
			for(int j = 0; j< numberOfSameSteps; j++) {
				++y;
				latestValue = getValue(x, y, points);
				points.add(new Puzzle6Point(x, y, latestValue));
			}
			for(int j = 0; j< numberOfSameSteps + 1; j++) {
				--x;
				latestValue = getValue(x, y, points);
				points.add(new Puzzle6Point(x, y, latestValue));
			}
			for(int j = 0; j< numberOfSameSteps + 1; j++) {
				--y;
				latestValue = getValue(x, y, points);
				points.add(new Puzzle6Point(x, y, latestValue));
			}
		}
		final int finalUserNumber = userNumber;
		points.stream().forEachOrdered(System.out::println);
		Optional<Integer> nextValue = points.stream().filter(p -> p.getValue() > finalUserNumber)
				.map(p -> p.getValue()).findFirst();

		if(nextValue.isPresent()) {
			System.out.println("Next value: " + nextValue.get());
		}
		else {
			System.out.println("Could not find the next value");
		}

	}
	
	private static int getValue(int x, int y, ArrayList<Puzzle6Point> points) {
		List<Puzzle6Point> surroundingPoints = points.stream().filter(
				p -> p.getX() <= (x+1) && p.getX() >= (x-1) && p.getY() <= (y+1) && p.getY() >= (y-1)).collect(Collectors.toList());
		return surroundingPoints.stream().map(p -> p.getValue()).reduce(0, (Integer a, Integer b) -> Integer.sum(a, b));
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
