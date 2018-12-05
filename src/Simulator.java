import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Simulator {

	private Enviroment enviroment;
    private SimulatorView simulatorView;
    private ArrayList<Being> being;
    private int [] totalOfInfected;
    private int [] totalOfMosquitoes;
    
    private int step;
    
    private int teste = 5;
	public Simulator(int height, int width, int infected, int people, int mosquitos) {

       enviroment = new Enviroment(height, width);
       simulatorView = new SimulatorView(height, width);
       being = new ArrayList<Being>();
       
       this.populate(people, mosquitos, infected);
       
       
       simulatorView.setColor(Adult.class, Color.red);
       simulatorView.setColor(Infected.class, Color.black);
       simulatorView.setColor(Person.class, Color.gray);
       simulatorView.setColor(Larva.class, Color.white);
       simulatorView.setColor(Egg.class, Color.white);
       simulatorView.setColor(Pupa.class, Color.white);
	}

	// Função para rodar a simulação em uma determinada quantidade de dias 
	 public void run(int steps) {
		 
		 totalOfInfected = new int [steps];
	     totalOfMosquitoes = new int [steps];
	   
	     for(int i=0; i<steps; i++) {
	    	 totalOfInfected[i]= 0;
		     totalOfMosquitoes[i] = 0;
	    	 
	     }
	        while (steps > 0) {

	        	runOneStep();
	        	
	        	try {
	        		
	     		   Thread.sleep(0);
	     	
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
	        UpdateHistograms();
	    }
	
	// Função para adicionar seres ao ambiente
	private void populate(int people, int mosquitoes, int infected_people) {
		
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
		
		while(infected_people > 0) {
			Random rand = new Random();
			int row = rand.nextInt(enviroment.getHeight());
			int col = rand.nextInt(enviroment.getWidth());

	        Position position = new Position(row, col);
	        Infected infected = new Infected(enviroment, position);
	        being.add(infected);
	        enviroment.setBeingAt(infected, row, col);
	        
	        infected_people -- ; 
	
		}
		
			
	}	
	
	public void UpdateHistograms() {
		
		
		for(Iterator<Being> it = being.iterator(); it.hasNext();) {
        	
            Being being = it.next();
            
            if(being instanceof Infected) {
            	totalOfInfected[step-1]++;
            }
            
            if(being instanceof Adult) {
            	totalOfMosquitoes[step-1]++;
            }
            
          
        }
        
		
}
	
	public void PrintHistogram() {
		
		System.out.println(" Total of Infected: ");
		
		for(int i=0; i < step; i++) {
			
			System.out.print( totalOfInfected[i]+ "   ");
		}
		
		System.out.println(" ");
		System.out.println(" Total of mosquitoes: ");
		
		for(int j=0; j < step; j++) {
			
			System.out.print( totalOfMosquitoes[j]+ "   ");
		}
		
	}
	
	// Função para resetar a simulação
    public void reset() {
       step = 0;
       being.clear();
       populate(1,1, 1);
       simulatorView.showStatus(step, enviroment);
    
    }
}
