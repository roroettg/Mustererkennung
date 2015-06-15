package adaptivesysteme.NeuronNetz;

public class TransferModiefiedTanh implements Transferfunktion {

	private double lambda = 1;
	private double epsilion = 0.02;

	@Override
	public double f(double x) {
		return (1 + Math.tanh(lambda * x)) / 2;
	}

	@Override
	public double df(double x) {
		return (2 * Math.pow(Math.cosh(x), 2)) / (Math.pow((Math.cosh(2 * x) + 1), 2));
	}

	@Override
	public double toDiskret(double x) {
		if (x <= 0 + epsilion)
			return 0;
		else if (x >= 1 - epsilion)
			return 1;
		else 
			return x;
	}

	@Override
	public void increaseLambda() {
		this.lambda++;
	}

}
