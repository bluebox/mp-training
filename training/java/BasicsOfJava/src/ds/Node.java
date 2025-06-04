package ds;

public class Node extends ListItem{

	public Node(ListItem obj) {
		super(obj);
	}
	
	public ListItem next() {
		
		return rightLink;
	}
	
	public ListItem setNext(ListItem item) {
		
		this.rightLink = item;
		return rightLink;
	}
	
	public ListItem previous() {
		
		return leftLink;
	}
	
	public ListItem setPrevious(ListItem item) {
		
		this.leftLink = item;
		return leftLink;
	}
	
	
	public int compareTo(ListItem item) {
		
		this.value.equals(item);
		return 0;
	}

}
