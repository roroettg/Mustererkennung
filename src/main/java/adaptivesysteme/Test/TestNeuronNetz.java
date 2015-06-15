package adaptivesysteme.Test;

import adaptivesysteme.NeuronNetz.NeuronenNetz;
import adaptivesysteme.NeuronNetz.TransferExp;
import adaptivesysteme.NeuronNetz.Transferfunktion;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestNeuronNetz {

	private int Train_iteration = 1000000;

	@Test
	public void test6AND() {
		double[][] training = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		double[][] loesung_AND = { { 0 }, { 0 }, { 0 }, { 1 } };

		int[] schichten = { 2, 2, 4, 4, 2, 1 };
		runTest(training, loesung_AND, schichten, " 1AND ", 2, 1);
	}

	@Test
	public void test3AND() {
		double[][] training = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		double[][] loesung_AND = { { 0 }, { 0 }, { 0 }, { 1 } };

		int[] schichten = { 2, 2, 1 };
		runTest(training, loesung_AND, schichten, " 1AND ", 2, 1);
	}

	@Test
	public void test2AND() {
		double[][] training = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		double[][] loesung_AND = { { 0 }, { 0 }, { 0 }, { 1 } };

		int[] schichten = { 2, 1 };
		runTest(training, loesung_AND, schichten, " 2AND ", 2, 1);
	}

	@Test
	public void test1AND() {
		double[][] training = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		double[][] loesung_AND = { { 0 }, { 0 }, { 0 }, { 1 } };

		int[] schichten = { 1 };
		runTest(training, loesung_AND, schichten, " 1AND ", 2, 1);
	}

	@Test
	public void test2XOR() {
		double[][] training = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		double[][] loesung = { { 0 }, { 1 }, { 1 }, { 0 } };

		int[] schichten = { 2, 1 };
		runTest(training, loesung, schichten, " 2XOR ", 2, 1);
	}

	@Test
	public void test3XOR() {
		double[][] training = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		double[][] loesung = { { 0 }, { 1 }, { 1 }, { 0 } };

		int[] schichten = { 2, 2, 1 };
		runTest(training, loesung, schichten, " 3XOR ", 2, 1);
	}
	
	@Test
	public void test4XOR() {
		double[][] training = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		double[][] loesung = { { 0 }, { 1 }, { 1 }, { 0 } };

		int[] schichten = { 2, 4, 2, 1 };
		runTest(training, loesung, schichten, " 3XOR ", 2, 1);
	}

	@Test
	public void test4XOR2() {
		double[][] training = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		double[][] loesung = { { 0 }, { 1 }, { 1 }, { 0 } };

		int[] schichten = { 2, 2, 4, 1 };
		runTest(training, loesung, schichten, " 3XOR ", 2, 1);
	}
	
	@Test
	public void test1OR() {
		double[][] training = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		double[][] loesung = { { 0 }, { 1 }, { 1 }, { 1 } };

		int[] schichten = { 1 };
		runTest(training, loesung, schichten, " 1OR ", 2, 1);
	}

	@Test
	public void test2OR() {
		double[][] training = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		double[][] loesung = { { 0 }, { 1 }, { 1 }, { 1 } };

		int[] schichten = { 2, 1 };
		runTest(training, loesung, schichten, " 2OR ", 2, 1);
	}

	@Test
	public void testOR3() throws Exception {
		double[][] training = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		double[][] loesung_OR = { { 0 }, { 1 }, { 1 }, { 1 } };
		int[] schichten = { 2, 2, 1 };
		runTest(training, loesung_OR, schichten, " 3OR ", 2, 1);
	}

	private void runTest(double[][] training, double[][] loesung, int[] schichten, String oper, int in, int out) {
		int fehler = 0;
		Transferfunktion f = new TransferExp();
		NeuronenNetz netz = new NeuronenNetz(in, out, schichten, f);
		for (int j = 0; j < Train_iteration; j++) {
			for (int a = 0; a < training.length; a++) {
				int i = 0;
				if(a % 100000 == 0)
					f.increaseLambda();
				Double d;
				d = Math.random() * training.length;
				i = d.intValue();
				netz.train(training[a], loesung[a]);
			}
			for (int i = 0; i < training.length; i++) {
				double[] y = netz.fire(training[i]);
				for (int a = 0; a < loesung[0].length; a++) {
					if (f.toDiskret(y[a]) != loesung[i][a]) {
						fehler++;
					}
				}
			}
			if (fehler == 0) {
				System.out.println("Nach " + j + " Trainingsläufen fertig");
				break;
			}
			fehler = 0;
		}
		System.out.println("Test: " + oper);
		netz.printW();
		for (int i = 0; i < training.length; i++) {
			double[] l = netz.fire(training[i]);
			for (int a = 0; a < loesung[0].length; a++) {
				assertTrue(f.toDiskret(l[a]) == loesung[i][a]);
			}
		}
	}

	@Test
	public void testSiebenSegmentAnzeige10_2() throws Exception {
		double[][] training = { { 0, 0, 0, 0 }, { 0, 0, 0, 1 }, { 0, 0, 1, 0 }, { 0, 0, 1, 1 }, { 0, 1, 0, 0 }, { 0, 1, 0, 1 }, { 0, 1, 1, 0 }, { 0, 1, 1, 1 },
				{ 1, 0, 0, 0 }, { 1, 0, 0, 1 } };
		//  _    a
		// |_| f g b
		// |_| e   c
		//       d 				 a  b  c  d  e  f  g
		double[][] loesung = { { 1, 1, 1, 1, 1, 1, 0 }, { 0, 1, 1, 0, 0, 0, 0 }, { 1, 1, 0, 1, 0, 1, 1 }, { 1, 1, 1, 1, 0, 0, 1 }, { 0, 1, 1, 0, 0, 1, 1 },
				{ 1, 0, 1, 1, 0, 1, 1 }, { 1, 0, 1, 1, 1, 1, 1 }, { 1, 1, 1, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 0, 1, 1 } };

		int[] schichten = { 4, 7 };
		runTest(training, loesung, schichten, " 7Segment10", 4, 7);
	}
	
	
	@Test
	public void testSiebenSegmentAnzeige10() throws Exception {
		double[][] training = { { 0, 0, 0, 0 }, { 0, 0, 0, 1 }, { 0, 0, 1, 0 }, { 0, 0, 1, 1 }, { 0, 1, 0, 0 }, { 0, 1, 0, 1 }, { 0, 1, 1, 0 }, { 0, 1, 1, 1 },
				{ 1, 0, 0, 0 }, { 1, 0, 0, 1 } };
		//  _    a
		// |_| f g b
		// |_| e   c
		//       d 				 a  b  c  d  e  f  g
		double[][] loesung = { { 1, 1, 1, 1, 1, 1, 0 }, { 0, 1, 1, 0, 0, 0, 0 }, { 1, 1, 0, 1, 0, 1, 1 }, { 1, 1, 1, 1, 0, 0, 1 }, { 0, 1, 1, 0, 0, 1, 1 },
				{ 1, 0, 1, 1, 0, 1, 1 }, { 1, 0, 1, 1, 1, 1, 1 }, { 1, 1, 1, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 0, 1, 1 } };

		int[] schichten = { 10, 7 };
		runTest(training, loesung, schichten, " 7Segment10", 4, 7);
	}

	@Test
	public void testSiebenSegmentAnzeige16() throws Exception {
		double[][] training = { { 0, 0, 0, 0 }, { 0, 0, 0, 1 }, { 0, 0, 1, 0 }, { 0, 0, 1, 1 }, { 0, 1, 0, 0 }, { 0, 1, 0, 1 }, { 0, 1, 1, 0 }, { 0, 1, 1, 1 },
				{ 1, 0, 0, 0 }, { 1, 0, 0, 1 }, { 1, 0, 1, 0 }, { 1, 0, 1, 1 }, { 1, 1, 0, 0 }, { 1, 1, 0, 1 }, { 1, 1, 1, 0 }, { 1, 1, 1, 1 } };
		double[][] loesung = { { 1, 1, 1, 1, 1, 1, 0 }, { 0, 1, 1, 0, 0, 0, 0 }, { 1, 1, 0, 1, 0, 1, 1 }, { 1, 1, 1, 1, 0, 0, 1 }, { 0, 1, 1, 0, 0, 1, 1 },
				{ 1, 0, 1, 1, 0, 1, 1 }, { 1, 0, 1, 1, 1, 1, 1 }, { 1, 1, 1, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 0, 1, 1 },
				{ 1, 1, 1, 0, 1, 1, 1 }, { 0, 0, 1, 1, 1, 1, 1 }, { 0, 0, 0, 1, 1, 0, 1 }, { 0, 1, 1, 1, 1, 0, 1 }, { 1, 0, 0, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 1, 1, 1 } };

		int[] schichten = { 16, 7 };
		runTest(training, loesung, schichten, " 7Segment16", 4, 7);
	}
}
