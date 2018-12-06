import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;

public class Main {
	public static void main(String[] args) {
		Simulator simulation = new Simulator(150, 150, 0, 300, 10);
		simulation.run(100);
		simulation.PrintHistogram();

		// Create Chart
		final XYChart chart = new XYChartBuilder().width(600).height(400)
				.title("Relação de mosquitos e infectados").xAxisTitle("Dias").yAxisTitle("Valor Total")
				.build();

		// Customize Chart
		chart.getStyler().setLegendPosition(LegendPosition.InsideNE);
		chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);

		// Series
		double [] totalInfect = new double [simulation.getTotalOfInfected().length];
		double [] totalMosquit = new double [simulation.getTotalOfMosquitoes().length];
		double [] steps = new double[simulation.getStep()];
		
		for (int i = 0; i < simulation.getStep(); i++) {
			steps[i] = (double) i;
		}
		
		for (int i = 0; i < totalInfect.length; i++) {
			totalInfect[i] = (double) simulation.getTotalOfInfected()[i];
		}
		
		for (int i = 0; i < totalMosquit.length; i++) {
			totalMosquit[i] = (double) simulation.getTotalOfMosquitoes()[i];
		}
		
		chart.addSeries("Total de infectados", steps, totalInfect);
		chart.addSeries("Total de mosquitos", steps, totalMosquit);

		JFrame frame = new JFrame("Advanced Example");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// chart
		JPanel chartPanel = new XChartPanel<XYChart>(chart);
		frame.add(chartPanel, BorderLayout.CENTER);

		// label
		JLabel label = new JLabel("@dengue", SwingConstants.CENTER);
		frame.add(label, BorderLayout.SOUTH);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
}
