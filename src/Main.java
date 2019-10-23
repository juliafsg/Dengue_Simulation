// Classe principal para inicio da simulação
public class Main {

	public static void main(String[] args) {

		Simulator simulation = new Simulator(150, 150, 50, 10, 50);
        simulation.run(100);
        simulation.PrintHistogram();
	}

}
