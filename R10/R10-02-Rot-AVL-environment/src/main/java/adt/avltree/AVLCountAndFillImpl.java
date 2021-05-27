package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter; 
	
	public AVLCountAndFillImpl() {
		
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		
		if (array != null) {
			BSTImpl<T> auxBst = new BSTImpl<>();
			for (T e: array) {
				auxBst.insert(e);
			}
			
			T[] sortedArray = auxBst.order();
			
			fillWithoutRebalance(sortedArray, 0, sortedArray.length-1, null);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private BSTNode<T> fillWithoutRebalance(T[] array, int leftIndex, int rightIndex, BSTNode<T> parent) {

		BSTNode<T> node = null; 
	    if (leftIndex > rightIndex) {
	        node = new BSTNode<T>();
	    }
	    
	    if (node == null) {
		    int mid = (leftIndex + rightIndex) / 2;
		    node = new BSTNode.Builder()
		    		.data(array[mid])
		    		.left(new BSTNode<>())
		    		.right(new BSTNode<>())
		    		.parent(parent)
		    		.build();
	    
		    if (root.isEmpty()) {
		    	root = node;
		    }
		        
	    	node.setLeft(fillWithoutRebalance(array, leftIndex, mid - 1, node));
		  
	    	node.setRight(fillWithoutRebalance(array, mid + 1, rightIndex, node));
	    }
    	return node;
		
	}
	
	@Override
	protected void rebalance(BSTNode<T> node) {
		if (node.getParent() != null) {
			BSTNode<T> pNode = (BSTNode<T>) node.getParent();
			int balanceNode = calculateBalance(pNode);
			int balanceLeftChild = calculateBalance((BSTNode<T>) pNode.getLeft());
			int balanceRightChild = calculateBalance((BSTNode<T>) pNode.getRight());
			
			if (Math.abs(balanceNode) > 1) {
				if ( (balanceNode == 2 && balanceLeftChild == 1) 
				  || (balanceNode == 2 && balanceLeftChild == 0) ) {
					BSTNode<T> pivot = Util.rightRotation(pNode);
					LLcounter++;
					verifyRootNode(pivot);
				}
				else if ( (balanceNode == -2 && balanceRightChild == -1) 
				       || (balanceNode == -2 && balanceRightChild == 0) ) {
					BSTNode<T> pivot = Util.leftRotation(pNode);
					RRcounter++;
					verifyRootNode(pivot);
				}
				else if ( (balanceNode == 2 && balanceLeftChild == -1) ) {
					Util.leftRotation((BSTNode<T>) pNode.getLeft());
					BSTNode<T> pivot = Util.rightRotation(pNode);
					LRcounter++;
					verifyRootNode(pivot);
				}
				else if ( (balanceNode == -2 && balanceRightChild == 1)) {
					Util.rightRotation((BSTNode<T>) pNode.getRight());
					BSTNode<T> pivot = Util.leftRotation(pNode);
					RLcounter++;
					verifyRootNode(pivot);
				}
			}
		}
	}

}
