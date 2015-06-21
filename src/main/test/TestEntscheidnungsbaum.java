import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import mustererkennung.algorithmen.InputHelper;
import mustererkennung.algorithmen.Merkmal;
import mustererkennung.entscheidungsbaum.MerkmalFinderImpl;
import mustererkennung.entscheidungsbaum.Mf3attrib;
import mustererkennung.entscheidungsbaum.DecisionTree;

import org.junit.Test;

public class TestEntscheidnungsbaum {

	@Test
	public void testEntropy_1() {
		DecisionTree n = new DecisionTree("Root", 0, new MerkmalFinderImpl());
		ArrayList<Merkmal> ms = new InputHelper().getLernDaten("gehen");
		assertTrue(n.getEntropy(ms) == 0);
	}

	@Test
	public void testEntropy_2() {
		DecisionTree n = new DecisionTree("Root", 0, new MerkmalFinderImpl());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = new ArrayList<>();
		ms.add(input.getLernDaten("drehen").get(0));
		ms.add((input.getLernDaten("sitzen").get(0)));
		System.out.println(n.getEntropy(ms));
		assertTrue(n.getEntropy(ms) == 1);
	}

	@Test
	public void testTree_1() {
		DecisionTree n = new DecisionTree("Root", 0, new MerkmalFinderImpl());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = input.getLernDaten("drehen");
		ms.addAll(input.getLernDaten("sitzen"));
		n.generateTree(ms);
		n.printTree();
		// Verify Trainingsmaterial
		for (Merkmal m : ms) {
			//System.out.println(n.classify(m) + " == " + m.getBewegungsart());
			assertTrue(n.classify(m) == m.getBewegungsart());
		}
	}

	@Test
	public void testTree_2() {
		DecisionTree n = new DecisionTree("Root", 0, new MerkmalFinderImpl());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = input.getLernDaten("treppe");
		ms.addAll(input.getLernDaten("gehen"));
		n.generateTree(ms);
		n.printTree();
		// Verify Trainingsmaterial
		for (Merkmal m : ms) {
			//System.out.println(n.classify(m) + " == " + m.getBewegungsart());
			assertTrue(n.classify(m) == m.getBewegungsart());
		}
	}

	@Test
	public void testTree_3() {
		DecisionTree n = new DecisionTree("Root", 0, new MerkmalFinderImpl());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = new ArrayList<Merkmal>();
		ms.add(input.getLernDaten("treppe").get(0));
		ms.add(input.getLernDaten("treppe").get(1));
		ms.add(input.getLernDaten("treppe").get(2));
		ms.add(input.getLernDaten("gehen").get(1));
		ms.add(input.getLernDaten("gehen").get(0));
		n.generateTree(ms);
		n.printTree();
		System.out.println("----------------------------");
		n.printDot();
		// Verify Trainingsmaterial
		for (Merkmal m : ms) {
			//System.out.println(n.classify(m) + " == " + m.getBewegungsart());
			assertTrue(n.classify(m) == m.getBewegungsart());
		}
	}

	@Test
	public void testTree_4() {
		DecisionTree n = new DecisionTree("Root", 0, new MerkmalFinderImpl());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = new ArrayList<Merkmal>();
		ms.add(input.getLernDaten("treppe").get(0));
		ms.add(input.getLernDaten("treppe").get(1));
		ms.addAll(input.getLernDaten("gehen"));
		n.generateTree(ms);
		n.printTree();
		System.out.println("----------------------------------");
		n.printDot();
		// Verify Trainingsmaterial
		for (Merkmal m : ms) {
			//System.out.println(n.classify(m) + " == " + m.getBewegungsart());
			assertTrue(n.classify(m) == m.getBewegungsart());
		}
	}

