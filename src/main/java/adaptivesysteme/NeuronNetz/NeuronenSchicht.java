package adaptivesysteme.NeuronNetz;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class NeuronenSchicht.
 */
public class NeuronenSchicht {

	/** Array mit den Schichten */
	private ArrayList<Neuron> neurons;

	/** Die Anzahl der Schichten. */
	private int size = 0;

	/** Anzahl der Eingänge jedes Neurons */
	private int inputs = 0;

	/**
	 * Instantiates eine neuronenSchicht.
	 *
	 * @param input   Die Anzahl der Eingabewerte
	 * @param output  Die Anzahl der Ausgangswerte = Anzahl der Neuronen in der
	 *                Schicht
	 * @param f       Die Transferfunktion für die Neuronen
	 */
	public NeuronenSchicht(int input, int output, Transferfunktion f) {
		neurons = new ArrayList<Neuron>();
		size = output;
		inputs = input;
		for (int i = 0; i < output; i++) {
			Neuron neuron = new Neuron(input, f, 0.5);
			neurons.add(neuron);
		}
	}

	/**
	 * Instantiates eine neuronenSchicht.
	 *
	 * @param input   Die Anzahl der Eingabewerte
	 * @param output  Die Anzahl der Ausgangswerte = Anzahl der Neuronen in der
	 *                Schicht
	 * @param f       Die Transferfunktion für die Neuronen
	 * @param name    Ein String der den Namen der Schicht angibt
	 */
	public NeuronenSchicht(int input, int output, Transferfunktion f, String name) {
		neurons = new ArrayList<Neuron>();
		size = output;
		inputs = input;
		for (int i = 0; i < output; i++) {
			Neuron neuron = new Neuron(input, f, 0.5, name + " " + i);
			neurons.add(neuron);
		}
	}

	/**
	 * Gets the last results.
	 *
	 * @return the last results
	 */
	public double[] getLastResults() {
		double[] lasts = new double[size];
		for (int i = 0; i < size; i++) {
			lasts[i] = neurons.get(i).getLastResult();
		}
		return lasts;
	}

	public double getSize() {
		return this.size;
	}

	/**
	 * Fire.
	 *
	 * @param x   the Eingabewerte
	 * @return the double[] Die Ergebnisse der Neuronen der Schicht
	 */
	public double[] fire(double[] x) {
		double[] result = new double[size];
		for (int i = 0; i < size; i++) {
			result[i] = neurons.get(i).fire(x);
		}
		return result;
	}

	/**
	 * Gibt die Gewichte auf der Kommandozeile aus
	 */
	public void print_W() {
		for (int i = 0; i < size; i++) {
			neurons.get(i).print_W();
		}
	}

	/**
	 * Trainiert die einzelnen Neuronen der Schicht
	 *
	 * @param x   Eingabewerte
	 * @param y   Der Erwartungswert
	 */
	public double[] train(double[] x, double[] y) {
		double[] ret = new double[size];
		for (int i = 0; i < size; i++) {
			ret[i] = neurons.get(i).train(x, y[i]);
		}
		return ret;
	}

	/**
	 * Train net.
	 *
	 * @param last Boolean ob es die letzte Schicht im Netz ist
	 * @param y Der Erwartungswert
	 * @param o_prev die o's der vorgelagerten Schicht
	 * @param w_prev Die Gewichte der vorgelagerten Schicht
	 * @param size_prev Die Größe der Vorgelagerten Schicht
	 * @return the double[] Die o's der Schicht um in der nächsten verwendet werden zu können
	 */
	public double[] trainNet(boolean last, double[] y, double[] o_prev, double[][] w_prev, double size_prev) {
		double[] o_akt = new double[neurons.size()];
		if (last == true) {
			for (int i = 0; i < neurons.size(); i++) {
				o_akt[i] = neurons.get(i).trainlast(y[i]);
			}
		} else {
			for (int i = 0; i < neurons.size(); i++) {
				double ow;
				ow = 0;
				for (int j = 0; j < size_prev; j++) {
					ow += (o_prev[j] * w_prev[j][i+1]);
				}
				o_akt[i] = neurons.get(i).trainMitte(ow);
			}
		}
		return o_akt;
	}

	/**
	 * Gibt die Gewichte aller Neuronen der Schicht zurück
	 * 
	 * @return Array der Gewichte der Neuronen der Schicht
	 * */
	public double[][] getW() {
		double[][] ws = new double[size][inputs + 1];
		for (int i = 0; i < size; i++) {
			Neuron n = this.neurons.get(i);
			double[] w = n.getW();
			for (int e = 0; e <= inputs; e++) {
				ws[i][e] = w[e];
			}
		}
		return ws;
	}
}
