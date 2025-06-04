package ds;

public class MyLinkedList implements NodeList{
	
	private ListItem root;
	
	public MyLinkedList(ListItem root) {
		
		this.root = root;
	}
	
	public ListItem getRoot() {
		
		return root;
	}
	
}
