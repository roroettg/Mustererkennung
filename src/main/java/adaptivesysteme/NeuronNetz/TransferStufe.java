package adaptivesysteme.NeuronNetz;

public class TransferStufe implements Transferfunktion {

	@Override
	public double f(double x) {
		if (x > 0)
			return 1;
		else
			return 0;
	}

	@Override
	public double df(double x) {
		if (x > 0)
			return 1;
		else
			return 1;
	}
	
	@Override
	public double toDiskret(double x) {
		if(x <=0.5)
			return 0;
		else
			return 1;
	}

	@Override
	public void increaseLambda() {
		//do nothing
	}

}
