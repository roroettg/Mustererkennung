import static org.junit.Assert.*;

import java.util.ArrayList;

import mustererkennung.algorithmen.InputHelper;
import mustererkennung.algorithmen.Merkmal;
import mustererkennung.entscheidungsbaum.MerkmalFinderImpl;
import mustererkennung.entscheidungsbaum.Mf3attrib;
import mustererkennung.entscheidungsbaum.TreeNode;

import org.junit.Test;

public class TestEntscheidnungsbaum {

	@Test
	public void testEntropy_1() {
		TreeNode n = new TreeNode("Root", 0, new MerkmalFinderImpl());
		ArrayList<Merkmal> ms = new InputHelper().getLernDaten("gehen");
		assertTrue(n.getEntropy(ms) == 0);
	}

	@Test
	public void testEntropy_2() {
		TreeNode n = new TreeNode("Root", 0,new MerkmalFinderImpl());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = input.getLernDaten("drehen");
		ms.remove(0);
		ms.addAll(input.getLernDaten("sitzen"));
		System.out.println(n.getEntropy(ms));
		assertTrue(n.getEntropy(ms) == 1);
	}

	@Test
	public void testTree_1() {
		TreeNode n = new TreeNode("Root", 0, new MerkmalFinderImpl());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = input.getLernDaten("drehen");
		ms.addAll(input.getLernDaten("sitzen"));
		n.generateTree(ms);
		n.printTree();
		// Verify Trainingsmaterial
		for (Merkmal m : ms) {
			System.out.println(n.classify(m) + " == " + m.getBewegungsart());
			assertTrue(n.classify(m) == m.getBewegungsart());
		}
	}

	@Test
	public void testTree_2() {
		TreeNode n = new TreeNode("Root", 0, new MerkmalFinderImpl());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = input.getLernDaten("treppe");
		ms.addAll(input.getLernDaten("gehen"));
		n.generateTree(ms);
		n.printTree();
		// Verify Trainingsmaterial
		for (Merkmal m : ms) {
			System.out.println(n.classify(m) + " == " + m.getBewegungsart());
			assertTrue(n.classify(m) == m.getBewegungsart());
		}
	}

	@Test
	public void testTree_3() {
		TreeNode n = new TreeNode("Root", 0, new MerkmalFinderImpl());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = new ArrayList<Merkmal>();
		ms.add(input.getLernDaten("treppe").get(0));
		ms.add(input.getLernDaten("treppe").get(1));
		ms.add(input.getLernDaten("treppe").get(2));
		ms.add(input.getLernDaten("gehen").get(1));
		ms.add(input.getLernDaten("gehen").get(0));
		n.generateTree(ms);
		n.printTree();
		// Verify Trainingsmaterial
		for (Merkmal m : ms) {
			System.out.println(n.classify(m) + " == " + m.getBewegungsart());
			assertTrue(n.classify(m) == m.getBewegungsart());
		}
	}

	@Test
	public void testTree_4() {
		TreeNode n = new TreeNode("Root", 0,new MerkmalFinderImpl());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = new ArrayList<Merkmal>();
		ms.add(input.getLernDaten("treppe").get(0));
		ms.add(input.getLernDaten("treppe").get(1));
		ms.addAll(input.getLernDaten("gehen"));
		n.generateTree(ms);
		n.printTree();
		// Verify Trainingsmaterial
		for (Merkmal m : ms) {
			System.out.println(n.classify(m) + " == " + m.getBewegungsart());
			assertTrue(n.classify(m) == m.getBewegungsart());
		}
	}

	@Test
	public void testTree_5() {
		TreeNode n = new TreeNode("Root", 0, new MerkmalFinderImpl());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = new ArrayList<Merkmal>();
		ms.addAll(input.getLernDaten("treppe"));
		ms.addAll(input.getLernDaten("gehen"));
		ms.addAll(input.getLernDaten("sitzen"));
		n.generateTree(ms);
		n.printTree();
		// Verify Trainingsmaterial
		for (Merkmal m : ms) {
			System.out.println(n.classify(m) + " == " + m.getBewegungsart());
			assertTrue(n.classify(m) == m.getBewegungsart());
		}
	}

	@Test
	public void testTree_Verify_1() {
		TreeNode n = new TreeNode("Root", 0, new MerkmalFinderImpl());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = new ArrayList<Merkmal>();
		ms.addAll(input.getLernDaten("treppe"));
		ms.addAll(input.getLernDaten("gehen"));
		n.generateTree(ms);
		n.printTree();
		// Verify Trainingsmaterial
		for (Merkmal m : ms) {
			System.out.println("LernTest " + n.classify(m) + " == " + m.getBewegungsart());
			assertTrue(n.classify(m) == m.getBewegungsart());
		}
		ArrayList<Merkmal> msVerify = input.getVerificationDaten("gehen");
		msVerify.addAll(input.getVerificationDaten("treppe"));
		int fehler = 0;
		for (Merkmal m : msVerify) {
			System.out.println("Verify " + n.classify(m) + " == " + m.getBewegungsart());
			if (m.getBewegungsart() != n.classify(m)) {
				fehler++;
			}
		}
		System.out.println(fehler + " von " + msVerify.size());
		assertTrue(fehler <= (msVerify.size() * 0.25));
	}

