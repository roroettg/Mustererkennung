package adaptivesysteme.NeuronNetz;

/**
 * The Class Neuron.
 * 
 * @author Jens Hölscher
 */
public class Neuron {

	/** Die Gewichte */
	private double w[];

	/** Der Threshold */
	private double threshold = 0;

	/** Der Zuletzt berechnete Eingangsvector */
	private double last_x[];

	/** Das zuletzt berechnete Ergebnis */
	private double last_result;

	/** Die Lernrate */
	private double lern_rate = 0.1;

	/** Die Anzahl der Eingänge */
	private int n_input;

	/** Die zu benutzende Transferfunktion als Strategiemuster */
	private Transferfunktion transfer;

	/** Der Name des Neurons, wird für eine Ausgabe benutzt */
	private String tag = "";

	/**
	 * Die möglichen Lernalgorithmen, wird noch per Strategiepattern ersetzt.
	 */
	private enum Lernalgo {
		Adaline, Perzeptron
	};

	/** The training. */
	private Lernalgo training = Lernalgo.Perzeptron;

	/**
	 * Instantiates a new neuron.
	 *
	 * @param input
	 *            Anzahl der Eingänge des Neurons
	 * @param f
	 *            Die Transferfunktion
	 * @param t
	 *            Der Threshold
	 */
	public Neuron(int input, Transferfunktion f, double t) {
		threshold = t;
		n_input = input;
		transfer = f;
		w = new double[input + 1];
		w[0] = 1;
		for (int i = 1; i <= input; i++)
			w[i] = Math.random();
	}

	/**
	 * Instantiates a new neuron with a Nametag.
	 *
	 * @param input
	 *            Anzahl der Eingänge
	 * @param f
	 *            Die Transferfunktion
	 * @param t
	 *            Der Threshold
	 * @param name
	 *            Der Name des Neurons der angezeigt werden kann
	 */
	public Neuron(int input, Transferfunktion f, double thresch, String name) {
		threshold = thresch;
		n_input = input;
		transfer = f;
		w = new double[input + 1];
		w[0] = 1;
		for (int i = 1; i <= input; i++)
			w[i] = Math.random();
		tag = name;
	}

	/**
	 * Gets the last result.
	 *
	 * @return the last result
	 */
	public double getLastResult() {
		return last_result;
	}

	/**
	 * Get die Gewichte.
	 *
	 * @return die Gewichte als Array
	 */
	public double[] getW() {
		return w;
	}
	
	public void setW(double[] newW){
		if(newW.length == this.w.length){
			for(int i=0; i<w.length; i++){
				this.w[i]= newW[i];
			}
		}
	}

	/**
	 * Gibt die Gewichte und den Tag des Neurons aus
	 */
	public void print_W() {
		StringBuffer b = new StringBuffer(this.tag + ":");
		for (int i = 0; i <= n_input; i++) {
			b.append("w" + i + ":" + w[i] + " ");
		}
		System.out.println(b.toString());
	}

	/**
	 * Abfragen des Neurons anhand der Eingangswerte
	 *
	 * @param x
	 *            Die Eingangswerte
	 * @return Der Ausgangswert abhängig von der gewählten Transferfunktion
	 */
	public double fire(double[] x) {
		return transfer.f(calc(x));
	}

	/**
	 * Durchführen der Berechnung mit der Rückgabe des Wertes ohne
	 * Transferfunktion
	 *
	 * @param x
	 *            Die Eingangswerte
	 * @return Der Ausgangswert
	 */
	private double calc(double[] x) {
		last_result = 0;
		last_x = new double[x.length + 1];
		last_x[0] = -threshold;
		for (int i = 0; i < x.length; i++) {
			last_x[i + 1] = x[i];
		}
		for (int i = 0; i <= n_input; i++) {
			last_result += (w[i] * last_x[i]);
		}
		return last_result;
	}

	/**
	 * Trainingsfunktion zur änderung der Gewichte für eine Neuronalesnetz, wenn
	 * das Neuron ein Ausgangsneuron ist
	 *
	 * @param y  Der Erwartungswert
	 * @return Der Fehlerwert für die nächste hiddon Schicht
	 */
	public double trainlast(double d) {
		double y = transfer.f(last_result);
		double e = (d - y);
		double o = transfer.df(last_result) * e;
		for (int k = 0; k <= n_input; k++) {
			w[k] += lern_rate * o * last_x[k];
		}
		return o;
	}

	/**
	 * Trainingsfunktion wenn das Neuron in einem Netzt ist und in der Mitte ist
	 *
	 * @param o_next
	 *            Die Übergebenen Fehler der nächsten Schicht
	 * @return Der Fehlerwert für die nächste hiddon Schicht
	 */
	public double trainMitte(double o_next) {
		double o = o_next;
		double h = transfer.f(last_result);
		o = h * (1 - h) * o;
		for (int k = 0; k <= n_input; k++) {
			w[k] += lern_rate * o * last_x[k];
		}
		return o;
	}

	/**
	 * Trainingsfunktion für ein einfaches alleinstehendes Neuron
	 *
	 * @param x
	 *            Der Eingangsvector
	 * @param y
	 *            Der Erwartungswert
	 */
	public double train(double[] x, double d) {
		double y = 0, e = 0, ret = fire(x);
		switch (training) {
		case Perzeptron:
			y = calc(x);
			e = d - transfer.f(y);
			for (int i = 0; i <= n_input; i++) {
				w[i] += (lern_rate * e * transfer.df(y) * last_x[i]);
			}
			break;
		case Adaline:
			y = calc(x);
			e = d - y;
			for (int i = 0; i <= n_input; i++) {
				w[i] += (lern_rate * e * last_x[i]);
			}
			break;
		}
		return ret;
	}

	/**
	 * Sets the lern rate.
	 *
	 * @param r
	 *            the new lern rate
	 */
	public void setLernRate(double r) {
		this.lern_rate = r;
	}
	

}
