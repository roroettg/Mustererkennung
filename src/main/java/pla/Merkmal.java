package pla;

public class Merkmal {
	private String SensorID="";
	private String Bewegungsart = "";
	
	private double[] averageAccX;
	private double[] averageAccY;
	private double[] averageAccZ;
	private double[] stdAccX;
	private double[] stdAccY;
	private double[] stdAccZ;
	private double[] medianAccX;
	private double[] medianAccY;
	private double[] medianAccZ;
	private double[] rangeAccX;
	private double[] rangeAccY;
	private double[] rangeAccZ;
	private double[] maxAccX;
	private double[] maxAccY;
	private double[] maxAccZ;
	private double[] minAccX;
	private double[] minAccY;
	private double[] minAccZ;

	public Merkmal(String id, String art){
		this.SensorID = id;
		this.Bewegungsart = art;
	}
	
	public String getSensorID() {
		return SensorID;
	}

	public String getBewegungsart() {
		return Bewegungsart;
	}
	
	public void setByTag(String tag, double[] value) {
		if (tag == "averageAccX") {
			averageAccX = value;
		} else if (tag == "averageAccY") {
			averageAccY = value;
		} else if (tag == "averageAccZ") {
			averageAccZ = value;
		} else if (tag == "stdAccX") {
			stdAccX = value;
		} else if (tag == "stdAccY") {
			stdAccY = value;
		} else if (tag == "stdAccZ") {
			stdAccZ = value;
		} else if (tag == "medianAccX") {
			medianAccX = value;
		} else if (tag == "medianAccY") {
			medianAccY = value;
		} else if (tag == "medianAccZ") {
			medianAccZ = value;
		} else if (tag == "rangeAccX") {
			rangeAccX = value;
		} else if (tag == "rangeAccY") {
			rangeAccY = value;
		} else if (tag == "rangeAccZ") {
			rangeAccZ = value;
		} else if (tag == "maxAccX") {
			maxAccX = value;
		} else if (tag == "maxAccY") {
			maxAccY = value;
		} else if (tag == "maxAccZ") {
			maxAccZ = value;
		} else if (tag == "minAccX") {
			minAccX = value;
		} else if (tag == "minAccY") {
			minAccY = value;
		} else if (tag == "minAccZ") {
			minAccZ = value;
		}

	}
	
	public double[] getVector(){
		double[] vec = new double[2];
		vec[0] = this.averageAccX[0];
		vec[1] = this.medianAccY[0];
		return vec;
	}

	public double[] getAverageAccX() {
		return averageAccX;
	}

	public double[] getAverageAccY() {
		return averageAccY;
	}

	public double[] getAverageAccZ() {
		return averageAccZ;
	}

	public double[] getStdAccX() {
		return stdAccX;
	}

	public double[] getStdAccY() {
		return stdAccY;
	}

	public double[] getStdAccZ() {
		return stdAccZ;
	}

	public double[] getMedianAccX() {
		return medianAccX;
	}

	public double[] getMedianAccY() {
		return medianAccY;
	}

	public double[] getMedianAccZ() {
		return medianAccZ;
	}

	public double[] getRangeAccX() {
		return rangeAccX;
	}

	public double[] getRangeAccY() {
		return rangeAccY;
	}

	public double[] getRangeAccZ() {
		return rangeAccZ;
	}

	public double[] getMaxAccX() {
		return maxAccX;
	}

	public double[] getMaxAccY() {
		return maxAccY;
	}

	public double[] getMaxAccZ() {
		return maxAccZ;
	}

	public double[] getMinAccX() {
		return minAccX;
	}

	public double[] getMinAccY() {
		return minAccY;
	}

	public double[] getMinAccZ() {
		return minAccZ;
	}
}
