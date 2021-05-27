package adt.bst;

import adt.bt.BTNode;

/**
 * 
 * Performs consistency validations within a BST instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class BSTVerifierImpl<T extends Comparable<T>> implements BSTVerifier<T> {
	
	private BSTImpl<T> bst;
	
	private BSTNode<T> prevNode;

	public BSTVerifierImpl(BST<T> bst) {
		this.bst = (BSTImpl<T>) bst;
	}
	
	private BSTImpl<T> getBSt() {
		return bst;
	}

	@Override
	public boolean isBST() {
		
		boolean result;
		prevNode = new BSTNode<T>();
		
		if (bst == null) {
			result = false;
		}
		else {
			result = isBST(getBSt().getRoot());
		}
		
		return result;
	}
	
	private boolean isBST(BTNode<T> node) {
		
		Boolean result = null;
		if (node.isEmpty()) {
			result = true;
		}

		else {
        	result = true;
            if (!isBST(node.getLeft())) {
            	result = false;
            }
 
            if (result && !(prevNode.isEmpty()) && node.getData().compareTo(prevNode.getData()) <= 0) {
                result = false;
            }
            
            if (result) {
            	prevNode = (BSTNode<T>) node;
                result = isBST(node.getRight());
            }
		}
		
		return result;
	}
}
