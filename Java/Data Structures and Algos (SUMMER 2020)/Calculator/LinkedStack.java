
public class LinkedStack<T>{
	private int size;
	private Node head;
	
	public LinkedStack() {
		head = null;
	}
	
	public void push(T data) {
		head = new Node(data, head);
		size++;
	}
	
	public T pop() {
		if (head == null)
			throw new IllegalStateException();
		else {
			T returnedItem = (T)head.data;
			head = head.next;
			size--;
			return returnedItem;
		}
		
	}
	
	public T top() {
		return (T)head.data;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return (head == null);
	}
	
	private class Node<T>{
		private T data;
		private Node next;
		
		public Node() {
			data = null;
			next = null;
		}
		
		public Node(T newData, Node nextN) {
			data = newData;
			next = nextN;
		}
		
	}

}
