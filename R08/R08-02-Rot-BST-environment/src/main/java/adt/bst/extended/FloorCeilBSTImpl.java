package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {

	@Override
	public Integer floor(Integer[] array, double numero) {
		if (!this.isEmpty()) {
			this.root = new BSTNode<Integer>();
		}
		
		Integer result = null;
		if (array != null) {
			
			for (int i : array) {
				this.insert(i);
			}
			
			result = floor(this.getRoot(), numero).getData();
		}
		return result;
	}
	
	private BSTNode<Integer> floor(BTNode<Integer> node, double numero) {
		if (node.isEmpty()) { 
            return (BSTNode<Integer>) node;
		}
        if (node.getData() == numero) {
        	return (BSTNode<Integer>) node;
        }
        if (node.getData() >  numero) {
        	return floor(node.getLeft(), numero);
        }
        
        BSTNode<Integer> rightNode = floor(node.getRight(), numero); 
        if (rightNode.isEmpty()) {
        	return (BSTNode<Integer>) node;
        }
        else {
        	return rightNode; 
        }	
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {	
		if (!this.isEmpty()) {
			this.root = new BSTNode<Integer>();
		}
		
		Integer result = null;
		if (array != null) {
			
			for (int i : array) {
				this.insert(i);
			}
			
			result = ceil(this.getRoot(), numero).getData();
		}
		return result;
	}
	
	private BSTNode<Integer> ceil(BTNode<Integer> node, double numero) {
		if (node.isEmpty()) { 
            return (BSTNode<Integer>) node;
		}
        if (node.getData() == numero) {
        	return (BSTNode<Integer>) node;
        }
        if (node.getData() <  numero) {
        	return ceil(node.getRight(), numero);
        }
        
        BSTNode<Integer> leftNode = ceil(node.getLeft(), numero); 
        if (leftNode.isEmpty()) {
        	return (BSTNode<Integer>) node;
        }
        else {
        	return leftNode; 
        }
	}

}
