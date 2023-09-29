
public class Node {
	
	Object data;
	Node next, prev;
	Node left, right;

	//constructor of LinkedList
	public Node() {
		data = null;
		next = null;
		prev = null;
	}
	
	//constructor of LinkedList
	public Node(Object data , Node next) {
		this.data = data;
		this.next = this.prev = next;
	}
	
	//constructor of LinkedList
	public Node(Object data ) {
		this.data = data;
		this.next = this.prev = null;
	}
	
	//constructor of Tree
	public Node(Object data , Object tree) {
		this.data= data;
		left = right = null;
	}
	
	public String toString() {
		return data + "";
	}
	

}
