package AbstractClasses;

public class Node <T> extends ListItem{
	public Node(Object value) {
		super(value);
	}

	@Override
	ListItem next() {
		return rightLink;
		
	}

	@Override
	ListItem setNext(ListItem right) {
		rightLink=right;
		return rightLink;
	}

	@Override
	ListItem previous() {
		return  leftLink;
	}

	@Override
	ListItem setPrevious(ListItem prev) {
		leftLink=prev;
		return leftLink;
	}

	@Override
	int compareTo(ListItem l) {
		return ((Integer) this.getValue()).compareTo((Integer)l.getValue());
		
	}


}
