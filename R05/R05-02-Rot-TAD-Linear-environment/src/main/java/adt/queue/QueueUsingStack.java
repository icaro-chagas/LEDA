package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;
	private int elements;
	private int capacity;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
		elements = 0;
		capacity = size;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		} else if (!(element == null)) {
			 try {
				stack1.push(element);
				elements++;
			} catch (StackOverflowException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		} else {
			fillsStack2();
	        try {
	        	elements--;
				return stack2.pop();
			} catch (StackUnderflowException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public T head() {
		if (isEmpty()) {
            return null;
        }
        else if (stack2.isEmpty()){
        	fillsStack2();
        	return stack2.top();
        }
        else {
        	return stack2.top();
        }
	}

	@Override
	public boolean isEmpty() {
		return stack1.isEmpty() && stack2.isEmpty();
	}

	@Override
	public boolean isFull() {
		return elements == capacity;
	}
	
	private void fillsStack2() {
		if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
            	try {
					stack2.push(stack1.pop());
				} catch (StackOverflowException e) {
					e.printStackTrace();
				} catch (StackUnderflowException e) {
					e.printStackTrace();
				}
            }
        }
	}

}
