package adt.bst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.bst.BSTImpl;
import adt.bt.BTNode;

public class StudentBSTTest {

	private BSTImpl<Integer> tree;
	private BTNode<Integer> NIL = new BTNode<Integer>();

	private void fillTree() {
		Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
		for (int i : array) {
			tree.insert(i);
		}
	}

	@Before
	public void setUp() {
		tree = new BSTImpl<>();
	}

	@Test
	public void testInit() {
		assertTrue(tree.isEmpty());
		assertEquals(0, tree.size());
		assertEquals(-1, tree.height());

		assertEquals(NIL, tree.getRoot());

		assertArrayEquals(new Integer[] {}, tree.order());
		assertArrayEquals(new Integer[] {}, tree.preOrder());
		assertArrayEquals(new Integer[] {}, tree.postOrder());

		assertEquals(NIL, tree.search(12));
		assertEquals(NIL, tree.search(-23));
		assertEquals(NIL, tree.search(0));

		assertEquals(null, tree.minimum());
		assertEquals(null, tree.maximum());

		assertEquals(null, tree.sucessor(12));
		assertEquals(null, tree.sucessor(-23));
		assertEquals(null, tree.sucessor(0));

		assertEquals(null, tree.predecessor(12));
		assertEquals(null, tree.predecessor(-23));
		assertEquals(null, tree.predecessor(0));
	}

	@Test
	public void testMinMax() {
		
		tree.insert(6);
		assertEquals(Integer.valueOf(6), tree.minimum().getData());
		assertEquals(Integer.valueOf(6), tree.maximum().getData());

		tree.insert(23);
		assertEquals(Integer.valueOf(6), tree.minimum().getData());
		assertEquals(Integer.valueOf(23), tree.maximum().getData());

		tree.insert(-34);
		assertEquals(Integer.valueOf(-34), tree.minimum().getData());
		assertEquals(Integer.valueOf(23), tree.maximum().getData());

		tree.insert(5);
		assertEquals(Integer.valueOf(-34), tree.minimum().getData());
		assertEquals(Integer.valueOf(23), tree.maximum().getData());

		tree.insert(9);
		assertEquals(Integer.valueOf(-34), tree.minimum().getData());
		assertEquals(Integer.valueOf(23), tree.maximum().getData());
	}

	@Test
	public void testSucessorPredecessor() {

		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		assertEquals(null, tree.predecessor(-40));
		assertEquals(Integer.valueOf(-34), tree.sucessor(-40).getData());

		assertEquals(Integer.valueOf(-40), tree.predecessor(-34).getData());
		assertEquals(Integer.valueOf(0), tree.sucessor(-34).getData());

		assertEquals(Integer.valueOf(-34), tree.predecessor(0).getData());
		assertEquals(Integer.valueOf(2), tree.sucessor(0).getData());

		assertEquals(Integer.valueOf(0), tree.predecessor(2).getData());
		assertEquals(Integer.valueOf(5), tree.sucessor(2).getData());
		
		assertEquals(null, tree.sucessor(232));
	}

	@Test
	public void testSize() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		int size = 12;
		assertEquals(size, tree.size());
		
