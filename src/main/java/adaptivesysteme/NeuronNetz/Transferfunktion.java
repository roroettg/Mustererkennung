package adaptivesysteme.NeuronNetz;

// TODO: Auto-generated Javadoc
/**
 * The Interface Transferfunktion.
 */
public interface Transferfunktion {

	/**
	 *  Die Funktion selbst
	 *
	 * @param x Der Wert der transformiert werden soll
	 * @return Das Ergebnis
	 */
	public double f(double x);
	
	/**
	 * Die Ableitung der Transferfunktion
	 *
	 * @param x the x
	 * @return the double
	 */
	public double df(double x);
	
	
	public double toDiskret(double x);
	
	public void increaseLambda();
}
