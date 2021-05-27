package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(T element) {
		
		if (isFull()) {
			throw new HashtableOverflowException();
		}
		int elementIndex = indexOf(element);
		if (element != null && elementIndex == -1) {
			int probe = 0;
			int hash;
			
			while (probe < capacity()) {
				
				hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
				T auxElement = (T) table[hash];
				
				if (auxElement == null || auxElement.equals(deletedElement)) {		
					elements++;	
					table[hash] = element;		
					break;
				}
				COLLISIONS++;
				
	
				probe++;
	
			}
		}
		else if (element != null && elementIndex != -1) {
			table[elementIndex] = element;
		}
		
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			int probe = 0;
			int hash;
			while (probe < capacity()) {
				hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
	
				if (table[hash] != null && table[hash].equals(element)) {
					table[hash] = deletedElement;
					elements--;
					break;
				}
	
				probe++;
	
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T search(T element) {
		T result = null;
		if (element != null) {
			int probe = 0;
			int hash;
			while (probe < capacity()) {
	
				hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
	
				if (table[hash] == null) {
					return result;
				}
	
				if (table[hash].equals(element)) {
					result = (T) table[hash];
					break;
				}
	
				probe++;
			}		
		}
		return result;
	}

	@Override
	public int indexOf(T element) {
		
		T auxElement = search(element);
		if (auxElement == null) {
			return -1;
		}

		int probe = 0;
		int hash = 0;
		while (probe < capacity()) {

			hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);

			if (table[hash].equals(auxElement)) {
				break;
			}

			probe++;

		}

		return hash;
	}

}
