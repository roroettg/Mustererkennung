package adaptivesysteme.KMeans;

import java.util.Random;

/**
 * Generate pseudo-random floating point values, with an approximately Gaussian
 * (normal) distribution.
 * 
 * Many physical measurements have an approximately Gaussian distribution; this
 * provides a way of simulating such values.
 */
public final class RandomGaussian {
	private double mean = 0;
	private double variance = 0;
	private Random fRandom = new Random();

	public RandomGaussian(double m, double v) {
		this.variance = v;
		this.mean = m;
	}
	

	public double getGaussian() {
		return mean + fRandom.nextGaussian() * variance;
	}
}
