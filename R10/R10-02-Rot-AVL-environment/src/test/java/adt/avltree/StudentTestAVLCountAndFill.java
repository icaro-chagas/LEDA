package adt.avltree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;


public class StudentTestAVLCountAndFill {

	protected AVLCountAndFill<Integer> tree1;
	protected AVLCountAndFill<Integer> tree2;
	protected AVLCountAndFill<Integer> tree3;
	
	AVLTreeVerifierImpl<Integer> teste;
	protected static int SIZE = 10;

	@Before
	public void setUp() throws Exception {
		tree1 = new AVLCountAndFillImpl<Integer>();
		tree2 = new AVLCountAndFillImpl<Integer>();
		for (int i = 0; i < SIZE; i++) {
			tree1.insert(i);
			tree2.insert(SIZE - i);
		}
		tree3 = new AVLCountAndFillImpl<Integer>();
		Integer[] data = { 8, 4, 6, 12, 10 };
		for (Integer integer : data) {
			tree3.insert(integer);
		}
	}

	@Test
	public void testLLcount() {
		assertEquals(0, tree1.LLcount());
		assertEquals(6, tree2.LLcount());
		assertEquals(0, tree3.LLcount());
	}

	@Test
	public void testRRcount() {
		assertEquals(6, tree1.RRcount());
		assertEquals(0, tree2.RRcount());
		assertEquals(0, tree3.RRcount());
	}

	@Test
	public void testLRcount() {
		assertEquals(0, tree1.LRcount());
		assertEquals(0, tree2.LRcount());
		assertEquals(1, tree3.LRcount());
	}

	@Test
	public void testRLcount() {
		assertEquals(0, tree1.RLcount());
		assertEquals(0, tree2.RLcount());
		assertEquals(1, tree3.RLcount());
	}

	@Test
	public void testFillWithoutRebalance() {
		tree1 = new AVLCountAndFillImpl<Integer>();
		Integer[] keys = {15,14,13,12,11,10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
		//Integer[] keys = { 8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15 };
		//Integer[] keys = { 22, 5, 3, 21, 2, 1, 20, 47, 49, 52, 54, 58, 60, 64, 66, 68, 70, 74, 76, 80, 88 };
		tree1.fillWithoutRebalance(keys);
		assertEquals(0, tree1.LLcount());
		assertEquals(0, tree1.RRcount());
		assertEquals(0, tree1.LRcount());
		assertEquals(0, tree1.RLcount());
		
		teste = new AVLTreeVerifierImpl<>(tree1);
		
		//System.out.println(tree1.getRoot().getRight().getRight().getLeft());
		
		assertTrue(teste.isAVLTree());
		
		System.out.println(Arrays.toString(tree1.preOrder()));
		
		/*System.out.println(Arrays.toString(tree1.postOrder()));
		System.out.println();
		
		BSTNode<Integer> node = (BSTNode<Integer>) tree1.search(76);
		System.out.println("Node: " + node.getData());
		System.out.println("Node.Parent: " + node.getParent());
		System.out.println("Node.Left: " + node.getLeft());
		System.out.println("Node.Right: " + node.getRight());*/
		
	}
}
