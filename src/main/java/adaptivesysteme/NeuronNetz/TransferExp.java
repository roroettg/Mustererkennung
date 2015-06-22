package adaptivesysteme.NeuronNetz;


public class TransferExp implements Transferfunktion {

	private double lambda = 1;
	private double ldelta = 1.5;
	private double eps = 0.1;
	private double updateRate = 0.01;

	public void setLambda(double lambda) {
		this.lambda = lambda;
	}

	public void setLdelta(double ldelta) {
		this.ldelta = ldelta;
	}

	public void setEps(double eps) {
		this.eps = eps;
	}

	public void setUpdateRate(double updateRate) {
		this.updateRate = updateRate;
	}

	public TransferExp() {
		this.lambda = 1;
		this.ldelta = 0.1;
		this.eps = 0.4;

	}

	public TransferExp(double l, double ld, double e) {
		this.lambda = l;
		this.ldelta = ld;
		this.eps = e;
	}

	public double getLambda() {
		return lambda;
	}

	@Override
	public double f(double x) {
		x = 1 / (1 + Math.exp(lambda * -x));
		return x;
	}

	@Override
	public double df(double x) {
		return Math.exp(lambda * x) / Math.pow((Math.exp(lambda * x) + 1), 2);
	}

	@Override
	public double toDiskret(double x) {
		if (x <= (0 + eps))
			return 0;
		else if (x >= (1 - eps))
			return 1;
		else
			return x;
	}

	@Override
	public void increaseLambda() {
		this.lambda += (ldelta * updateRate);
	}
}
