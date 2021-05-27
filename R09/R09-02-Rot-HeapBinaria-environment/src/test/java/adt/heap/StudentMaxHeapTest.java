package adt.heap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

public class StudentMaxHeapTest {

	Heap<Integer> heap;

	@Before
	public void setUp() {
		// TODO Instancie seu comparator para fazer sua estrutura funcionar como
		// uma max heap aqui. Use instanciacao anonima da interface
		// Comparator!!!!
		Comparator<Integer> comparator = (o1,o2) -> o1 - o2;
		heap = new HeapImpl<Integer>(comparator);
	}

	@Test
	public void testBuild() {
		heap.buildHeap(new Integer[] { 82, 6, 99, 12, 34, 64, 58, 1 });

		assertEquals(8, heap.size());
		assertFalse(heap.isEmpty());
		assertNotNull(heap.rootElement());
		//verifyHeap(new Integer[] { 99, 12, 82, 6, 34, 64, 58, 1 });
		verifyHeap(new Integer[] { 99, 34, 82, 6, 12, 64, 58, 1 });
		
		heap.buildHeap(new Integer[] { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 });

		assertEquals(12, heap.size());
		assertFalse(heap.isEmpty());
		assertNotNull(heap.rootElement());
		
		verifyHeap(new Integer[] { 232, 76, 2, 12, 67, -34, 0, 5, 6, 23, 9, -40 });
	}

	@Test
	public void testInsert() {
		heap.insert(8);
		heap.insert(12);
		heap.insert(-2);
		heap.insert(7);
		heap.insert(8);
		heap.insert(-5);
		heap.insert(14);
		heap.insert(3);
		heap.insert(-10);
		heap.insert(0);
		
		assertEquals(10, heap.size());
		assertFalse(heap.isEmpty());
		assertNotNull(heap.rootElement());
		verifyHeap(new Integer[] { 14, 8, 12, 7, 8, -5, -2, 3, -10, 0 });
	}

	@Test
	public void testRemove() {
		heap.insert(22);
		heap.insert(45);
		heap.insert(38);
		heap.insert(17);
		heap.insert(40);
		heap.insert(15);
		heap.insert(26);
		heap.insert(79);
		heap.insert(53);
		heap.insert(30);

		assertEquals(Integer.valueOf(79), heap.extractRootElement());
		assertEquals(Integer.valueOf(53), heap.extractRootElement());
		assertEquals(Integer.valueOf(45), heap.extractRootElement());
		assertEquals(Integer.valueOf(40), heap.extractRootElement());
		assertEquals(Integer.valueOf(38), heap.extractRootElement());

		assertEquals(5, heap.size());
		assertFalse(heap.isEmpty());
		assertNotNull(heap.rootElement());

		verifyHeap(new Integer[] { 22, 17, 15, 26, 30 });
		
		heap.extractRootElement();
		heap.extractRootElement();
		heap.extractRootElement();
		heap.extractRootElement();
		heap.extractRootElement();
		
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());
		assertNull(heap.extractRootElement());
		assertNull(heap.rootElement());
		
		verifyHeap(new Integer[] { });
	}

	@Test
	public void testSort() {
		//-----------------------------------------------------------------------------
		assertArrayEquals(new Integer[] { 5, 6, 12, 20, 34, 43, 49, 92 },
		heap.heapsort(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 }));

		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());
		assertNull(heap.extractRootElement());
		assertNull(heap.rootElement());

		assertArrayEquals(new Integer[] {}, heap.toArray());
		//-----------------------------------------------------------------------------
		
		//-----------------------------------------------------------------------------
		assertArrayEquals(new Integer[] { -40, -34, 0, 2, 5, 6, 9, 12, 23, 67, 76, 232 },
		heap.heapsort(new Integer[] { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 }));

		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());
		assertNull(heap.extractRootElement());
		assertNull(heap.rootElement());

		assertArrayEquals(new Integer[] {}, heap.toArray());
		//-----------------------------------------------------------------------------
	}

	private void verifyHeap(Integer[] expected) {
		boolean isHeap = true;

		Comparable<Integer>[] original = heap.toArray();

		Arrays.sort(expected);
		Arrays.sort(original);

		if (Arrays.equals(expected, original) == false)
			isHeap = false;

		original = heap.toArray();

		for (int i = 0; i < original.length; i++) {
			if (2 * i + 1 < original.length && original[i].compareTo((Integer) original[2 * i + 1]) < 0)
				isHeap = false;
			if (2 * i + 2 < original.length && original[i].compareTo((Integer) original[2 * i + 2]) < 0)
				isHeap = false;
		}

		assertTrue(isHeap);
	}

}
