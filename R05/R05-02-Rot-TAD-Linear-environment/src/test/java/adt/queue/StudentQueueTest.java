package adt.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class StudentQueueTest {

	public Queue<Integer> queue1;
	public Queue<Integer> queue2;
	public Queue<Integer> queue3;
	
	public Queue<String> queue4;
	public Queue<String> queue5;
	public Queue<String> queue6;
	
	public Queue<String> queue7;
	
	@Before
	public void setUp() throws QueueOverflowException {

		getImplementations3();

		// Fila com 3 elementos não cheia.
		queue1.enqueue(1);
		queue1.enqueue(2);
		queue1.enqueue(3);

		// Fila com 2 elementos de tamanho 2. Fila cheia.
		queue2.enqueue(1);
		queue2.enqueue(2);
		
		queue4.enqueue("a");
		queue4.enqueue("b");
		queue4.enqueue("c");
		
		queue5.enqueue("w");
		queue5.enqueue("z");	

	}
	
	private void getImplementations1() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		queue1 = new QueueImpl<Integer>(5);
		queue2 = new QueueImpl<Integer>(2);
		queue3 = new QueueImpl<Integer>(6);
		
		queue4 = new QueueImpl<String>(5);
		queue5 = new QueueImpl<String>(2);
		queue6 = new QueueImpl<String>(6);
		
		queue7 = new QueueImpl<String>(0);
		
	}
	
	private void getImplementations2() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		queue1 = new CircularQueue<Integer>(5);
		queue2 = new CircularQueue<Integer>(2);
		queue3 = new CircularQueue<Integer>(6);
		
		queue4 = new CircularQueue<String>(5);
		queue5 = new CircularQueue<String>(2);
		queue6 = new CircularQueue<String>(6);
		
		queue7 = new CircularQueue<String>(0);
	}

	private void getImplementations3() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação		
		queue1 = new QueueUsingStack<Integer>(5);
		queue2 = new QueueUsingStack<Integer>(2);
		queue3 = new QueueUsingStack<Integer>(6);
		
		queue4 = new QueueUsingStack<String>(5);
		queue5 = new QueueUsingStack<String>(2);
		queue6 = new QueueUsingStack<String>(6);
		
		queue7 = new QueueUsingStack<String>(0);
	}

	// MÉTODOS DE TESTE
	@Test
	public void testHead() {
		assertEquals(Integer.valueOf(1), queue1.head());
		
		assertEquals("a", queue4.head());
		
		try {
			queue1.dequeue();
			queue4.dequeue();
		
		} catch (QueueUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(Integer.valueOf(2), queue1.head());
		assertEquals("b", queue4.head());
	}
	
	@Test
	public void testHeadComFilaVazia() {
		assertEquals(null, queue3.head());	
		assertEquals(null, queue6.head());
		assertEquals(null, queue7.head());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(queue1.isEmpty());
		assertFalse(queue4.isEmpty());
		
		assertTrue(queue3.isEmpty());
		assertTrue(queue6.isEmpty());
		
		assertTrue(queue7.isEmpty());
	}
	
	@Test
	public void testIsEmpty2() {
		try {
			queue1.dequeue();
			queue1.dequeue();
			queue1.dequeue();
			
			queue2.dequeue();
			queue2.dequeue();
			
			queue4.dequeue();
			queue4.dequeue();
			queue4.dequeue();
			
			queue5.dequeue();
			queue5.dequeue();
			
		} catch (QueueUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(queue1.isEmpty());
		assertTrue(queue2.isEmpty());
		assertTrue(queue4.isEmpty());
		assertTrue(queue5.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(queue1.isFull());
		assertFalse(queue4.isFull());
		
		assertTrue(queue2.isFull());
		assertTrue(queue5.isFull());
		
		System.out.println(queue7.isFull());
		//assertTrue(queue7.isFull());
	}
	
	@Test
	public void testIsFull2() {
		try {
			queue1.enqueue(Integer.valueOf(12));
			queue1.enqueue(Integer.valueOf(35));
			
			queue3.enqueue(Integer.valueOf(13));
			queue3.enqueue(Integer.valueOf(-12));
			queue3.enqueue(Integer.valueOf(22));
			queue3.enqueue(Integer.valueOf(75));
			queue3.enqueue(Integer.valueOf(-13));
			queue3.enqueue(Integer.valueOf(8));	
			
			queue4.enqueue("##");
			queue4.enqueue("&&");
			
			queue6.enqueue("@#");
			queue6.enqueue("34");
			queue6.enqueue("-09");
			queue6.enqueue("faca");
			queue6.enqueue("?!");
			queue6.enqueue("��");
			
		} catch (QueueOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(queue1.isFull());
		assertTrue(queue3.isFull());
		assertTrue(queue4.isFull());
		assertTrue(queue6.isFull());
	}

	@Test
	public void testEnqueue() {
		try {
			queue1.enqueue(Integer.valueOf(5));
			queue1.enqueue(Integer.valueOf(12));
			
			queue4.enqueue("!");
			queue4.enqueue("**");
		} catch (QueueOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = QueueOverflowException.class)
	public void testEnqueueComErro() throws QueueOverflowException {
		queue2.enqueue(Integer.valueOf(5)); 
	}
	
	@Test(expected = QueueOverflowException.class)
	public void testEnqueueComErro2() throws QueueOverflowException {
		queue5.enqueue("98"); 
	}
	
	@Test(expected = QueueOverflowException.class)
	public void testEnqueueComErro3() throws QueueOverflowException {
		queue3.enqueue(Integer.valueOf(13));
		queue3.enqueue(Integer.valueOf(-12));
		queue3.enqueue(Integer.valueOf(22));
		queue3.enqueue(Integer.valueOf(75));
		
		queue3.head();
		
		queue3.enqueue(Integer.valueOf(-13));
		queue3.enqueue(Integer.valueOf(8));
		queue3.enqueue(Integer.valueOf(7));
	}
	
	@Test(expected = QueueOverflowException.class)
	public void testEnqueueComErro4() throws QueueOverflowException {
		queue7.enqueue("c"); 
	}	
	
	@Test
	public void testEqueueComNull() {
		try {
			queue1.enqueue(null);
			
			queue4.enqueue(null);
		}
		catch (QueueOverflowException e) {
			e.printStackTrace();
		}
		assertEquals(Integer.valueOf(1), queue1.head());
		assertEquals("a", queue4.head());
	}

	@Test
	public void testDequeue() {
		try {
			assertEquals(Integer.valueOf(1), queue1.dequeue());
			assertEquals(Integer.valueOf(2), queue1.dequeue());
			assertEquals(Integer.valueOf(3), queue1.dequeue());
			
			assertEquals("a", queue4.dequeue());
			assertEquals("b", queue4.dequeue());
			assertEquals("c", queue4.dequeue());
		} catch (QueueUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = QueueUnderflowException.class)
	public void testDequeueComErro1() throws QueueUnderflowException {
		assertEquals(Integer.valueOf(1), queue3.dequeue()); 
	}
	
	@Test(expected = QueueUnderflowException.class)
	public void testDequeueComErro2() throws QueueUnderflowException {
		assertEquals(Integer.valueOf(1), queue6.dequeue()); 
	}
	
	@Test(expected = QueueUnderflowException.class)
	public void testDequeueComErro3() throws QueueUnderflowException {
		queue2.dequeue();
		queue2.dequeue();
		queue2.dequeue();
	}
	
	@Test(expected = QueueUnderflowException.class)
	public void testDequeueComErro4() throws QueueUnderflowException {
		queue7.dequeue();
	}
}