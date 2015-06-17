import static org.junit.Assert.*;

import java.util.ArrayList;

import mustererkennung.algorithmen.InputHelper;
import mustererkennung.algorithmen.Merkmal;
import mustererkennung.entscheidungsbaum.TreeNode;

import org.junit.Test;

public class TestEntscheidnungsbaum {

	@Test
	public void testEntropy_1() {
		TreeNode n = new TreeNode("Root", 0);
		ArrayList<Merkmal> ms = new InputHelper().getLernDaten("gehen");
		assertTrue(n.getEntropy(ms) == 0);
	}

	@Test
	public void testEntropy_2() {
		TreeNode n = new TreeNode("Root", 0);
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = input.getLernDaten("drehen");
		ms.remove(0);
		ms.addAll(input.getLernDaten("sitzen"));
		System.out.println(n.getEntropy(ms));
		assertTrue(n.getEntropy(ms) == 1);
	}

	@Test
	public void testTree_1() {
		TreeNode n = new TreeNode("Root", 0);
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = input.getLernDaten("drehen");
		ms.addAll(input.getLernDaten("sitzen"));
		n.generateTree(ms);
		n.printTree();
		//Verify Trainingsmaterial
		for(Merkmal m: ms){
			System.out.println(n.classify(m) + " == " + m.getBewegungsart());
			assertTrue(n.classify(m) == m.getBewegungsart());
		}
	}
	
	
	
	
	@Test
	public void testTree_2() {
		TreeNode n = new TreeNode("Root", 0);
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = input.getLernDaten("treppe");
		ms.addAll(input.getLernDaten("gehen"));
		n.generateTree(ms);
		n.printTree();
		//Verify Trainingsmaterial
		for(Merkmal m: ms){
			System.out.println(n.classify(m) + " == " + m.getBewegungsart());
			assertTrue(n.classify(m) == m.getBewegungsart());
		}
	}

	@Test
	public void testTree_3() {
		TreeNode n = new TreeNode("Root", 0);
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = new ArrayList<Merkmal>();
		ms.add(input.getLernDaten("treppe").get(0));
		ms.add(input.getLernDaten("treppe").get(1));
		ms.add(input.getLernDaten("treppe").get(2));
		ms.add(input.getLernDaten("gehen").get(1));
		ms.add(input.getLernDaten("gehen").get(0));
		n.generateTree(ms);
		n.printTree();
		//Verify Trainingsmaterial
		for(Merkmal m: ms){
			System.out.println(n.classify(m) + " == " + m.getBewegungsart());
			assertTrue(n.classify(m) == m.getBewegungsart());
		}
	}
	
	@Test
	public void testTree_4() {
		TreeNode n = new TreeNode("Root", 0);
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = new ArrayList<Merkmal>();
		ms.add(input.getLernDaten("treppe").get(0));
		ms.add(input.getLernDaten("treppe").get(1));
		ms.addAll(input.getLernDaten("gehen"));
		n.generateTree(ms);
		n.printTree();
		//Verify Trainingsmaterial
		for(Merkmal m: ms){
			System.out.println(n.classify(m) + " == " + m.getBewegungsart());
			assertTrue(n.classify(m) == m.getBewegungsart());
		}
	}
	
	
	@Test
	public void testSplitAttribut_1() {
		TreeNode n = new TreeNode("Root", 0);
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = input.getLernDaten("treppe");
		ms.addAll(input.getLernDaten("gehen"));
		System.out.println(n.splitAttribut(ms));
	}
	
	@Test
	public void testSplitAttribut_2() {
		TreeNode n = new TreeNode("Root", 0);
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = new ArrayList<Merkmal>();
		ms.add(input.getLernDaten("treppe").get(0));;
		ms.add(input.getLernDaten("gehen").get(0));
		System.out.println(n.splitAttribut(ms));
		ArrayList<Merkmal>[] msa = n.splitArray(ms, n.splitAttribut(ms));
		assertTrue(msa[0].size() == 1);
		assertTrue(msa[1].size() == 1);
	}
	
	@Test
	public void testSplitAttribut_3() {
		TreeNode n = new TreeNode("Root", 0);
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = new ArrayList<Merkmal>();
		ms.add(input.getLernDaten("treppe").get(0));
		ms.add(input.getLernDaten("treppe").get(1));
		ms.add(input.getLernDaten("gehen").get(1));
		ms.add(input.getLernDaten("gehen").get(0));
		System.out.println(n.splitAttribut(ms));
		ArrayList<Merkmal>[] msa = n.splitArray(ms, n.splitAttribut(ms));
		assertTrue(msa[0].size() == 2);
		assertTrue(msa[1].size() == 2);
	}
	
	
	@Test
	public void testSplitAttribut_4() {
		TreeNode n = new TreeNode("Root", 0);
		InputHelper input = new InputHelper();
		ArrayList<Merkmal> ms = new ArrayList<Merkmal>();
		ms.add(input.getLernDaten("treppe").get(0));
		ms.add(input.getLernDaten("treppe").get(1));
		ms.add(input.getLernDaten("treppe").get(2));
		ms.add(input.getLernDaten("gehen").get(1));
		ms.add(input.getLernDaten("gehen").get(0));
		System.out.println(n.splitAttribut(ms));
		ArrayList<Merkmal>[] msa = n.splitArray(ms, n.splitAttribut(ms));
		assertTrue(msa[0].size() == 3);
		assertTrue(msa[1].size() == 2);
	}
	
}
