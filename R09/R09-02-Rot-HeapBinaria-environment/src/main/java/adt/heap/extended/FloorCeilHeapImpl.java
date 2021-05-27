package adt.heap.extended;

import java.util.Comparator;

import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double numero) {
		
		this.index = -1;
		Integer result = null;
		if (array != null) {
			
			if (array.length > 0) {
				for (Integer i : array) {
					this.insert(i);
				}
				
				Integer oldRoot = this.extractRootElement();
				int size = array.length - 1;
				Integer currentRoot = this.rootElement();
				Integer floor = null;
				
				result = floor(size, floorNumber(numero), oldRoot, currentRoot, floor);
			}
		}
		
		return result;
	}
	
	private Integer floor(int size, int numero, Integer oldRoot, Integer currentRoot, Integer floor) {
		
		if (oldRoot > currentRoot && numero > oldRoot) {
			floor = oldRoot;
		}
		else if (size == 1 && numero > currentRoot) {
			floor = currentRoot;
		}
		else if (comparator.compare(numero, oldRoot) <= 0 && comparator.compare(numero, currentRoot) >= 0) {
			if (numero == oldRoot) {
				floor = oldRoot;
			}
			else if (numero == currentRoot) {
				floor = currentRoot;
			}		
			else if (oldRoot < currentRoot) {
				floor = oldRoot;
			}
			else {
				floor = currentRoot;
			}
		}
		
		if (floor == null && size > 1) {
			oldRoot = this.extractRootElement();
			currentRoot = this.rootElement();
			size--;
			floor = floor(size, numero, oldRoot, currentRoot, floor);
		}
		
		return floor;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		
		this.index = -1;
		Integer result = null;
		if (array != null) {
			
			if (array.length > 0) {
				for (Integer i : array) {
					this.insert(i);
				}
				
				Integer oldRoot = this.extractRootElement();
				int size = array.length - 1;
				Integer currentRoot = this.rootElement();
				Integer ceil = null;
				
				result = ceil(size, ceilNumber(numero), oldRoot, currentRoot, ceil);
			}
		}
		
		return result;
	}	
	
	private Integer ceil(int size, int numero, Integer oldRoot, Integer currentRoot, Integer ceil) {
		
		if (oldRoot < currentRoot && numero < oldRoot) {
			ceil = oldRoot;
		}
		else if (size == 1 && numero < currentRoot) {
			ceil = currentRoot;
		}
		else if (comparator.compare(numero, oldRoot) <= 0 && comparator.compare(numero, currentRoot) >= 0) {
			if (numero == oldRoot) {
				ceil = oldRoot;
			}
			else if (numero == currentRoot) {
				ceil = currentRoot;
			}
			
			else if (oldRoot < currentRoot) {
				ceil = currentRoot;
			}
			else {
				ceil = oldRoot;
			}
		}
		
		if (ceil == null && size > 1) {
			oldRoot = this.extractRootElement();
			currentRoot = this.rootElement();
			size--;
			ceil = ceil(size, numero, oldRoot, currentRoot, ceil);
		}
		
		return ceil;

	}
	
	private int floorNumber(double number) {
		
		int floor = 0;
		double frac = number - (int)number;
		if (number > 0 || frac == 0) {
			floor = (int) number;
		}
		else {
			floor = (int) number -1;
		}
		return floor;
		
	}
	
	private int ceilNumber(double number) {
		
		int ceil = 0;
		double frac = number - (int)number;
		if (number < 0 || frac == 0) {
			ceil = (int) number;
		}
		else {
			ceil = (int) number + 1;
		}
		return ceil;
		
	}

}
