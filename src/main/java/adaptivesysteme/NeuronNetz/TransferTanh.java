package adaptivesysteme.NeuronNetz;

public class TransferTanh implements Transferfunktion {

	private double lambda = 1;
	private double ldelta = 0.2;
	private double eps = 0.02;

	@Override
	public double f(double x) {
		return (Math.tanh(lambda * x));
	}

	@Override
	public double df(double x) {
		return (lambda * 4 * Math.pow(Math.cosh(lambda * x), 2)) / (Math.pow((Math.cosh(2 * lambda * x) + 1), 2));
	}

	@Override
	public double toDiskret(double x) {
		if (x <= -1 + this.eps)
			return 0;
		else if (x >= 1 - this.eps)
			return 1;
		else
			return x;
	}

	@Override
	public void increaseLambda() {
		lambda += this.ldelta;
	}
}
