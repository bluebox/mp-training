package July1;

public class Node extends ListItem implements Comparable<ListItem>{

	public Node(Object value) {
		super(value);
	}

	@Override
	ListItem next() {
		return this.rightLink;
	}

	@Override
	ListItem setNext(ListItem right) {
		this.rightLink = right;
		return this.rightLink;
	}

	@Override
	ListItem previous() {
		return this.leftLink;
	}

	@Override
	ListItem setPrevious(ListItem left) {
		this.leftLink = left;
		return this.leftLink;
	}

	@Override
	public
	int compareTo(ListItem litem) {
		if(litem!=null) {
			return((String) super.getValue()).compareTo((String) litem.getValue());
		}else {
			return -1;
		}
	}
}
