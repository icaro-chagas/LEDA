package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {
	}
	
	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next, RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {
		if (!(element == null)) {
			if (isEmpty()) {
				data = element;
				next = new RecursiveDoubleLinkedListImpl<T>();
				previous = new RecursiveDoubleLinkedListImpl<T>();		
			} 
			else {						
				T nextData = getData();
				RecursiveSingleLinkedListImpl<T> nextOfNext = getNext();
			
				data = element;
				previous = new RecursiveDoubleLinkedListImpl<T>();
				next = new RecursiveDoubleLinkedListImpl<T>(nextData, nextOfNext, this);
				
			}
		}
	}
	
	@Override
	public void insert(T element) {
		if(!(element == null)) {
			if (isEmpty()) {
				data = element;
				next = new RecursiveDoubleLinkedListImpl<T>();
				((RecursiveDoubleLinkedListImpl<T>)next).setPrevious(this);
				
				if (previous == null) {
					previous = new RecursiveDoubleLinkedListImpl<T>();
				}	
			} 
			else {
				next.insert(element);
			}
		}
	}
	
	@Override
	public void remove(T element) {
		if(!isEmpty()) 
			if (getData().equals(element)) { 
				if(previous.isEmpty() && next.isEmpty()){
					data = null; 
					next = null; 
					previous = null;
				}
				else {
					data = next.data;	
					next = next.next;
					if(next != null) {
						((RecursiveDoubleLinkedListImpl<T>)next).previous = this;
					}
				}
			}
			else {
				next.remove(element);
			}
	}
	
	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			if(previous.isEmpty() && next.isEmpty()){
				data = null ;
				next = null;
				previous = null;
			}
			else {
				data = next.getData();
				next = next.getNext();
			}
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (next.isEmpty() && previous.isEmpty()) {
					data = null;
					next = null;
					previous = null;
			} 
			else if (next.isEmpty()) {
					previous.next = new RecursiveDoubleLinkedListImpl<T>();
			} 
			else {
				((RecursiveDoubleLinkedListImpl<T>) next).removeLast();
			}
		}
	}
	

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
