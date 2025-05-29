package Linkedlist;

public class LinkMain {
	public static void main(String[] args) {
		LinkLis linkedList=new LinkLis();
		linkedList.add(1);
		linkedList.add(32);
		linkedList.add(5);
		linkedList.add(112);
		linkedList.add(18);
		LinkLis linkedList1=new LinkLis();
		LinkLis linkedList2=new LinkLis();
		Node iter=linkedList.head;
		while(iter!=null) {
			if(iter.data%2==0) {
				linkedList1.add(iter.data);
			}
			else {
				linkedList2.add(iter.data);
			}
			iter=iter.next;
		}
		linkedList1.merge(linkedList2);
		linkedList1.display();
	}

}
