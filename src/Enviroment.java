import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Enviroment {
	
    private Cell[][] enviroment;
    private double temperature;
    private int height;
    private int width;
    
    public Enviroment(int height, int width, double temperature) {

        this.height = height;
        this.width = width;
        this.temperature = temperature;

        enviroment = new Cell[height][width];

        for (int i = 0; i < height; i++) {

            for (int j = 0; j < width; j++) {

                enviroment[i][j] = new Cell();

            }
        }
    }
    
    public int getHeight() {

        return this.height;

    }
    
    public int getWidth() {

        return this.width;

    }
    
    public Being getBeingAt(int row, int col) {

        if ((row >= 0 && row <= this.height) && (col >= 0 && col <= this.width)) {

        	return this.enviroment[row][col].getBeing();

        }

        else {

            return null;

        }

    }
    
    public Being getBeingAt(Position position) {
        
    	return getBeingAt(position.getRow(), position.getCol());

    }
    
    public int getWaterAt(Position position) {
        
    	return getWaterAt(position.getRow(), position.getCol());

    }
    
    private int getWaterAt(int row, int col) {
    	if ((row >= 0 && row <= this.height) && (col >= 0 && col <= this.width)) {

        	return this.enviroment[row][col].getWater();

        }

        else {

            return 0;

        }
	}

	public Position freeAdjacentPosition(Position position) {
    	 
        List<Position> free = getFreeAdjacentPositions(position);
        
        if (free.size() > 0) {
        	
            return free.get(0);
            
        }
        
        else {
        	
            return null;
        
        }
    }
    
    public List<Position> getFreeAdjacentPositions(Position position) {
        	
            List<Position> free = new LinkedList<Position>();
            List<Position> adjacent = adjacentPositions(position);
            
            for (Position next : adjacent) {
            	
                if (getBeingAt(next) == null) {
                	
                    free.add(next);
        
                }
        
            }

            return free;
        
        } 
    
    public List<Position> adjacentPositions(Position position) {
        
        assert position != null : "Null location passed to adjacentLocations";
    
        List<Position> positions = new LinkedList<Position>();
        
        if (position != null) {
        	
            int row = position.getRow();
            int col = position.getCol();
            
            for (int roffset = -1; roffset <= 1; roffset++) {
            	
                int nextRow = row + roffset;
                
                if (nextRow >= 0 && nextRow < height) {
                	
                    for(int coffset = -1; coffset <= 1; coffset++) {
                    	
                        int nextCol = col + coffset;
                        
                        if (nextCol >= 0 && nextCol < width && (roffset != 0 || coffset != 0)) {
                        	
                            positions.add(new Position(nextRow, nextCol));
                       
                        }
                    
                    }
                
                }
            
            }
            Collections.shuffle(positions);
        }
        
        return positions;
    
    }

    public void setBeingAt(Being being, int row, int col) {

        if ((row >= 0 && row <= this.height) && (col >= 0 && col <= this.width)) {

            this.enviroment[row][col].setBeing(being);

        }

    }
    
    public void clear(Position position) {
		
        this.enviroment[position.getRow()][position.getCol()].clear();
	
	}

	public List<Position> getFreePositions(Position position) {
		List<Position> free = new LinkedList<Position>();
        List<Position> positions = Positions();
        
        for (Position next : positions) {
        	
            if (getBeingAt(next) == null) {
            	
                free.add(next);
    
            }
    
        }

        return free;
	}
	
	public List<Position> Positions() {
		
		List<Position> allPositions = new LinkedList<Position>();
		
		  for (int i = 0; i < height; i++) {

	            for (int j = 0; j < width; j++) {
	            	
	            	Position position = new Position(i,j);
	                allPositions.add(position);

	            }
	        }
		  
		  Collections.shuffle(allPositions);
		
		return allPositions;
		
	}
	

    
}    
