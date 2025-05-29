package abstractClassLinkedListAndBinaryTrees;

public class Node extends ListItem{

	public Node(Object value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public ListItem previous() {
		
		// TODO Auto-generated method stub
		return this.leftLink;
	}

	

	@Override
	public int compareTo(ListItem item) {
		// TODO Auto-generated method stub
		
		return ((String) super.getValue()).compareTo((String) item.getValue());
	}

	@Override
	public ListItem next() {
		// TODO Auto-generated method stub
		return this.rightLink;
	}

	@Override
	public ListItem setNext(ListItem i) {
		// TODO Auto-generated method stub
		this.leftLink=i;
		return this.leftLink;
	}

	@Override
	public ListItem setPrevious(ListItem i) {
		// TODO Auto-generated method stub
		this.leftLink=i;
		return this.previous();
		
	}

}
