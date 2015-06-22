package mustererkennung.algorithmen;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import adaptivesysteme.NeuronNetz.NeuronenSchicht;
import adaptivesysteme.NeuronNetz.TransferExp;
import adaptivesysteme.NeuronNetz.Transferfunktion;

// TODO: Auto-generated Javadoc
/**
 * The Class PLA.
 */
public class PLA {

	/** The n. */
	private NeuronenSchicht n;

	/** The f. */
	private TransferExp f;

	private PrintWriter file;

	/**
	 * Instantiates a new pla.
	 *
	 * @param dim
	 *            the dim
	 */
	public PLA(int dim, int input) {
		this.f = new TransferExp();
		this.f.setLdelta(1.5);
		this.f.setUpdateRate(0.005);
		this.n = new NeuronenSchicht(input, dim, f);
	}

	/**
	 * Trainieren des PLA
	 *
	 * @param werte
	 *            Die Trainingsdatensätze
	 * @param result
	 *            Der Erwartungswert
	 */
	public void train(double[][] werte, double[][] result) {
		try {
			file = new PrintWriter("Errors_PLA.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int fehler = 1;
		int i = 0, j = 0;
		for (j = 0; j < 10000 && fehler != 0; j++) {
			f.increaseLambda();
			fehler = 0;
			double[] y;
			for (i = 0; i < werte.length; i++) {
				n.train(werte[i], result[i]);
				// Anzahl Fehler berechnen
			}
			fehler = 0;
			for (int k = 0; k < werte.length && fehler == 0; k++) {
				y = n.fire(werte[k]);
				for (int e = 0; e < y.length; e++) {
					if (f.toDiskret(y[e]) != result[k][e]) {
						fehler++;
						break;
					}
				}
			}
			// n.print_W();
			file.append(fehler + ",\n"); // Setzen neuer gewichte
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
	public double[] fire(double[] x) {
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
