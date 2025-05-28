package Arrays;

public class LinkLis {
Node n1=null;
public void add(int data) {
	Node temp=new Node(data);
	if(n1==null) {
		n1=temp;
	}
	else {
	Node iter=n1;
	while(iter.next!=null) {
		iter=iter.next;
	}
	iter.next=temp;
	}
}

public void remove(int data) {
	Node iter=n1;
	if(iter==null) {
		System.out.println("Underflow error");
	}
	else {
		while(iter.next!=null) {
			if(iter.next.data==data) {
			}
		}
	}
}
}

