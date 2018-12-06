import java.util.ArrayList;

public abstract class Being {
	private Enviroment enviroment;
	protected Position position;
	private int age;
	private int foodLevel;
	private boolean alive;
	
	public Being(Enviroment enviroment, Position position) {
		this.enviroment = enviroment;
		this.position = position;
		this.setAge(0);
		this.foodLevel = 5;
		this.alive = true;
	}

	public abstract void act(ArrayList<Being> newBeing);
	
	public void beBitten(Adult adult, ArrayList<Being> being) {
		
	}

	public void incrementHunger() {
		foodLevel--;
		if (foodLevel <= 0) {
			setDead();
		}
	}

	public boolean isAlive() {
		return alive;
	}

	public void setDead() {
		enviroment.clear(position);
		alive = false;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}

	public int getFoodLevel() {
		return foodLevel;
	}

	public Position getPosition() {

		return position;
	}

	public Enviroment getEnviroment() {
		return enviroment;
	}
}
