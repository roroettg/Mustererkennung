package adaptivesysteme.KMeans;

public class NormEuklid implements Norm {

	@Override
	public double getAbstand(double[] a, double[] b) {
		double dist = 0;
		if (a.length == b.length) {
			for (int i = 0; i < a.length; i++) {
				dist += Math.pow(a[i] - b[i], 2);
			}
			dist = Math.sqrt(dist);
		}
		return dist;
	}
}
