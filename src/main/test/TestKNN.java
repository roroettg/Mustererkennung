import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import mustererkennung.algorithmen.InputHelper;
import mustererkennung.algorithmen.KNN;
import mustererkennung.algorithmen.Merkmal;

import org.junit.Test;

import adaptivesysteme.KMeans.Norm;
import adaptivesysteme.KMeans.NormEuklid;
import adaptivesysteme.KMeans.NormManhattan;

public class TestKNN {

	private void runTest(ArrayList<Merkmal> lern, ArrayList<Merkmal> test, int neighbour, Norm norm) {
		KNN knn = new KNN(lern, neighbour, norm);
		int fehler = 0;
		HashMap<String, Double> klassen = new HashMap<String, Double>();
		for (Merkmal m : test) {
			Double d = klassen.get(m.getBewegungsart());
			if (d == null || d == 0) {
				d = 1.0;
			} else {
				d++;
			}
			klassen.put(m.getBewegungsart(), d);
		}
		int[] truePositiv = new int[klassen.size()];
		int[] trueNegativ = new int[klassen.size()];
		int[] falsePositiv = new int[klassen.size()];
		int[] falseNegativ = new int[klassen.size()];
		for (Merkmal m : test) {
			String result;
			result = knn.classify(m);
			if (!m.getBewegungsart().equals(result)) {
				fehler++;
			}
			int index = (new ArrayList(klassen.keySet()).indexOf(m.getBewegungsart()));
			if (m.getBewegungsart() == result) {
				truePositiv[index]++;
				for (int i = 0; i < klassen.size(); i++) {
					if (i != index) {
						trueNegativ[i]++;
					}
				}
			} else if (m.getBewegungsart() != result) {
				falseNegativ[index]++;
				int e = (new ArrayList(klassen.keySet()).indexOf(result));
				falsePositiv[e]++;
			}
		}
		System.out.println("fehlerrate:" + fehler + " von " + test.size());
		ArrayList<String> k = new ArrayList(klassen.keySet());
		for (int i = 0; i < klassen.size(); i++) {
			System.out.println("\n\n" + k.get(i));
			System.out.println("TruePositive:  " + truePositiv[i]);
			System.out.println("FalsePositive: " + falsePositiv[i]);
			System.out.println("TrueNegative:  " + trueNegativ[i]);
			System.out.println("FalseNegative: " + falseNegativ[i]);
		}
		assertTrue(fehler <= test.size() * 0.25);
	}

	@Test
	public void testKNN2Euklid3() {
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> lern = input.getLernDaten("gehen");
		lern.addAll(input.getLernDaten("sitzen"));
		ArrayList<Merkmal> test = input.getVerificationDaten("gehen");
		test.addAll(input.getVerificationDaten("sitzen"));
		System.out.println("\nTest testKNN2");
		runTest(lern, test, 3, new NormEuklid());
	}

	@Test
	public void testKNN2Manhattan3() {
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> lern = input.getLernDaten("gehen");
		lern.addAll(input.getLernDaten("sitzen"));
		ArrayList<Merkmal> test = input.getVerificationDaten("gehen");
		test.addAll(input.getVerificationDaten("sitzen"));
		System.out.println("\nTest testKNN2Manhattan");
		runTest(lern, test, 3, new NormManhattan());
	}

	@Test
	public void testKNN3Euklid3() {
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> lern = input.getLernDaten("gehen");
		lern.addAll(input.getLernDaten("sitzen"));
		lern.addAll(input.getLernDaten("joggen"));
		ArrayList<Merkmal> test = input.getVerificationDaten("gehen");
		test.addAll(input.getVerificationDaten("sitzen"));
		test.addAll(input.getVerificationDaten("joggen"));
		System.out.println("\nTest testKNN3Euklid");
		runTest(lern, test, 3, new NormEuklid());
	}

