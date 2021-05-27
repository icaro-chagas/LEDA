package adt.hashtable.open;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;

public class StudentTestHashtableOpenAddressLinearProbing {

	protected AbstractHashtableOpenAddress<HashtableElement> table1;
	protected AbstractHashtableOpenAddress<HashtableElement> table2;

	protected final int PROPOSED_SIZE = 10;

	@Before
	public void setUp() throws Exception {
		table1 = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(
				PROPOSED_SIZE, HashFunctionClosedAddressMethod.DIVISION);
		// o tamanho real utilizado vai ser PROPOSED_SIZE
		table1.insert(new HashtableElement(2)); // coloca no slot indexado com 2
		table1.insert(new HashtableElement(3)); // coloca no slot indexado com 3
		table1.insert(new HashtableElement(4)); // coloca no slot indexado com 4
		table1.insert(new HashtableElement(5)); // coloca no slot indexado com 5

		table2 = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(
				PROPOSED_SIZE, HashFunctionClosedAddressMethod.DIVISION);
	}

	@Test
	public void testInsert() {
		assertEquals(0, table1.getCOLLISIONS());
		table1.insert(new HashtableElement(7)); // nao produz colisao. coloca no
												// slot indexado com 7
		assertEquals(7, table1.indexOf(new HashtableElement(7)));
		assertEquals(0, table1.getCOLLISIONS());

		table1.insert(new HashtableElement(9)); // nao produz colisao. coloca no
												// slot indexado com 9
		assertEquals(9, table1.indexOf(new HashtableElement(9)));
		assertEquals(0, table1.getCOLLISIONS());

		table1.insert(new HashtableElement(12)); // produz colisao com o 2.
													// coloca no slot indexado
													// com 6 (prox disponivel)
		assertEquals(6, table1.indexOf(new HashtableElement(12)));
		assertEquals(4, table1.getCOLLISIONS());
		
		table1.insert(new HashtableElement(12)); 
		assertEquals(6, table1.indexOf(new HashtableElement(12)));
		assertEquals(4, table1.getCOLLISIONS());
		
		table1.insert(null); 
		assertEquals(4, table1.getCOLLISIONS());

		table2.insert(new HashtableElement(14)); // nao produz colisao. coloca
													// no slot indexado com 4
		assertEquals(4, table2.indexOf(new HashtableElement(14)));
		assertEquals(0, table2.getCOLLISIONS());
		
		table2.insert(new HashtableElement(14));
		assertEquals(4, table2.indexOf(new HashtableElement(14)));
		assertEquals(0, table2.getCOLLISIONS());
		
		table2.insert(null);

	}
	
	@Test(expected = HashtableOverflowException.class)
	public void testInsertComErro() {
		table1.insert(new HashtableElement(1)); // enche a tabela
		table1.insert(new HashtableElement(6));
		table1.insert(new HashtableElement(7));
		table1.insert(new HashtableElement(8));
		table1.insert(new HashtableElement(9));
		table1.insert(new HashtableElement(10));
		
		table1.insert(new HashtableElement(0));
	}

	@Test
	public void testRemove() {
		table1.remove(new HashtableElement(12)); // elemento inexistente
		table1.remove(null);
		assertEquals(4, table1.size());

		table1.remove(new HashtableElement(5)); // elemento existente
		table1.remove(null);
		assertEquals(3, table1.size());
		
		table2.remove(new HashtableElement(12));
		table2.remove(null);
		
		assertEquals(0, table2.size());	
	}

	@Test
	public void testSearch() {
		assertEquals(new HashtableElement(5), table1.search(new HashtableElement(5))); 
		assertEquals(new HashtableElement(4), table1.search(new HashtableElement(4)));
		assertEquals(new HashtableElement(3), table1.search(new HashtableElement(3))); 
		assertEquals(new HashtableElement(2), table1.search(new HashtableElement(2))); 
		
		assertNull(table1.search(new HashtableElement(0))); 
		assertNull(table1.search(new HashtableElement(1))); 
		assertNull(table1.search(new HashtableElement(6)));
		assertNull(table1.search(new HashtableElement(7)));
		assertNull(table1.search(new HashtableElement(15))); 
		
		assertNull(table2.search(new HashtableElement(13))); 
		assertNull(table2.search(new HashtableElement(15))); 
		assertNull(table2.search(new HashtableElement(16))); 
		
		assertNull(table1.search(null)); 
		assertNull(table2.search(null));
	}
	
	@Test
	public void testIndexOf() {
		
		table1.insert(new HashtableElement(12)); 
		
		assertEquals(table1.indexOf(new HashtableElement(2)), 2);
		assertEquals(table1.indexOf(new HashtableElement(3)), 3);
		assertEquals(table1.indexOf(new HashtableElement(4)), 4);
		assertEquals(table1.indexOf(new HashtableElement(5)), 5);
		assertEquals(table1.indexOf(new HashtableElement(12)), 6);
		
		assertEquals(table1.indexOf(new HashtableElement(0)), -1);
		assertEquals(table1.indexOf(new HashtableElement(6)), -1);
		assertEquals(table1.indexOf(new HashtableElement(15)), -1);
		assertEquals(table1.indexOf(null), -1);
		
		assertEquals(table2.indexOf(new HashtableElement(2)), -1);
		assertEquals(table2.indexOf(new HashtableElement(13)), -1);
		assertEquals(table2.indexOf(null), -1);
	}

	@Test
	public void testIsEmpty() {
		assertFalse(table1.isEmpty());
		table1.remove(new HashtableElement(2)); // esvazia
		table1.remove(new HashtableElement(3));
		table1.remove(new HashtableElement(4));
		table1.remove(new HashtableElement(5));
		assertTrue(table1.isEmpty());

		assertTrue(table2.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(table1.isFull());
		table1.insert(new HashtableElement(1)); // enche a tabela
		table1.insert(new HashtableElement(6));
		table1.insert(new HashtableElement(7));
		table1.insert(new HashtableElement(8));
		table1.insert(new HashtableElement(9));
		table1.insert(new HashtableElement(10));
		assertTrue(table1.isFull());

		assertFalse(table2.isFull());
	}

	@Test
	public void testSize() {
		table1.insert(new HashtableElement(2));
		assertEquals(4, table1.size());
		
		table1.insert(null);
		assertEquals(4, table1.size());
		
		table1.insert(new HashtableElement(12));
		assertEquals(5, table1.size());
		
		table1.insert(new HashtableElement(1));
		table1.insert(new HashtableElement(7));
		table1.insert(new HashtableElement(8));
		table1.insert(new HashtableElement(9));
		table1.insert(new HashtableElement(10));
		
		assertEquals(10,table1.size());
		
		table1.remove(new HashtableElement(2)); 
		table1.remove(new HashtableElement(3));
		table1.remove(new HashtableElement(4));
		table1.remove(new HashtableElement(5));
		table1.remove(new HashtableElement(12));
		
		table1.remove(new HashtableElement(1));
		table1.remove(new HashtableElement(7));
		table1.remove(new HashtableElement(8));
		table1.remove(new HashtableElement(9));
		table1.remove(new HashtableElement(10));
		
		assertEquals(0,table1.size());
		
		assertEquals(0,table2.size());
	}

}
