import java.util.ArrayList;
import java.util.Random;

public class Person extends Being {

	public Person(Enviroment enviroment, Position position) {
		super(enviroment, position);
	}

	@Override
	public void act(ArrayList<Being> being) {
		if (isAlive()) {
			Random rand = new Random();
			int move = rand.nextInt(2);
			if (move == 1) {
				move();
			}
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
	
	public void beBitten(Adult adult, ArrayList<Being> being) {
		if (adult.isInfected()) {
			Position oldPosition = getPosition();
			Infected infected = new Infected(getEnviroment(), oldPosition);
			setDead();
			getEnviroment().setBeingAt(infected, oldPosition.getRow(), oldPosition.getCol());
			being.add(infected);
		}
	}
}
