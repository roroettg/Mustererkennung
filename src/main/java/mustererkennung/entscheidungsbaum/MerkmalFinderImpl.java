package mustererkennung.entscheidungsbaum;

import mustererkennung.algorithmen.Merkmal;

public class MerkmalFinderImpl implements MerkmalFinder {

	@Override
	public double[] getSplitAttribut(Merkmal m) {
		double[] ret = new double[2];
		ret[0] = m.getAverageAccX()[0];
		ret[1] = m.getAverageAccY()[0];
		return ret;
	}
	@Override
	public String[] getSplitAttributName() {
		String[] ret = new String[2];
		ret[0] = "AverageAccX";
		ret[1] = "AverageAccY";
		return ret;
	}

}
