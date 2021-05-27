package adt.stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentStackTest {

	public Stack<Integer> stack1;
	public Stack<Integer> stack2;
	public Stack<Integer> stack3;
	
	public Stack<String> stack4;
	public Stack<String> stack5;
	public Stack<String> stack6;
	
	public Stack<String> stack7;

	@Before
	public void setUp() throws StackOverflowException {

		getImplementations();

		// Pilha com 3 elementos não cheia.
		stack1.push(1);
		stack1.push(2);
		stack1.push(3);

		// Pilha com 2 elementos de tamanho 2, pilha cheia.
		stack2.push(1);
		stack2.push(2);

		stack4.push("a");
		stack4.push("b");
		stack4.push("c");
		
		stack5.push("w");
		stack5.push("z");
	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		stack1 = new StackDoubleLinkedListImpl<Integer>(5);
		stack2 = new StackDoubleLinkedListImpl<Integer>(2);
		stack3 = new StackDoubleLinkedListImpl<Integer>(6);
		
		stack4 = new StackDoubleLinkedListImpl<String>(5);
		stack5 = new StackDoubleLinkedListImpl<String>(2);
		stack6 = new StackDoubleLinkedListImpl<String>(6);
		
		stack7 = new StackDoubleLinkedListImpl<String>(0);
		
	}

	// MÉTODOS DE TESTE
	@Test
	public void testTop() {
		assertEquals(Integer.valueOf(3), stack1.top());
		assertEquals("c", stack4.top());
		
		try {
			stack1.pop();
			stack4.pop();
		} catch (StackUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(Integer.valueOf(2), stack1.top());
		assertEquals("b", stack4.top());
		
	}
	
	@Test
	public void testTopComPilhaVazia() {
		assertEquals(null, stack3.top());
		assertEquals(null, stack6.top());
		assertEquals(null, stack7.top());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(stack1.isEmpty());
		assertFalse(stack4.isEmpty());
		
		assertTrue(stack3.isEmpty());
		assertTrue(stack6.isEmpty());
		
		assertTrue(stack7.isEmpty());
	}
		
	@Test
	public void testIsEmpty2() {
		try {
			stack1.pop();
			stack1.pop();
			stack1.pop();
			
			stack2.pop();
			stack2.pop();
			
			stack4.pop();
			stack4.pop();
			stack4.pop();
			
			stack5.pop();
			stack5.pop();
		} catch (StackUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(stack1.isEmpty());
		assertTrue(stack2.isEmpty());
		assertTrue(stack4.isEmpty());
		assertTrue(stack5.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(stack1.isFull()); 
		assertFalse(stack4.isFull()); 
		
		assertTrue(stack2.isFull());
		assertTrue(stack5.isFull());
		
		assertTrue(stack7.isFull());
	}	
	
	@Test
	public void testIsFull2() {
		try {
			stack1.push(Integer.valueOf(12));
			stack1.push(Integer.valueOf(35));
			
			stack3.push(Integer.valueOf(13));
			stack3.push(Integer.valueOf(-12));
			stack3.push(Integer.valueOf(22));
			stack3.push(Integer.valueOf(75));
			stack3.push(Integer.valueOf(-13));
			stack3.push(Integer.valueOf(8));
			
			stack4.push("$");
			stack4.push(".3*");
			
			stack6.push("@#");
			stack6.push("34");
			stack6.push("-09");
			stack6.push("faca");
			stack6.push("?!");
			stack6.push("��");
			
		} catch (StackOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(stack1.isFull());
		assertTrue(stack3.isFull());
		assertTrue(stack4.isFull());
		assertTrue(stack5.isFull());
	}

	@Test
	public void testPush() {
		try {
			stack1.push(Integer.valueOf(5));
			stack1.push(Integer.valueOf(12));
			
			stack4.push("!");
			stack4.push("**");
		} catch (StackOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = StackOverflowException.class)
	public void testPushComErro1() throws StackOverflowException {
		stack2.push(Integer.valueOf(5)); 			
	}
	
	@Test(expected = StackOverflowException.class)
	public void testPushComErro2() throws StackOverflowException {
		stack5.push("34");			
	}
	
	@Test(expected = StackOverflowException.class)
	public void testPushComErro3() throws StackOverflowException {
		stack3.push(Integer.valueOf(13));
		stack3.push(Integer.valueOf(-12));
		stack3.push(Integer.valueOf(22));
		stack3.push(Integer.valueOf(75));
		stack3.push(Integer.valueOf(-13));
		stack3.push(Integer.valueOf(8));
		stack3.push(Integer.valueOf(19));
		
	}
	
	@Test(expected = StackOverflowException.class)
	public void testPushComErro4() throws StackOverflowException {
		stack7.push("(!)");			
	}
	
	@Test
	public void testPushComNull() {
		try {
			stack1.push(10);
			stack1.push(null);
			
			stack4.push("%");
			stack4.push(null);
		}
		catch (StackOverflowException sof) {
			sof.printStackTrace();
		}
		assertEquals(Integer.valueOf(10), stack1.top());
		assertEquals("%", stack4.top());
	}

	@Test
	public void testPop() {
		try {
			assertEquals(Integer.valueOf(3), stack1.pop());
			assertEquals(Integer.valueOf(2), stack1.pop());
			assertEquals(Integer.valueOf(1), stack1.pop());
			
			assertEquals("c", stack4.pop());
			assertEquals("b", stack4.pop());
			assertEquals("a", stack4.pop());
		} catch (StackUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = StackUnderflowException.class)
	public void testPopComErro1() throws StackUnderflowException {
		assertEquals(Integer.valueOf(3), stack3.pop()); 
													
	}
	
	@Test(expected = StackUnderflowException.class)
	public void testPopComErro2() throws StackUnderflowException {
		assertEquals("c", stack6.pop()); // levanta excecao apenas se
													// stack1 for vazia
	}
	
	@Test(expected = StackUnderflowException.class)
	public void testPopComErro3() throws StackUnderflowException {
		stack2.pop();
		stack2.pop();
		stack2.pop();
	}
	
	@Test(expected = StackUnderflowException.class)
	public void testPopComErro4() throws StackUnderflowException {
		stack7.pop();	
	}
	
	
}