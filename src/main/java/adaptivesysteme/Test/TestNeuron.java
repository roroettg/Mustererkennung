package adaptivesysteme.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import adaptivesysteme.NeuronNetz.Neuron;
import adaptivesysteme.NeuronNetz.TransferExp;
import adaptivesysteme.NeuronNetz.TransferStufe;
import adaptivesysteme.NeuronNetz.Transferfunktion;

public class TestNeuron {

	private int iterationen = 10000000;

	@Test
	public void testAnd() {
		double[][] training_AND = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		double[][] loesung_AND = { { 0 }, { 0 }, { 0 }, { 1 } };
		Neuron n = new Neuron(2, new TransferStufe(), 0.5);
		boolean fehler = true;
		int j = 0;
		for (j = 0; j < iterationen && fehler; j++) {
			fehler = false;
			for (int i = 0; i < 4; i++) {
				double y;
				y = n.train(training_AND[i], loesung_AND[i][0]);
				if (y != loesung_AND[i][0]) {
					fehler = true;
				}
			}
		}
		System.out.println("Fertig nach " + j);
		System.out.println("TestAnd Gewichte:");
		n.print_W();
		assertTrue(0 == n.fire(training_AND[0]));
		assertTrue(0 == n.fire(training_AND[1]));
		assertTrue(0 == n.fire(training_AND[2]));
		assertTrue(1 == n.fire(training_AND[3]));
	}

	@Test
	public void testAnd_Exp() {
		double[][] training_AND = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		double[][] loesung_AND = { { 0 }, { 0 }, { 0 }, { 1 } };
		Transferfunktion f = new TransferExp();
		Neuron n = new Neuron(2, f, 0.5);
		boolean fehler = true;
		int j = 0;
		for (j = 0; j < iterationen && fehler; j++) {
			if (j % 1000 == 0)
				f.increaseLambda();
			fehler = false;
			for (int i = 0; i < 4; i++) {
				double y;
				y = n.train(training_AND[i], loesung_AND[i][0]);
				if (f.toDiskret(y) != loesung_AND[i][0]) {
					fehler = true;
				}
			}
		}
		System.out.println("Fertig nach " + j);
		System.out.println("TestAnd Gewichte:");
		n.print_W();
		assertTrue(0 == f.toDiskret(n.fire(training_AND[0])));
		assertTrue(0 == f.toDiskret(n.fire(training_AND[1])));
		assertTrue(0 == f.toDiskret(n.fire(training_AND[2])));
		assertTrue(1 == f.toDiskret(n.fire(training_AND[3])));
	}

	@Test
	public void testOR() {
		double[][] training_OR = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		double[][] loesung_OR = { { 0 }, { 1 }, { 1 }, { 1 } };
		Neuron n = new Neuron(2, new TransferStufe(), 0.5);
		boolean fehler = true;
		int j = 0;
		for (j = 0; j < iterationen && fehler; j++) {
			fehler = false;
			for (int i = 0; i < 4; i++) {
				double y;
				y = n.train(training_OR[i], loesung_OR[i][0]);
				if (y != loesung_OR[i][0]) {
					fehler = true;
				}
			}
		}
		System.out.println("Fertig nach " + j);	
		System.out.println("TestOR Gewichte:");
		n.print_W();
		assertTrue(0 == n.fire(training_OR[0]));
		assertTrue(1 == n.fire(training_OR[1]));
		assertTrue(1 == n.fire(training_OR[2]));
		assertTrue(1 == n.fire(training_OR[3]));
	}

	@Test
	public void testXOR() {
		double[][] training_XOR = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		double[][] loesung_XOR = { { 0 }, { 1 }, { 1 }, { 0 } };
		int iterationen = 1000;
		Neuron n = new Neuron(2, new TransferStufe(), 0.5);
		for (int j = 0; j < iterationen; j++) {
			for (int i = 0; i < 4; i++) {
				n.train(training_XOR[i], loesung_XOR[i][0]);
			}
		}
		System.out.println("TestXOR Gewichte:");
		n.print_W();
		// Üerprüfen ob richtig gelernt wurde
		assertTrue(0 == n.fire(training_XOR[0]));
		assertTrue(1 == n.fire(training_XOR[1]));
		assertTrue(1 == n.fire(training_XOR[2]));
		assertTrue(0 == n.fire(training_XOR[3]));
	}

	@Test
	public void testANDmitRauschen() throws Exception {
		double[][] training_AND = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		double[][] loesung_AND = { { 0 }, { 0 }, { 0 }, { 1 } };
		Neuron n = new Neuron(2, new TransferStufe(), 0.5);
		boolean fehler = true;
		int j = 0;
		for (j = 0; j < iterationen && fehler; j++) {
			fehler = false;
			for (int i = 0; i < 4; i++) {
				double y;
				y = n.train(training_AND[i], loesung_AND[i][0]);
				if (y != loesung_AND[i][0]) {
					fehler = true;
				}
			}
		}
		System.out.println("Fertig nach " + j);

		System.out.println("TestAnd Gewichte:");
		n.print_W();
		assertTrue(0 == n.fire(training_AND[0]));
		assertTrue(0 == n.fire(training_AND[1]));
		assertTrue(0 == n.fire(training_AND[2]));
		assertTrue(1 == n.fire(training_AND[3]));

	}

	@Test
	public void testAndMitStetigerTransferfunktion() throws Exception {
		double[][] training_AND = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		double[][] loesung_AND = { { 0 }, { 0 }, { 0 }, { 1 } };
		// Erzeuge Neuron im Exp als Transferfunktion
		Transferfunktion f = new TransferExp();
		Neuron n = new Neuron(2, f, 0.5);
		boolean fehler = true;
		int j = 0;
		for (j = 0; j < iterationen && fehler; j++) {
			if (j % 1000 == 0)
				f.increaseLambda();
			fehler = false;
			for (int i = 0; i < 4; i++) {
				double y;
				y = n.train(training_AND[i], loesung_AND[i][0]);
				if (f.toDiskret(y) != loesung_AND[i][0]) {
					fehler = true;
				}
			}
		}
		System.out.println("Fertig nach " + j);
		System.out.println("TestAnd Gewichte:");
		n.print_W();
		System.out.println(n.fire(training_AND[0]) + " " + f.toDiskret(n.fire(training_AND[0])));
		System.out.println(n.fire(training_AND[1]) + " " + f.toDiskret(n.fire(training_AND[1])));
		System.out.println(n.fire(training_AND[2]) + " " + f.toDiskret(n.fire(training_AND[2])));
		System.out.println(n.fire(training_AND[3]) + " " + f.toDiskret(n.fire(training_AND[3])));
		assertTrue(0 == (f.toDiskret(n.fire(training_AND[0]))));
		assertTrue(0 == (f.toDiskret(n.fire(training_AND[1]))));
		assertTrue(0 == (f.toDiskret(n.fire(training_AND[2]))));
		assertTrue(1 == (f.toDiskret(n.fire(training_AND[3]))));
	}
}
