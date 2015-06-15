package adaptivesysteme.KMeans;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class CodeBook.
 */
public class CodeBook {

	/** The dimension. */
	private int dimension;
	
	/** The vector. */
	private double vector[];
	
	/** The means. */
	private ArrayList<MeansVektor> means;
	
	/** The tag. */
	private String tag = "";

	/**
	 * Instantiates a new code book.
	 *
	 * @param dim the dim
	 * @param vec the vec
	 * @param tag the tag
	 */
	public CodeBook(int dim, double[] vec, String tag) {
		this.means = new ArrayList<MeansVektor>();
		this.dimension = dim;
		this.vector = vec;
		this.tag = tag;
	}

	/**
	 * Ordne ein Vector diesem Codebook zu
	 *
	 * @param m the m
	 */
	public void addVector(MeansVektor m) {
		means.add(m);
	}
	
	public double[] getVector(){
		return vector;
	}

	/**
	 * Lösche Vector aus der Liste der zugeordneten Vectoren
	 *
	 * @param m the m
	 */
	public void delVector(MeansVektor m) {
		means.remove(m);
	}

	/**
	 * Berechnet die Vectoren die Falsch zugeordnet wurden anhand der Vektoren die häufiger zugeordnet wurden.
	 *
	 * @return die Falsch zugeordneten Vectoren
	 */
	public double getFehlerMeans() {
		double fehler = 0;
		double typ = 0;
		if (!means.isEmpty()) {
			typ = means.get(0).getTyp();
			for (MeansVektor v : means) {
				if (typ != v.getTyp()) {
					fehler++;
				}
			}
		}
		double size = means.size();
		if (fehler > size/2) {
			fehler = size - fehler;
		}
		System.out.println(tag + " Fehler:" + fehler);
		return fehler;
	}

	/**
	 * Hilfsfunktion die die neue Position anhand der zugeordneten Vektoren berechnet.
	 *
	 * @return the double
	 */
	public double calcNewPosition() {
		double[] v = new double[this.dimension];
		double diff = 0;
		if (this.means.size() > 0) {
			for (MeansVektor vec : means) {
				for (int i = 0; i < this.dimension; i++) {
					v[i] += vec.getValue(i);
				}
			}
			for (int i = 0; i < this.dimension; i++) {
				v[i] *= (1.0 / this.means.size());
				diff += Math.pow(v[i] - vector[i], 2);
				this.vector[i] = v[i];
			}
			return Math.sqrt(diff);
		} else {
			return 0;
		}
	}

	/**
	 * Clear means.
	 */
	public void clearMeans() {
		this.means.clear();
	}

	/**
	 * Gets the value.
	 *
	 * @param index the index
	 * @return the value
	 */
	public double getValue(int index) {
		if (index < this.dimension) {
			return this.vector[index];
		} else {
			return 0;
		}
	}

	/**
	 * Setzt das angegebene Vectorelement
	 *
	 * @param index Index des Vectoreleementss
	 * @param value Das Vektorelement
	 */
	public void setValue(int index, double value) {
		if (index < this.dimension) {
			this.vector[index] = value;
		}
	}

	/**
	 * Berechnet die Maximale Distanz zu den Zugeordneten Vectoren 
	 *
	 * @return the double
	 */
	public double meansDistanceTo() {
		double dist = 0;
		double max = 0;
		for (MeansVektor v : this.means) {
			dist = 0;
			for (int i = 0; i < this.dimension; i++) {
				dist += Math.pow(v.getValue(i) - vector[i], 2);
			}
			dist = Math.sqrt(dist);
			if (max < dist) {
				max = dist;
			}
		}
		return max;
	}

	/* (nicht-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer b = new StringBuffer(tag + " ");
		for (double d : vector) {
			b.append(d + "; ");
		}
		return b.toString();
	}

}
