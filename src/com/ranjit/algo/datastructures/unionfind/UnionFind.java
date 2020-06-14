package com.ranjit.algo.datastructures.unionfind;

/**
 * UnionFind/Disjoint Set data structure implementation.
 *  Two Major operation find() and unify().
 * 
 * @author ranjit
 *
 */
public class UnionFind {

	// The number of elements in this union find
	private int size;
	
	
	// Used to track the size of each of the component
	private int[] sz;
	
	
	// id[i] points to the parent of i, if id[i] = i then i is a root node
	private int[] id;
	
	private int numComponents;
	
	public UnionFind(int size) {
		
		if(size < 0) throw new IllegalArgumentException("Size should be greater than '0'");
		
		this.size = numComponents = size;
		
		id = new int[size];
		sz = new int[size];
		
		for(int i = 0; i < size; i++) {
			id[i] = i; //initially each node is root node so Link to itself (self root)
			sz[i] = 1; //initially roots having single elements so size of each root 1.
		}
	}
	
	
	/**
	 * Find which component/set 'p' belongs to.
	 * Takes amortized constant time with path compression along with find operation.
	 * 
	 * 
	 * @param p
	 * @return
	 */
	public int find(int p) {
		
		int root = p;
		
		while(root != id[root]) {
			root = id[root];	
		}
		
		//Path compression 
	    // Compress the path leading back to the root.
	    // Doing this operation is called "path compression"
	    // and is what gives us amortized time complexity.
		while(p !=root) {
			int next = id[p];
			id[p] = root;
			p = next;
		}
		
		return root;
	}
	
	
	
	/**
	 * Unify the components/sets containing elements 'p' and 'q'
	 * 
	 * 
	 * @param p
	 * @param q
	 */
	public void unify(int p, int q) {
		
		int root1 = find(p); 
		int root2 = find(q);
		
		// These elements are already in the same group!
		if( root1 == root2) 
			return;
		
		 // Merge smaller component/set into the larger one.
		if(sz[root1] < sz[root2]) {
			id[root1] = root2;
			sz[root2] += sz[root1]; 
		}
		else {
			id[root2] = root1;
			sz[root1] += sz[root2];
		}
		
		
		 // Since the roots found are different we know that the
	    // number of components/sets has decreased by one
		numComponents--;
		
		
	}
	
	
	
	public boolean isunifyable(int p, int q) {
		
		int root1 = find(p); 
		int root2 = find(q);
		
		// These elements are already in the same group!
		if( root1 == root2) 
			return false;
		
		 // Merge smaller component/set into the larger one.
		if(sz[root1] < sz[root2]) {
			id[root1] = root2;
			sz[root2] += sz[root1]; 
		}
		else {
			id[root2] = root1;
			sz[root1] += sz[root2];
		}
		
		
		 // Since the roots found are different we know that the
	    // number of components/sets has decreased by one
		numComponents--;
		
		return true;
	}
	
	
	
	
	/**
	 * Return whether or not the elements 'p' and 'q' are in the same components/set.
	 * 
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean connected(int p , int q) {
		return find(p) == find(q); 
	}
	
	
	
	/**
	 * Return the size of the components/set 'p' belongs to
	 * 
	 * @param p
	 * @return
	 */
	public int componentSize(int p) {
		return sz[find(p)];
	}
	
	public int size() {
		return size;
	}
	
	public int numberOfComponent() {
		return numComponents;
	}
	
	
	
		
}
