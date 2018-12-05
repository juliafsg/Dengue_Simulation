import java.util.List;

public class Larva extends Mosquito {

	private static final int MAX_AGE = 5;
	
	public Larva(Enviroment enviroment, Position position) {

		super(enviroment, position);

	}
	
	 public void act(List<Being> being) {
	      
	        if (isAlive()) {
	        	
	        	incrementAge();
	        
	        }
		
	    }
	
	

	public void incrementAge() {
	       
	    	setAge(getAge() + 1);
	        
	        if (getAge() > MAX_AGE) {
	     
	            setDead();
	    
	        }
	    
	    }
}

