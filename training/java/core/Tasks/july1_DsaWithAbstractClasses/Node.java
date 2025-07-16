package corejava.july1_DsaWithAbstractClasses;

public class Node extends ListItem{
	
	public Node(Object value) {
		super(value);
	}
	
	ListItem next() {
		return rightLink;
	}
	    
	ListItem setNext(ListItem item) {
		this.rightLink = item;
		return this.rightLink;
	}

	ListItem previous() {
		return leftLink;
	}

	    
	ListItem setPrevious(ListItem item) {
		this.leftLink = item;
	    return this.leftLink;
	}

	int compareTo(ListItem item) {
		if (this.value == null) 
			return (item.value == null) ? 0 : -1;
	    if (item.value == null) 
	    	return 1;

	    if (!(value instanceof Comparable)) {
	    	throw new IllegalStateException("Value must be Comparable");
	    }

	    return ((Comparable<Object>)this.value).compareTo(item.getValue());
	}
}
