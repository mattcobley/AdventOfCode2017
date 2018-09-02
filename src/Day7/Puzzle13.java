package Day7;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Puzzle13 {

	public static void main(String[] args) {
		
		ArrayList<Program> input = readFromTextFile("C:/Users/mattc/Documents/adventofcode13.txt");
		Program bottomLevelProgram = getBottomProgram(input);
		System.out.println("Answer: " + bottomLevelProgram.getName());
		
		int weight = getRequiredWeight(input, bottomLevelProgram);
		System.out.println("Required weight of wrong program: " + weight);	
	}
	
	private static Program getBottomProgram(ArrayList<Program> input) {
		List<String> previousLevelProgramNames = input.stream().filter(in -> in.getParents().size() == 0)
				.map(in -> in.getName())
				.collect(Collectors.toList());
		
		List<Program> remainingPrograms = input.stream().filter(in -> in.getParents().size() > 0)
				.collect(Collectors.toList());
		
		while(remainingPrograms.size() != 1) {
			ArrayList<Program> thisLevelPrograms = new ArrayList<Program>();
			ArrayList<Program> newRemainingPrograms = new ArrayList<Program>();
			final List<String> prevProgNames = previousLevelProgramNames;
			for(Program prog : remainingPrograms) {
				List<String> parents = prog.getParents();
				List<String> parentsAtPreviousLevel = parents.stream()
						.filter(p -> prevProgNames.indexOf(p) != -1).collect(Collectors.toList());
				if(parentsAtPreviousLevel.size() != 0) {
					thisLevelPrograms.add(prog);
				}
				else {
					newRemainingPrograms.add(prog);
				}
			}
			previousLevelProgramNames = thisLevelPrograms.stream().map(p -> p.getName())
					.collect(Collectors.toList());
			remainingPrograms = newRemainingPrograms;
		}
		
		return remainingPrograms.stream().findFirst().get();
	}
	
	private static int getRequiredWeight(ArrayList<Program> input, Program bottomLevelProgram) {
		
		boolean foundWrongWeight = false;
		ArrayList<Program> remainingPrograms = input;
		ArrayList<Program> thisLevelPrograms = new ArrayList<Program>();
		thisLevelPrograms.add(bottomLevelProgram);
		
		while(!foundWrongWeight) {			
			ArrayList<Program> allNextLevelPrograms = new ArrayList<Program>();
			for(Program thisLevelProgram: thisLevelPrograms) {
				int programWeight = thisLevelProgram.getWeight();
				ArrayList<Integer> nextLevelProgramWeights = new ArrayList<Integer>();
				for(String parent: bottomLevelProgram.getParents()) {
					Program prog = input.stream().filter(p -> p.getName().equals(parent)).findFirst().get();
					nextLevelProgramWeights.add(prog.getWeight());
					allNextLevelPrograms.add(prog);
				}
				
			}
		}
	}

	private static ArrayList<Program> readFromTextFile(String filename) {
		
		Path pathToFile = Paths.get(filename);
		ArrayList<Program> rows = new ArrayList<Program>();
		
		try(BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)){
			String line = br.readLine();
			while(line != null) {
				String[] split1 = line.split(" ");
				String name = split1[0];
				String weightString = split1[1].substring(1, split1[1].length() - 1);
				int weight = Integer.parseInt(weightString);
				List<String> parents = new ArrayList<String>();
				int length = split1.length;
				if(length > 2) {
					//we skip entry with index 2 in the array, since it is the arrow
					for(int i=3; i < split1.length; i++) {
						String entry = split1[i];
						if(entry.length() > 4) {
							entry = entry.substring(0, entry.length()-1);
						}
						parents.add(entry);
					}
				}
				Program program = new Program(name, weight, parents);
				rows.add(program);
				line = br.readLine();
			}
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		return rows;
	}
}
