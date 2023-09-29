import java.util.Scanner;

public class training {

	public static void main(String[] args) {

//		Node newNode = new Node(12);
//		LinkedList list = new LinkedList(newNode);
		
		
//		list.insertLast(120);
//		list.insertLast(122);
//		list.insertFirst(899);
//		list.insertFirst(87);
//		list.insertLast(14);
//		list.insertFirst(27);
//		list.deleteFirst();
//		list.deleteFirst();
//		list.deleteFirst();
//		list.deleteFirst();
//		
//		list.printList();
//
//		LinkedList l2 = list.copy();
//		l2.printList();
//		System.out.println("-----------------");
////		l2.clear();
//		l2.sort(l2.head);
//		l2.printList();
////		l2.managedList(l2.head).toString();
//		l2.clear();
//		l2.insertLast(568);
//		l2.insertAfter(l2.getNode(568, 1), 98765);
//		System.out.println("-----------------");
////		l2.clear();
//		l2.sort(l2.head);
//		l2.deleteLast();
//		l2.printList();
//		System.out.println(l2.head.prev);
		
		
//		Queue queue = new Queue();
//		
//		queue.enQueue(237);
//		queue.enQueue(8765);
//		queue.enQueue(99857);
//		queue.enQueue("salam");
//		queue.printList();
//		System.out.println(queue.deQueue() + "******");
//		queue.printList();
//		
//		Stack stack = new Stack();
//		Scanner sc = new Scanner(System.in);
//		String s = sc.nextLine();
		
//		stack.push(12.5);
//		double d = (double)stack.pop().data;
//		System.out.println(("f(3)".split("\\("))[1].split("\\)")[0].split(",")[0]);
//		System.out.println(mainClass.computeEBARAT(mainClass.replaceLetterWithNum("12+x+5", 'x', "8")));
//		HashMap map = new HashMap();
//		map.put("f(x)", "x+2");
		System.out.println("f(g(9),g(3),7)".split("\\(" , 2)[1].substring(0,"f(g(9),g(3),7)".split("\\(" , 2)[1].length()-1 ).split(",")[2]);
		System.out.println("f(g(1),9)".split("\\(")[1].substring(0,"f(g(1),9)".split("\\(")[1].length() - 1).split(",")[0]);
//		System.out.println("-------------");
//		#define f(x,y,z)=x+y*z
//				#define g(x)=5+x
//				#print f(g(9),g(3),7)
//		stack.push("eshgham?");
//		stack.push("khoobi");
//		stack.push("salam");
//		System.out.println(stack.pop());
//		System.out.println(stack.pop());
//		System.out.println(stack.pop());
//		System.out.println(stack.pop());
		 
//		System.out.println(eval("8/(5*2-2*9"));
		
//		Tree tree = new Tree();
//		tree.root = new Node("salam");
//		tree.root.left = new Node("eshgham");
//		tree.root.right = new Node("Khoobi??");
//		LinkedList s = Tree.infixToPostfix("(-5+3)*(-6-7)*8*7*(-6)");
//		s.printList();
//		System.out.println(Tree.calPostfix(s));
		
//		String sl = "-12";
//		double d = Double.parseDouble(sl);
//		System.out.println(d);
//		
//		HashMap map = new HashMap();
//		map.put("20", "mahshid");
//		map.printMap();
		
//		Object[][] tamrin = new Object[2][3];
//		tamrin[0][0] = 14;
//		System.out.println(tamrin[0][2]);
		
	
		
		
	}
	
	public static double eval(final String str) {
	    return new Object() {
	        int pos = -1, ch;

	        void nextChar() {
	            ch = (++pos < str.length()) ? str.charAt(pos) : -1;
	        }

	        boolean eat(int charToEat) {
	            while (ch == ' ') nextChar();
	            if (ch == charToEat) {
	                nextChar();
	                return true;
	            }
	            return false;
	        }

	        double parse() {
	            nextChar();
	            double x = parseExpression();
	            if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
	            return x;
	        }

	        // Grammar:
	        // expression = term | expression `+` term | expression `-` term
	        // term = factor | term `*` factor | term `/` factor
	        // factor = `+` factor | `-` factor | `(` expression `)`
	        //        | number | functionName factor | factor `^` factor

	        double parseExpression() {
	            double x = parseTerm();
	            for (;;) {
	                if      (eat('+')) x += parseTerm(); // addition
	                else if (eat('-')) x -= parseTerm(); // subtraction
	                else return x;
	            }
	        }

	        double parseTerm() {
	            double x = parseFactor();
	            for (;;) {
	                if      (eat('*')) x *= parseFactor(); // multiplication
	                else if (eat('/')) x /= parseFactor(); // division
	                else return x;
	            }
	        }

	        double parseFactor() {
	            if (eat('+')) return parseFactor(); // unary plus
	            if (eat('-')) return -parseFactor(); // unary minus

	            double x;
	            int startPos = this.pos;
	            if (eat('(')) { // parentheses
	                x = parseExpression();
	                eat(')');
	            } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
	                while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
	                x = Double.parseDouble(str.substring(startPos, this.pos));
	            } else if (ch >= 'a' && ch <= 'z') { // functions
	                while (ch >= 'a' && ch <= 'z') nextChar();
	                String func = str.substring(startPos, this.pos);
	                x = parseFactor();
	                if (func.equals("sqrt")) x = Math.sqrt(x);
	                else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
	                else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
	                else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
	                else throw new RuntimeException("Unknown function: " + func);
	            } else {
	                throw new RuntimeException("Unexpected: " + (char)ch);
	            }

	            if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

	            return x;
	        }
	    }.parse();
	}
	
	
//#define f(x,y) = x+2^y
//#define g(t) = t^3+2
//#print 1^(g(g(f(1,0))))

}
