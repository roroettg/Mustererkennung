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
	private double teta = 0.1;

	/** The split. */
	private double split = 0;

	private int attributIndex = 0;

	/** The max tree depth. */
	private final int MAX_TREE_DEPTH = 1000;

	private MerkmalFinder findmerkmal;
	/**
	 * Instantiates a new tree node.
	 *
	 * @param t
	 *            the t
	 * @param rang
	 *            the rang
	 */
	public TreeNode(String t, int rang, MerkmalFinder finder) {
		this.findmerkmal = finder;
		this.tag = "\t" + t;
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
			if (this.split <= findmerkmal.getSplitAttribut(m)[this.attributIndex]) {
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
			this.split = this.splitAttribut(ms);
			ArrayList<Merkmal>[] x12 = this.splitArray(ms, this.split, this.attributIndex);
			this.childs[0] = new TreeNode(this.tag + "," + x12[0].size(), this.rang + 1, this.findmerkmal);
			this.childs[0].generateTree(x12[0]);
			this.childs[1] = new TreeNode(this.tag + "," + x12[1].size(), this.rang + 1, this.findmerkmal);
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
			Double d;
			d = klassen.get(m.getBewegungsart());
			if (d == null || d == 0) {
				d = new Double(1);
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
	 * Gets the split entropy.
	 *
	 * @param x12
	 *            the x12
	 * @param ms
	 *            the ms
	 * @return the split entropy
	 */
	public double getSplitEntropy(ArrayList<Merkmal>[] x12, ArrayList<Merkmal> ms) {
		double ent = 0;
		for (int j = 0; j < x12.length; j++) {
			ent += ((double) (x12[j].size() / (double) ms.size()) * this.getEntropy(x12[j]));
		}
		return ent;
	}

	/**
	 * Split attribut.
	 *
	 * @param ms
	 *            the ms
	 * @return the double
	 */
	public double splitAttribut(ArrayList<Merkmal> ms) {
		double xi = findmerkmal.getSplitAttribut(ms.get(0))[this.attributIndex];
		double minEnt = this.getSplitEntropy(splitArray(ms, xi, 0), ms);
		double bestf = xi;
		double e = 0;
		int attribIndex=0;
		ArrayList<Merkmal> x12[];
		for (Merkmal m : ms) {
			for (int i = 0; i < findmerkmal.getSplitAttribut(m).length; i++) {
				xi = findmerkmal.getSplitAttribut(m)[i];
				x12 = splitArray(ms, xi, i);
				e = this.getSplitEntropy(x12, ms);
				if (e < minEnt) {
					minEnt = e;
					bestf = xi;
					attribIndex = i;
				}
				x12[0].clear();
				x12[1].clear();
			}
		}
		this.attributIndex = attribIndex;
		return bestf;
	}

	/**
	 * Split array.
	 *
	 * @param ms
	 *            the ms
	 * @param xi
	 *            the xi
	 * @return the array list[]
	 */
	public ArrayList<Merkmal>[] splitArray(ArrayList<Merkmal> ms, double xi, int attributIndex) {
		ArrayList<Merkmal>[] xs = new ArrayList[2];
		xs[0] = new ArrayList<Merkmal>();
		xs[1] = new ArrayList<Merkmal>();
		for (Merkmal m2 : ms) {
			if (xi <= findmerkmal.getSplitAttribut(m2)[attributIndex]) {
				xs[0].add(m2);
			} else {
				xs[1].add(m2);
			}
		}
		return xs;
	}

	/**
	 * Prints the tree.
	 */
	public void printTree() {
		if (leaf) {
			System.out.println(tag + " -> " + this.klasse);
		} else {
			System.out.println(this.tag + " "+ findmerkmal.getSplitAttributName()[attributIndex] +":( xi <= " + this.split + ")");
			childs[0].printTree();
			childs[1].printTree();
		}
	}

}
