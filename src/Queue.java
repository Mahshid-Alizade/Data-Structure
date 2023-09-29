
public class Queue {
	
	
	LinkedList list = new LinkedList();
	
	//ENQUEUE
	public void enQueue(Object n) {
		list.insertLast(n);
	}
	
	//DEQUEUE
	public Node deQueue() {
		if(list.isEmpty()) 
			return null;
		
		Node headTmp = list.head;
		list.deleteFirst();
		return headTmp;
	}
	
	
	//GETFIRST
	public Node getFirst() {
		return list.getFirst();
	}
	
	//GETSIZE
	public long getSize() {
		return list.getSize();
	}

	//ISEMPTY
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	//PRINTLIST
	public void printList() {
		list.printList();
	}
	
	//GETFRONT
	public Node getFront() {
		return list.head;
	}
	
	//GETREAR
	public Node getRear() {
		return list.tail;
	}
}
