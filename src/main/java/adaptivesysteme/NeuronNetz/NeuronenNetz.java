package adaptivesysteme.NeuronNetz;

import java.util.ArrayList;

/**
 * The Class NeuronenNetz.
 */
public class NeuronenNetz {

	/**  ArrayList mit allen Schichten. */
	private ArrayList<NeuronenSchicht> schichten;

	/**
	 * Instantiates a new neuronen netz.
	 *
	 * @param in
	 *            Anzahl der Eingänge
	 * @param out
	 *            Anzahl der Ausgänge des Neuronalen netz
	 * @param ebenen
	 *            Array in dem die größe der Schichten angegeben ist
	 * @param f
	 *            Die Transferfunktion die die Neuronen benutzen
	 */
	public NeuronenNetz(int in, int out, int[] ebenen, Transferfunktion f) {
		schichten = new ArrayList<NeuronenSchicht>();
		for (int i = 0; i < ebenen.length - 1; i++) {
			schichten.add(new NeuronenSchicht(in, ebenen[i], f, "Schicht " + i));
			in = ebenen[i];
		}
		schichten.add(new NeuronenSchicht(in, out, f, "Ausgang "));
	}

	/**
	 * Abfrage an das Neuronale Netz.
	 *
	 * @param x            Die Eingangswerte als Array
	 * @return Alle Ausgangswerte als Array
	 */
	public double[] fire(double[] x) {
		for (NeuronenSchicht s : schichten) {
			x = s.fire(x);
		}
		return x;
	}

	/**
	 * Gibt die Gewichte aller Neuronen aus.
	 */
	public void printW() {
		for (NeuronenSchicht s : schichten) {
			s.print_W();
		}
	}

	/**
	 * Trainiert das Neurale Netz und modifiziert die Gewichte.
	 *
	 * @param x            Die Eingangswerte für einen Trainingsdurchlauf
	 * @param y            Die Erwatungswerte
	 * @return the double[]
	 */
	public double[] train(double[] x, double[] y) {
		double[] o_prev = null;
		double[][] w_prev = null, w_prev_prev;
		double size_prev = 0;
		boolean last = true;
		double d[] = this.fire(x);
		// Training
		for (int j = schichten.size() - 1; j >= 0; j--) {
			NeuronenSchicht n = schichten.get(j);
			w_prev_prev = n.getW();
			o_prev = n.trainNet(last, y, o_prev, w_prev, size_prev);
			w_prev = w_prev_prev;
			size_prev = n.getSize();
			last = false;
		}
		return d;
	}
	
	
	
	/**
	 * Gibt die Gewichtsmatrix aller Gewichte des Netzes zurück.
	 *
	 * @return the w
	 */
	public double[][][] getW(){
		double[][][] w = new double[schichten.size()][][];
		for(int i=0; i< schichten.size(); i++){
			w[i] = schichten.get(i).getW();
		}
		return w;
	}
}
