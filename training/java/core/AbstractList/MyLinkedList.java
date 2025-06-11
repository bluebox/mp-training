package AbstractList;

public class MyLinkedList implements NodeList {

	ListItem root;

	public MyLinkedList(ListItem root) {
		this.root = root;
	}

	@Override
	public ListItem getRoot() {
		return this.root;
	}

	@Override
	public boolean addItem(ListItem item) {
		if (root == null) {
			this.root = item;
			return true;
		}
		ListItem curr = this.root;
		while (curr != null) {
			int comparison = curr.compareTo(item);
			if (comparison < 0) {
				if (curr.next() != null) {
					curr = curr.next();
				} else {
					curr.setNext(item).setPrevious(curr);
					return true;
				}
			} else if (comparison > 0) {
				if (curr.previous() != null) {
					curr.previous().setNext(item).setPrevious(curr.previous());
					item.setNext(this.root).setPrevious(item);
					this.root.setPrevious(item);
					this.root = item;
				}
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean removeItem(ListItem item) {
		if (item == null)
			return false;
		ListItem curr = this.root;
		while (curr != null) {
			int comparison = curr.compareTo(item);
			if (comparison == 0) {
				if (curr == this.root) {
					this.root = curr.next();
				} else {
					curr.previous().setNext(item.next());
					if (curr.next() != null) {
						curr.next().setPrevious(curr.previous());
					}
				}
				return true;
			} else if (comparison < 0) {
				curr = curr.next();
			} else {
				return false;
			}
		}

		return false;
	}

	@Override
	public void traverse(ListItem root) {
		
		if(root==null) {
			System.out.println("List is empty");
		}
		else {
			while(root!=null) {
				System.out.println(root.getValue());
				root=root.next();
			}
		}
	}

}
