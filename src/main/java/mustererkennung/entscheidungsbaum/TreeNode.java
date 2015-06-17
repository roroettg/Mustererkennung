package mustererkennung.entscheidungsbaum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import mustererkennung.algorithmen.Merkmal;

// TODO: Auto-generated Javadoc
/**
 * The Class TreeNode.
 */
public class TreeNode {

	/** The childs. */
	private TreeNode[] childs;

	/** The klasse. */
	private String klasse = "";

	/** The leaf. */
	private boolean leaf = false;

	/** The rang. */
	private int rang;

	/** The tag. */
	private String tag = "";

	/** The teta. */
	private double teta = 0.3;

	/** The split. */
	private double split = 0;
	
	private final int MAX_TREE_DEPTH = 10;

	/**
	 * Instantiates a new tree node.
	 *
	 * @param t
	 *            the t
	 * @param rang
	 *            the rang
	 */
	public TreeNode(String t, int rang) {
		this.tag = t;
		this.rang = rang;
		this.childs = new TreeNode[2];
	}

	/**
	 * Gets the split attribut.
	 *
	 * @param m
	 *            the m
	 * @return the split attribut
	 */
	private double getSplitAttribut(Merkmal m) {
		return m.getAverageAccX()[1];
	}

	/**
	 * Classify.
	 *
	 * @param m
	 *            the m
	 * @return the string
	 */
	public String classify(Merkmal m) {
		if (leaf) {
			return klasse;
		} else {
			if (this.split <= this.getSplitAttribut(m)) {
				return childs[0].classify(m);
			} else {
				return childs[1].classify(m);
			}
		}
	}

	/**
	 * Generate tree.
	 *
	 * @param ms
	 *            the ms
	 * @return the int
	 */
	public int generateTree(ArrayList<Merkmal> ms) {
		if (this.getEntropy(ms) < this.teta || MAX_TREE_DEPTH < this.rang) {
			setLeaf(ms);
		} else {
			System.out.println(this.tag + " " + ms.size() + " Entropy:" + this.getEntropy(ms));
			this.split = this.splitAttribut(ms);
			ArrayList<Merkmal>[] x12 = this.splitArray(ms, this.split);
			this.childs[0] = new TreeNode("\t" + this.tag + "," + x12[0].size(), this.rang + 1);
			this.childs[0].generateTree(x12[0]);
			this.childs[1] = new TreeNode("\t" + this.tag + "," + x12[1].size(), this.rang + 1);
			this.childs[1].generateTree(x12[1]);
		}
		return 0;
	}

	/**
	 * Gets the entropy.
	 *
	 * @param ms
	 *            the ms
	 * @return the entropy
	 */
	public double getEntropy(ArrayList<Merkmal> ms) {
		// count each Klasse
		double entropy = 0, p = 0;
		HashMap<String, Double> klassen = new HashMap<String, Double>();
		for (Merkmal m : ms) {
			Double d = klassen.get(m.getBewegungsart());
			if (d == null || d == 0) {
				d = 1.0;
			} else {
				d++;
			}
			klassen.put(m.getBewegungsart(), d);
		}
		for (Entry<String, Double> e : klassen.entrySet()) {
			p = e.getValue() / ms.size();
			entropy += (p * (Math.log(p) / Math.log(2)));
		}
		return -entropy;
	}

	/**
	 * Sets the leaf.
	 *
	 * @param ms
	 *            the new leaf
	 */
	private void setLeaf(ArrayList<Merkmal> ms) {
		this.leaf = true;
		HashMap<String, Double> klassen = new HashMap<String, Double>();
		for (Merkmal m : ms) {
			Double d = klassen.get(m.getBewegungsart());
			if (d == null || d == 0) {
				d = 1.0;
			} else {
				d++;
			}
			klassen.put(m.getBewegungsart(), d);
		}
		String klasse = "";
		double max = 0;
		for (Entry<String, Double> e : klassen.entrySet()) {
			if (e.getValue() > max) {
				klasse = e.getKey();
				max = e.getValue();
			}
		}
		this.klasse = klasse;

	}

	/**
	 * Split attribut.
	 *
	 * @param ms
	 *            the ms
	 * @return the double
	 */
	public double splitAttribut(ArrayList<Merkmal> ms) {
		double xi = this.getSplitAttribut(ms.get(0));
		double minEnt = 2;
		Double bestf = null;
		double e;
		ArrayList<Merkmal> x12[];
		for (Merkmal m : ms) {
			xi = this.getSplitAttribut(m);
			x12 = splitArray(ms, xi);
			e = this.getEntropy(x12[0]) + this.getEntropy(x12[1]);
			if (e < minEnt || bestf == null) {
				minEnt = e;
				bestf = xi;
			}
			x12[0].clear();
			x12[1].clear();
		}
		x12 = splitArray(ms, bestf);
		for (int i = 0; i < 2; i++)
			for (Merkmal m : x12[i]) {
				System.out.println("Array" + i + " " + this.getSplitAttribut(m) + " " + m.getBewegungsart());
			}
		return bestf;
	}

	public ArrayList<Merkmal>[] splitArray(ArrayList<Merkmal> ms, double xi) {
		ArrayList<Merkmal>[] xs = new ArrayList[2];
		xs[0] = new ArrayList<Merkmal>();
		xs[1] = new ArrayList<Merkmal>();
		for (Merkmal m2 : ms) {
			if (xi <= this.getSplitAttribut(m2)) {
				xs[0].add(m2);
			} else {
				xs[1].add(m2);
			}
		}
		return xs;
	}

	public void printTree() {
		if (leaf) {
			System.out.println(tag + " -> " + this.klasse);
		} else {
			System.out.println(this.tag + "( xi <= " + this.split + ")");
			childs[0].printTree();
			childs[1].printTree();
		}
	}

}
