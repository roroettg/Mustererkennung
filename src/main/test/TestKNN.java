import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

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
		for (Merkmal m : test) {
			if (!m.getBewegungsart().equals(knn.classify(m))) {
				fehler++;
			}
		}
		System.out.println("fehlerrate:" + fehler + " von " + test.size());
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
		lern.addAll(input.getLernDaten("joggen"));
		ArrayList<Merkmal> test = input.getVerificationDaten("gehen");
		test.addAll(input.getVerificationDaten("sitzen"));
		test.addAll(input.getVerificationDaten("joggen"));
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
}
