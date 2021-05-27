package adt.bst.extended;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class TestFloorCeilBSTImpl {

	private Integer[] array;
	private Integer[] array2;
	private Integer[] array3;
	private Integer[] array4;
	private FloorCeilBST floorCeil;
	
	@Before
	public void setUp() throws Exception {
		array = new Integer[] { 100, 50, 150, 25, 75, 125, 175, 120, 140, 160, 190 };
		array2 = new Integer[] { 100, 50, 150, 25, 75, 125, 175, 120, 140, 160, 190 };
		array3 = new Integer[0];
		array4 = null;
		floorCeil = new FloorCeilBSTImpl();
	}

	@Test
	public void testFloor() {
		
		assertNull(floorCeil.floor(array3, 5));
		assertNull(floorCeil.floor(array4, 5));
		
		Arrays.sort(array2);
	
		for (int i = array2[0] - 5; i < array2[0]; i++) {
			Integer floor = floorCeil.floor(array, i);
			assertNull(floor);
		}
		
		for (int i = 0; i < array2.length; i++) {
			Integer floor = floorCeil.floor(array, array[i]);
			assertEquals(floor, array[i]);
			
			if (i < array2.length -1) {
				int a = array2[i] + 1;
				while (a < array2[i+1]) {					
					Integer floor2 = floorCeil.floor(array, a);
					assertEquals(floor2, array2[i]);
					a++;
				}
			}
		}
		
		for (int i = array2[array2.length-1] + 1; i <= array2[array2.length-1] + 5; i++) {
			Integer floor = floorCeil.floor(array, i);
			assertEquals(array2[array2.length-1], floor);
		}
	}

	@Test
	public void testCeil() {
		
		assertNull(floorCeil.ceil(array3, 5));
		assertNull(floorCeil.ceil(array4, 5));
		
		Integer[] array3 = new Integer[0];
		Integer[] array4 = new Integer[0];
		
		assertNull(floorCeil.floor(array3, 5));
		assertNull(floorCeil.floor(array4, 5));
		
		
		Arrays.sort(array2);
		
		for (int i = array2[0] - 5; i < array2[0]; i++) {
			Integer ceil = floorCeil.ceil(array, i);
			assertEquals(ceil, array2[0]);
		}
		
		for (int i = 0; i < array2.length; i++) {
			Integer ceil = floorCeil.ceil(array, array[i]);
			assertEquals(ceil, array[i]);

			
			
			if (i < array2.length -1) {
				int a = array2[i] + 1;
				while (a < array2[i+1]) {					
					Integer ceil2 = floorCeil.ceil(array, a);
					assertEquals(ceil2, array2[i+1]);
					a++;
				}
			}
		}
		
		for (int i = array2[array2.length-1] + 1; i <= array2[array2.length-1] + 5; i++) {
			Integer ceil = floorCeil.ceil(array, i);
			assertNull(ceil);
		}
	}

}
