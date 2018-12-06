import java.util.ArrayList;

public class Pupa extends Mosquito {
	private static final int MAX_AGE = 5;
	private static final int EVOLVE_MIN_AGE = 3;

	public Pupa(Enviroment enviroment, Position position) {
		super(enviroment, position);
	}

	public void act(ArrayList<Being> being) {
		if (isAlive()) {
			incrementAge();
			evolve(being);
		}
	}

	private void evolve(ArrayList<Being> being) {
		if (canEvolve()) {
			Position oldPosition = getPosition();
			Adult adult = new Adult(getEnviroment(), oldPosition);
			setDead();
			this.getEnviroment().setBeingAt(adult, oldPosition.getRow(), oldPosition.getCol());
			being.add(adult);
		}
	}

	private boolean canEvolve() {
		if (getAge() > EVOLVE_MIN_AGE) {
			return true;
		}
		return false;
	}

	public void incrementAge() {
		setAge(getAge() + 1);
		if (getAge() > MAX_AGE) {
			setDead();
		}
	}
}
