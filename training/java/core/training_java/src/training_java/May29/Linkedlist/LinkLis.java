package Linkedlist;

public class LinkLis {
	Node head = null;

	public void add(int data) {
		Node temp = new Node(data);
		if (head == null) {
			head = temp;
		} else {
			Node iter = head;
			while (iter.next != null) {
				iter = iter.next;
			}
			iter.next = temp;
		}
	}

	public void display() {
		Node iter = head;
		while (iter != null) {
			System.out.print(" " + iter.data + "---->");

			iter = iter.next;
		}
		System.out.print("  Null");
	}

	public void merge(LinkLis l2) {
		Node iter = head;
		if (head == null) {
			head = l2.head;
		} else {
			while (iter.next != null) {
				iter = iter.next;
			}
			iter.next = l2.head;
		}
	}

}