	@Test
	public void testTree_5() {
		DecisionTree n = new DecisionTree("Root", 0, new MerkmalFinderImpl());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = new ArrayList<Merkmal>();
		ms.addAll(input.getLernDaten("treppe"));
		ms.addAll(input.getLernDaten("gehen"));
		ms.addAll(input.getLernDaten("sitzen"));
		n.generateTree(ms);
		n.printTree();
		// Verify Trainingsmaterial
		for (Merkmal m : ms) {
			//System.out.println(n.classify(m) + " == " + m.getBewegungsart());
			assertTrue(n.classify(m) == m.getBewegungsart());
		}
	}

	@Test
	public void testTree_Verify_1() {
		DecisionTree n = new DecisionTree("Root", 0, new MerkmalFinderImpl());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = new ArrayList<Merkmal>();
		ms.addAll(input.getLernDaten("treppe"));
		ms.addAll(input.getLernDaten("gehen"));
		n.generateTree(ms);
		n.printTree();
		// Verify Trainingsmaterial
		for (Merkmal m : ms) {
			//System.out.println("LernTest " + n.classify(m) + " == " + m.getBewegungsart());
			assertTrue(n.classify(m) == m.getBewegungsart());
		}
		ArrayList<Merkmal> msVerify = input.getVerificationDaten("gehen");
		msVerify.addAll(input.getVerificationDaten("treppe"));
		int fehler = verifyTree(n, msVerify);
		System.out.println(fehler + " von " + msVerify.size());
	}

	@Test
	public void testTree_Verify_2() {
		DecisionTree n = new DecisionTree("Root", 0, new MerkmalFinderImpl());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = new ArrayList<Merkmal>();
		ms.addAll(input.getLernDaten("treppe"));
		ms.addAll(input.getLernDaten("gehen"));
		ms.addAll(input.getLernDaten("sitzen"));
		n.generateTree(ms);
		n.printTree();
		System.out.println("-----------------------------");
		n.printDot();
		// Verify Trainingsmaterial
		for (Merkmal m : ms) {
			//System.out.println("LernTest " + n.classify(m) + " == " + m.getBewegungsart());
			assertTrue(n.classify(m) == m.getBewegungsart());
		}
		ArrayList<Merkmal> msVerify = input.getVerificationDaten("gehen");
		msVerify.addAll(input.getVerificationDaten("treppe"));
		msVerify.addAll(input.getVerificationDaten("sitzen"));
		int fehler = verifyTree(n, msVerify);
		System.out.println(fehler + " von " + msVerify.size());
	}

	@Test
	public void testTree_Verify_3() {
		DecisionTree n = new DecisionTree("Root", 0, new Mf3attrib());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = new ArrayList<Merkmal>();
		ms.addAll(input.getLernDaten("treppe"));
		ms.addAll(input.getLernDaten("gehen"));
		ms.addAll(input.getLernDaten("sitzen"));
		ms.addAll(input.getLernDaten("drehen"));
		n.generateTree(ms);
		n.printTree();
		// Verify Trainingsmaterial
		for (Merkmal m : ms) {
			//System.out.println("LernTest " + n.classify(m) + " == " + m.getBewegungsart());
			assertTrue(n.classify(m) == m.getBewegungsart());
		}
		ArrayList<Merkmal> msVerify = input.getVerificationDaten("gehen");
		msVerify.addAll(input.getVerificationDaten("treppe"));
		msVerify.addAll(input.getVerificationDaten("sitzen"));
		msVerify.addAll(input.getVerificationDaten("drehen"));
		int fehler = verifyTree(n, msVerify);
		System.out.println(fehler + " von " + msVerify.size());
	}

