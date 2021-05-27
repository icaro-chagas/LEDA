package orderStatistic;

import util.Util;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * 
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1]. A 3a
 * estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * 
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as
 * estatisticas de ordem maiores que k.
 * 
 * Requisitos do algoritmo: 
 * - DEVE ser in-place. 
 * - Voce pode modificar o array original 
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em
 * sala para calcular estatisticas de ordem. 
 * - Se a entrada for invalida, deve-se retornar um array vazio (por exemplo, ao solicitar os 5 maiores
 * elementos em um array que soh contem 3 elementos).
 * - Este metodo NUNCA deve retornar null.
 * 
 * @author campelo and adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T> {

	private static boolean ORDERED = false;
	
	@Override
	public T[] getKLargest(T[] array, int k) {
		
		//Integer[] test = {};
		
		if(!testInputValidity(array, k)) {
			return (T[]) new Comparable[]{};
		}
		
		quickSort(array, 0, array.length-1);
		ORDERED = true;
		
		T[] result = (T[]) new Comparable[k];
		
		int j = 0;
		for (int i = array.length-k; i < array.length; i++) {
			
			if (orderStatistics(array, i+1) != null) {
				result[j] = orderStatistics(array, i+1);
			}
			j++;
		}
		
		ORDERED = false;
		
		return result;
	}

	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando a ideia
	 * de algum algoritmo de ordenacao visto em sala. Caso nao exista a k-esima
	 * estatistica de ordem, entao retorna null.
	 * 
	 * Obs: o valor de k deve ser entre 1 e N.
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	public T orderStatistics(T[] array, int k) {	
		
		if (k < 1 || k > array.length) {
			return null;
		}
		
		if (!ORDERED) {
			quickSort(array, 0, array.length-1);
		}
		
		return array[k-1];
	}

	
	private void quickSort(T[] array, int leftIndex, int rightIndex) {
		
		if (leftIndex < rightIndex) {
			
			int pivotIndex = partition(array, leftIndex, rightIndex);
			quickSort(array, leftIndex, pivotIndex-1);
			quickSort(array, pivotIndex+1, rightIndex);
			
		}
	}
	
	private int partition(T[] array, int leftIndex, int rightIndex) {
		
		int range = rightIndex - leftIndex + 1;
        int randPivotIndex = (int) (Math.random() * range) + leftIndex;
        
        Util.swap(array, leftIndex, randPivotIndex);
		
		T pivot = array[leftIndex];
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
	
	private boolean testInputValidity(T[] array, int k) {
		
		  boolean validity = true;

		  if (array == null) {
	    	  validity = false;
	      }
		  else if (k > array.length || k < 0) {
	    	  validity = false;
	      }
	      else if (array.length == 0) {
	    	  validity = false;
	      }
	      
		return validity;
	}

}
