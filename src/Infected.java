import java.util.ArrayList;

public class Infected extends Being {

	public Infected(Enviroment enviroment, Position position) {
		super(enviroment, position);
		
	}
	
	public void act(ArrayList<Being> newBeing) {
		System.out.println("Atuou");
	}

}
