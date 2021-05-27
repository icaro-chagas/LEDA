package adt.hashtable.open;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;

public class StudentTestHashtableOpenAddressQuadraticProbing {
	protected AbstractHashtableOpenAddress<HashtableElement> table1;
	protected AbstractHashtableOpenAddress<HashtableElement> table2;

	protected final int PROPOSED_SIZE = 10;

	@Before
	public void setUp() throws Exception {
		table1 = new HashtableOpenAddressQuadraticProbingImpl<HashtableElement>(PROPOSED_SIZE,
				HashFunctionClosedAddressMethod.DIVISION, 3, 5);
		// o tamanho real utilizado vai ser PROPOSED_SIZE
		table1.insert(new HashtableElement(10)); // coloca no slot indexado com
													// 0
		table1.insert(new HashtableElement(15)); // coloca no slot indexado com
													// 5
		table1.insert(new HashtableElement(2)); // coloca no slot indexado com 2
		table1.insert(new HashtableElement(12)); // coloca no slot indexado com
													// 8, teve 2 colisoes
		table1.insert(new HashtableElement(4)); // coloca no slot indexado com 4
		table1.insert(new HashtableElement(8)); // coloca no slot indexado com
												// 6, teve 1 colisao

		table2 = new HashtableOpenAddressQuadraticProbingImpl<HashtableElement>(PROPOSED_SIZE,
				HashFunctionClosedAddressMethod.DIVISION, 3, 5);
	}

	@Test
	public void testInsert() {
		assertEquals(3, table1.getCOLLISIONS());
		table1.insert(new HashtableElement(11)); // nao tem colisao. coloca no
													// slot indexado com 1
		assertEquals(3, table1.getCOLLISIONS());
		assertEquals(1, table1.indexOf(new HashtableElement(11)));

		table1.insert(new HashtableElement(21)); // tem 1 colisao. coloca no
													// slot indexado com 9
		assertEquals(4, table1.getCOLLISIONS());
		assertEquals(9, table1.indexOf(new HashtableElement(21)));

		table1.insert(new HashtableElement(21));
		assertEquals(4, table1.getCOLLISIONS());
		assertEquals(9, table1.indexOf(new HashtableElement(21)));

		table1.insert(null);
		assertEquals(4, table1.getCOLLISIONS());

		table2.insert(new HashtableElement(14));
		assertEquals(4, table2.indexOf(new HashtableElement(14)));
		assertEquals(0, table2.getCOLLISIONS());

		table2.insert(new HashtableElement(14));
		assertEquals(4, table2.indexOf(new HashtableElement(14)));
		assertEquals(0, table2.getCOLLISIONS());

		table2.insert(null);

	}

	@Test(expected = HashtableOverflowException.class)
	public void testInsertComErro() {
		table1.insert(new HashtableElement(11));
		table1.insert(new HashtableElement(21));
		table1.insert(new HashtableElement(3));
		table1.insert(new HashtableElement(7));

		table1.insert(new HashtableElement(9));
	}

	@Test
	public void testRemove() {
		table1.remove(new HashtableElement(17)); // elemento inexistente
		table1.remove(null);
		assertEquals(6, table1.size());

		table1.remove(new HashtableElement(12)); // elemento existente
		table1.remove(null);
		assertEquals(5, table1.size());
		assertNull(table1.search(new HashtableElement(12)));

		table2.remove(new HashtableElement(12));
		table2.remove(null);

		assertEquals(0, table2.size());
	}

	@Test
	public void testSearch() {
		assertEquals(new HashtableElement(10), table1.search(new HashtableElement(10)));
		assertEquals(new HashtableElement(15), table1.search(new HashtableElement(15)));
		assertEquals(new HashtableElement(2), table1.search(new HashtableElement(2)));
		assertEquals(new HashtableElement(12), table1.search(new HashtableElement(12)));
		assertEquals(new HashtableElement(4), table1.search(new HashtableElement(4)));
		assertEquals(new HashtableElement(8), table1.search(new HashtableElement(8)));

		assertNull(table1.search(new HashtableElement(0)));
		assertNull(table1.search(new HashtableElement(1)));
		assertNull(table1.search(new HashtableElement(6)));
		assertNull(table1.search(new HashtableElement(7)));

		assertNull(table2.search(new HashtableElement(13)));
		assertNull(table2.search(new HashtableElement(15)));
		assertNull(table2.search(new HashtableElement(16)));

		assertNull(table1.search(null));
		assertNull(table2.search(null));
	}

	@Test
	public void testIndexOf() {

		table1.insert(new HashtableElement(12));

		assertEquals(table1.indexOf(new HashtableElement(10)), 0);
		assertEquals(table1.indexOf(new HashtableElement(15)), 5);
		assertEquals(table1.indexOf(new HashtableElement(2)), 2);
		assertEquals(table1.indexOf(new HashtableElement(12)), 8);
		assertEquals(table1.indexOf(new HashtableElement(4)), 4);
		assertEquals(table1.indexOf(new HashtableElement(8)), 6);

		assertEquals(table1.indexOf(new HashtableElement(0)), -1);
		assertEquals(table1.indexOf(new HashtableElement(6)), -1);
		assertEquals(table1.indexOf(new HashtableElement(7)), -1);
		assertEquals(table1.indexOf(null), -1);

		assertEquals(table2.indexOf(new HashtableElement(2)), -1);
		assertEquals(table2.indexOf(new HashtableElement(13)), -1);
		assertEquals(table2.indexOf(null), -1);
	}

	@Test
	public void testIsEmpty() {
		assertFalse(table1.isEmpty());
		table1.remove(new HashtableElement(15)); // esvazia
		table1.remove(new HashtableElement(8));
		table1.remove(new HashtableElement(12));
		table1.remove(new HashtableElement(2));
		table1.remove(new HashtableElement(10));
		table1.remove(new HashtableElement(4));
		assertTrue(table1.isEmpty());

		assertTrue(table2.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(table1.isFull());
		table1.insert(new HashtableElement(1)); // enche a tabela
		table1.insert(new HashtableElement(23));
		table1.insert(new HashtableElement(37));
		table1.insert(new HashtableElement(49));
		assertTrue(table1.isFull());

		assertFalse(table2.isFull());
	}

	@Test
	public void testSize() {
		table1.insert(new HashtableElement(2));
		assertEquals(6, table1.size());

		table1.insert(null);
		assertEquals(6, table1.size());

		table1.insert(new HashtableElement(1)); // enche a tabela
		table1.insert(new HashtableElement(23));
		table1.insert(new HashtableElement(37));
		table1.insert(new HashtableElement(49));

		assertEquals(10, table1.size());

		table1.remove(new HashtableElement(1));
		table1.remove(new HashtableElement(23));
		table1.remove(new HashtableElement(37));
		table1.remove(new HashtableElement(49));

		table1.remove(new HashtableElement(10)); 
		table1.remove(new HashtableElement(15)); 
		table1.remove(new HashtableElement(2));
		table1.remove(new HashtableElement(12));
		table1.remove(new HashtableElement(4));
		table1.remove(new HashtableElement(8));
		
		assertEquals(0, table1.size());
		
		table1.insert(null);
		assertEquals(0, table2.size());
	}

}
