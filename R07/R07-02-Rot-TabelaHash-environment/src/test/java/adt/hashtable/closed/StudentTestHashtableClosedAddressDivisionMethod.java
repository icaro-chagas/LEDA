package adt.hashtable.closed;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import adt.hashtable.closed.AbstractHashtableClosedAddress;
import adt.hashtable.closed.HashtableClosedAddressImpl;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;

public class StudentTestHashtableClosedAddressDivisionMethod {

	protected AbstractHashtableClosedAddress<Integer> table1;
	protected AbstractHashtableClosedAddress<Integer> table2;
	protected AbstractHashtableClosedAddress<Integer> table3;

	protected final int PROPOSED_SIZE = 100;

	@Before
	public void setUp() throws Exception {
		table1 = new HashtableClosedAddressImpl<Integer>(PROPOSED_SIZE,
				HashFunctionClosedAddressMethod.DIVISION);

		Integer initialValue = 200;
		int increment = 5;
		while (initialValue < 600) {
			table1.insert(initialValue);
			initialValue = initialValue + increment;
		}

		table2 = new HashtableClosedAddressImpl<Integer>(PROPOSED_SIZE,
				HashFunctionClosedAddressMethod.DIVISION);
		
		table3 = new HashtableClosedAddressImpl<Integer>(50,
				HashFunctionClosedAddressMethod.DIVISION);
		
		initialValue = 200;
		while (initialValue < 600) {
			table3.insert(initialValue);
			initialValue = initialValue + increment;
		}
	}

	@Test
	public void testInsert() {
		assertEquals(0, table1.getCOLLISIONS());
		table1.insert(105); // nao produz colisao
		assertEquals(0, table1.getCOLLISIONS());
		assertEquals(4, table1.indexOf(105));
		table1.insert(110); // nao produz colisao
		assertEquals(0, table1.getCOLLISIONS());
		assertEquals(9, table1.indexOf(110));
		table1.insert(101); // produz colisao no 0
		assertEquals(1, table1.getCOLLISIONS());
		assertEquals(0, table1.indexOf(101));
		
		table1.insert(101);
		assertEquals(1, table1.getCOLLISIONS());
		assertEquals(0, table1.indexOf(101));
		
		table1.insert(null);
		assertEquals(1, table1.getCOLLISIONS());

		table2.insert(103); // nao produz colisao inserindo 1 elemento na talbe
							// vazia
		assertEquals(0, table2.getCOLLISIONS());
		assertEquals(2, table2.indexOf(103));
		
		table2.insert(103); 
		assertEquals(0, table2.getCOLLISIONS());
		assertEquals(2, table2.indexOf(103));
		
		table2.insert(null);
		assertEquals(0, table2.getCOLLISIONS());
			
		
	}

	@Test
	public void testRemove() {
		int currentSize = table1.size();
		int currentSize2 = table2.size();
		
		table1.remove(200); // elemento existente
		assertEquals(currentSize - 1, table1.size());
		assertEquals(-1, table1.indexOf(200));
		
		table1.remove(null);
		
		table1.remove(609); // elemento inexistente
		assertEquals(currentSize - 1, table1.size());
		assertEquals(-1, table1.indexOf(200));
		
		table2.remove(null);
		
		table2.remove(205);
		assertEquals(currentSize2, table2.size());
	}

	@Test
	public void testSearch() {
		assertNull(table1.search(100));
		assertEquals(-1, table1.indexOf(100));
		
		assertNull(table1.search(195));
		assertEquals(-1, table1.indexOf(195));
		
		assertNull(table1.search(600));
		assertEquals(-1, table1.indexOf(600));
		
		assertNull(table1.search(null));
		
		Integer initialValue = 200;
		int increment = 5;
		while (initialValue < 600) {
			assertEquals(Integer.valueOf(initialValue), table1.search(initialValue));
			assertNull(table2.search(initialValue));
			initialValue = initialValue + increment;
		}

		assertEquals(Integer.valueOf(305), table1.search(305));
		assertEquals(2, table1.indexOf(305));
		
		assertNull(table2.search(null));
		

	}
	
	@Test
	public void testIndexOf() {
		
		Integer initialValue = 200;
		int increment = 5;
		
		Integer realSize = ((HashtableClosedAddressImpl<Integer>)table1).getPrimeAbove(PROPOSED_SIZE);
		
		while (initialValue < 600) {
			assertEquals(initialValue%realSize, table1.indexOf(initialValue));
			assertEquals(-1, table2.indexOf(initialValue));
			initialValue = initialValue + increment;
		}
		
		Integer initialValue2 = 197;
		while (initialValue2 < 600) {
			assertEquals(-1, table1.indexOf(initialValue2));
			initialValue2 = initialValue2 + increment;
		}
		
		assertEquals(-1, table1.indexOf(null));
		assertEquals(-1, table2.indexOf(null));
		
	}
	
	@Test
	public void testIsEmpty() {
		assertFalse(table1.isEmpty());
		
		int initialValue = 200;
		int increment = 5;
		while (initialValue < 600) {
			table1.remove(initialValue);
			initialValue = initialValue + increment;
		}
		
		assertTrue(table1.isEmpty());
		assertTrue(table2.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(table1.isFull());
		
	}

	@Test
	public void testSize() {
		assertEquals(80, table1.size());
		
		Integer initialValue = 200;
		int increment = 5;
		while (initialValue < 600) {
			table1.insert(initialValue);
			initialValue = initialValue + increment;
		}
		
		table1.insert(null);
		assertEquals(80, table1.size());
		
		table1.insert(600);
		assertEquals(81, table1.size());
		
		initialValue = 200;
		while (initialValue <= 600) {
			table1.remove(initialValue);
			initialValue = initialValue + increment;
		}
		
		assertEquals(0, table1.size());
		assertEquals(0, table2.size());
	}
}
