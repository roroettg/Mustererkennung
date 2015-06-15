package adaptivesysteme.NeuronNetz;

public class TransferExp implements Transferfunktion {

	private double lambda = 1;
	private double ldelta = 0.1;
	private double eps = 0.2;

	
	public TransferExp(){
		this.lambda = 1;
		this.ldelta = 0.1;
		this.eps = 0.2;

	}
	
	public TransferExp(double l, double ld, double e){
		this.lambda = l;
		this.ldelta = ld;
		this.eps = e;
	}
	
	@Override
	public double f(double x) {
		return 1 / (1 + Math.exp(lambda * -x));
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
		this.lambda += ldelta;

	}
}
