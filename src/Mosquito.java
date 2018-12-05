import java.util.ArrayList;
import java.util.List;

public class Mosquito extends Being {
	
	// Atribuições do mosquito
	private int hunger;
	private boolean bloodNeed;
	private int locomotion;
	private boolean infected;
	
	public Mosquito(Enviroment enviroment, Position position) {

		super(enviroment, position);
		hunger = 0;
		bloodNeed = false;
		infected = false;
		locomotion = 0;
		
	}

	public int getHunger() {
		return hunger;
	}

	public void setHunger(int hunger) {
		this.hunger = hunger;
	}

	public boolean getBloodNeed() {
		return bloodNeed;
	}

	public void setBloodNeed(boolean bloodNeed) {
		this.bloodNeed = bloodNeed;
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