	@Test
	public void testTree_Verify_2() {
		TreeNode n = new TreeNode("Root", 0, new MerkmalFinderImpl());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = new ArrayList<Merkmal>();
		ms.addAll(input.getLernDaten("treppe"));
		ms.addAll(input.getLernDaten("gehen"));
		ms.addAll(input.getLernDaten("sitzen"));
		n.generateTree(ms);
		n.printTree();
		// Verify Trainingsmaterial
		for (Merkmal m : ms) {
			System.out.println("LernTest " + n.classify(m) + " == " + m.getBewegungsart());
			assertTrue(n.classify(m) == m.getBewegungsart());
		}
		ArrayList<Merkmal> msVerify = input.getVerificationDaten("gehen");
		msVerify.addAll(input.getVerificationDaten("treppe"));
		msVerify.addAll(input.getVerificationDaten("sitzen"));
		int fehler = 0;
		for (Merkmal m : msVerify) {
			System.out.println("Verify " + n.classify(m) + " == " + m.getBewegungsart());
			if (m.getBewegungsart() != n.classify(m)) {
				fehler++;
			}
		}
		System.out.println(fehler + " von " + msVerify.size());
		assertTrue(fehler <= (msVerify.size() * 0.25));
	}

	
	
	
	@Test
	public void testTree_Verify_3() {
		TreeNode n = new TreeNode("Root", 0, new Mf3attrib());
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
			System.out.println("LernTest " + n.classify(m) + " == " + m.getBewegungsart());
			assertTrue(n.classify(m) == m.getBewegungsart());
		}
		ArrayList<Merkmal> msVerify = input.getVerificationDaten("gehen");
		msVerify.addAll(input.getVerificationDaten("treppe"));
		msVerify.addAll(input.getVerificationDaten("sitzen"));
		msVerify.addAll(input.getVerificationDaten("drehen"));
		int fehler = 0;
		for (Merkmal m : msVerify) {
			System.out.println("Verify " + n.classify(m) + " == " + m.getBewegungsart());
			if (m.getBewegungsart() != n.classify(m)) {
				fehler++;
			}
		}
		System.out.println(fehler + " von " + msVerify.size());
		assertTrue(fehler <= (msVerify.size() * 0.25));
	}
	
	@Test
	public void testTree_Verify_4() {
		TreeNode n = new TreeNode("Root", 0, new Mf3attrib());
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
			System.out.println("LernTest " + n.classify(m) + " == " + m.getBewegungsart());
			assertTrue(n.classify(m) == m.getBewegungsart());
		}
		ArrayList<Merkmal> msVerify = input.getVerificationDaten("gehen");
		msVerify.addAll(input.getVerificationDaten("treppe"));
		msVerify.addAll(input.getVerificationDaten("sitzen"));
		msVerify.addAll(input.getVerificationDaten("drehen"));
		msVerify.addAll(input.getVerificationDaten("joggen"));
		int fehler = 0;
		for (Merkmal m : msVerify) {
			System.out.println("Verify " + n.classify(m) + " == " + m.getBewegungsart());
			if (m.getBewegungsart() != n.classify(m)) {
				fehler++;
			}
		}
		System.out.println(fehler + " von " + msVerify.size());
		assertTrue(fehler <= (msVerify.size() * 0.25));
	}
	
	@Test
	public void testSplitAttribut_1() {
		TreeNode n = new TreeNode("Root", 0, new MerkmalFinderImpl());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = input.getLernDaten("treppe");
		ms.addAll(input.getLernDaten("gehen"));
		System.out.println(n.splitAttribut(ms));
	}

	@Test
	public void testSplitAttribut_2() {
		TreeNode n = new TreeNode("Root", 0, new MerkmalFinderImpl());
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = new ArrayList<Merkmal>();
		ms.add(input.getLernDaten("treppe").get(0));
		;
		ms.add(input.getLernDaten("gehen").get(0));
		System.out.println(n.splitAttribut(ms));
		ArrayList<Merkmal>[] msa = n.splitArray(ms, n.splitAttribut(ms), 0);
		assertTrue(msa[0].size() == 1);
		assertTrue(msa[1].size() == 1);
	}

	@Test
	public void testSplitAttribut_3() {
		TreeNode n = new TreeNode("Root", 0, new MerkmalFinderImpl());
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
		TreeNode n = new TreeNode("Root", 0, new MerkmalFinderImpl());
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
