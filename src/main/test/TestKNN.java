import adaptivesysteme.KMeans.NormEuklid;
import adaptivesysteme.KMeans.NormManhattan;
import org.junit.Test;
import pla.InputHelper;
import pla.KNN;
import pla.Merkmal;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class TestKNN {

	@Test
	public void testKNN2() {
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = input.getLernDaten("gehen");
		ms.addAll(input.getLernDaten("sitzen"));
		KNN knn = new KNN(ms, 3, new NormEuklid());
		ArrayList<Merkmal> test  = input.getVerificationDaten("gehen");
		test.addAll(input.getVerificationDaten("sitzen"));
		System.out.println("\nTest testKNN2");
		for(Merkmal m : test){
			System.out.println(m.getBewegungsart() +" == " + knn.classify(m));
		}
		for(Merkmal m : test){
			assertTrue((m.getBewegungsart() == knn.classify(m)));
		}
	}

	
	@Test
	public void testKNN2Manhattan() {
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> lern = input.getLernDaten("gehen");
		lern.addAll(input.getLernDaten("sitzen"));
		KNN knn = new KNN(lern, 3, new NormManhattan());
		ArrayList<Merkmal> test  = input.getVerificationDaten("gehen");
		test.addAll(input.getVerificationDaten("sitzen"));
		System.out.println("\nTest testKNN2Manhattan");
		for(Merkmal m : test){
			System.out.println(m.getBewegungsart() +" == " + knn.classify(m));
		}
		for(Merkmal m : test){
			assertTrue((m.getBewegungsart() == knn.classify(m)));
		}
	}
	
	@Test
	public void testKNN3Euklid() {
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> lern = input.getLernDaten("gehen");
		lern.addAll(input.getLernDaten("sitzen"));
		lern.addAll(input.getLernDaten("joggen"));
		KNN knn = new KNN(lern, 3, new NormManhattan());
		ArrayList<Merkmal> test  = input.getVerificationDaten("gehen");
		test.addAll(input.getVerificationDaten("sitzen"));
		test.addAll(input.getVerificationDaten("joggen"));
		System.out.println("\nTest testKNN3Euklid");
		for(Merkmal m : test){
			System.out.println(m.getBewegungsart() +" == " + knn.classify(m));
		}
		for(Merkmal m : test){
			assertTrue((m.getBewegungsart() == knn.classify(m)));
		}
	}
	
	@Test
	public void testKNN4Euklid() {
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> lern = input.getLernDaten("gehen");
		lern.addAll(input.getLernDaten("sitzen"));
		lern.addAll(input.getLernDaten("joggen"));
		lern.addAll(input.getLernDaten("treppe"));
		KNN knn = new KNN(lern, 3, new NormManhattan());
		ArrayList<Merkmal> test  = input.getVerificationDaten("gehen");
		test.addAll(input.getVerificationDaten("sitzen"));
		test.addAll(input.getVerificationDaten("joggen"));
		test.addAll(input.getVerificationDaten("treppe"));
		System.out.println("\nTest testKNN3Euklid");
		for(Merkmal m : test){
			System.out.println(m.getBewegungsart() +" == " + knn.classify(m));
		}
		for(Merkmal m : test){
			assertTrue((m.getBewegungsart() == knn.classify(m)));
		}
	}
}
