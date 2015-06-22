package mustererkennung.entscheidungsbaum;

import mustererkennung.algorithmen.Merkmal;

public class Mf3attrib implements MerkmalFinder {

	@Override
	public double[] getSplitAttribut(Merkmal m) {
		double[] ret = new double[15];
		ret[0] = m.getAverageAccX()[0];
		ret[1] = m.getAverageAccY()[0];
		ret[2] = m.getAverageAccZ()[0];
		ret[3] = m.getStdAccX()[0];
		ret[4] = m.getStdAccY()[0];
		ret[5] = m.getStdAccZ()[0];
		ret[6] = m.getMaxAccX()[0];
		ret[7] = m.getMaxAccY()[0];
		ret[8] = m.getMaxAccZ()[0];
		ret[9] = m.getMedianAccX()[0];
		ret[10] = m.getMedianAccY()[0];
		ret[11] = m.getMedianAccZ()[0];
		ret[12] = m.getRangeAccX()[0];
		ret[13] = m.getRangeAccY()[0];
		ret[14] = m.getRangeAccZ()[0];
		return ret;
	}
	@Override
	public String[] getSplitAttributName() {
		String[] ret = new String[15];
		ret[0] = "AverageAccX0";
		ret[1] = "AverageAccY0";
		ret[2] = "MedianAccY1";
		ret[3] = "StdAccX()";
		ret[4] = "m.getStdAccY()";
		ret[5] = "m.getStdAccZ()";
		ret[6] = "m.getMaxAccX()";
		ret[7] = "m.getMaxAccY()";
		ret[8] = "m.getMaxAccZ()";
		ret[9] = "m.getMedianAccX()";
		ret[10] = "m.getMedianAccY()";
		ret[11] = "m.getMedianAccZ()";
		ret[12] = "m.getRangeAccX()";
		ret[13] = "m.getRangeAccY()";
		ret[14] = "m.getRangeAccZ()";
		return ret;
	}

}
