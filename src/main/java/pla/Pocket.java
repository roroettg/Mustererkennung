package pla;

import adaptivesysteme.NeuronNetz.NeuronenSchicht;
import adaptivesysteme.NeuronNetz.TransferExp;
import adaptivesysteme.NeuronNetz.Transferfunktion;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Pocket {

	/** The n. */
	private NeuronenSchicht n;

	/** The f. */
	private Transferfunktion f;

	private double[][] bestWeights;

	private double bestError = -1;

	private int dim = 0;
	private PrintWriter file;

	/**
	 * Instantiates a new pla.
	 *
	 * @param dim
	 *            the dim
	 */
	public Pocket(int dim) {
		this.dim = dim;
		this.f = new TransferExp();
		this.n = new NeuronenSchicht(1, dim, f, "Pocket");
	}

	private void copyWeights(double[][] newW) {
		bestWeights = new double[dim][];
		for (int i = 0; i < dim; i++) {
			bestWeights[i] = new double[newW[i].length];
			for (int e = 0; e < newW[i].length; e++) {
				bestWeights[i][e] = newW[i][e];
			}
		}
	}

	private void updateWeights(int error) {
		if (error < bestError) {
			bestError = error;
			copyWeights(n.getW());
		}
	}

	/**
	 * Trainieren des PLA
	 *
	 * @param werte
	 *            Die Trainingsdatensätze
	 * @param result
	 *            Der Erwartungswert
	 */
	public double train(double[][] werte, double[][] result) {
		try {
			file = new PrintWriter("Errors_Pocket.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Automatisch generierter Erfassungsblock
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Automatisch generierter Erfassungsblock
			e.printStackTrace();
		}
		this.bestError = result.length;
		int fehler = -1;
		int i = 0, j = 0;
		for (j = 0; j < 100000 && fehler != 0; j++) {
			if (j % 1000 == 0)
				f.increaseLambda();
			for (i = 0; i < werte.length; i++) {
				double[] y;
				y = n.train(werte[i], result[i]);
				// Anzahl Fehler berechnen
				fehler = 0;
				for (int k = 0; k < werte.length; k++) {
					y = n.fire(werte[k]);
					for (int g = 0; g < dim; g++) {
						if (f.toDiskret(y[g]) != result[k][g]) {
							fehler++;
						}
					}
				}
				if (fehler == 0)
					break;
				file.append(bestError + ",\n");
				// Setzen neuer gewichte
				this.updateWeights(fehler);
			}
		}
		System.out.println("Fertig nach " + j);
		System.out.println("TestAnd Gewichte:");
		n.print_W();
		// Am Ende die Besten Gewichte Setzen
		file.close();
		return j;
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
