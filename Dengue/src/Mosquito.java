import java.util.ArrayList;
import java.util.Random;

public class Mosquito extends Being {
	// Atribuições do mosquito
	private int locomotion;
	private boolean infected;

	public Mosquito(Enviroment enviroment, Position position) {
		super(enviroment, position);
		Random random = new Random();
		infected = random.nextBoolean();
		locomotion = 0;
	}

	public double getLocomotion() {
		return locomotion;
	}

	public void setLocomotion(int locomotion) {
		this.locomotion = locomotion;
	}

	public boolean isInfected() {
		return infected;
	}

	public void setInfected(boolean infected) {
		this.infected = infected;
	}

	@Override
	public void act(ArrayList<Being> newBeing) {

	}
}
