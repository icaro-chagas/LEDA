package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if(!testInputValidity(array, leftIndex, rightIndex)) {
			return;
		}
		
		if (leftIndex < rightIndex) {
			
			int middleIndex = (leftIndex + rightIndex)/2;
			
			sort(array, leftIndex, middleIndex);
			sort(array, middleIndex+1, rightIndex);
			
			merge(array, leftIndex, middleIndex, rightIndex);
			
		}
	}
	
	private void merge(T[] array, int leftIndex, int middleIndex, int rightIndex) {
		
		T[] tempArray = (T[]) new Comparable[array.length];
		for (int i = leftIndex; i <= rightIndex; i++) {
			tempArray[i] = array[i];
		}
		
		int i = leftIndex;
		int j = middleIndex + 1;
		int k = leftIndex;
		
		while (i <= middleIndex && j <= rightIndex) {
			
			if (tempArray[i].compareTo(tempArray[j]) <= 0) {
				array[k++] = tempArray[i++];
			}
			else {
				array[k++] = tempArray[j++];
			}
		}
		
		while (i <= middleIndex) {
			array[k++] = tempArray[i++];
		}
		
		while (j <= rightIndex) {
			array[k++] = tempArray[j++];
		}
	}
	
	private boolean testInputValidity(T[] array, int leftIndex, int rightIndex) {
		
		  boolean validity = true;
	      if (array == null) {
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