	@Test
	public void testKNN3Euklid5() {
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> lern = input.getLernDaten("gehen");
		lern.addAll(input.getLernDaten("sitzen"));
		lern.addAll(input.getLernDaten("joggen"));
		ArrayList<Merkmal> test = input.getVerificationDaten("gehen");
		test.addAll(input.getVerificationDaten("sitzen"));
		test.addAll(input.getVerificationDaten("joggen"));
		System.out.println("\nTest testKNN3Manhattan");
		runTest(lern, test, 5, new NormEuklid());
	}

	@Test
	public void testKNN3Euklid7() {
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> lern = input.getLernDaten("gehen");
		lern.addAll(input.getLernDaten("sitzen"));
		lern.addAll(input.getLernDaten("treppe"));
		ArrayList<Merkmal> test = input.getVerificationDaten("gehen");
		test.addAll(input.getVerificationDaten("sitzen"));
		test.addAll(input.getVerificationDaten("treppe"));
		System.out.println("\nTest testKNN3Manhattan");
		runTest(lern, test, 7, new NormEuklid());
	}

	@Test
	public void testKNN4Euklid3() {
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> lern = input.getLernDaten("gehen");
		lern.addAll(input.getLernDaten("sitzen"));
		lern.addAll(input.getLernDaten("joggen"));
		lern.addAll(input.getLernDaten("treppe"));
		ArrayList<Merkmal> test = input.getVerificationDaten("gehen");
		test.addAll(input.getVerificationDaten("sitzen"));
		test.addAll(input.getVerificationDaten("joggen"));
		test.addAll(input.getVerificationDaten("treppe"));
		System.out.println("\nTest testKNN3Euklid");
		runTest(lern, test, 3, new NormEuklid());
	}

	@Test
	public void testKNN4Manhattan3() {
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> lern = input.getLernDaten("gehen");
		lern.addAll(input.getLernDaten("sitzen"));
		lern.addAll(input.getLernDaten("joggen"));
		lern.addAll(input.getLernDaten("treppe"));
		ArrayList<Merkmal> test = input.getVerificationDaten("gehen");
		test.addAll(input.getVerificationDaten("sitzen"));
		test.addAll(input.getVerificationDaten("joggen"));
		test.addAll(input.getVerificationDaten("treppe"));
		System.out.println("\nTest testKNN3Manhattan");
		runTest(lern, test, 3, new NormManhattan());
	}

	@Test
	public void testKNN5Manhattan3() {
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> lern = input.getLernDaten("gehen");
		lern.addAll(input.getLernDaten("sitzen"));
		lern.addAll(input.getLernDaten("joggen"));
		lern.addAll(input.getLernDaten("treppe"));
		lern.addAll(input.getLernDaten("drehen"));
		ArrayList<Merkmal> test = input.getVerificationDaten("gehen");
		test.addAll(input.getVerificationDaten("sitzen"));
		test.addAll(input.getVerificationDaten("joggen"));
		test.addAll(input.getVerificationDaten("treppe"));
		test.addAll(input.getVerificationDaten("drehen"));
		System.out.println("\nTest testKNN3Manhattan");
		runTest(lern, test, 3, new NormManhattan());
	}

	@Test
	public void test_Variationen_von_K() throws Exception {
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> lern = input.getLernDaten("gehen");
		lern.addAll(input.getLernDaten("sitzen"));
		lern.addAll(input.getLernDaten("joggen"));
		lern.addAll(input.getLernDaten("drehen"));
		lern.addAll(input.getLernDaten("treppe"));
		ArrayList<Merkmal> test = input.getVerificationDaten("gehen");
		test.addAll(input.getVerificationDaten("sitzen"));
		test.addAll(input.getVerificationDaten("joggen"));
		test.addAll(input.getVerificationDaten("drehen"));
		test.addAll(input.getVerificationDaten("treppe"));
		for (int i = 3; i <= 111; i = i + 2) {
			System.out.println("\nTest testK= " + i);
			runTest(lern, test, i, new NormEuklid());
		}
	}
}
