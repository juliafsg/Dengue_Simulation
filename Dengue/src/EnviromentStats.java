import java.util.HashMap;

public class EnviromentStats {
	// Counters for each type of entity (shark, herring, etc.) in the simulation:
	private HashMap<Class<? extends Being>, Counter> counters;
	// Whether the counters are currently up to date:
	private boolean countsValid;

	public EnviromentStats() {
		// Set up a collection for counters for each type of fish that
		// we might find
		counters = new HashMap<Class<? extends Being>, Counter>();
		countsValid = true;
	}

	public String getPopulationDetails(Enviroment enviroment) {
		StringBuffer buffer = new StringBuffer();

		if (!countsValid) {
			generateCounts(enviroment);
		}

		for (Class<? extends Being> key : counters.keySet()) {
			Counter info = counters.get(key);
			buffer.append(info.getName());
			buffer.append(": ");
			buffer.append(info.getCount());
			buffer.append(' ');
		}
		return buffer.toString();
	}

	public void reset() {
		countsValid = false;
		for (Class<? extends Being> key : counters.keySet()) {
			Counter cnt = counters.get(key);
			cnt.reset();
		}
	}

	public void incrementCount(Class<? extends Being> fishClass) {
		Counter cnt = counters.get(fishClass);

		if (cnt == null) {
			// we do not have a counter for this species yet - create one
			cnt = new Counter(fishClass.getName());
			counters.put(fishClass, cnt);
		}
		cnt.increment();
	}

	public void countFinished() {
		countsValid = true;
	}

	private void generateCounts(Enviroment enviroment) {
		reset();
		for (int row = 0; row < enviroment.getHeight(); row++) {
			for (int col = 0; col < enviroment.getWidth(); col++) {
				Being being = enviroment.getBeingAt(row, col);
				if (being != null) {
					incrementCount(being.getClass());
				}
			}
		}
		countsValid = true;
	}

	public HashMap<Class<? extends Being>, Counter> getCounters() {
		return counters;
	}

	public void setCounters(HashMap<Class<? extends Being>, Counter> counters) {
		this.counters = counters;
	}
}
