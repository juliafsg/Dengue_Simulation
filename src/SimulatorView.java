import java.awt.*;
import javax.swing.*;
import java.util.HashMap;


public class SimulatorView extends JFrame {

	private static final long serialVersionUID = 1L;

    private static final Color EMPTY_COLOR = Color.white;

    private static final Color UNKNOWN_COLOR = Color.gray;

    private final String STEP_PREFIX = "Days: ";
    private final String POPULATION_PREFIX = "Population: ";
    private JLabel stepLabel, population;
    private EnviromentView enviromentView;
  
    private HashMap<Class<? extends Being>,Color> colors;

    private EnviromentStats stats;

    public EnviromentStats getStats() {
		return stats;
	}


	public void setStats(EnviromentStats stats) {
		this.stats = stats;
	}

	public SimulatorView(int height, int width) {

        stats = new EnviromentStats();
        colors = new HashMap<Class<? extends Being>,Color>();

        setTitle("Simulation");
        stepLabel = new JLabel(STEP_PREFIX, JLabel.CENTER);
        population = new JLabel(POPULATION_PREFIX, JLabel.CENTER);
        
        setLocation(100, 50);
        
        enviromentView = new EnviromentView(height, width);

        Container contents = getContentPane();
        contents.add(stepLabel, BorderLayout.NORTH);
        contents.add(enviromentView, BorderLayout.CENTER);
        contents.add(population, BorderLayout.SOUTH);
        pack();
        setVisible(true);

    }
   

    public void setColor(Class<? extends Being> beingClass, Color color) {

        colors.put(beingClass, color);

    }


    private Color getColor(Class<? extends Being> beingClass) {

        Color col = colors.get(beingClass);

        if (col == null) {

            return UNKNOWN_COLOR;
        
        }

        else {

            return col;

        }

    }

    public void showStatus(int step, Enviroment enviroment) {

        if (!isVisible()) {

            setVisible(true);
        
        }

        stepLabel.setText(STEP_PREFIX + step);

        stats.reset();
        enviromentView.preparePaint();
            
        for (int row = 0; row < enviroment.getHeight(); row++) {

            for (int col = 0; col < enviroment.getWidth(); col++) {

                Being being = enviroment.getBeingAt(row, col);

                if (being != null && being.isAlive()) {

                    stats.incrementCount(being.getClass());
                    enviromentView.drawMark(col, row, getColor(being.getClass()));

                }

                else {

                    enviromentView.drawMark(col, row, EMPTY_COLOR);

                }

            }

        }

        stats.countFinished();
        population.setText(POPULATION_PREFIX + stats.getPopulationDetails(enviroment));
        enviromentView.repaint();

    }



    private class EnviromentView extends JPanel {

		private static final long serialVersionUID = 1L;
		private final int GRID_VIEW_SCALING_FACTOR = 6;
        private int gridWidth, gridHeight;
        private int xScale, yScale;
        Dimension size;
        private Graphics g;
        private Image enviromentImage;


        public EnviromentView(int height, int width) {

            gridHeight = height;
            gridWidth = width;
            size = new Dimension(0, 0);

        }


        public Dimension getPreferredSize() {

            return new Dimension(gridWidth * GRID_VIEW_SCALING_FACTOR,
                                 gridHeight * GRID_VIEW_SCALING_FACTOR);
       
       }
        

        public void preparePaint() {

            if (! size.equals(getSize())) {  // if the size has changed...

                size = getSize();
                enviromentImage = enviromentView.createImage(size.width, size.height);
                g = enviromentImage.getGraphics();

                xScale = size.width / gridWidth;

                if (xScale < 1) {

                    xScale = GRID_VIEW_SCALING_FACTOR;
                
                }
                
                yScale = size.height / gridHeight;
                
                if (yScale < 1) {

                    yScale = GRID_VIEW_SCALING_FACTOR;

                }

            }

        }

        public void drawMark(int x, int y, Color color) {

            g.setColor(color);
            g.fillRect(x * xScale, y * yScale, xScale-1, yScale-1);

        }


        public void paintComponent(Graphics g) {

            if (enviromentImage != null) {

                Dimension currentSize = getSize();

                if (size.equals(currentSize)) {

                    g.drawImage(enviromentImage, 0, 0, null);

                }

                else {

                    // Rescale the previous image.
	                    g.drawImage(enviromentImage, 0, 0, currentSize.width, currentSize.height, null);
	                
	                }

	            }

	        }

	    }

	}

