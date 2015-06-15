package adaptivesysteme.KMeans;

public class NormManhattan implements Norm {

	@Override
	public double getAbstand(double[] a, double[] b) {
		double dist = 0;
		if (a.length == b.length) {
			for (int i = 0; i < a.length; i++) {
				dist += Math.abs(Math.abs(a[i]) - Math.abs(b[i]));
			}
		}
		return dist;
	}

}
