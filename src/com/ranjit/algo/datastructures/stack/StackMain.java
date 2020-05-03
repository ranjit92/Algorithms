package com.ranjit.algo.datastructures.stack;

public class StackMain {

	public static void main(String[] args) {

		String str1 = "{}[]()";
		System.out.println(isBracketsValid(str1));
	}

	public static boolean isBracketsValid(String bracketStr) {
		Stack<Character> stack = new Stack<>();
		String openBrackets = "{([";
		for (int i = 0; i < bracketStr.length(); i++) {
			char reverseBracket = getReverseBracket(bracketStr.charAt(i));
			if (openBrackets.contains(Character.toString(bracketStr.charAt(i)))) {
				stack.push(bracketStr.charAt(i));
			} else if (stack.isEmpty() || !stack.pop().equals(reverseBracket))
				return false;
		}
		return stack.isEmpty();
	}

	private static char getReverseBracket(char charAt) {

		switch (charAt) {
		case '}':
			return '{';
		case ')':
			return '(';
		case ']':
			return '[';
		}
		return 0;
	}
}
