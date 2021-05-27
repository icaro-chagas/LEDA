package adt.linkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentLinkedListTest {

	protected LinkedList<Integer> lista1;
	protected LinkedList<Integer> lista2;

	@Before
	public void setUp() throws Exception {

		getImplementations();

		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);

	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		lista1 = new SingleLinkedListImpl<Integer>();
		lista2 = new SingleLinkedListImpl<Integer>();
		
//		lista1 = new RecursiveSingleLinkedListImpl<Integer>();
//		lista2 = new RecursiveSingleLinkedListImpl<Integer>();
	}

	
	/*@Test
	public void meuTeste() {
		lista1.insert(2);
		System.out.println(((SingleLinkedListImpl)lista1).getHead());
		lista1.insert(30);
		System.out.println(((SingleLinkedListImpl)lista1).getHead());
		System.out.println(((SingleLinkedListImpl)lista1).size());
	}*/
	
	@Test
	public void testIsEmpty() {
		Assert.assertFalse(lista1.isEmpty());
		lista1.remove(2);
		lista1.remove(1);
		lista1.remove(3);
		
		Assert.assertTrue(lista1.isEmpty());
		
		Assert.assertTrue(lista2.isEmpty());
	}

	@Test
	public void testSize() {
		Assert.assertEquals(3, lista1.size());
		
		lista1.insert(49);
		Assert.assertEquals(4, lista1.size());
		
		lista1.remove(3);
		Assert.assertEquals(3, lista1.size());
		
		lista1.remove(49);
		Assert.assertEquals(2, lista1.size());
		
		lista1.remove(1);
		Assert.assertEquals(1, lista1.size());
		
		lista1.remove(2);
		
		Assert.assertEquals(0, lista1.size());
		Assert.assertEquals(0, lista2.size());
	}

	@Test
	public void testSearch() {
		Assert.assertTrue(2 == lista1.search(2));
		Assert.assertTrue(1 == lista1.search(1));
		Assert.assertTrue(3 == lista1.search(3));
		Assert.assertNull(lista1.search(4));
		
		Assert.assertFalse(3 == lista1.search(2));
		Assert.assertNull(lista2.search(34));
	}

	@Test
	public void testInsert() {
		
		Assert.assertArrayEquals(new Integer[] {3, 2, 1}, lista1.toArray());
		lista1.insert(null);
		Assert.assertArrayEquals(new Integer[] {3, 2, 1}, lista1.toArray());
		
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
		lista2.insert(null);
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
		
		Assert.assertEquals(3, lista1.size());
		lista1.insert(5);
		lista1.insert(7);
		Assert.assertEquals(5, lista1.size());

		Assert.assertEquals(0, lista2.size());
		lista2.insert(4);
		lista2.insert(7);
		Assert.assertEquals(2, lista2.size());
	}

	@Test
	public void testRemove() {
		
		lista1.insert(12);
		lista1.insert(19);
		
		Assert.assertArrayEquals(new Integer[] {3, 2, 1, 12, 19}, lista1.toArray());
		lista1.remove(30);
		lista1.remove(22);
		lista1.remove(null);
		Assert.assertArrayEquals(new Integer[] {3, 2, 1, 12, 19}, lista1.toArray());
		
		lista1.remove(12);
		lista1.remove(19);
		
		Assert.assertEquals(3, lista1.size());
		lista1.remove(2);
		lista1.remove(1);
		Assert.assertEquals(1, lista1.size());
		
		lista2.remove(3);
		Assert.assertArrayEquals(new Integer[] { }, lista2.toArray());
		
		lista2.remove(null);
		Assert.assertArrayEquals(new Integer[] { }, lista2.toArray());
	}

	@Test
	public void testToArray() {
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
		Assert.assertArrayEquals(new Integer[] { 3, 2, 1 }, lista1.toArray());
	}
}