import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Enviroment {
	
    private Cell[][] enviroment;
    private int height;
    private int width;

    // Construtor do ambiente
    public Enviroment(int height, int width) {

        this.height = height;
        this.width = width;

        enviroment = new Cell[height][width];

        for (int i = 0; i < height; i++) {

            for (int j = 0; j < width; j++) {

                enviroment[i][j] = new Cell();

            }
        }
    }
    
    // Retorna a altura do ambiente
    public int getHeight() {

        return this.height;

    }
    
    // Retorna a largura do ambiente
    public int getWidth() {

        return this.width;

    }
    
    // Override que retorna o ser em uma determinada posição
    public Being getBeingAt(int row, int col) {

        if ((row >= 0 && row <= this.height) && (col >= 0 && col <= this.width)) {

        	return this.enviroment[row][col].getBeing();

        }

        else {

            return null;

        }

    }
    
    // Retorna o ser em determinada posição
    public Being getBeingAt(Position position) {
        
    	return getBeingAt(position.getRow(), position.getCol());

    }
    
    // Override que retorna a quantidade de água
    public int getWaterAt(Position position) {
        
    	return getWaterAt(position.getRow(), position.getCol());

    }
    
    // Função que retorna a quantidade de agua em uma região
    private int getWaterAt(int row, int col) {
    	if ((row >= 0 && row <= this.height) && (col >= 0 && col <= this.width)) {

        	return this.enviroment[row][col].getWater();

        }

        else {

            return 0;

        }
	}

    // Função que  resgata uma única posição adjacente livre
	public Position freeAdjacentPosition(Position position) {
    	 
        List<Position> free = getFreeAdjacentPositions(position);
        
        if (free.size() > 0) {
        	
            return free.get(0);
            
        }
        
        else {
        	
            return null;
        
        }
    }
    
    // Função para buscar as posições adjacentes livres
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
    
    // Função para buscar as posições adjacentes de uma lista
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

    // Função para adicionar um ser em uma determinada posição
    public void setBeingAt(Being being, int row, int col) {

        if ((row >= 0 && row <= this.height) && (col >= 0 && col <= this.width)) {

            this.enviroment[row][col].setBeing(being);

        }

    }
    
    // Função para desocupar uma determinada posição
    public void clear(Position position) {
		
        this.enviroment[position.getRow()][position.getCol()].clear();
	
	}

    // Função que retorna uma lista com todas as posições disponíveis
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
	
    // Função que retorna uma lista com todas as posições aleatoriamente
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
