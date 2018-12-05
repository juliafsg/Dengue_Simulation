import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Simulator {

	private Enviroment enviroment;
    private SimulatorView simulatorView;
    private ArrayList<Being> being;
    private int step;
    
    private int teste = 5;
	public Simulator(int height, int width, int temperature, int people, int mosquitos) {

       enviroment = new Enviroment(height, width, temperature);
       simulatorView = new SimulatorView(height, width);
       being = new ArrayList<Being>();
       
       this.populate(people, mosquitos);
       
       simulatorView.setColor(Adult.class, Color.red);
       simulatorView.setColor(Infected.class, Color.green);
       simulatorView.setColor(Person.class, Color.black);
       simulatorView.setColor(Larva.class, Color.orange);
       simulatorView.setColor(Egg.class, Color.blue);
	}

	// Função para rodar a simulação em uma determinada quantidade de dias 
	 public void run(int steps) {
	    	
	        while (steps > 0) {

	        	runOneStep();
	        	
	        	try {
	        		
	     		   Thread.sleep(50);
	     	
		     	} catch (Exception e) {
		     		
		     		   e.printStackTrace();
		     	
		     	}
	        	
	        	steps--;
	        }
	        
	    }
	 
	 // Função para simular um dia
	 public void runOneStep() {

	        this.step++;
	        ArrayList<Being> newBeing = new ArrayList<Being>();
	        
	        for(Iterator<Being> it = being.iterator(); it.hasNext();) {
	        	
	            Being being = it.next();
	            being.act(newBeing);
	            
	            if (!being.isAlive()) {
	            	
	                it.remove();
	                
	            }
	            
	        }
	        
	        being.addAll(newBeing);
	      
	        
	        simulatorView.showStatus(step, enviroment);
	        
	    }
	
	// Função para adicionar seres ao ambiente
	private void populate(int people, int mosquitoes) {
		
		while(people > 0) {
			Random rand = new Random();
			int row = rand.nextInt(enviroment.getHeight());
			int col = rand.nextInt(enviroment.getWidth());

	        Position position = new Position(row, col);
	        Person person = new Person(enviroment, position);
	        being.add(person);
	        enviroment.setBeingAt(person, row, col);
	        
	        people -- ; 
	
		}
		
		while(mosquitoes > 0) {
			Random rand = new Random();
			int row = rand.nextInt(enviroment.getHeight());
			int col = rand.nextInt(enviroment.getWidth());

	        Position position = new Position(row, col);
	        Adult mosquito = new Adult(enviroment, position);
	        being.add(mosquito);
	        enviroment.setBeingAt(mosquito, row, col);
	        
	        mosquitoes -- ; 
	
		}
		
			
	}	
	
	// Função para resetar a simulação
    public void reset() {
       step = 0;
       being.clear();
       populate(1,1);
       simulatorView.showStatus(step, enviroment);
    
    }
}
