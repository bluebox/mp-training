package challanges;
import java.util.*;
public class EvenNumsFirst {
	
	class Node{
		int data;
		Node next;
		Node(int val){
			this.data=val;
			this.next=null;
		}
	}
	
	Node head=null;
	
	public void insertData(int data) {
		Node newNode=new Node(data);
		if(head==null) {
			head=newNode;
			return;
		}
		Node temp=head;
		while(temp.next!=null) {
			temp=temp.next;
		}
		temp.next=newNode;
	}
	
	public void printEvenFirst() {
		Node temp=head;
		while(temp.next!=null) {
			System.out.print(temp.data+" ->");
			temp=temp.next;
		}
		System.out.println(temp.data);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EvenNumsFirst ln=new EvenNumsFirst();
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the no of elements : ");
		int n=sc.nextInt();
		int[] ar=new int[n];
		System.out.print("enter the elements : ");
		for(int i=0;i<n;i++) {
			ar[i]=sc.nextInt();
		}
		System.out.println("Array is : "+Arrays.toString(ar));
		for(int ele:ar) {
			if(ele%2==0) {
			ln.insertData(ele);
			}
		}
		for(int ele:ar) {
			if(ele%2!=0) {
			ln.insertData(ele);
			}
		}
		System.out.println("Even Numbers First then odd number next :");
		ln.printEvenFirst();
		sc.close();
	}

}
