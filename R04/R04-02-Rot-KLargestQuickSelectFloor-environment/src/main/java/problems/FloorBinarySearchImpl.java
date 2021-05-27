package problems;

import util.Util;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		
		if(!testInputValidity(array)) {
			return null;
		}

		// Supondo que o array de entrada está desordenado, utiliza-se o método quickSort para ordenar
		// e fornecer uma entrada consistente ao método floorSearch.
		quickSort(array, 0, array.length - 1);
		
		return floorSearch(array, 0, array.length - 1, x);
		
	}
	
	private Integer floorSearch(Integer[] array, int leftIndex, int rightIndex, Integer x) {
		
		if (x < array[leftIndex]) {
	        return null;
	    }
		
		if (x >= array[rightIndex]) {
			return array[rightIndex];
		}
		
		int middleIndex = (leftIndex + rightIndex) / 2;

		if (middleIndex == leftIndex) {
	        return array[leftIndex];
	    }
		
		if (array[middleIndex] == x) {
			return array[middleIndex];
		}
		
		if (x < array[middleIndex]) {
			return floorSearch(array, leftIndex, middleIndex - 1, x);
		}
		
		return floorSearch(array, middleIndex, rightIndex, x);
	}
	
	private void quickSort(Integer[] array, int leftIndex, int rightIndex) {
		
		
		if (leftIndex < rightIndex) {
			
			int pivotIndex = partition(array, leftIndex, rightIndex);
			quickSort(array, leftIndex, pivotIndex-1);
			quickSort(array, pivotIndex+1, rightIndex);
			
		}
	}
	
	private int partition(Integer[] array, int leftIndex, int rightIndex) {
		
		int range = rightIndex - leftIndex + 1;
        int randPivotIndex = (int) (Math.random() * range) + leftIndex;
        
        Util.swap(array, leftIndex, randPivotIndex);
		
        Integer pivot = array[leftIndex];
		int i = leftIndex;
		
		for (int j = leftIndex + 1; j <= rightIndex; j++) {
			if (array[j].compareTo(pivot) <= 0) {
				i++;
				Util.swap(array, i, j);
			}
		}
		
		Util.swap(array, leftIndex, i);
		
		return i;
		
	}
	
	private boolean testInputValidity(Integer[] array) {
		
		  boolean validity = true;
	      if (array == null) {
	    	  validity = false;
	      }
	      else if (array.length == 0) {
	    	  validity = false;
	      }
	      
		return validity;
	}

}
