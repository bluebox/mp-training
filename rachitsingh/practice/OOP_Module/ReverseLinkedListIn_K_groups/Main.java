package ReverseLinkedListIn_K_groups;


public class Main 
{
	public static void main(String [] args)
	{
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		
		int k = 3;
		head = reverseInKGroups(head, k);
		
		ListNode current = head;
		while(current != null)
		{
			System.out.print(current.data + "->");
			current = current.next;
		}
		
	}
	public static ListNode reverseInKGroups(ListNode head, int k)
	{
		if(head == null || k == 1)
		{
			return head;
		}
		
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode previous = dummy;
		
		int length = 0;
		ListNode current = head;
		while(current!= null)
		{
			length++;
			current = current.next;
		}
		if(k > length)
		{
			return head;
		}
		
		while(length >= k)
		{
			ListNode currentPtr = previous.next;
			ListNode nextPtr = currentPtr.next;
			
			for(int i = 0; i<k-1; i++)
			{
				currentPtr.next = nextPtr.next;
				nextPtr.next = previous.next;
				previous.next = nextPtr;
				nextPtr = currentPtr.next;
			}
			previous = currentPtr;
			length -= k;
		}
		return dummy.next;
	}
}