		while (!tree.isEmpty()) {
			tree.remove(tree.getRoot().getData());
			assertEquals(--size, tree.size());
		}
	}

	@Test
	public void testHeight() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		Integer[] preOrder = new Integer[] { 6, -34, -40, 5, 2, 0, 23, 9, 12,
				76, 67, 232 };
		assertArrayEquals(preOrder, tree.preOrder());
		assertEquals(4, tree.height());

		tree.remove(0);
		assertEquals(3, tree.height());

		tree.remove(2);
		assertEquals(3, tree.height());
	}

	@Test
	public void testRemove() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		Integer[] order = { -40, -34, 0, 2, 5, 6, 9, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		tree.remove(6);
		tree.remove(null);
		order = new Integer[] { -40, -34, 0, 2, 5, 9, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		tree.remove(9);
		tree.remove(null);
		order = new Integer[] { -40, -34, 0, 2, 5, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		assertEquals(NIL, tree.search(6));
		assertEquals(NIL, tree.search(9));

	}
	
	@Test
	public void testPreOrder() {
		Integer[] preOrder = new Integer[] {};
		assertArrayEquals(preOrder, tree.preOrder());
		
		fillTree();
		preOrder = new Integer[] {6, -34, -40, 5, 2, 0, 23, 9, 12, 76, 67, 232};
		assertArrayEquals(preOrder, tree.preOrder());
		
		tree.remove(6);
		preOrder = new Integer[] {9, -34, -40, 5, 2, 0, 23, 12, 76, 67, 232};
		assertArrayEquals(preOrder, tree.preOrder());
		
		tree.remove(9);
		preOrder = new Integer[] {12, -34, -40, 5, 2, 0, 23, 76, 67, 232};
		assertArrayEquals(preOrder, tree.preOrder());
		
		tree.remove(5);
		preOrder = new Integer[] {12, -34, -40, 2, 0, 23, 76, 67, 232};
		assertArrayEquals(preOrder, tree.preOrder());
		
		tree.remove(76);
		preOrder = new Integer[] {12, -34, -40, 2, 0, 23, 232, 67};
		assertArrayEquals(preOrder, tree.preOrder());
		
		tree.remove(-34);
		preOrder = new Integer[] {12, 0, -40, 2, 23, 232, 67};
		assertArrayEquals(preOrder, tree.preOrder());
		
		tree.insert(240);
		preOrder = new Integer[] {12, 0, -40, 2, 23, 232, 67, 240};
		assertArrayEquals(preOrder, tree.preOrder());
		
		tree.remove(232);
		preOrder = new Integer[] {12, 0, -40, 2, 23, 240, 67};
		assertArrayEquals(preOrder, tree.preOrder());
		
		tree.remove(12);
		preOrder = new Integer[] {23, 0, -40, 2, 240, 67};
		assertArrayEquals(preOrder, tree.preOrder());
		
		tree.remove(2);
		preOrder = new Integer[] {23, 0, -40, 240, 67};
		assertArrayEquals(preOrder, tree.preOrder());
		
		tree.remove(-40);
		preOrder = new Integer[] {23, 0, 240, 67};
		assertArrayEquals(preOrder, tree.preOrder());
		
		tree.remove(240);
		preOrder = new Integer[] {23, 0, 67};
		assertArrayEquals(preOrder, tree.preOrder());
		
		tree.remove(23);
		preOrder = new Integer[] {67, 0};
		assertArrayEquals(preOrder, tree.preOrder());
		
		tree.remove(67);
		preOrder = new Integer[] {0};
		assertArrayEquals(preOrder, tree.preOrder());
		
		tree.remove(0);
		preOrder = new Integer[] {};
		assertArrayEquals(preOrder, tree.preOrder());
	}
	
	@Test
	public void testOrder() {
		Integer[] order = new Integer[] {};
		assertArrayEquals(order, tree.order());
		
		fillTree();
		order = new Integer[] {-40, -34, 0, 2, 5, 6, 9, 12, 23, 67, 76, 232};
		assertArrayEquals(order, tree.order());
		
		tree.remove(6);
		order = new Integer[] {-40, -34, 0, 2, 5, 9, 12, 23, 67, 76, 232};
		assertArrayEquals(order, tree.order());
		
		tree.remove(9);
		order = new Integer[] {-40, -34, 0, 2, 5, 12, 23, 67, 76, 232};
		assertArrayEquals(order, tree.order());
		
		tree.remove(5);
		order = new Integer[] {-40, -34, 0, 2, 12, 23, 67, 76, 232};
		assertArrayEquals(order, tree.order());
		
		tree.remove(76);
		order = new Integer[] {-40, -34, 0, 2, 12, 23, 67, 232};
		assertArrayEquals(order, tree.order());
		
		tree.remove(-34);
		order = new Integer[] {-40, 0, 2, 12, 23, 67, 232};
		assertArrayEquals(order, tree.order());
		
		tree.insert(240);
		order = new Integer[] {-40, 0, 2, 12, 23, 67, 232, 240};
		assertArrayEquals(order, tree.order());
		
		tree.remove(232);
		order = new Integer[] {-40, 0, 2, 12, 23, 67, 240};
		assertArrayEquals(order, tree.order());
		
		tree.remove(12);
		order = new Integer[] {-40, 0, 2, 23, 67, 240};
		assertArrayEquals(order, tree.order());
		
		tree.remove(2);
		order = new Integer[] {-40, 0, 23, 67, 240};
		assertArrayEquals(order, tree.order());
		
		tree.remove(-40);
		order = new Integer[] {0, 23, 67, 240};
		assertArrayEquals(order, tree.order());
		
		tree.remove(240);
		order = new Integer[] {0, 23, 67};
		assertArrayEquals(order, tree.order());
		
		tree.remove(23);
		order = new Integer[] {0, 67};
		assertArrayEquals(order, tree.order());
		
		tree.remove(67);
		order = new Integer[] {0};
		assertArrayEquals(order, tree.order());
		
		tree.remove(0);
		order = new Integer[] {};
		assertArrayEquals(order, tree.order());
	}
	
	@Test
	public void testPostOrder() {
		Integer[] postOrder = new Integer[] {};
		assertArrayEquals(postOrder, tree.postOrder());
		
		fillTree();
		postOrder = new Integer[] {-40, 0, 2, 5, -34, 12, 9, 67, 232, 76, 23, 6};
		assertArrayEquals(postOrder, tree.postOrder());
		
		tree.remove(6);
		postOrder = new Integer[] {-40, 0, 2, 5, -34, 12, 67, 232, 76, 23, 9};
		assertArrayEquals(postOrder, tree.postOrder());
		
		tree.remove(9);
		postOrder = new Integer[] {-40, 0, 2, 5, -34, 67, 232, 76, 23, 12};
		assertArrayEquals(postOrder, tree.postOrder());
		
		tree.remove(5);
		postOrder = new Integer[] {-40, 0, 2, -34, 67, 232, 76, 23, 12};
		assertArrayEquals(postOrder, tree.postOrder());
		
		tree.remove(76);
		postOrder = new Integer[] {-40, 0, 2, -34, 67, 232, 23, 12};
		assertArrayEquals(postOrder, tree.postOrder());
		
		tree.remove(-34);
		postOrder = new Integer[] {-40, 2, 0, 67, 232, 23, 12};
		assertArrayEquals(postOrder, tree.postOrder());
		
		tree.insert(240);
		postOrder = new Integer[] {-40, 2, 0, 67, 240, 232, 23, 12};
		assertArrayEquals(postOrder, tree.postOrder());
		
		tree.remove(232);
		postOrder = new Integer[] {-40, 2, 0, 67, 240, 23, 12};
		assertArrayEquals(postOrder, tree.postOrder());
		
		tree.remove(12);
		postOrder = new Integer[] {-40, 2, 0, 67, 240, 23};
		assertArrayEquals(postOrder, tree.postOrder());
		
		tree.remove(2);
		postOrder = new Integer[] {-40, 0, 67, 240, 23};
		assertArrayEquals(postOrder, tree.postOrder());
		
		tree.remove(-40);
		postOrder = new Integer[] {0, 67, 240, 23};
		assertArrayEquals(postOrder, tree.postOrder());
		
		tree.remove(240);
		postOrder = new Integer[] {0, 67, 23};
		assertArrayEquals(postOrder, tree.postOrder());
		
		tree.remove(23);
		postOrder = new Integer[] {0, 67};
		assertArrayEquals(postOrder, tree.postOrder());
		
		tree.remove(67);
		postOrder = new Integer[] {0};
		assertArrayEquals(postOrder, tree.postOrder());
		
		tree.remove(0);
		postOrder = new Integer[] {};
		assertArrayEquals(postOrder, tree.postOrder());
	}

	@Test
	public void testSearch() {

		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		assertEquals(Integer.valueOf(-40), tree.search(-40).getData());
		assertEquals(Integer.valueOf(-34), tree.search(-34).getData());
		assertEquals(NIL, tree.search(2534));
		assertEquals(NIL, tree.search(null));
	}
}
