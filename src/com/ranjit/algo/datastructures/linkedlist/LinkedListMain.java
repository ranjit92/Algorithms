package com.ranjit.algo.datastructures.linkedlist;

public class LinkedListMain {

	public static void main(String[] args) {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.add(1);
		list.add(2);
		System.out.println(list.toString());
		list.addFirst(3);
		System.out.println(list);
		list.addLast(4);
		System.out.println(list);
		System.out.println(list.size());
		
		System.out.println(list.indexOf(1));
		list.remove(3);
		System.out.println(list);
	}
}
