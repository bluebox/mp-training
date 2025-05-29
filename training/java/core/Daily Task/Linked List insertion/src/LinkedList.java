
public class LinkedList {
	public int val;
	public LinkedList next;
	public LinkedList(int val) {
		this.val=val;
		this.next=null;
	}
	public void add(int x,LinkedList head) {
		if(head==null) {
			head=new LinkedList(x);
		}
		else {
			LinkedList p = head;
			head.next=new LinkedList(x);
		}
	}
}
