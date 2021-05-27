package adt.bst;

import java.util.ArrayList;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(root);
	}
	
	protected int height(BTNode<T> node) {
		if (node.isEmpty()) {
			return -1;
		}
		else {
	    	return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
	    }
	}

	@Override
	public BSTNode<T> search(T element) {
		
		BSTNode<T> result;
		if (element == null) {
			result = new BSTNode<T>();
		}
		else {
			result = search(this.root, element);
		}
		return result;
	}
	
	private BSTNode<T> search(BTNode<T> node, T element) {
		if (node.isEmpty()) {
			return new BSTNode<T>();
		}
	    if (element.equals(node.getData())) {
	    	return (BSTNode<T>)node;
	    }
	    if (element.compareTo(node.getData()) < 0) {
	    	return search(node.getLeft(), element);
	    }
	    else {
	    	return search(node.getRight(), element);
	    }
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
		        root = (BSTNode<T>) new BSTNode.Builder<T>()
		        		.data(element)
		        		.left((BSTNode<T>) new BSTNode.Builder<T>().parent(root).build())
		        		.right((BSTNode<T>) new BSTNode.Builder<T>().parent(root).build())
		        		.build();
			}	
		    else {
		        insert(this.root, element);
		    }
		}
	}
	
	@SuppressWarnings("unchecked")
	private void insert(BTNode<T> node, T element) {
		
		if (element.compareTo(node.getData()) < 0) {
	        if (node.getLeft().isEmpty()) {
	            node.setLeft((BSTNode<T>) new BSTNode.Builder<T>()
	            		.data(element)
	            		.parent(node)
	            		.left((BSTNode<T>) new BSTNode.Builder<T>().parent(node).build())
	            		.right((BSTNode<T>) new BSTNode.Builder<T>().parent(node).build())
	            		.build());
	        }
	        else {
	        	insert(node.getLeft(), element);
	        }
	    } 
		else {
	        if (node.getRight().isEmpty()) {
	        	node.setRight((BSTNode<T>) new BSTNode.Builder<T>()
	        			.data(element)
	        			.parent(node)
	        			.left((BSTNode<T>) new BSTNode.Builder<T>().parent(node).build())
	            		.right((BSTNode<T>) new BSTNode.Builder<T>().parent(node).build())
	        			.build());
	        }
	        else {
	        	insert(node.getRight(), element);
	        }
	    }
	}

	@Override
	public BSTNode<T> maximum() {
		BSTNode<T> result;
		if (isEmpty()) {
			result = null;
		}
		else {
			result = maximum(root);
		}	
		return result;
	}
	
	private BSTNode<T> maximum(BTNode<T> node) {
		
		if (node.getRight().isEmpty()) {
			return (BSTNode<T>)node;
		}
	    else {
	    	return maximum(node.getRight());
	    }
	}

	@Override
	public BSTNode<T> minimum() {	
		BSTNode<T> result;
		if (isEmpty()) {
			result = null;
		}
		else {
			result = minimum(root);
		}		
		return result;
	}
	
	private BSTNode<T> minimum(BTNode<T> node) {
		
		if (node.getLeft().equals(new BTNode<T>())) {
			return (BSTNode<T>)node;
		}
	    else {
	    	return minimum(node.getLeft());
	    }
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		
		BSTNode<T> node = search(element);
		BSTNode<T> result;
		if (node.isEmpty()) {
			result = null;
		}
		
		else if (!node.getRight().isEmpty()) {
	        result = minimum(node.getRight());
	    }
	    else {
	    	BSTNode<T> nodeAux = (BSTNode<T>) node.getParent();
	            
	        while (nodeAux != null && nodeAux.getData().compareTo(node.getData()) < 0) {
	        	nodeAux = (BSTNode<T>) nodeAux.getParent();
	        }	            
	        result = nodeAux;
	    }	    
	    return result;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		
		BSTNode<T> node = search(element);
		BSTNode<T> result;
		if (node.isEmpty()) {
			result = null;
		}
		
		else if (!node.getLeft().isEmpty()) {
			result = maximum(node.getLeft());
	    }
	    else {
	    	BSTNode<T> nodeAux = (BSTNode<T>) node.getParent();
	            
	        while (nodeAux != null && nodeAux.getData().compareTo(node.getData()) > 0) {
	        	nodeAux = (BSTNode<T>) nodeAux.getParent();
	        }
	            
	        result = nodeAux;
	    }
		return result;
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			BTNode<T> node = search(element);
			if (!node.isEmpty()) {
				remove(node);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void remove(BTNode<T> node) {
		if (node.isLeaf()) {
		    if (node.getData().equals(root.getData()) 
	        		&& node.getLeft().equals(root.getLeft()) 
	        		&& node.getRight().equals(root.getRight()))  {
		        root = new BSTNode<T>();
		    }
		    else {
		        if (node.getData().compareTo(node.getParent().getData()) < 0) {
		        	node.getParent().setLeft((BSTNode<T>) new BSTNode.Builder<T>().parent(node.getParent()).build());
		        }
		        else {
		        	node.getParent().setRight((BSTNode<T>) new BSTNode.Builder<T>().parent(node.getParent()).build());
		        }
		    }
		} 
		else if (hasOnlyOneLeftChild(node)) {
	        if (node.getData().equals(root.getData()) 
	        		&& node.getLeft().equals(root.getLeft()) 
	        		&& node.getRight().equals(root.getRight()))  {
	            root = (BSTNode<T>) node.getLeft();
	            root.setParent(null);
	        } 
	        else {
	        	node.getLeft().setParent(node.getParent());
	            if (node.getData().compareTo(node.getParent().getData()) < 0) {
	            	node.getParent().setLeft(node.getLeft());
	            }
	            else {
	            	node.getParent().setRight(node.getLeft());
	            }
	        }
	    }
		else if (hasOnlyOneRightChild(node)) {
	        if (node.getData().equals(root.getData()) 
	        		&& node.getLeft().equals(root.getLeft()) 
	        		&& node.getRight().equals(root.getRight()))  {
	        	root = (BSTNode<T>) node.getRight();
	        	root.setParent(null);
	        } 
	        else {
	        	node.getRight().setParent(node.getParent());
	            if (node.getData().compareTo(node.getParent().getData()) < 0) {
	            	node.getParent().setLeft(node.getRight());
	            }
	            else {
	            	node.getParent().setRight(node.getRight());
	            }
	        }
		}
		else {
	        BSTNode<T> sucessor = sucessor(node.getData());
	        node.setData(sucessor.getData());
	        remove(sucessor);
	    }
	}
	
	protected boolean hasOnlyOneLeftChild(BTNode<T> node) {
		return ( !node.getLeft().isEmpty() ) && ( node.getRight().isEmpty() );
	}
	
	protected boolean hasOnlyOneRightChild(BTNode<T> node) {
		return ( !node.getRight().isEmpty() ) && ( node.getLeft().isEmpty() );
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] preOrder() {
		ArrayList<T> list = new ArrayList<>();
		
		preOrder(root, list);
		
		return (T[]) list.toArray(new Comparable[size()]); 
			
	}
	
	private void preOrder(BTNode<T> node, ArrayList<T> list) {
	    if (!node.isEmpty()) {
	        list.add(node.getData());
	        preOrder(node.getLeft(), list);
	        preOrder(node.getRight(), list);
	    }
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] order() {	
		ArrayList<T> list = new ArrayList<>();
		
		order(root, list);
		
		return (T[]) list.toArray(new Comparable[size()]); 
	}
	
	private void order(BTNode<T> node, ArrayList<T> list) {
		if (!node.isEmpty()) {
			order(node.getLeft(), list);
			list.add(node.getData());
	        order(node.getRight(), list);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] postOrder() {
		ArrayList<T> list = new ArrayList<>();
		
		postOrder(root, list);
		
		return (T[]) list.toArray(new Comparable[size()]); 
	}
	
	private void postOrder(BTNode<T> node, ArrayList<T> list) {
		if (!node.isEmpty()) {
			postOrder(node.getLeft(), list);
			postOrder(node.getRight(), list);
			list.add(node.getData());
		}
	}
	

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}
	
	public void buildBst (T[] array) {
		if (array != null) {
			for (T i: array) {
				insert(i);
			}
		}
	}

}
