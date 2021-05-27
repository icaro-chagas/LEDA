package orderStatistic;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class TestOrderStatisticsHeapImpl {
	
	OrderStatisticsHeapImpl<Integer> oshi;
	Integer[] array;
	Integer[] array2;
	
	
	@Before
	public void setUp() throws Exception {
		oshi = new OrderStatisticsHeapImpl<Integer>();
		array = new Integer[] {6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40};
		array2 = new Integer[] {150, 200, 95, 132, 173, 121, 104, 245, 197, 203, 260, 78};
	}

	@Test
	public void testOrderStatistic() {
		Integer[] arrayCopy = Arrays.copyOf(array, array.length);
		Integer[] arrayCopy2 = Arrays.copyOf(array2, array2.length);
		
		Arrays.sort(arrayCopy);
		Arrays.sort(arrayCopy2);
		for (int i=1; i <= array.length; i++) {
			assertEquals(oshi.getOrderStatistics(array, i), arrayCopy[i-1]);
			assertEquals(oshi.getOrderStatistics(array2, i), arrayCopy2[i-1]);
		}
		
		assertNull(oshi.getOrderStatistics(array, 0));
		assertNull(oshi.getOrderStatistics(array, -1));
		assertNull(oshi.getOrderStatistics(array, array.length + 1));
		
		assertNull(oshi.getOrderStatistics(array2, 0));
		assertNull(oshi.getOrderStatistics(array2, -1));
		assertNull(oshi.getOrderStatistics(array2, array2.length + 1));
		
	}

}
