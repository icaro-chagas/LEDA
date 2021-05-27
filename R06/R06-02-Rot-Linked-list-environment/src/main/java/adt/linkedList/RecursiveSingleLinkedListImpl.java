package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {
	}
	
	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		if (data == null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int size() {
		if(isEmpty()) {
			return 0;
		}	
		else {
			return 1 + next.size();
		}
	}

	@Override
	public T search(T element) {
		if (isEmpty()) {
			return null;
		}
		else {
			if (data.equals(element)) {
				return data;
			}
			else {
				return next.search(element);
			}
		}
	}

	@Override
	public void insert(T element) {
		if(!(element == null)) {
			if(isEmpty()) {
				data = element;
				next = new RecursiveSingleLinkedListImpl<T>();
			}
			else {
				next.insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if(!isEmpty()) {
			if (data.equals(element)) {
				data = next.data;
				next = next.next;
			}
			else {
				next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Comparable[this.size()];
		
		toArray(result, this, 0);
		
		return result;
		
	}
	
	private T[] toArray(T[] result, RecursiveSingleLinkedListImpl<T> node, int count) {
		
		if (!node.isEmpty()){
			result[count] = node.getData();
			count++;
			toArray(result, node.next, count);
		}
		
		return result;
		
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
