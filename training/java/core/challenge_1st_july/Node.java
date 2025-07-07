package challenge_1st_july;
public class Node extends ListItem {

	public Node(Object value) {
		super(value);
	}

	@Override
	ListItem next() {
		return this.next;
	}

	@Override
	ListItem setNext(ListItem item) {
		this.next = item;
		return item;
	}

	@Override
	ListItem previous() {
		return this.previous;
	}

	@Override
	ListItem setPrevious(ListItem item) {
		this.previous = item;
		return item;
	}

	@Override
	int compareTo(ListItem item) {
		if (item == null)
			return -1;
		return ((String) this.value).compareTo((String) item.value);
	}

}
