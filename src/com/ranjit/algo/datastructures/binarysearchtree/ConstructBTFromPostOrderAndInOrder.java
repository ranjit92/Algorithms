package com.ranjit.algo.datastructures.binarysearchtree;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ConstructBTFromPostOrderAndInOrder {

	private class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	public TreeNode buildTree(int[] inorder, int[] postorder) {

		int length = inorder.length;
		
		Map<Integer, Integer> inOrdermap = new HashMap<>();
		for(int i = 0; i < length; i++) {
			inOrdermap.put(inorder[i], i);
		}
		
		AtomicInteger pivot = new AtomicInteger(length-1);
		
		return buildTreeHelper(0, length-1, postorder, pivot, inOrdermap);
		
	}
	
	
	public TreeNode buildTreeHelper(int start, int end, int[] postOrder, AtomicInteger pivot, Map<Integer, Integer> inorderMap) {
		
		
		if(start > end) {
			return null;
		}
		
		TreeNode currRoot = new TreeNode(postOrder[pivot.getAndDecrement()]);
		
		int index = inorderMap.get(currRoot.val);
		
		currRoot.right = buildTreeHelper(index+1, end, postOrder, pivot, inorderMap);
		currRoot.left  = buildTreeHelper(start, index-1, postOrder, pivot, inorderMap);
		
		return currRoot;
		
	}
	

	
	public static void main(String[] args) {
		ConstructBTFromPostOrderAndInOrder obj = new ConstructBTFromPostOrderAndInOrder();
		obj.buildTree(new int[]{9,3,15,20,7}, new int[] {9,15,7,20,3});
	}
}
