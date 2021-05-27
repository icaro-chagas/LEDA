package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Voce pode assumir que o maior inteiro armazenado não chega a 100.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		
		if(!testInputValidity(array, leftIndex, rightIndex)) {
			return;
		}
		
		int[] count = new int[100];

        for (int i = leftIndex; i <= rightIndex; i++) {
        	count[array[i]] += 1;
        }
        
        for (int i = 1; i < count.length; i++) {
        	count[i] += count[i-1];
        }

        int[] result = new int[(rightIndex + 1) - leftIndex];
  
        for (int i = rightIndex; i >= leftIndex; i--) {
        	result[count[array[i]] -1] = array[i];
        	
        	count[array[i]] -= 1;
        }
        
        int j = 0;
        for (int i = leftIndex; i <= rightIndex; i++) {
        	array[i] = result[j];
        	j++;
        }

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
