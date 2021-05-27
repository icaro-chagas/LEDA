package adt.bst;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class TestSimpleBSTManipulationImpl {

	private BSTImpl<Integer> tree1;
	private BSTImpl<Integer> tree2;
	private SimpleBSTManipulationImpl<Integer> bstManipulation;
	
	
	@Before
	public void setUp() throws Exception {
		bstManipulation = new SimpleBSTManipulationImpl<>();
		tree1 = new BSTImpl<>();
		tree2 = new BSTImpl<>();
	}

	@Test
	public void testEqualsBST() {
		Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
		assertTrue(bstManipulation.equals(tree1, tree2));
		for (int i : array) {
			tree1.insert(i);
			tree2.insert(i);
			assertTrue(bstManipulation.equals(tree1, tree2));
		}
		tree1.insert(-80);
		assertFalse(bstManipulation.equals(tree1, tree2));
		
		tree1 = new BSTImpl<>();
		tree2 = new BSTImpl<>();
		Integer[] array2 = {150, 200, 95, 132, 173, 121, 104, 245, 197, 203, 260, 78};
		for (int i = 0; i < array.length; i++) {
			tree1.insert(array[i]);
			tree2.insert(array2[i]);
			assertFalse(bstManipulation.equals(tree1, tree2));
		}
		
		tree1 = null;
		tree2 = new BSTImpl<>();
		assertFalse(bstManipulation.equals(tree1, tree2));
		
		tree1 = new BSTImpl<>();
		tree2 = null;
		assertFalse(bstManipulation.equals(tree1, tree2));
		
		tree1 = null;
		tree2 = null;
		assertFalse(bstManipulation.equals(tree1, tree2));
		
	}

	@Test
	public void testIsSimilar() {
		Integer[] array = {6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40};
		Integer[] array2 = {150, 200, 95, 132, 173, 121, 104, 245, 197, 203, 260, 78};	
		
		assertTrue(bstManipulation.isSimilar(tree1, tree2));
		for (int i = 0; i < array.length; i++) {
			tree1.insert(array[i]);
			tree2.insert(array2[i]);
			assertTrue(bstManipulation.isSimilar(tree1, tree2));
		}
		
		tree1 = new BSTImpl<>();
		tree2 = new BSTImpl<>();
		for (int i : array) {
			tree1.insert(i);
			tree2.insert(i);
			assertTrue(bstManipulation.isSimilar(tree1, tree2));
		}
		
		tree1.insert(-80);
		assertFalse(bstManipulation.isSimilar(tree1, tree2));
		
		tree1 = null;
		tree2 = new BSTImpl<>();
		assertFalse(bstManipulation.isSimilar(tree1, tree2));
		
		tree1 = new BSTImpl<>();
		tree2 = null;
		assertFalse(bstManipulation.isSimilar(tree1, tree2));
		
		tree1 = null;
		tree2 = null;
		assertFalse(bstManipulation.isSimilar(tree1, tree2));
	}

	@Test
	public void testOrderStatistic() {
		Integer[] array = {6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40};
		Integer[] array2 = {150, 200, 95, 132, 173, 121, 104, 245, 197, 203, 260, 78};
		for (int i=0; i < array.length; i++) {
			tree1.insert(array[i]);
			tree2.insert(array2[i]);
		}
		
		Arrays.sort(array);
		Arrays.sort(array2);
		for (int i=1; i <= array.length; i++) {
			assertEquals(bstManipulation.orderStatistic(tree1, i), array[i-1]);
			assertEquals(bstManipulation.orderStatistic(tree2, i), array2[i-1]);
		}
		assertNull(bstManipulation.orderStatistic(tree1, 0));
		assertNull(bstManipulation.orderStatistic(tree1, -1));
		assertNull(bstManipulation.orderStatistic(tree1, array.length + 1));
		
		assertNull(bstManipulation.orderStatistic(tree2, 0));
		assertNull(bstManipulation.orderStatistic(tree2, -1));
		assertNull(bstManipulation.orderStatistic(tree2, array2.length + 1));
		
	}

}
