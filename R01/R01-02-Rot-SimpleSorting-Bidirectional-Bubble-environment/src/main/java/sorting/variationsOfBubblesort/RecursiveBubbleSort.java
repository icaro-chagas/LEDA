package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

public class RecursiveBubbleSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * Implementação recursiva do bubble sort. Você deve implementar apenas esse
	 * método sem usar nenhum outro método auxiliar (exceto
	 * Util.swap(array,int,int)). Para isso, tente definir o caso base do
	 * algoritmo e depois o caso indutivo, que reduz o problema para uma entrada
	 * menor em uma chamada recursiva. Seu algoritmo deve ter complexidade
	 * quadrática O(n^2).
	 */
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if(!testInputValidity(array, leftIndex, rightIndex)) {
			return;
		}
		
		if (rightIndex == leftIndex) {
			return;
		}
		
		for (int i = leftIndex; i < rightIndex; i++) {
			if (array[i].compareTo(array[i+1]) > 0) {
				Util.swap(array, i, i+1);
			}
		}
		sort(array, leftIndex, rightIndex - 1);
		
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
