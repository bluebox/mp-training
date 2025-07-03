package July1;

public class MyLinkedList implements NodeList {

	private ListItem root;

	public MyLinkedList(ListItem root) {
		this.root = root;
	}

	@Override
	public ListItem getRoot() {
		return this.root;
	}

	@Override
	public boolean addItem(ListItem item) {
		if (this.root == null) {
			this.root = item;
			return true;
		}
		ListItem currentItem = this.root;
		while (currentItem != null) {
			int comparision = (currentItem.compareTo(item));

			if (comparision < 0) {
				if (currentItem.next() != null) {
					currentItem = currentItem.next();
				} else {
					currentItem.setNext(item).setPrevious(currentItem);
					return true;
				}
			} else if (comparision > 0) {
				item.setPrevious(currentItem.previous());
				item.setNext(currentItem);

				if (currentItem.previous() != null) {
					currentItem.previous().setNext(item);
				} else {
					this.root = item;
				}

				currentItem.setPrevious(item);
				return true;
			} else {
				System.out.println(item.getValue() + " is already present, so not added.");
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean removeItem(ListItem item) {
		if (item != null) {
			System.out.println("\nDeleting item: " + item.getValue());
		}
		ListItem currentItem = this.root;
		while (currentItem != null) {
			int comparision = currentItem.compareTo(item);
			if (comparision == 0) {
				if (currentItem == this.root) {
					this.root = currentItem.next();

				} else {
					currentItem.previous().setNext(currentItem.next());
					if (currentItem.next() != null) {
						currentItem.next().setPrevious(currentItem.previous());
					}
				}
				return true;
			} else if (comparision < 0) {
				currentItem = currentItem.next();
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public void traverse(ListItem root) {
		if(root==null) {
			System.out.println("The List is empty");
		}else {
			while(root!=null) {
				System.out.println(root.getValue());
				root=root.next();
			}
		}
	}

}
