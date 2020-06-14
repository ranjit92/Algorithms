package com.ranjit.algo.datastructures.binarysearchtree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BST<T extends Comparable<T>> {

	private int nodeCount;

	private Node root;

	private class Node {
		T data;
		Node left, right;

		public Node(Node left, Node right, T elem) {
			this.left = left;
			this.right = right;
			this.data = elem;
		}
	}

	public int size() {
		return nodeCount;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean add(T elem) {

		 // Check if the value already exists in this
	    // binary tree, if it does ignore adding it
		if (contains(elem))
			return false;
		
		 // Otherwise add this element to the binary tree
		else {
			root = add(root, elem);
			nodeCount++;
			return true;
		}
	}

	private Node add(Node node, T elem) {

		// Base case: found a leaf node where we want to insert
		if (node == null) {
			node = new Node(null, null, elem);
		}

		// Pick a subtree to insert element either left of right based on comparable
		else {

			if (elem.compareTo(node.data) < 0) {
				node.left = add(node.left, elem);
			} else {
				node.right = add(node.right, elem);
			}
		}

		return node;
	}

	// returns true is the element exists in the tree
	public boolean contains(T elem) {
		return contains(root, elem);
	}

	private boolean contains(Node node, T elem) {

		if (node == null)
			return false;

		int comp = elem.compareTo(node.data);

		// Dig into the left subtree because the value we're
		// looking for is smaller than the current value
		if (comp < 0) {
			return contains(node.left, elem);
		}

		// Dig into the right subtree because the value we're
		// looking for is greater than the current value
		else if (comp > 0) {
			return contains(node.right, elem);
		}

		// We found the value we were looking for
		else {
			return true;
		}
	}
	
	
	
	public boolean remove(T elem) {
		if(contains(elem)) {
			root = remove(root, elem);
			nodeCount--;
			return true;
		}
		return false;
	}
	
	
	
	private Node remove(Node node , T elem) {
		
		if(node == null) 
			return null;
		
		int cmp = elem.compareTo(node.data);
		
		 // Dig into left subtree, the value we're looking
	    // for is smaller than the current value
		if(cmp < 0){
			node.left = remove(node.left, elem);
		}
		
	    // Dig into right subtree, the value we're looking
	    // for is greater than the current value
		else if(cmp > 0) {
			node.right = remove(node.right, elem);
		}
		
		// Found the node we wish to remove
		else {
			
			// This is the case with only a right subtree or
		    // no subtree at all. In this situation just
		    // swap the node we wish to remove with its right child.
			if(node.left == null) {

				Node successor = node.right;
				node.data = null;
				node = null;
				
				return successor;
				
			}
			
			

	        // This is the case with only a left subtree or
	        // no subtree at all. In this situation just
	        // swap the node we wish to remove with its left child.
			else if(node.right == null) {

				Node successor = node.left;
				node.data = null;
				node = null;
				
				return successor;
			}
			
			
			
			// When removing a node from a binary tree with two links the
	        // successor of the node being removed can either be the largest
	        // value in the left subtree or the smallest value in the right
	        // subtree. In this implementation I have decided to find the
	        // smallest value in the right subtree which can be found by
	        // traversing as far left as possible in the right subtree.
			else {
			
				Node successor = findMin(node.right);
				node.data = successor.data;
				
				
				 // Go into the right subtree and remove the leftmost node we
		        // found and swapped data with. This prevents us from having
		        // two nodes in our tree with the same value.
				//This way will get rooted sub-tree on right of current node after removing successor node which already replaced with removed node. 
				node.right = remove(node.right, successor.data);
				
			}
		}
		
		return node;
	}
	
	// Helper method to find the leftmost node (which has the smallest value)
	private Node findMin(Node node) {
		while(node.left != null) {
			node = node.left;
		}
		return node;
	}
	
	
	public int height() {
		return height(root);
	}
	
	
	private int height(Node node) {
		if(node == null)
			return 0;
		return Math.max(height(node.left), height(node.right))+1;
	}
	
	
	public List<T> traverse(TreeTraversalOrder order) {
		List<T> listArray = new LinkedList<>();

		switch (order) {
		case PRE_ORDER:
			return preOrderTraversal(root, listArray);
		case POST_ORDER:
			return postOrderTraversal(root, listArray);
		case IN_ORDER:
			return inOrderTraversal(root, listArray);
		case LEVEL_ORDER:
			return levelOrderTraversal(root, listArray);
		default:
			return null;
		}
	}

	private List<T> preOrderTraversal(Node node, List<T> listArray) {
		
		if(node == null) {
			return listArray;
		}
		listArray.add(node.data);
		preOrderTraversal(node.left, listArray);
		preOrderTraversal(node.right, listArray);
		
		return listArray;
	}

	private List<T> postOrderTraversal(Node node, List<T> listArray) {

		if (node == null) {
			return listArray;
		}
		postOrderTraversal(node.left, listArray);
		postOrderTraversal(node.right, listArray);
		listArray.add(node.data);

		return listArray;
	}
	
	
	private List<T> inOrderTraversal(Node node, List<T> listArray) {

		if (node == null) {
			return listArray;
		}
		inOrderTraversal(node.left, listArray);
		listArray.add(node.data);
		inOrderTraversal(node.right, listArray);

		return listArray;
	}
	
	
	private List<T> levelOrderTraversal(Node node, List<T> listArray) {

		if (listArray == null) {
			return null;
		}

		Queue<Node> queue = new LinkedList<>();
		queue.add(node);

		while (!queue.isEmpty()) {
			Node travNode = queue.poll();
			listArray.add(travNode.data);
			if (travNode.left != null)
				queue.add(travNode.left);
			if (travNode.right != null)
				queue.add(travNode.right);
		}

		return listArray;
	}

}
