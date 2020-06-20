package com.ranjit.algo.datastructures.binarysearchtree;

public class ConstructBSTFromPreorder {

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

	public TreeNode bstFromPreorder(int[] preorder) {

		TreeNode root=null;
		if(preorder == null || preorder.length == 0) return root;
		
		for(int i : preorder) {
			root = add(root, i);
		}
		return root;
	}
	
	private TreeNode add(TreeNode node, Integer elem) {

		// Base case: found a leaf node where we want to insert
		if (node == null) {
			node = new TreeNode(elem, null, null);
		}

		// Pick a subtree to insert element either left of right based on comparable
		else {

			if (elem.compareTo(node.val) < 0) {
				node.left = add(node.left, elem);
			} else {
				node.right = add(node.right, elem);
			}
		}

		return node;
	}
	
	
	public static void main(String[] args) {
		ConstructBSTFromPreorder obj = new ConstructBSTFromPreorder();
		obj.bstFromPreorder(new int[]{8,5,1,7,10,12});
	}
}
