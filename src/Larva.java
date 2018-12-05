import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Larva extends Mosquito {

	private static final int MAX_AGE = 10;
	private static final int EVOLVE_MIN_AGE = 5;
	
	public Larva(Enviroment enviroment, Position position) {

		super(enviroment, position);
		setLocomotion(20);
	}
	
	 public void act(ArrayList<Being> being) {
	      
	        if (isAlive()) {
	        	
	        	incrementAge();

	        	boolean foundFood = lookFood();
	        	
	        	if(!foundFood) {
	        		incrementHunger();
	        	}
	
	        	move();
	        	
	        	evolve(being);
	        	}
	        }

	private boolean lookFood() {
		 Enviroment enviroment = getEnviroment();
 		 
	     List<Position> adjacent = enviroment.getFreeAdjacentPositions(getPosition());
	     Iterator<Position> it = adjacent.iterator();
	   
	     while (it.hasNext()) {
	        	
	            Position where = it.next();
	            
	            int food = enviroment.getWaterAt(where);
	            
	            if(food == 1) {
	         	
	            	moveTo(where);	
	            	
	            	return true;
	              }
	           }
	     
	     return false;
		
		
	}

	 private void evolve(ArrayList<Being> being) {
			 
			if(canEvolve()) { 
				
				Position oldPosition = getPosition();
				Pupa pupa = new Pupa(getEnviroment(),oldPosition);
				 setDead();
				 this.getEnviroment().setBeingAt(pupa, oldPosition.getRow(), oldPosition.getCol());
				 being.add(pupa);
			}
			 
		}
		 
		 private boolean canEvolve() {
			 if(getAge() > EVOLVE_MIN_AGE) {
				 
				 return true;
			 }
			 
			 return false;
		 }
		
	
	public void move() {
		
		 for(int i = 0; i< getLocomotion(); i++) {
			 moveOnePosition();
		 }
		  
	} 
	
	public void moveOnePosition() {
	  
		Position newPosition = getEnviroment().freeAdjacentPosition(getPosition());
			 
		  Position oldPosition = getPosition();
		  
		  if (newPosition != null) {
			    
	         setPosition(newPosition);
	         this.getEnviroment().setBeingAt(this, newPosition.getRow(), newPosition.getCol());
	         getEnviroment().clear(oldPosition);
	     }
	  
	} 	
	
	private void moveTo(Position newPosition) {
		 
		 if (newPosition !=null) {
			 
			 Position oldPosition = getPosition();
			 setPosition(newPosition);
	         this.getEnviroment().setBeingAt(this, newPosition.getRow(), newPosition.getCol());
	         getEnviroment().clear(oldPosition);
		 }
	}

	public void incrementAge() {
	       
	    	setAge(getAge() + 1);
	        
	        if (getAge() > MAX_AGE) {
	     
	            setDead();
	    
	        }
	    
	    }
}

