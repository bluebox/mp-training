package abstractClassLinkedListAndBinaryTrees;

public abstract class ListItem {
	protected ListItem rightLink;
	protected ListItem leftLink;
	Object value;
	public ListItem(Object value) {
		super();
		this.value = value;
	}
	public abstract ListItem next();
	public abstract ListItem previous();
	public abstract ListItem setNext(ListItem i);
	public abstract ListItem setPrevious(ListItem i);
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public abstract int compareTo(ListItem item);
}
