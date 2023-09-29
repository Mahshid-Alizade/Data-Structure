
public class Stack {
	
	LinkedList list = new LinkedList();
	
	//POP
	public Node pop() {
		if(!list.isEmpty()) {
			Node headTmp = list.head;
			list.deleteFirst();
			return headTmp;
		}
		return null;
	}
	
	//PUSH
	public void push(Object o) {
		list.insertFirst(o);
	}
	
	//PEEK
	public Node peek() {
		return list.head;
	}
	
	//GETSIZE
	public long getSize() {
		return list.getSize();
	}
	
	//CLEAR
	public void clear() {
		list.clear();
	}
	
	//ISEMPTY
	boolean isEmpty() {
		return list.isEmpty();
	}
	
	//PRINTLIST
	public void printList() {
		list.printList();
	}
}
