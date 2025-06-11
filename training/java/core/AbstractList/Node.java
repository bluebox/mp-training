package AbstractList;

public class Node extends ListItem{
	
	public Node(Object value) {
		super(value);
	}

	@Override
	public ListItem next() {
		return this.rightlink;
	}

	@Override
	public ListItem setNext(ListItem item) {
		this.rightlink=item;
		return this.rightlink;
	}

	@Override
	public ListItem previous() {
		
		return this.leftLink;
	}

	@Override
	public ListItem setPrevious(ListItem item) {
		this.leftLink=item;
		return this.leftLink;
	}

	@Override
	public int compareTo(ListItem item) {
		
		if(item!=null) {
			return ((String) this.getValue()).compareTo((String)item.getValue());
		}
		
		return -1;
	}
	

}
