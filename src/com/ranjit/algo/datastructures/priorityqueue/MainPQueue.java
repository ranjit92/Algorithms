package com.ranjit.algo.datastructures.priorityqueue;

public class MainPQueue {
	
	
	public static void main(String[] args) {
		Integer[] array = {16,14,12,8,6,5,1};
		
		PQueue<Integer> pq = new PQueue<Integer>(array);
		System.out.println(pq.toString());
		System.out.println(pq.isMinHeap(0));
		
	}

}
