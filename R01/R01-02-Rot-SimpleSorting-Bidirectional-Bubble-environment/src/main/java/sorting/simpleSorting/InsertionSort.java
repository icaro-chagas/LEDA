package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if(!testInputValidity(array, leftIndex, rightIndex)) {
			return;
		}
		
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			T key = array[i];
			int j = i-1;
			
			while(j >= leftIndex && array[j].compareTo(key) > 0) {
				array[j+1] = array[j];
				j--;
			array[j+1] = key;
			}
		}
	}
	
	private boolean testInputValidity(T[] array, int leftIndex, int rightIndex) {
		
		  boolean validity = true;
	      if (array == null) {
	    	  validity = false;
	      } 
	      else if (rightIndex < leftIndex) {
	    	  validity = false;
	      }
	      else if (leftIndex < 0) {
	    	  validity = false;
	      }
	      else if (rightIndex > array.length-1) {
	          validity = false;
	       }
	      
		return validity;
	}
}
