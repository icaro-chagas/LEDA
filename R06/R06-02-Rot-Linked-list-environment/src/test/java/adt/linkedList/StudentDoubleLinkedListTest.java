package adt.linkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentDoubleLinkedListTest extends StudentLinkedListTest {

	private DoubleLinkedList<Integer> lista3;
	

	@Before
	public void setUp() throws Exception {

		getImplementations();

		// Lista com 3 elementos.
		lista1.insert(3);
		//System.out.println(Arrays.toString(lista1.toArray()));
		lista1.insert(2);
		//System.out.println(Arrays.toString(lista1.toArray()));
		lista1.insert(1);
		//System.out.println(Arrays.toString(lista1.toArray()));

		// Lista com 1 elemento.
		lista3.insert(1);
	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
//		lista1 = new DoubleLinkedListImpl<Integer>();
//		lista2 = new DoubleLinkedListImpl<Integer>();
//		lista3 = new DoubleLinkedListImpl<Integer>();
		
		lista1 = new RecursiveDoubleLinkedListImpl<Integer>();
		lista2 = new RecursiveDoubleLinkedListImpl<Integer>();
		lista3 = new RecursiveDoubleLinkedListImpl<Integer>();
		
		
	}

	// Métodos de DoubleLinkedList

	@Test
	public void testInsertFirst() {
		((DoubleLinkedList<Integer>) lista1).insertFirst(4);
		Assert.assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, lista1.toArray());
		
		((DoubleLinkedList<Integer>) lista1).insertFirst(null);
		Assert.assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, lista1.toArray());
		
		((DoubleLinkedList<Integer>) lista1).insertFirst(37);
		Assert.assertArrayEquals(new Integer[] { 37, 4, 3, 2, 1 }, lista1.toArray());
		
		((DoubleLinkedList<Integer>) lista2).insertFirst(13);
		Assert.assertArrayEquals(new Integer[] { 13 }, lista2.toArray());
		
		//-----------------------------------------------------------------------------
		((DoubleLinkedList<Integer>) lista3).remove(13);
		((DoubleLinkedList<Integer>) lista1).insertFirst(18);
		//-----------------------------------------------------------------------------
		
	}

	@Test
	public void testRemoveFirst() {
		((DoubleLinkedList<Integer>) lista3).removeFirst();
		Assert.assertArrayEquals(new Integer[] { }, lista3.toArray());
		
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] { 2, 1 }, lista1.toArray());
		
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] { 1 }, lista1.toArray());
		
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] { }, lista1.toArray());
		
		((DoubleLinkedList<Integer>) lista2).removeFirst();
		Assert.assertArrayEquals(new Integer[] { }, lista2.toArray());
	}

	@Test
	public void testRemoveLast() {
		((DoubleLinkedList<Integer>) lista3).removeFirst();
		Assert.assertArrayEquals(new Integer[] { }, lista3.toArray());
		
		((DoubleLinkedList<Integer>) lista1).removeLast();
		Assert.assertArrayEquals(new Integer[] { 3, 2 }, lista1.toArray());
		
		((DoubleLinkedList<Integer>) lista1).removeLast();
		
		Assert.assertArrayEquals(new Integer[] { 3 }, lista1.toArray());
		
		((DoubleLinkedList<Integer>) lista1).removeLast();
		
		Assert.assertArrayEquals(new Integer[] { }, lista1.toArray());
		
		((DoubleLinkedList<Integer>) lista2).removeFirst();
		Assert.assertArrayEquals(new Integer[] { }, lista2.toArray());
	}
}