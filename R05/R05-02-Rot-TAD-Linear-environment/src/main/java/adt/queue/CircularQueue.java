package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = 0; 
		tail = -1;
		elements = 0;
		
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		} else if (!(element == null)) {
			
			tail = (tail + 1) % array.length;
			array[tail] = element;
			
			elements++;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		} else {
			
			T element = array[head]; 			
			head = ((head + 1) % array.length); 
					
			elements--;
			
			return element;
		}
	}

	@Override
	public T head() {
		if (isEmpty()) {
            return null;
        }
        else {
        	return array[head];
        }
	}

	@Override
	public boolean isEmpty() {
		return elements == 0;
	}

	@Override
	public boolean isFull() {
		return elements == array.length; 
	}

}
