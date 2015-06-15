package pla;

import adaptivesysteme.KMeans.Norm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * The Class KNN.
 */
public class KNN {

	/** Die verwendete Norm um den Abstand der Vectoren zu bestimmen */
	private Norm norm;

	/** Die Trainingsvectoren */
	private ArrayList<Merkmal> points;

	/** Die dimension der Trainingsvectoren */
	private int k;

	/**
	 * Instantiates a new knn.
	 *
	 * @param p Liste mit den Trainingsvectoren 
	 * @param k Die Dimension der Trainingsvectoren
	 */
	public KNN(ArrayList<Merkmal> p, int k, Norm n) {
		this.points = p;
		this.norm = n;
		this.k = k;
	}

	/**
	 * Classify.
	 *
	 * @param merkmal Das zu klassifizierende Merkmal
	 * @return Der Erkannte typ des Merkmals
	 */
	public String classify(Merkmal merkmal) {
		double[] dists = new double[k];
		Merkmal[] merks = new Merkmal[k];
		// Liste mit Merkmalen und distanzen füllen
		for (Merkmal m : points) {
			double d = norm.getAbstand(merkmal.getVector(), m.getVector());
			for (int i = 0; i < k; i++) {
				if (merks[i] != null) {
					if (d < dists[i]) {
						for (int e = k - 1; e > i; e--) {
							dists[e] = dists[e - 1];
							merks[e] = merks[e - 1];
						}
						dists[i] = d;
						merks[i] = m;
						break;
					}
				} else {
					dists[i] = d;
					merks[i] = m;
				}
			}
		}
		// Verschiedene Typen zählen
		HashMap<String, Double> zaehler = new HashMap<String, Double>();
		for (int i = 0; i < k; i++) {
			Double d = zaehler.get(merks[i].getBewegungsart());
			if (d == null) {
				d = new Double(1);
			} else {
				d++;
			}
			zaehler.put(merks[i].getBewegungsart(), d);
		}
		//Grösste Menge heraussuchen
		String ret = " ";
		double min = -1;
		for (Entry<String, Double> e : zaehler.entrySet()) {
			if (min < e.getValue()) {
				ret = e.getKey();
			}
		}
		return ret;
	}

}
