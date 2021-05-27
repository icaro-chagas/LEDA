package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		this.last = new DoubleLinkedListNode<T>();
		this.head = new DoubleLinkedListNode<T>();
	}
	
	@Override
	public void insertFirst(T element) {
		if (!(element == null)) {
			
			DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, (DoubleLinkedListNode<T>) head, new DoubleLinkedListNode<T>());
	
			((DoubleLinkedListNode<T>) head).previous = newHead;
			if (head.isNIL()) {
				last = newHead;
			}
			head = newHead;
			
		}
	}
	
	@Override
	public void insert(T element) {
		if (!(element == null)) {
			DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<T>(), last);
	 
			last.next = newLast;
			if (last.isNIL()) { 
				head = newLast;
			}
			last = newLast;
		}
	}

	@Override
	public void removeFirst() {
		if (!(head.isNIL())) {
			head = head.next;
			
			if (head.isNIL()) {
				last = (DoubleLinkedListNode<T>) head;
			} 
			else {
				((DoubleLinkedListNode<T>) head).previous = new DoubleLinkedListNode<T>();
			}
		}	
	}

	@Override
	public void removeLast() {
		if (!(last.isNIL())) {
			last = last.previous;
					
			if (last.isNIL()) {
				head = last;
			}
			else {
				last.next = new DoubleLinkedListNode<T>();
			}
		}
	}
	
	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (head.getData().equals(element)) {
				removeFirst();	
			}
			else {
				DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) head;
				
				while (!(aux.isNIL()) && !(aux.data.equals(element))) {
					aux = (DoubleLinkedListNode<T>) aux.next;
				}
				
				if (!(aux.isNIL())) {
					aux.previous.next = aux.next;
					((DoubleLinkedListNode<T>)aux.next).previous = aux.previous;
				}
			}
		}
	}

	@Override
	public T search(T element) {
		if (!isEmpty()) {
			DoubleLinkedListNode<T> auxHead = (DoubleLinkedListNode<T>) head;
			DoubleLinkedListNode<T> auxLast = last;
			while (!(auxHead.equals(auxLast)) && !(auxHead.next.equals(auxLast)) && !(auxHead.data.equals(element)) && !(auxLast.data.equals(element))) {
				auxHead = (DoubleLinkedListNode<T>) auxHead.next;
				auxLast = auxLast.previous;
			}
			if (auxHead.data.equals(element)) {
				return auxHead.data;
			}
			if (auxLast.data.equals(element)) {
				return auxLast.data;
			}

		}

		return null;
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
