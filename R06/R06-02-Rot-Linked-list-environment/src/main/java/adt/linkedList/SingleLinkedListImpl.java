package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return head.equals(new SingleLinkedListNode<T>());
	}

	@Override
	public int size() {

		int size = 0;
		SingleLinkedListNode<T> aux = head;
		while (!(aux.isNIL())) {
			size++;
			aux = aux.next;
		}
		return size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> aux = head;
		while (!(aux.isNIL()) && !(aux.getData().equals(element))) {
			aux = aux.next;
		}
		return aux.getData();
	}

	@Override
	public void insert(T element) {
		if (!(element == null)) {
			SingleLinkedListNode<T> aux = head;
			if (head.isNIL()) {
				SingleLinkedListNode<T> newHead = new SingleLinkedListNode<T>(element, head);
				head = newHead;
			} 
			else {
				while (!(aux.isNIL())) {
					aux = aux.next;
				}
				aux.data = element;
				aux.next = new SingleLinkedListNode<T>();
			}
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (head.data.equals(element)) {
				head = head.next;
			}
			else{
				SingleLinkedListNode<T> aux = head;
				while (!(aux.isNIL()) && !(aux.data.equals(element))) {
					aux = aux.next;
				}
				if (!(aux.isNIL())){
					aux.data = aux.next.data;
					aux.next = aux.next.next;
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Comparable[this.size()];
		
		SingleLinkedListNode<T> aux = head;
		
		int i = 0;
		while (!(aux.isNIL())) {
			result[i] = aux.getData();
			aux = aux.next;
			i++;
		}
		return result;	
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
