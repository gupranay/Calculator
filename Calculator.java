package com.vv.programming.vvcalc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Calculator {
	private static final Operator[] operators = { Operator.PLUS, Operator.MINUS, Operator.MULTIPLY, Operator.DIVIDE,
			Operator.EXPONENT };
	private static final char[] nums = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.' };
	private static final char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
			'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	public static void main(String[] args) {
		String userInput = acceptCalc();
		ArrayList<Token> list = populateArray(userInput);

		String answer = evaluate(postFix(list));

		System.out.println("Answer: " + answer);
	}

	private static ArrayList<Token> populateArray(String userInput) {
		ArrayList<Token> list = new ArrayList<>();
		boolean isCalc = true;
		String current = "";
		userInput += " ";
		for (int i = 0; i < userInput.length(); i++) {
			char now = userInput.charAt(i);
//			char now2 = userInput.charAt(i-2);
				if (isAlpha(now)) {
				System.out.println("You can't use letters for this calculator");
				isCalc = false;
				break;
			}
			
				if (userInput.startsWith("-")) {
					Token token = new Token();
					token.setType(TokenType.OPERAND);
						token.setValue(userInput.substring(0, 1));
					}
				
			
			if (isDigit(now)) {
				current = current + now;
			} else if (!"".equals(current)) {
				Token token = new Token();
				token.setValue(current.trim());
				token.setType(TokenType.OPERAND);
				current = "";
				list.add(token);
			}
			
			
			

			Operator currentOperator = getOperator(now);
			if (currentOperator != null) {
				Token token = new Token();
				token.setOperator(currentOperator);
				token.setType(TokenType.OPERATOR);
				list.add(token);
			} else if ('(' == now || ')' == now) {
				Token token = new Token();
				token.setValue("" + now);
				// token.setType(TokenType.OPERATOR);
				list.add(token);
			}

		}
		for (int i = 0; i < list.size() && isCalc; i++) {
			System.out.print(list.get(i) + " , ");
		}
		return list;

	}

	private static ArrayList<Token> postFix(ArrayList<Token> list) {
		Stack<Token> stack = new Stack<>();
		ArrayList<Token> postFix = new ArrayList<>();
		int i = 0;
		while (i < list.size()) {

			Token currentToken = list.get(i);

			if (TokenType.OPERAND.equals(currentToken.getType())) {
				postFix.add(currentToken);
			}

			if ("(".equals(currentToken.getValue())) {
				stack.push(currentToken);
			}
			if (")".equals(currentToken.getValue())) {
				while (stack.getSize() != 0 && !"(".equals(stack.getTopValue().getValue())) {
					postFix.add(stack.pop());
				}
				// stack.pop();
			}
			if (TokenType.OPERATOR.equals(currentToken.getType())) {
				if (stack.getSize() == 0 || "(".equals(stack.getTopValue().getValue())) {
					stack.push(currentToken);
				} else {

					Token stacktopvalue = stack.getTopValue();

					while (stack.getSize() != 0 && !"(".equals(stacktopvalue.getValue())
							&& currentToken.getOperator().getPriority() <= stacktopvalue.getOperator().getPriority()) {
						postFix.add(stack.pop());
					}
					stack.push(currentToken);
				}
			}
			i++;
		}
		while (stack.getSize() != 0) {
			postFix.add(stack.pop());
		}
		return postFix;

	}

	public static String evaluate(ArrayList<Token> postFixTokenList) {

		System.out.println("\npostfix list: " + postFixTokenList);

		Stack<Token> stack = new Stack<>();
		for (int i = 0; i < postFixTokenList.size(); i++) {
			Token currentPostFixToken = postFixTokenList.get(i);
			if (TokenType.OPERAND.equals(currentPostFixToken.getType())) {
				stack.push(currentPostFixToken);
			}
			if (TokenType.OPERATOR.equals(currentPostFixToken.getType())) {
				double a = Double.parseDouble(stack.pop().getValue());
				double b = Double.parseDouble(stack.pop().getValue());
				Token op = postFixTokenList.get(i);
				Token done = new Token();
				done.setType(TokenType.OPERAND);
				switch (op.getValue()) {
				case "+":
					done.setValue((a + b) + "");
					break;
				case "-":
					done.setValue((a - b) + "");
					break;
				case "*":
					done.setValue((a * b) + "");
					break;
				case "/":
					done.setValue((a / b) + "");
					break;
				case "^":
					done.setValue((Math.pow(b, a)) + "");
					break;
				}
				stack.push(done);
			}
		}
		return stack.pop().getValue();
	}

	private static Operator getOperator(char now) {
		boolean isOperator = false;
		for (int i = 0; i < operators.length; i++) {
			if (now == operators[i].getValue()) {
				return operators[i];
			}
		}
		return null;
	}

	private static String acceptCalc() {
		System.out.println("Type in something to calculate");
		Scanner scan = new Scanner(System.in);
		String userInput = scan.nextLine();
		return userInput;
	}

	private static boolean isDigit(char userInput) {

		boolean isDigit = false;
		for (int i = 0; i < nums.length; i++) {
			if (userInput == nums[i]) {
				isDigit = true;
				break;
			}
		}
		return isDigit;
	}

	private static boolean isAlpha(char userInput) {

		boolean isAlpha = false;
		for (int i = 0; i < alphabet.length; i++) {
			if (userInput == alphabet[i]) {
				isAlpha = true;
				break;
			}
		}
		return isAlpha;
	}

}
