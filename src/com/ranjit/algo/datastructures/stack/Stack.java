package com.ranjit.algo.datastructures.stack;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

public class Stack<T> implements Iterable<T> {

	LinkedList<T> list = new LinkedList<>();

	public Stack() {

	}

	public Stack(T elem) {
		push(elem);
	}

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public void push(T elem) {
		list.addFirst(elem);
	}

	public T pop() {

		if (isEmpty())
			throw new EmptyStackException();
		return list.removeFirst();
	}

	public T peek() {
		if (isEmpty())
			throw new EmptyStackException();
		return list.peekFirst();
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

}
