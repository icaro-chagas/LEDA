package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return - noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		
		BSTNode<T> pivot = (BSTNode<T>) node.right;
		pivot.parent = node.parent;
		if (pivot.parent != null) {
			if (pivot.parent.left.equals(node)) {
				pivot.parent.left = pivot;
			}
			else if (pivot.parent.right.equals(node)) {
				pivot.parent.right = pivot;
			}
		}
		node.right = pivot.left;
		pivot.left.parent = node;
		
		pivot.left = node;
		node.parent = pivot;
		
		return pivot;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		
		BSTNode<T> pivot = (BSTNode<T>) node.left;
		pivot.parent = node.parent;
		if (pivot.parent != null) {
			if (pivot.parent.left.equals(node)) {
				pivot.parent.left = pivot;
			}
			else if (pivot.parent.right.equals(node)) {
				pivot.parent.right = pivot;
			}
		}
		node.left = pivot.right;
		pivot.right.parent = node;
		
		pivot.right = node;
		node.parent = pivot;	
		
		return pivot;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
