package ds;

public abstract class ListItem {
	
	protected ListItem leftLink;
	protected ListItem rightLink;
	protected ListItem value;
	
	public ListItem(ListItem obj) {
		this.value = obj;
	}
	
	public abstract ListItem next();
	
	public abstract ListItem setNext(ListItem item);
	
	public abstract ListItem previous();
	
	public abstract ListItem setPrevious(ListItem item);
	
	public ListItem getValue() {
		
		return value;
	}
	

	public void setValue(ListItem value) {
		
		this.value = value;
	}
	
	public abstract int compareTo(ListItem item);
	
}
