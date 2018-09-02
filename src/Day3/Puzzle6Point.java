package Day3;

public class Puzzle6Point {
	
	public Puzzle6Point() {
		
	}
	
	public Puzzle6Point(int x, int y, int value) {
		this.setX(x);
		this.setY(y);
		this.setValue(value);
	}

	private int x;
	private int y;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	private int value;
	
	public String toString() {
		return "x: " + this.getX() + ", y: " + this.getY() + ", value: " + this.getValue();
	}
	
}
