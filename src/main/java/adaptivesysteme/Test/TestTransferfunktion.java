package adaptivesysteme.Test;

import adaptivesysteme.NeuronNetz.*;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestTransferfunktion {

	private int iterationen = 1000000;

	@Test
	public void testAndExp() throws Exception {
		double[][] training_AND = { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 0, 0 }, { 1, 1, 1 } };
		// Erzeuge Neuron im Exp als Transferfunktion
		Transferfunktion f = new TransferExp();
		Neuron n = new Neuron(2, f, 0.5);
		boolean fehler = true;
		int j = 0;
		for (j = 1; j < iterationen && fehler; j++) {
			if (j % 10000 == 0)
				f.increaseLambda();
			fehler = false;
			for (int i = 0; i < 4; i++) {
				double y;
				y = n.train(training_AND[i], training_AND[i][0]);
				if (f.toDiskret(y) != training_AND[i][0]) {
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

	@Test
	public void testAndModifiedTanh() throws Exception {
		double[][] training_AND = { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 0, 0 }, { 1, 1, 1 } };
		Transferfunktion f = new TransferModiefiedTanh();
		Neuron n = new Neuron(2, f, 0.5);
		for (int j = 0; j < iterationen; j++) {
			if (j % 10000 == 0)
				f.increaseLambda();
			for (int i = 0; i < 4; i++) {
				n.train(training_AND[i], training_AND[i][2]);
			}
		}
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

	@Test
	public void testAndTanh() throws Exception {
		double[][] training_AND = { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 0, 0 }, { 1, 1, 1 } };
		Transferfunktion f = new TransferTanh();
		Neuron n = new Neuron(2, f, 0.5);
		for (int j = 0; j < iterationen; j++) {
			if (j % 10000 == 0)
				f.increaseLambda();
			for (int i = 0; i < 4; i++) {
				n.train(training_AND[i], training_AND[i][2]);
			}
		}
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

	@Test
	public void testORExp() {
		double[][] training_OR = { { 0, 0, 0 }, { 0, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
		Transferfunktion f = new TransferExp();
		Neuron n = new Neuron(2, f, 0.5);
		for (int j = 0; j < iterationen; j++) {
			if (j % 10000 == 0)
				f.increaseLambda();
			for (int i = 0; i < 4; i++) {
				n.train(training_OR[i], training_OR[i][2]);
			}
		}
		System.out.println("TestOR Gewichte:");
		n.print_W();
		assertTrue(0 == f.toDiskret(n.fire(training_OR[0])));
		assertTrue(1 == f.toDiskret(n.fire(training_OR[1])));
		assertTrue(1 == f.toDiskret(n.fire(training_OR[2])));
		assertTrue(1 == f.toDiskret(n.fire(training_OR[3])));
	}

	@Test
	public void testORTanh() {
		double[][] training_OR = { { 0, 0, 0 }, { 0, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
		Transferfunktion f = new TransferTanh();
		Neuron n = new Neuron(2, f, 0.5);
		boolean fehler = true;
		int j = 0;
		for (j = 0; j < iterationen && fehler; j++) {
			if (j % 10000 == 0)
				f.increaseLambda();
			fehler = false;
			double y;
			for (int i = 0; i < 4; i++) {
				y = n.train(training_OR[i], training_OR[i][2]);
				if (f.toDiskret(y) != training_OR[i][0]) {
					fehler = true;
				}
			}
		}
		System.out.println("Fertig nach " + j);
		System.out.println("TestOR Gewichte:");
		n.print_W();
		assertTrue(0 == f.toDiskret(n.fire(training_OR[0])));
		assertTrue(1 == f.toDiskret(n.fire(training_OR[1])));
		assertTrue(1 == f.toDiskret(n.fire(training_OR[2])));
		assertTrue(1 == f.toDiskret(n.fire(training_OR[3])));
	}
}