	@Test
	public void testTree_Verify_4() {
		DecisionTree n = new DecisionTree("Root", 0, new Mf3attrib());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = new ArrayList<Merkmal>();
		ms.addAll(input.getLernDaten("treppe"));
		ms.addAll(input.getLernDaten("gehen"));
		ms.addAll(input.getLernDaten("sitzen"));
		ms.addAll(input.getLernDaten("drehen"));
		ms.addAll(input.getLernDaten("joggen"));
		n.generateTree(ms);
		n.printTree();
		// Verify Trainingsmaterial
		for (Merkmal m : ms) {
			//System.out.println("LernTest " + n.classify(m) + " == " + m.getBewegungsart());
			assertTrue(n.classify(m) == m.getBewegungsart());
		}
		ArrayList<Merkmal> msVerify = input.getVerificationDaten("gehen");
		msVerify.addAll(input.getVerificationDaten("treppe"));
		msVerify.addAll(input.getVerificationDaten("sitzen"));
		msVerify.addAll(input.getVerificationDaten("drehen"));
		msVerify.addAll(input.getVerificationDaten("joggen"));
		int fehler = verifyTree(n, msVerify);
		System.out.println("Fehlerrate: " +fehler + " von " + msVerify.size());
		
	}

	private int verifyTree(DecisionTree n, ArrayList<Merkmal> msVerify) {
		int fehler = 0;
		HashMap<String, Double> klassen = new HashMap<String, Double>();
		for (Merkmal m : msVerify) {
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
		for (Merkmal m : msVerify) {
			//System.out.println("Verify " + n.classify(m) + " == " + m.getBewegungsart());
			String result;
			result = n.classify(m);
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
		ArrayList<String> k = new ArrayList(klassen.keySet());
		for (int i = 0; i < klassen.size(); i++) {
			System.out.println("\n\n" + k.get(i));
			System.out.println("TruePositive:  " + truePositiv[i]);
			System.out.println("FalsePositive: " + falsePositiv[i]);
			System.out.println("TrueNegative:  " + trueNegativ[i]);
			System.out.println("FalseNegative: " + falseNegativ[i]);
		}
		assertTrue(fehler <= (msVerify.size() * 0.25));
		return fehler;
	}

	@Test
	public void testSplitAttribut_1() {
		DecisionTree n = new DecisionTree("Root", 0, new MerkmalFinderImpl());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = input.getLernDaten("treppe");
		ms.addAll(input.getLernDaten("gehen"));
		System.out.println(n.splitAttribut(ms));
	}

	@Test
	public void testSplitAttribut_2() {
		DecisionTree n = new DecisionTree("Root", 0, new MerkmalFinderImpl());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = new ArrayList<Merkmal>();
		ms.add(input.getLernDaten("treppe").get(0));
		ms.add(input.getLernDaten("gehen").get(0));
		System.out.println(n.splitAttribut(ms));
		ArrayList<Merkmal>[] msa = n.splitArray(ms, n.splitAttribut(ms), 0);
		assertTrue(msa[0].size() == 1);
		assertTrue(msa[1].size() == 1);
	}

	@Test
	public void testSplitAttribut_3() {
		DecisionTree n = new DecisionTree("Root", 0, new MerkmalFinderImpl());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = new ArrayList<Merkmal>();
		ms.add(input.getLernDaten("treppe").get(0));
		ms.add(input.getLernDaten("treppe").get(1));
		ms.add(input.getLernDaten("gehen").get(1));
		ms.add(input.getLernDaten("gehen").get(0));
		System.out.println(n.splitAttribut(ms));
		ArrayList<Merkmal>[] msa = n.splitArray(ms, n.splitAttribut(ms), 0);
		assertTrue(msa[0].size() == 2);
		assertTrue(msa[1].size() == 2);
	}

	@Test
	public void testSplitAttribut_4() {
		DecisionTree n = new DecisionTree("Root", 0, new MerkmalFinderImpl());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = new ArrayList<Merkmal>();
		ms.add(input.getLernDaten("treppe").get(0));
		ms.add(input.getLernDaten("treppe").get(1));
		ms.add(input.getLernDaten("treppe").get(2));
		ms.add(input.getLernDaten("gehen").get(1));
		ms.add(input.getLernDaten("gehen").get(0));
		System.out.println(n.splitAttribut(ms));
		ArrayList<Merkmal>[] msa = n.splitArray(ms, n.splitAttribut(ms), 0);
		assertTrue(msa[0].size() == 3);
		assertTrue(msa[1].size() == 2);
	}

}
