package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * A CLASSE AVLTree herda de BSTImpl. VOCE PRECISA SOBRESCREVER A IMPLEMENTACAO
 * DE BSTIMPL RECEBIDA COM SUA IMPLEMENTACAO "OU ENTAO" IMPLEMENTAR OS SEGUITNES
 * METODOS QUE SERAO TESTADOS NA CLASSE AVLTREE:
 *  - insert
 *  - preOrder
 *  - postOrder
 *  - remove
 *  - height
 *  - size
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.
	
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
		rebalance((BSTNode<T>) node);

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
		    rebalanceUp((BSTNode<T>) node);
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
	        rebalanceUp((BSTNode<T>) node);
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
	        rebalanceUp((BSTNode<T>) node);
		}
		else {
	        BSTNode<T> sucessor = sucessor(node.getData());
	        node.setData(sucessor.getData());
	        remove(sucessor);
	    }
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int result = Integer.MIN_VALUE;
		if (!node.isEmpty()) {
			int heightLeft = height(node.getLeft());
			int heightRight = height(node.getRight());
			
			if (heightLeft > -1 && heightRight > -1) {
				result = (heightLeft + 1) - (heightRight + 1);
			}
			else if (heightLeft > -1 && heightRight == -1) {
				result = heightLeft + 1;
			}
			else if (heightLeft == -1 && heightRight > -1) {
				result = -(heightRight + 1);
			}
		}
		return result;
	}

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
					verifyRootNode(pivot);
				}
				else if ( (balanceNode == -2 && balanceRightChild == -1) 
				       || (balanceNode == -2 && balanceRightChild == 0) ) {
					BSTNode<T> pivot = Util.leftRotation(pNode);
					verifyRootNode(pivot);
				}
				else if ( (balanceNode == 2 && balanceLeftChild == -1) ) {
					Util.leftRotation((BSTNode<T>) pNode.getLeft());
					BSTNode<T> pivot = Util.rightRotation(pNode);
					verifyRootNode(pivot);
				}
				else if ( (balanceNode == -2 && balanceRightChild == 1)) {
					Util.rightRotation((BSTNode<T>) pNode.getRight());
					BSTNode<T> pivot = Util.leftRotation(pNode);
					verifyRootNode(pivot);
				}
			}
		}
	}

	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node;
		while (parent != null) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
	}
	
	protected void verifyRootNode(BSTNode<T> node) {
		if(node.getLeft().equals(this.root) || node.getRight().equals(this.root)) {
			this.root = node;
		}
	}
	
}
