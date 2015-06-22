package mustererkennung.entscheidungsbaum;

import mustererkennung.algorithmen.Merkmal;

public class MerkmalFinderImpl implements MerkmalFinder {

	@Override
	public double[] getSplitAttribut(Merkmal m) {
		double[] ret = new double[3];
		ret[0] = m.getRangeAccX()[0];
		ret[1] = m.getAverageAccX()[0];
		ret[2] = m.getAverageAccY()[0];
		return m.getVector();
	}
	@Override
	public String[] getSplitAttributName() {
		String[] vec = new String[5];
		vec[0] = "averageAccX";
		vec[1] = "averageAccY[2]";
		vec[2] = "rangeAccY[1]";
		//vec[3] = this.rangeAccX[0];
		vec[4] = "averageAccZ[0]";
		vec[3] = "maxAccY[1]"; // 7 und 3
		return vec;
	}

}
