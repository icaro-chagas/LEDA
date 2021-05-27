package adt.heap.extended;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class TestFloorCeilHeapImpl {

	private Integer[] array;
	private Integer[] array2;
	private Integer[] array3;
	private Integer[] array4;
	private FloorCeilHeapImpl floorCeilMin;
	private FloorCeilHeapImpl floorCeilMax;
	
	@Before
	public void setUp() throws Exception {
		array = new Integer[] { -12, 2, 125, 32, 15, 100, 50, 150, 25, 12, 75, 125, 175, 120, 140, 15, 160, 173, 190 };
		array2 = new Integer[] { -12, 2, 125, 32, 15, 100, 50, 150, 25, 12, 75, 125, 175, 120, 140, 15, 160, 173, 190 };
		array3 = new Integer[0];
		array4 = null;
		floorCeilMin = new FloorCeilHeapImpl((o1,o2) -> o2 - o1);
		floorCeilMax = new FloorCeilHeapImpl((o1,o2) -> o1 - o2);

	}

	@Test
	public void testFloor() {
		
		assertNull(floorCeilMax.floor(array3, 5));
		assertNull(floorCeilMax.floor(array4, 5));
		
		assertNull(floorCeilMin.floor(array3, 5));
		assertNull(floorCeilMin.floor(array4, 5));
		
		Arrays.sort(array2);
	
		for (double i = array2[0] - 5; i < array2[0]; i += 0.5) {
			Integer floor1 = floorCeilMax.floor(array, i);
			Integer floor2 = floorCeilMin.floor(array, i);

			assertNull(floor1);
			assertNull(floor2);
		}
		
		for (int i = 0; i < array2.length; i++) {
			Integer floor1 = floorCeilMax.floor(array, array[i]);
			Integer floor2 = floorCeilMin.floor(array, array[i]);
			
			assertEquals(floor1, array[i]);
			assertEquals(floor2, array[i]);
			
			if (i < array2.length -1) {
				double a = array2[i] + 0.5;
				while (a < array2[i+1]) {					
					Integer floor3 = floorCeilMax.floor(array, a);
					Integer floor4 = floorCeilMin.floor(array, a);
					
					assertEquals(floor3, array2[i]);
					assertEquals(floor4, array2[i]);
					a += 0.5;
				}
			}
		}
		
		for (double i = array2[array2.length-1] + 0.5; i <= array2[array2.length-1] + 5; i += 0.5) {
			Integer floor1 = floorCeilMax.floor(array, i);
			Integer floor2 = floorCeilMin.floor(array, i);
			
			assertEquals(array2[array2.length-1], floor1);
			assertEquals(array2[array2.length-1], floor2);
		}
	}

	@Test
	public void testCeil() {
		
		assertNull(floorCeilMax.ceil(array3, 5));
		assertNull(floorCeilMax.ceil(array4, 5));
		
		assertNull(floorCeilMin.ceil(array3, 5));
		assertNull(floorCeilMin.ceil(array4, 5));
		
		
		Arrays.sort(array2);
		
		for (double i = array2[0] - 5; i < array2[0]; i += 0.5) {
			Integer ceil1 = floorCeilMax.ceil(array, i);
			Integer ceil2 = floorCeilMin.ceil(array, i);
			
			assertEquals(ceil1, array2[0]);
			assertEquals(ceil2, array2[0]);
		}
		
		for (int i = 0; i < array2.length; i++) {
			Integer ceil1 = floorCeilMax.ceil(array, array[i]);
			Integer ceil2 = floorCeilMin.ceil(array, array[i]);
			
			assertEquals(ceil1, array[i]);
			assertEquals(ceil2, array[i]);

			
			
			if (i < array2.length -1) {
				double a = array2[i] + 0.5;
				while (a < array2[i+1]) {					
					Integer ceil3 = floorCeilMax.ceil(array, a);
					Integer ceil4 = floorCeilMin.ceil(array, a);
					
					assertEquals(ceil3, array2[i+1]);
					assertEquals(ceil4, array2[i+1]);
					a += 0.5;
				}
			}
		}
		
		for (double i = array2[array2.length-1] + 0.5; i <= array2[array2.length-1] + 5; i += 0.5) {
			Integer ceil1 = floorCeilMax.ceil(array, i);
			Integer ceil2 = floorCeilMin.ceil(array, i);
			
			assertNull(ceil1);
			assertNull(ceil2);
		}
	}

}
