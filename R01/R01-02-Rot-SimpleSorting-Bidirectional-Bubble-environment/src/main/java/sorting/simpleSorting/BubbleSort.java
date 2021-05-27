package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if(!testInputValidity(array, leftIndex, rightIndex)) {
			return;
		}
		
		boolean swapped;
		for (int i = 0; i < rightIndex; i++) {
			
			swapped = false;
			for (int j = leftIndex; j < rightIndex - i; j++) {
				if (array[j].compareTo(array[j + 1]) > 0) {
					Util.swap(array, j, j+1);
					swapped = true;
				}
			}
			
			if (!swapped) {
				break;
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
