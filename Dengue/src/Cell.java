import java.util.Random;

public class Cell {
	private Being being;
	private int food;
	private int water;

	public Cell() {
		being = null;
		Random rand = new Random();
		water = rand.nextInt(2);
		food = rand.nextInt(2);
	}

	public Being getBeing() {
		return being;
	}

	public void setBeing(Being being) {
		this.being = being;
	}

	public void clear() {
		this.being = null;
	}

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

	public int getWater() {
		return water;
	}

	public void setWater(int water) {
		this.water = water;
	}
}
