package mustererkennung.entscheidungsbaum;

import mustererkennung.algorithmen.Merkmal;

public class MfAlleattrib implements MerkmalFinder {

	@Override
	public double[] getSplitAttribut(Merkmal m) {
		double[] ret = new double[3];
		ret[0] = m.getAverageAccX()[0];
		ret[1] = m.getAverageAccY()[0];
		ret[2] = m.getStdAccX()[0];
		return ret;
	}
	@Override
	public String[] getSplitAttributName() {
		String[] ret = new String[3];
		ret[0] = "AverageAccX0";
		ret[1] = "AverageAccY0";
		ret[2] = "MedianAccY1";
		return ret;
	}

}
