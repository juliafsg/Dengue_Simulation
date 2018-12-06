import java.util.ArrayList;
import java.util.Random;

public class Infected extends Being {
	private static final int BETTER_MIN_AGE = 100;

	public Infected(Enviroment enviroment, Position position) {
		super(enviroment, position);
	}

	public void act(ArrayList<Being> being) {
		if (isAlive()) {
			Random rand = new Random();
			int move = rand.nextInt(2);
			incrementAge();
			if (move == 1) {
				move();
			}
			beBetter(being);
		}
	}
	
	public void beBetter(ArrayList<Being> being) {
		if (getAge() > BETTER_MIN_AGE) {
			Position oldPosition = getPosition();
			Person person = new Person(getEnviroment(), oldPosition);
			setDead();
			getEnviroment().setBeingAt(person, oldPosition.getRow(), oldPosition.getCol());
			being.add(person);
		}
	}

	public void move() {
		Position newPosition = getEnviroment().freeAdjacentPosition(getPosition());
		Position oldPosition = getPosition();
		if (newPosition != null) {
			setPosition(newPosition);
			this.getEnviroment().setBeingAt(this, newPosition.getRow(), newPosition.getCol());
			getEnviroment().clear(oldPosition);
		}
	}

	public void incrementAge() {
		setAge(getAge() + 1);
	}
}
