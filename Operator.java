package com.vv.programming.vvcalc;

public class Operator {

	private final char value;
	private final int priority;

	public char getValue() {
		return value;
	}

	public int getPriority() {
		return priority;
	}

	private Operator(char value, int priority) {
		this.value = value;
		this.priority = priority;

	}

	public static final Operator PLUS = new Operator('+', 1);
	public static final Operator MINUS = new Operator('-', 1);
	public static final Operator MULTIPLY = new Operator('*', 2);
	public static final Operator DIVIDE = new Operator('/', 2);
	public static final Operator EXPONENT = new Operator('^', 3);

	public static Operator getOperator(char now) {
		switch (now) {
		case '+':
			return PLUS;
		case '-':
			return MINUS;
		case '*':
			return MULTIPLY;
		case '/':
			return DIVIDE;
		case '^':
			return EXPONENT;

		}
		return null;

	}

}
