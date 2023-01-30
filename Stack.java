package com.vv.programming.vvcalc;

import java.util.ArrayList;
import java.util.List;

public class Stack<T> {
	private List<T> items = new ArrayList<>();

	public void push(T token) {
		items.add(token);
	}

	public T pop() {
		if (items.size() > 0) {
			return items.remove((getSize() - 1));
		} else {
			return null;
		}
	}

	public T getTopValue() {
		if (items.size() > 0) {
			return items.get((getSize() - 1));
		} else {
			return null;
		}
	}

	public int getSize() {
		return items.size();
	}

}
