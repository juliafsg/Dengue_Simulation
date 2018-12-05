import java.util.List;

public class Pupa extends Mosquito{

	private static final int MAX_AGE = 30;
	
	public Pupa(Enviroment enviroment, Position position) {

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
