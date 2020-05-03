package com.ranjit.algo.datastructures.linkedlist;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {

	private int size = 0;
	private Node<T> head = null;
	private Node<T> tail = null;

	private static class Node<T> {

		private T data;
		private Node<T> prev;
		private Node<T> next;

		public Node(T data, Node<T> prev, Node<T> next) {
			super();
			this.data = data;
			this.prev = prev;
			this.next = next;
		}

	}

	// Time complexity O(n)
	public void clear() {
		Node<T> trav = head;
		while (trav != null) {
			Node<T> next = trav.next;
			trav.prev = trav.next = null;
			trav.data = null;
			trav = next;
		}
		// cleaning up memory
		head = tail = trav = null;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	// Adding at last with o(1)
	public void addLast(T elem) {
		if (isEmpty())
			head = tail = new Node<>(elem, null, null);
		else {
			tail.next = new Node<>(elem, tail, null);
			tail = tail.next;
		}
		size++;
	}

	public void add(T elem) {
		addLast(elem);
	}

	// Adding at last with o(1)
	public void addFirst(T elem) {

		if (isEmpty())
			head = new Node<>(elem, null, null);
		else {
			head.prev = new Node<>(elem, null, head);
			head = head.prev;
		}
		size++;
	}

	public void addAt(int index, T elem) {

		if (index >= size() || index < 0)
			throw new IllegalArgumentException("illegal index");

		if (index == 0) {
			addFirst(elem);
			return;
		}

		if (index == size() - 1) {
			addLast(elem);
			return;
		}

		Node<T> temp = head;
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}

		Node<T> newNode = new Node<>(elem, temp, temp.next);
		temp.next.prev = newNode;
		temp.next = newNode;

		size++;
	}

	public T peekFirst() {
		if (isEmpty())
			throw new RuntimeException("Empty list");
		return head.data;
	}

	public T peekLast() {
		if (isEmpty())
			throw new RuntimeException("Empty list");
		return tail.data;
	}

//	Takes constant time O(1)
	public T removeFirst() {
		if (isEmpty())
			throw new RuntimeException("Empty list");

		T data = head.data;
		head = head.next;
		--size;

//		if list is empty set tail is null
		if (isEmpty())
			tail = null;

//		Do a memory cleanup of head previous node
		else
			head.prev = null;

		return data;

	}

//	Takes constant time O(1)
	public T removeLast() {
		if (isEmpty())
			throw new RuntimeException("Empty list");

		T data = tail.data;
		tail = tail.prev;
		--size;

		if (isEmpty())
			head = null;

//		Do a memory cleanup of head previous node
		else
			tail.next = null;

		return data;
	}
	
	// Remove an arbitrary node from the linked list, O(1)
	public T remove(Node<T> node) {
		
	    // If the node to remove is somewhere either at the
	    // head or the tail handle those independently
		if(node.next == null)
			return removeLast();
		if(node.prev == null)
			return removeFirst();
		
		 // Temporarily store the data we want to return
		T temp = node.data;
		
		node.prev.next = node.next;
		node.next.prev = node.prev;
		
		// Memory cleanup
		node.data = null;
		node = node.next = node.prev = null;
		
		--size;
		
		return temp;
	}
	
	
	// Remove a node at a particular index, O(n) but iterate only one half of the list using smart pointer O(n/2) = O(n) 
	 public T removeAt(int index) {
		 if (index >= size() || index < 0)
				throw new IllegalArgumentException("illegal index");
		 
		 Node<T> trav;
		 int i;
		 
		// Search from the front of the list
		 if(index < size/2) {
			 for(i = 0, trav = head; i != index; i++) {
				 trav = trav.next;
			 }
		 }
		  // Search from the back of the list
		 else {
			 for(i = size-1, trav = tail; i != index; i--) {
				 trav = trav.prev;
			 }
		 }
		 return remove(trav); 
	 }
	 
	 
	// Remove a particular value in the linked list, O(n)
	 public boolean remove(Object obj) {
		 Node<T> trav;
		 
		  // Support searching for null
		 if(obj == null) {
			 for(trav = head; trav != null; trav = trav.next) {
				 if(trav.data == null) {
					 remove(trav);
					 return true;
				 }
				 
			 }
		 }
		 
		// Search for non null object
		 else {
			 for(trav = head; trav != null; trav = trav.next) {
				 if(obj.equals(trav.data)) {
					 remove(trav);
					 return true;
				 }
				 
			 }
		 }
		return false; 
	 }
	 
	public int indexOf(Object obj) {
		 Node<T> trav;
		 int index = 0;
		 
		  // Support searching for null
		 if(obj == null) {
			 for(trav = head; trav != null; trav = trav.next, index++) {
				 if(trav.data == null) {
					 return index;
				 }
				 
			 }
		 }
		// Search for non null object
		 else {
			 for(trav = head; trav != null; trav = trav.next, index++) {
				 if(obj.equals(trav.data)) {
					 return index;
				 }
				 
			 }
		 }
		 return -1;
	}
	
	// Check is a value is contained within the linked list
	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}
	 

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			private Node<T> trav = head;
			
			@Override
			public boolean hasNext() {
				
				return trav!=null;
			}

			@Override
			public T next() {
				T data = trav.data;
				trav = trav.next;
				return data;
			}
			
//			@Override from Iterator interface
			 @Override
		      public void remove() {
		        throw new UnsupportedOperationException();
		      }
		};
	}
	
	
	public String toString() {
		Node<T> trav = head;
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		
		while(trav != null) {
			sb.append(trav.data);
			if(trav.next != null)
				sb.append(", ");
			
			trav = trav.next;
		}
		sb.append(" ]");
		return sb.toString();
	}

}
