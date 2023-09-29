import java.awt.Color;

public class Tree {

	// Root of Binary Tree
	Node root;

	// Constructors
	Tree(Object key) {
		root = new Node(key);
	}

	Tree() {
		root = null;
	}

	String preResult = "";

	public String preOrder(Node t) {
		if (t == null)
			return preResult;

		preResult += t.data + "\n";
		preOrder(t.left);
		preOrder(t.right);

		return preResult;
	}

	String postResult = "";

	public String postOrder(Node t) {
		if (t == null)
			return postResult;

		postOrder(t.left);
		postOrder(t.right);
		postResult += t.data + "\n";

		return postResult;
	}

	String inResult = "";

	public String inOrder(Node t) {
		if (t == null)
			return inResult;

		inOrder(t.left);
		inResult += t.data + "\n";
		inOrder(t.right);

		return inResult;
	}

	// The methods of expression tree
	static boolean isOperator(char c) {
		return (c == '+' || c == '-' || c == '*' || c == '/' || c == '^');
	}

	// The methods of expression tree
	static boolean isOperatorExeptNegative(char c) {
		return (c == '+' || c == '*' || c == '/' || c == '^');
	}

	static boolean isOprand(char c) {
		return (c != '+' && c != '-' && c != '*' && c != '/' && c != '^' && c != '(' && c != ')');
	}

	// priority
	static int priority(char ch) {
		switch (ch) {
		case '+':
		case '-':
			return 1;

		case '*':
		case '/':
			return 2;

		case '^':
			return 3;
		}
		return -1;
	}

	public static LinkedList infixToPostfix(String infix) {
		LinkedList l = new LinkedList();
		String tmp = "";
		String result = "";
		Stack s = new Stack();

		if (mainClass.manageErrors(infix)) {
			mainClass.errorLabel.setForeground(Color.RED);
			mainClass.errorLabel.setText(">>err1 : wrong exp");
			mainClass.answerArea.append(">>err1 : wrong exp" + "\n");
			return null;
		}

		for (int i = 0; i < infix.length(); i++) {

			// if the char is litter or digits
			if (!isOperator(infix.charAt(i)) && infix.charAt(i) != '(' && infix.charAt(i) != ')'
					&& infix.charAt(i) != ' ') {
				while (i < infix.length() && !isOperator(infix.charAt(i)) && infix.charAt(i) != '('
						&& infix.charAt(i) != ')' && infix.charAt(i) != ' ') {
					result += infix.charAt(i) + "";
					tmp += infix.charAt(i);
					i++;
				}

				l.insertLast(tmp);
				tmp = "";
				i--;
			}

			// "("
			else if (infix.charAt(i) == '(')
				s.push(infix.charAt(i));

			// ")"
			else if (infix.charAt(i) == ')') {

				String tmp2 = "";

				while (!s.isEmpty() && !s.peek().data.equals('(')) {
					tmp2 = s.pop().data + "";
					result += tmp2;
					l.insertLast(tmp2);
				}

				if (!s.isEmpty() && !s.peek().data.equals('(')) {
					mainClass.errorLabel.setText(">>err3 : wrong syntax");
				} else
					s.pop();
			} else if (infix.charAt(i) != ' ') {// oprator
				String tmp2 = "";
				if (i == 0) {
					s.push(infix.charAt(i));
					if (priority((char) s.peek().data) == 1) {
						tmp2 = (char) s.pop().data + "";
						i++;
						while (i < infix.length() && !isOperator(infix.charAt(i)) && infix.charAt(i) != '('
								&& infix.charAt(i) != ')' && infix.charAt(i) != ' ') {
							result += infix.charAt(i) + "";
							tmp2 += infix.charAt(i);
							i++;
						}

						l.insertLast(tmp2);
						tmp2 = "";
						i--;
					}

				} else if (infix.charAt(i) == '-' && infix.charAt(i + 1) != '(' && infix.charAt(i - 1) != ')'
						&& !isOprand(infix.charAt(i - 1))) {
					tmp2 += infix.charAt(i);
					i++;
					while (i < infix.length() && !isOperator(infix.charAt(i)) && infix.charAt(i) != '('
							&& infix.charAt(i) != ')' && infix.charAt(i) != ' ') {
						result += infix.charAt(i) + "";
						tmp2 += infix.charAt(i);
						i++;
					}

					l.insertLast(tmp2);
					tmp2 = "";
					i--;

				} else {
					while (!s.isEmpty() && (priority(infix.charAt(i)) <= priority((char) s.peek().data))) {
						tmp2 = s.pop() + "";
						result += tmp2;
						l.insertLast(tmp2);
					}

					s.push(infix.charAt(i));
				}
			}
		}

		while (!s.isEmpty())

		{
			String tmp2 = s.pop().data + "";
			result += tmp2;
			l.insertLast(tmp2);
		}

		return l;
	}

	// calculate Postfix
	public static String calPostfix(LinkedList postfix) {

		Stack stack = new Stack();
		char c = ' ';
		boolean itsChar = false;
		long size = postfix.size;

		if (postfix == null)
			return null;

		for (int i = 0; i < size; i++) {
			// char c = postfix.charAt(i);
			if (((String) postfix.getFirst().data).trim().length() == 1) {
				c = ((String) (postfix.getFirst().data)).charAt(0);
				postfix.deleteFirst();
				itsChar = true;
			} else {

				try {
					stack.push(Double.parseDouble((String) postfix.getFirst().data));
				} catch (NumberFormatException e) {
					// mainClass.errorLabel.setForeground(Color.RED);
					// mainClass.errorLabel.setText(">>err1 : wrong exp");
					// mainClass.answerArea.append(">>err1 : wrong exp" + "\n");
					// return null;
				}
				// stack.printList();
				postfix.deleteFirst();
				itsChar = false;
			}

			if (!isOperator(c) && itsChar) {
				try {
					if (c == '(') {
						mainClass.errorLabel.setForeground(Color.RED);
						mainClass.errorLabel.setText(">>err3 : wrong syntax");
						mainClass.answerArea.append(">>err3 : wrong syntax" + "\n");
						return null;
					}
					stack.push(Double.parseDouble(c + ""));
				} catch (NumberFormatException e) {
					mainClass.errorLabel.setForeground(Color.RED);
					mainClass.errorLabel.setText(">>err2 : Undefined value");
					mainClass.answerArea.append(">>err2 : Undefined value" + "\n");
					return null;
				}
				continue;
			} else if (isOperator(c)) {

				double i1 = 0, i2 = 0;

				try {
					i1 = (double) stack.pop().data;
					i2 = (double) stack.pop().data;
				} catch (NullPointerException e) {
					mainClass.errorLabel.setForeground(Color.RED);
					mainClass.errorLabel.setText(">>err3 : wrong syntax");
					mainClass.answerArea.append(">>err3 : wrong syntax" + "\n");
					return null;
				}

				switch (c) {
				case '+':
					stack.push(i2 + i1);
					break;

				case '-':
					stack.push(i2 - i1);
					break;

				case '/':
					stack.push(i2 / i1);
					break;

				case '*':
					stack.push(i2 * i1);
					break;
				case '^':
					stack.push(Math.pow(i2, i1));
					break;
				}
				c = ' ';
			}

			// stack.printList();
		}

		return stack.pop() + " ";
	}

}
