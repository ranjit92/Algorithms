package com.ranjit.algo.datastructures.binarysearchtree;

import java.util.List;

public class BSTTest {

	
	public static void main(String[] args) {
		BST<Integer> bst = new BST<>();
		bst.add(8);
		bst.add(7);
		bst.add(9);
		bst.add(3);
		bst.add(5);
//		bst.remove(3);
		print(bst.traverse(TreeTraversalOrder.PRE_ORDER));
		System.out.println();
		print(bst.traverse(TreeTraversalOrder.POST_ORDER));
		System.out.println();
		print(bst.traverse(TreeTraversalOrder.IN_ORDER));
		System.out.println();
		print(bst.traverse(TreeTraversalOrder.LEVEL_ORDER));
		
		
		
	}
	
	private static void print(List list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i)+"  ");
		}

	}
}
