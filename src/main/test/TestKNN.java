import adaptivesysteme.KMeans.NormEuklid;
import adaptivesysteme.KMeans.NormManhattan;
import mustererkennung.algorithmen.InputHelper;
import mustererkennung.algorithmen.KNN;
import mustererkennung.algorithmen.Merkmal;

import org.junit.Test;

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
		int fehler=0;
		for(Merkmal m : test){
			System.out.println(m.getBewegungsart() +" == " + knn.classify(m));
			if(!m.getBewegungsart().equals(knn.classify(m))){
				fehler++;
			}
		}
		System.out.println("fehlerrate:"  + fehler +" von "+test.size());
		assertTrue(fehler == 0);
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
		int fehler=0;
		for(Merkmal m : test){
			System.out.println(m.getBewegungsart() +" == " + knn.classify(m));
			if(!m.getBewegungsart().equals(knn.classify(m))){
				fehler++;
			}
		}
		System.out.println("fehlerrate:"  + fehler +" von "+test.size());
		assertTrue(fehler == 0);
	}
	
	@Test
	public void testKNN3Euklid() {
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> lern = input.getLernDaten("gehen");
		lern.addAll(input.getLernDaten("sitzen"));
		lern.addAll(input.getLernDaten("joggen"));
		KNN knn = new KNN(lern, 3, new NormEuklid());
		ArrayList<Merkmal> test  = input.getVerificationDaten("gehen");
		test.addAll(input.getVerificationDaten("sitzen"));
		test.addAll(input.getVerificationDaten("joggen"));
		System.out.println("\nTest testKNN3Euklid");
		int fehler=0;
		for(Merkmal m : test){
			System.out.println(m.getBewegungsart() +" == " + knn.classify(m));
			if(!m.getBewegungsart().equals(knn.classify(m))){
				fehler++;
			}
		}
		System.out.println("fehlerrate:"  + fehler +" von "+test.size());
		assertTrue(fehler == 0);
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
		int fehler=0;
		for(Merkmal m : test){
			System.out.println(m.getBewegungsart() +" == " + knn.classify(m));
			if(!m.getBewegungsart().equals(knn.classify(m))){
				fehler++;
			}
		}
		System.out.println("fehlerrate:"  + fehler +" von "+test.size());
		assertTrue(fehler == 0);
	}
	
	@Test
	public void testKNN4Manhattan() {
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
		System.out.println("\nTest testKNN3Manhattan");
		int fehler=0;
		for(Merkmal m : test){
			System.out.println(m.getBewegungsart() +" == " + knn.classify(m));
			if(!m.getBewegungsart().equals(knn.classify(m))){
				fehler++;
			}
		}
		System.out.println("fehlerrate:"  + fehler +" von "+test.size());
		assertTrue(fehler == 0);
	}
	
	
	@Test
	public void testKNN5Manhattan() {
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> lern = input.getLernDaten("gehen");
		lern.addAll(input.getLernDaten("sitzen"));
		lern.addAll(input.getLernDaten("joggen"));
		lern.addAll(input.getLernDaten("treppe"));
		lern.addAll(input.getLernDaten("drehen"));
		KNN knn = new KNN(lern, 3, new NormManhattan());
		ArrayList<Merkmal> test  = input.getVerificationDaten("gehen");
		test.addAll(input.getVerificationDaten("sitzen"));
		test.addAll(input.getVerificationDaten("joggen"));
		test.addAll(input.getVerificationDaten("treppe"));
		test.addAll(input.getVerificationDaten("drehen"));
		System.out.println("\nTest testKNN3Manhattan");
		int fehler=0;
		for(Merkmal m : test){
			System.out.println(m.getBewegungsart() +" == " + knn.classify(m));
			if(!m.getBewegungsart().equals(knn.classify(m))){
				fehler++;
			}
		}
		System.out.println("fehlerrate:"  + fehler +" von "+test.size());
		assertTrue(fehler == 0);
	}
}
