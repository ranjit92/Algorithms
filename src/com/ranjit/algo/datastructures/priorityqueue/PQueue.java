package com.ranjit.algo.datastructures.priorityqueue;

import java.util.ArrayList;
import java.util.List;

public class PQueue<T extends Comparable<T>> {

	private List<T> heap = null;

	public PQueue() {
		this(1);
	}

	public PQueue(int size) {
		heap = new ArrayList<>(size);
	}
	
	
	  // Construct a priority queue using heapify in O(n) time, a great explanation can be found at:
	  // http://www.cs.umd.edu/~meesh/351/mount/lectures/lect14-heapsort-analysis-part.pdf
	public PQueue(T[] elem) {
		int length = elem.length;
		heap = new ArrayList<>(length);
		
		for(int i = 0; i < length; i++) {
			heap.add(elem[i]);
		}
		
		// Heapify process, O(n)
		//As we know leaf starts in BT from n/2 to n (0 based) or n/2+1 to n (1 based).
		//And leaf node is already a heap if consider alone so if we see below also its satisfies heap invariant as well.
		//so will start from largest index of non leaf element(n/2 -1 in 0 based) till root (0).
		//so will take one by one from range (n/2-1 to n) and check and do adjustment to fulfill heap property from that node till bottom using sink(i).   
		
		for(int i = Math.max(0, (length/2)-1); i >= 0; i--){
			sink(i);
		}
		
	}
	
	public int size() {
		return heap.size();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public void clear() {
		heap.clear();
	}

	public T peek() {
		if (heap.isEmpty())
			return null;
		return heap.get(0);
	}

	public boolean contains(T elem) {
		if (heap.isEmpty())
			return false;
		for (T heapElement : heap) {
			return heapElement.equals(elem);
		}
		return false;
	}

	// Tests if the value of node i <= node j
	// This method assumes i & j are valid indices, O(1)
	private boolean less(int i, int j) {
		T node1 = heap.get(i);
		T node2 = heap.get(j);

		return node1.compareTo(node2) <= 0;
	}

	private void swap(int i, int j) {
		T elemI = heap.get(i);
		T elemJ = heap.get(j);

		heap.set(i, elemJ);
		heap.set(j, elemI);
	}

	// Perform bottom up node swim, O(log(n)) because we have to max swim till height of tree and height of BT = log n
	private void swim(int k) {

		// Grab the index of the next parent node WRT to k
		int parent = (k - 1) / 2;

		// Keep swimming while we have not reached the
		// root and while we're less than our parent.
		while (k >= 0 && less(k, parent)) {
			swap(k, parent);

			// k become new parent
			k = parent;

			// Grab the index of the next parent node WRT to k
			parent = (k - 1) / 2;
		}
	}

//	Perform top down node sink, O(log(n)) because we have to max swim till height of tree and height of BT = log n
	private void sink(int k) {

		while (true) {
			int left = 2 * k + 1; // left child WRT to k
			int right = 2 * k + 2; // right child WRT to k

			int smallest = left; // assume left is smallest and if right is check below
			if (right < heap.size() && less(right, left))
				smallest = right;

			// base condition for while(true)
			// Stop if we're outside the bounds of the tree
			// or stop early if we cannot sink k anymore
			if (left >= heap.size() || less(k, smallest))
				break;

			swap(smallest, k);
			k = smallest;

		}

	}

	// Removes a node at particular index, O(log(n))
	private T removeAt(int index) {
		if (isEmpty())
			return null;
		
		if (index > size() || index < 0)
			throw new IndexOutOfBoundsException();

		int lastIndex = size() - 1;
		T removedData = heap.get(index);

//		swap with last index and then remove last
		swap(index, lastIndex);
		heap.remove(lastIndex);

//		check if last index was removed
		if (index == lastIndex)
			return removedData;

//		swim to satisfy the heap invariant from index to top
		swim(index);

//		if heap invariant satisfy from index to top then sink to satisfy heap invariant from index to bottom
		if (heap.get(index).equals(removedData)) {
			sink(index);
		}

		return removedData;
	}

	
	// Removes a particular element in the heap, O(n)
	public boolean remove(T elem) {
		if(elem == null) return false;
		
		for(int i = 0; i < size(); i++) {
			if(heap.get(i).equals(elem)) {
				removeAt(i);
				return true;
			}
			
		}
		return false;
	}
	
	public void add(T elem) {
		if(elem == null) throw new IllegalArgumentException();
		
		heap.add(elem);
		swim(size()-1);
		
	}
	
	
	// Recursively checks if this heap is a min heap
	// This method is just for testing purposes to make
	// sure the heap invariant is still being maintained
	// Called this method with k=0 to start at the root
	public boolean isMinHeap(int k) {

		int heapSize = size();

		// If we are outside the bounds of the heap return true
		if (k >= heapSize)
			return true;

		int left = 2 * k + 1;
		int right = 2 * k + 2;

		if (left < heapSize && !less(k, left))
			return false;
		if (right < heapSize && !less(k, right))
			return false;
		
		// Recurse on both children to make sure they're also valid heaps
		return (isMinHeap(left) && isMinHeap(right));

	}
	
	@Override
	public String toString() {
		return heap.toString();
	}

}
