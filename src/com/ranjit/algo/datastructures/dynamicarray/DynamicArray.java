package com.ranjit.algo.datastructures.dynamicarray;

import java.util.Arrays;
import java.util.Iterator;

public class DynamicArray<T> implements Iterable<T> {

	private T[] arrElement;
	
//	length user thinks array is
	int length = 0;
	
//	Actual array size
	int capacity = 0;
	
	public DynamicArray() {
		this(16);
	}
	
	@SuppressWarnings("unchecked")
	public DynamicArray(int capacity) {
		if(capacity < 0) throw new IllegalArgumentException("Illegal Capacity "+ capacity);
		this.capacity = capacity;
		arrElement = (T[]) new Object[capacity];
	}
	
	public int size() {
		return length;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public T get(int index) {
		if(index > length  || index < 0) throw new ArrayIndexOutOfBoundsException();
		return arrElement[index];
	}
	
	public void set(int index, T elem) {
		if(index > length || index < 0) throw new ArrayIndexOutOfBoundsException();
		arrElement[index] = elem;
	}
	
	
	public void clear() {
		for(int i = 0; i < length ; i++) 
			arrElement[i] = null;
		length = 0;
	}
	
	@SuppressWarnings("unchecked")
	public void add(T elem) {
		
		if(length + 1 > capacity) {
			if(capacity == 0)
				capacity = 1;
			else
				capacity *= 2;
			
			T[] newArray = (T[]) new Object[capacity];
			for(int i = 0; i < length ; i++) {
				newArray[i] = arrElement[i];
			}
			arrElement = newArray;
		}
		
		arrElement[length++] = elem; 
			
	}
	
	
	@SuppressWarnings("unchecked")
	public T removeAt(int index) {
		if(index > length || index < 0) throw new ArrayIndexOutOfBoundsException();
		T data = arrElement[index];
		T[] newArray = (T[]) new Object[length -1];
		
		for(int i = 0, j = 0; i < length ; i++, j++ ) {
			if(index == i)
				j--;
			else
				newArray[j] = arrElement[i];
		}
		arrElement = newArray;
		capacity = --length;
		return data;
	}
	
	
	public int indexOf(Object object) {
		for(int i = 0; i < length; i++) {
			if(object == null) {
				if(arrElement[i] == null)
					return i;
			}
			else if(object.equals(arrElement[i]))
				return i;
				
		}
		return -1;
	}
	
	
	
	public boolean remove(Object obj) {
		
		int index = indexOf(obj);
		if(index == -1) return false;
		
		removeAt(index);
		return true;
	}
	
	
	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}


	@Override
	public Iterator<T> iterator() {
		
		return new Iterator<T>() {
			int index = 0;
			
			@Override
			public boolean hasNext() {
				return index < length;
			}

			@Override
			public T next() {
				
				return arrElement[index++];
			}
		};
	}

	@Override
	public String toString() {
		if(length == 0) return "[]";
		else {
			StringBuilder strBuild = new StringBuilder(length);
			strBuild.append("[");
			for(int i = 0; i < length-1; i++){
				strBuild.append(arrElement[i]+" ,");
			}
			return strBuild.append(arrElement[length-1]+"]").toString();
		}
			
	}
	
	
	

}
