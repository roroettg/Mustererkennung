package mustererkennung.algorithmen;

import adaptivesysteme.NeuronNetz.Neuron;
import adaptivesysteme.NeuronNetz.TransferExp;
import adaptivesysteme.NeuronNetz.Transferfunktion;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

// TODO: Auto-generated Javadoc
/**
 * The Class PLA.
 */
public class PLA {

	/** The n. */
	private Neuron n;

	/** The f. */
	private Transferfunktion f;

	private PrintWriter file;

	/**
	 * Instantiates a new pla.
	 *
	 * @param dim
	 *            the dim
	 */
	public PLA(int dim) {
		this.f = new TransferExp();
		this.n = new Neuron(dim, f, 0.5);
	}

	/**
	 * Trainieren des PLA
	 *
	 * @param werte
	 *            Die Trainingsdatensätze
	 * @param result
	 *            Der Erwartungswert
	 */
	public void train(double[][] werte, double[] result) {
		try {
			file = new PrintWriter("Errors_PLA.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Automatisch generierter Erfassungsblock
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Automatisch generierter Erfassungsblock
			e.printStackTrace();
		}
		int fehler = 1;
		int i = 0, j = 0;
		for (j = 0; j < 100000 && fehler != 0; j++) {
			if (j % 1000 == 0)
				f.increaseLambda();
			fehler = 0;
			for (i = 0; i < werte.length; i++) {
				double y;
				y = n.train(werte[i], result[i]);
				// Anzahl Fehler berechnen
				fehler = 0;
				for (int k = 0; k < werte.length; k++) {
					y = n.fire(werte[k]);
					if (f.toDiskret(y) != result[k]) {
						fehler++;
					}
				}
				if (fehler == 0)
					break;
				file.append(fehler + ",\n");
				// Setzen neuer gewichte
			}
		}
		System.out.println("Fertig nach " + j);
		System.out.println("TestAnd Gewichte:");
		n.print_W();
		file.close();
	}

	/**
	 * Fire.
	 *
	 * @param x
	 *            the x
	 * @return the double
	 */
	public double fire(double[] x) {
		return n.fire(x);
	}

	/**
	 * Gets the f.
	 *
	 * @return the f
	 */
	public Transferfunktion getF() {
		return this.f;
	}

}