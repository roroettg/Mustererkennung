package mustererkennung.algorithmen;

public class Merkmal {
	private String SensorID = "";
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
	private double[] a;
	private double[] b;
	private double[] c;
	
	private double[] energyAbsAccXWithDelta;
	private double[] energyAbsAccYWithDelta;
	private double[] energyAbsAccZWithDelta;

	public Merkmal(String id, String art) {
		this.SensorID = id;
		this.Bewegungsart = art;
	}

	public String getSensorID() {
		return SensorID;
	}

	public double[] getEnergyAbsAccXWithDelta() {
		return energyAbsAccXWithDelta;
	}

	public double[] getEnergyAbsAccYWithDelta() {
		return energyAbsAccYWithDelta;
	}

	public double[] getEnergyAbsAccZWithDelta() {
		return energyAbsAccZWithDelta;
	}

	public String getBewegungsart() {
		return Bewegungsart;
	}

	public void setByTag(String tag, double[] value) {
		if (tag == "avgAccXWithDelta") {
			averageAccX = value;
		} else if (tag == "avgAccYWithDelta") {
			averageAccY = value;
		} else if (tag == "avgAccZWithDelta") {
			averageAccZ = value;
		} else if (tag == "stdAccXWithDelta") {
			stdAccX = value;
		} else if (tag == "stdAccYWithDelta") {
			stdAccY = value;
		} else if (tag == "stdAccZWithDelta") {
			stdAccZ = value;
		} else if (tag == "medianAccXWithDelta") {
			medianAccX = value;
		} else if (tag == "medianAccYWithDelta") {
			medianAccY = value;
		} else if (tag == "medianAccZWithDelta") {
			medianAccZ = value;
		} else if (tag == "rangeAccXWithDelta") {
			rangeAccX = value;
		} else if (tag == "rangeAccYWithDelta") {
			rangeAccY = value;
		} else if (tag == "rangeAccZWithDelta") {
			rangeAccZ = value;
		} else if (tag == "maxAccXWithDelta") {
			maxAccX = value;
		} else if (tag == "maxAccYWithDelta") {
			maxAccY = value;
		} else if (tag == "maxAccZWithDelta") {
			maxAccZ = value;
		} else if (tag == "minAccXWithDelta") {
			minAccX = value;
		} else if (tag == "minAccYWithDelta") {
			minAccY = value;
		} else if (tag == "minAccZWithDelta") {
			minAccZ = value;
		} else if (tag == "energyAbsAccXWithDelta") {
			energyAbsAccXWithDelta = value;
		} else if (tag == "energyAbsAccYWithDelta") {
			energyAbsAccYWithDelta = value;
		} else if (tag == "energyAbsAccZWithDelta") {
			energyAbsAccZWithDelta = value;
		} else if (tag == "a") {
			a = value;
		} else if (tag == "b") {
			b = value;
		} else if (tag == "c") {
			c = value;
		}else {
			System.out.println("Keinen Parameter gefunden");
		}

	}

	public double[] getVector() {
		double[] vec = new double[5];
		vec[0] = this.averageAccX[0];
		vec[1] = this.averageAccY[2];
		vec[2] = this.rangeAccY[1];
		// vec[3] = this.rangeAccX[0];
		vec[4] = this.averageAccZ[0];
		vec[3] = this.maxAccY[1]; // 7 und 3
		// vec[3] = this.stdAccY[2]; // 5 und 3
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
