package Day7;

import java.util.List;

public class Program {
	
	public Program () {
		
	}
	
	public Program (String name, int weight, List<String> parents) {
		this.setName(name);
		this.setWeight(weight);
		this.setParents(parents);
	}
	
	private int weight;
	private String name;
	private List<String> parents;

	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getParents() {
		return parents;
	}
	public void setParents(List<String> parents) {
		this.parents = parents;
	}
}
