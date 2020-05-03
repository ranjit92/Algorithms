package com.ranjit.algo.datastructures.queue;

import java.util.Iterator;
import java.util.LinkedList;

public class Queue<T> implements Iterable<T> {

	LinkedList<T> list = new LinkedList<>();

	public Queue() {

	}

	public Queue(T elem) {
		list.addLast(elem);
	}

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

//	Adding the element at back of list O(1)
	public void offer(T elem) {
		list.addLast(elem);
	}

//	poll the element from the front of queue FIFO, O(1) 
	public T poll() {
		if (isEmpty())
			throw new RuntimeException("Empty Queue");
		return list.removeFirst();
	}

//	peek element from the front but not delete from the queue O(n)
	public T peek() {
		if (isEmpty())
			throw new RuntimeException("Empty Queue");
		return list.peekFirst();
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

}
