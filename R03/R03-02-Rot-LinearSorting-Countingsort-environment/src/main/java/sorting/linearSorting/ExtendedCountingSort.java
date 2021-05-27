package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		
		if(!testInputValidity(array, leftIndex, rightIndex)) {
			return;
		}
		
		int[] values = findHighestAndLowest(array, leftIndex, rightIndex);
		
		int[] count = new int[(values[0] + 1) - values[1]];

        for (int i = leftIndex; i <= rightIndex; i++) {
        	count[array[i] - values[1]] += 1;
        }
        
        for (int i = 1; i < count.length; i++) {
        	count[i] += count[i-1];
        }

        int[] result = new int[(rightIndex + 1) - leftIndex];
  
        for (int i = rightIndex; i >= leftIndex; i--) {
        	result[count[array[i] - values[1]] -1] = array[i];
        	
        	count[array[i] - values[1]] -= 1;
        }
        
        int j = 0;
        for (int i = leftIndex; i <= rightIndex; i++) {
        	array[i] = result[j];
        	j++;
        }

	}
	
	private int[] findHighestAndLowest(Integer[] array, int leftIndex, int rightIndex) {
		
		int highest = array[0];
		int lowest = array[0];
		
		for (int i=leftIndex; i <= rightIndex; i++) {
			if (array[i] > highest) {
				highest = array[i];
			}
			
			if (array[i] < lowest) {
				lowest = array[i];
			}
		}
			
		return new int[] {highest, lowest};
	}
	
	private boolean testInputValidity(Integer[] array, int leftIndex, int rightIndex) {
		
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
