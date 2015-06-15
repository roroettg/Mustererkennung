package adaptivesysteme.KMeans;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class KMeans.
 */
public class KMeans {

	/** Array mit den Codebook Vectoren. */
	private CodeBook[] codeBook;

	/** größe des Codebooks */
	private int codeBookSize;

	/** Die dimension eiens Vectors */
	private int dimension;

	/** Liste mit allen Vectoren */
	private ArrayList<MeansVektor> vector;
	
	private Norm norm;

	/**
	 * Instantiates a new k means.
	 *
	 * @param cSize
	 *            Anzahl der Codebooks
	 * @param vector
	 *            Die Vectoren
	 * @param dim
	 *            Die Dimension der Vectoren
	 */
	public KMeans(int cSize, ArrayList<MeansVektor> vector, int dim, Norm norm) {
		this.codeBookSize = cSize;
		this.vector = vector;
		this.dimension = dim;
		this.norm = norm;
		this.codeBook = new CodeBook[cSize];
		for (int i = 0; i < codeBookSize; i++) {
			double[] d = new double[dim];
			for (int j = 0; j < this.dimension; j++) {
				d[j] = (Math.random());
			}
			this.codeBook[i] = new CodeBook(this.dimension, d, "CodeBook " + (i+1));
		}
	}

	/**
	 * Gets the vector.
	 *
	 * @return the vector
	 */
	public ArrayList<MeansVektor> getVector() {
		return vector;
	}

	/**
	 * Gets the code books.
	 *
	 * @return the code books
	 */
	public CodeBook[] getCodeBooks() {
		return this.codeBook;
	}

	/**
	 * Hilfsfunktion die die Vektoren einem Codebook zuordnet
	 * 
	 */
	private void setNewCodeBookToVector() {
		double[] distance = new double[this.codeBookSize];
		for (MeansVektor v : this.vector) {
			for (int i = 0; i < codeBookSize; i++) {
				// Distanze zwischen dem Vektor und allen Codebuchvectoren berechnen
				distance[i] = norm.getAbstand(v.getVector(), codeBook[i].getVector());
			}
			// Dem kleinsten Abstand heraussuchen
			int min = 0;
			double minV = distance[0];
			for (int j = 1; j < this.codeBookSize; j++) {
				if (minV >= distance[j]) {
					minV = distance[j];
					min = j;
				}
			}
			// Vector einem Codebook zuordnen
			this.codeBook[min].addVector(v);
		}
	}

	/**
	 * Gets the calc code book.
	 *
	 * @return the calc code book
	 */
	public double getCalcCodeBook() {
		// Löschen der zuordnung der Codebooks
		for (CodeBook c : codeBook) {
			c.clearMeans();
		}
		// Neue zuordnung bestimmen
		this.setNewCodeBookToVector();
		// Neuen Schwerpunkt der Codebooks bestimmen
		double deltaCodeBooks = 0;
		for (CodeBook c : this.codeBook) {
			deltaCodeBooks += Math.pow(c.calcNewPosition(),2);
			c.getFehlerMeans();
		}
		return deltaCodeBooks;
	}

	/**
	 * Hilfsfunktion die die neuen Vectoren erzeugt
	 *
	 * @param anzahl
	 *            Wie viele Vectoren sollen erzeugt werden
	 * @param typ
	 *            Welchen Typ sollen diese haben
	 * @param x_diff
	 *            Wohin sollen diese in x richtung verschoben werden
	 * @param y_diff
	 *            Wohin sollen diese in y richtung verschoben werden
	 * @return Liste mit den Vektoren
	 */
	public static ArrayList<MeansVektor> createVektors(int anzahl, int typ, double x_diff, double y_diff) {
		ArrayList<MeansVektor> vector = new ArrayList<MeansVektor>();
		RandomGaussian gauss1 = new RandomGaussian(0.5, 0.5);
		RandomGaussian gauss2 = new RandomGaussian(0.5, 0.25);
		for (int i = 0; i < anzahl; i++) {
			MeansVektor v;
			double d[] = new double[2];
			switch (typ) {
			case 1:// Quadrat
				d[0] = (Math.random() - 0.5) + x_diff;
				d[1] = (Math.random() - 0.5) + y_diff;
				break;
			case 2:// Elipse
				d[0] = (gauss1.getGaussian() - 0.5) + x_diff;
				d[1] = (gauss2.getGaussian() - 0.5) + y_diff;
				break;
			case 3: // Kreis
				double a = Math.random() * 2 * Math.PI;
				d[0] = (Math.cos(a) - (Math.random() * Math.cos(a))) * 0.5 + (x_diff);
				d[1] = (Math.sin(a) - (Math.random() * Math.sin(a))) * 0.5 + (y_diff);
				break;
			}
			v = new MeansVektor(typ, 2, d);
			vector.add(v);
		}
		return vector;
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		ArrayList<MeansVektor> vector = KMeans.createVektors(300, 1, 1, 1);
		KMeans kmeans = new KMeans(3, vector, 2, new NormEuklid());
		for (MeansVektor v : vector) {
			System.out.println(v.getValue(0) + " " + v.getValue(1));
		}

	}
}
