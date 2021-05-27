package adt.avltree;

import adt.bst.BSTNode;
import adt.bst.BSTVerifierImpl;
import adt.bt.BTNode;

/**
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeVerifierImpl<T extends Comparable<T>> extends BSTVerifierImpl<T> implements AVLTreeVerifier<T> {

	private AVLTreeImpl<T> avlTree;

	public AVLTreeVerifierImpl(AVLTree<T> avlTree) {
		super(avlTree);
		this.avlTree = (AVLTreeImpl<T>) avlTree;
	}

	private AVLTreeImpl<T> getAVLTree() {
		return avlTree;
	}

	@Override
	public boolean isAVLTree() {
		
		boolean result;
		
		if (avlTree == null || !this.isBST()) {
			result = false;
		}
		else {
			result = isAVLTree(getAVLTree().getRoot());
		}
		
		return result;
	}
	
	private boolean isAVLTree(BTNode<T> node) {
		
        boolean result = false;
        if (node.isEmpty()) {
            result = true;
        }
 
        if (result == false) {
        	result = Math.abs(avlTree.calculateBalance((BSTNode<T>) node)) <= 1 
                	&& isAVLTree(node.getLeft()) && isAVLTree(node.getRight());
        }
        
		return result;

	}

}
