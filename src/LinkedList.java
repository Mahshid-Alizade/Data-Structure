
public class LinkedList {

	Node head;
	Node tail;
	long size = 0;
	String stringForm = "";

	// constructor
	public LinkedList(Node head) {
		this.head = head;
		this.tail = head;
		size++;
	}
	
	public LinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	// inserts method
	// wanted method
	public void insertFirst(Object data) {

		if (head == null) {
			head = new Node(data);
			tail = head;
			size++;
		} else {
			Node tmp = new Node(data);
			tmp.prev = null;
			tmp.next = head;
			head = tmp;
			head.next.prev = head;
			size++;
		}
	}

	// insert after
	public void insertAfter(Node prevNode, Object newData) {

		if (prevNode == null) {
			System.out.println("The given previous node cannot be null");
			return;
		}

		if (prevNode == tail) {
			insertLast(newData);
			return;
		}

		Node newNode = new Node(newData);
		newNode.next = prevNode.next;
		prevNode.next = newNode;
		newNode.prev = prevNode;
		newNode.next.prev = newNode;
		size++;
		return;
	}

	// insert last
	// wanted method
	public void insertLast(Object data) {

		if (head == null) {
			head = new Node(data);
			tail = head;
			size++;
			return;
		}

		else {
			tail.next = new Node(data);
			tail.next.prev = tail;
			tail = tail.next;
			size++;
			return;
		}
	}

	// print list
	public void printList() {

		Node tmp = this.head;
		while (tmp != null) {
			System.out.println(tmp.data);
			stringForm += tmp.data + " ";
			tmp = tmp.next;
		}
		
	}

	// size of linked list
	// wanted method
	public long getSize() {
		return size;
	}

	// delete method
	public void delete(Object data) {

		Node tmp = head;

		if (tmp.data == data) {
			head = tmp.next;
			size--;
			return;
		}

		while (tmp.next != null) {
			if (tmp.next.data == data) {
				if (getNode(data, 1).next != null)
					getNode(data, 1).next.prev = getNode(data, 1).prev;
				if (getNode(data, 1).prev != null)
					getNode(data, 1).prev.next = getNode(data, 1).next;

				size--;
				return;
			}

			tmp = tmp.next;
		}
		return;
	}

	// delete first
	// wanted method
	public void deleteFirst() {
		if(size == 1) {
			clear();
		}
		else if (head != null) {
			head = head.next;
			head.prev = null;
			size--;
		}
	}

	// delete last
	// wanted method
	public void deleteLast() {
		tail = tail.prev;
		tail.next = null;
	}

	// copy a linked list
	public LinkedList copy() {

		LinkedList copyList = new LinkedList(head);
		copyList.head = head;
		Node newList = null;
		Node currNode = head;

		while (currNode != null) {
			if (newList == null) {
				newList = new Node(currNode.data, null);
			} else {
				copyList.insertLast(currNode.data);
			}

			currNode = currNode.next;
		}
		return copyList;
	}

	// is empty or not?
	// wanted method
	public boolean isEmpty() {

		if (this.size == 0)
			return true;

		return false;

	}

	// get midde node of a linkedList
	public Node getMiddle(Node head) {

		if (head == null)
			return head;

		Node fast = head.next;
		Node slow = head;

		while (fast != null) {
			fast = fast.next;
			if (fast != null) {
				slow = slow.next;
				fast = fast.next;
			}
		}
		return slow;
	}

	// merge
	public Node merge(Node head1, Node head2) {

		if (head1 == null) {
			return head2;
		}
		if (head2 == null) {
			return head1;
		}

		//if data of first list is smaller than the second one
		if ((int) head1.data <= (int) head2.data) {
			
			head1.next = merge(head1.next, head2);
			head1.next.prev = head1;
			head1.prev = null;
			this.head = head1;
			return head1;

		} else {
			
			head2.next = merge(head2.next, head1);
			head2.next.prev = head2;
			head2.prev = null;
			this.head = head2;
			return head2;
		}

	}

	// manage list
	public Node managedList(Node head) {

		Node newHead = new Node(head.data);
		LinkedList lRes = new LinkedList(newHead);

		while (head != null) {
			lRes.insertLast(head.data);
			head = head.next;
		}

		return newHead;
	}

	// merge sort
	public Node sort(Node head) {

		if (head == null || head.next == null) {
			return head;
		}

		Node middle = getMiddle(head);
		Node nextOfMiddle = middle.next;

		middle.next = null;

		Node left = sort(head);
		Node right = sort(nextOfMiddle);

		return merge(left, right);
	}

	// return the node of that data
	public Node getNode(Object data, int x) {

		Node tmp = head;
		int counter = 0;

		while (tmp != null) {
			
			if (tmp.data.equals(data)) {
				counter++;
				if (counter == x)
					return tmp;
			} else {
				tmp = tmp.next;
			}
		}

		return null;
	}

	// get first/head
	// wanted method
	public Node getFirst() {
		if(this.isEmpty())
			return null;
		return head;
	}

	// get last/tail
	// wanted method
	public Node getLast() {
		if(this.isEmpty())
			return null;
		return tail;
	}

	// clear the linkedList
	// wanted method
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}
	
	
	//it get String and convert it to linked list
	public static LinkedList stringToLinkedList(String s) {
		LinkedList list = new LinkedList();
		String array[] = s.split(" ");
		for(int i = 0 ; i < array.length ; i++) {
			list.insertLast(array[i]);
		}
		list.printList();
		return list;
	}

	//return String form of linked list
	public String getStringForm() {
		
		Node tmp = this.head;
		while (tmp != null) {
			stringForm += tmp.data + " ";
			tmp = tmp.next;
		}
		
		return stringForm.trim();
	}
	
}
