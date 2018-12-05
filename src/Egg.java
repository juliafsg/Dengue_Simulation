import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Egg extends Mosquito{

	private static final int MAX_AGE = 35;
	private static final int EVOLVE_MIN_AGE = 10;
	
	public Egg(Enviroment enviroment, Position position) {

		super(enviroment, position);
		setLocomotion(1);

	}
	
	 public void act(ArrayList<Being> being) {
	      
	        if (isAlive()) {
	        	
	        	incrementAge();
	        	
	        	boolean foundWater = lookWater();
	        	
	        	if(!foundWater) {
	        		move();
	        		
	        	}
	        	
	        	else if(foundWater) {
	        		
	        		evolve(being);
	        	}
	        	
	        }
		
	    }
	 
	 private void evolve(ArrayList<Being> being) {
		 
		if(canEvolve()) { 
			
			Position oldPosition = getPosition();
			 Larva larva = new Larva(getEnviroment(),oldPosition);
			 setDead();
			 this.getEnviroment().setBeingAt(larva, oldPosition.getRow(), oldPosition.getCol());
			 being.add(larva);
		}
		 
	}
	 
	 private boolean canEvolve() {
		 if(getAge() > EVOLVE_MIN_AGE) {
			 
			 return true;
		 }
		 
		 return false;
	 }

	private void move() {
		 
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

	private boolean lookWater() {
		 
		 Enviroment enviroment = getEnviroment();
	     		 
	     List<Position> adjacent = enviroment.getFreeAdjacentPositions(getPosition());
	     Iterator<Position> it = adjacent.iterator();
	   
	     while (it.hasNext()) {
	        	
	            Position where = it.next();
	            
	            int water = enviroment.getWaterAt(where);
	            
	            if(water == 1) {
	         	
	            	moveTo(where);	
	            	return true;
	              }
	           }
	     
	     return false;
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
