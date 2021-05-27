package adt.bst;

import adt.bt.BTNode;

/**
 * - Esta eh a unica classe que pode ser modificada 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	private static int numRecursions = 0;
	
	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		boolean result;
		if (tree1 == null || tree2 == null) {
			result = false;
		}
		else {
			result = equals(tree1.getRoot(), tree2.getRoot());	
		}
		return result;
	}

	private boolean equals(BTNode<T> node1, BTNode<T> node2) {
        if (node1.isEmpty() && node2.isEmpty()) {
            return true;
        }
        if (!node1.isEmpty() && !node2.isEmpty()) 
            return (node1.getData().equals(node2.getData())
                    && equals(node1.getLeft(), node2.getLeft())
                    && equals(node1.getRight(), node2.getRight()));
   
        return false;
	}
	
	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		boolean result;
		if (tree1 == null || tree2 == null) {
			result = false;
		}
		else {
			result = isSimilar(tree1.getRoot(), tree2.getRoot());		
		}
		return result;
	}
	
	
	private boolean isSimilar(BTNode<T> node1, BTNode<T> node2) {
		 
	    if (node1.isEmpty() && node2.isEmpty()) {
	    	return true;
	    }
	 
	    if (!node1.isEmpty() && !node2.isEmpty()) {
	        return (isSimilar(node1.getLeft(), node2.getLeft()) && isSimilar(node1.getRight(), node2.getRight()));
	    }
	    
	    return false;
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		T result;
		if (tree == null) {
			result = null;
		}
		else {
			numRecursions = 0;
			result = orderStatistic(tree.getRoot(), k).getData();
		}
		return result;
	}
	
	private BSTNode<T> orderStatistic(BTNode<T> node, int k) {
		if (node.isEmpty()) {
            return new BSTNode<T>();
		}
		
		BSTNode<T> leftNode = orderStatistic(node.getLeft(), k);
      
        if (!leftNode.isEmpty()) {
            return (BSTNode<T>) leftNode;
        }
        
        numRecursions++;
        if (numRecursions == k) {
            return (BSTNode<T>) node;
        }
        
        return orderStatistic(node.getRight(), k);

	}

}
