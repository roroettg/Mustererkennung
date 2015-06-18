package mustererkennung.entscheidungsbaum;

import mustererkennung.algorithmen.Merkmal;

public interface MerkmalFinder {

	
	public double[] getSplitAttribut(Merkmal m);

	public String[] getSplitAttributName();
}
