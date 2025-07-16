package corejava.july1_DsaWithAbstractClasses;

public class MyLinkedList implements NodeList{
	
	private ListItem root;
	
	public MyLinkedList(ListItem root) {
		this.root = root;
	}

	@Override
    public ListItem getRoot() {
		return root;
	}

	@Override
	public boolean addItem(ListItem newItem) {
		if (root == null) {
			root = newItem;
	        return true;
	    }
		ListItem currentItem = root;
		while (currentItem != null) {
			int comparison = currentItem.compareTo(newItem);
			if (comparison < 0) {
				if (currentItem.next() != null) {
	        		currentItem = currentItem.next();
	            } 
	        	else
	        	{
	        		currentItem.setNext(newItem);
	        		newItem.setPrevious(currentItem);
	        		return true;
	        	}
	        } 
	        else if (comparison > 0) {
	        	if (currentItem.previous() != null) {
	        		currentItem.previous().setNext(newItem);
	        		newItem.setPrevious(currentItem.previous());
	        		newItem.setNext(currentItem);
	        		currentItem.setPrevious(newItem);
	            }
	        	else {
	        		newItem.setNext(root);
	                root.setPrevious(newItem);
	                root = newItem;
	            }
	            return true;
	        } 
	        else {
	        	System.out.println(newItem.getValue() + " is already present, not added.");
	        	return false;
	        }
	    }
	    return false;
	}
	
	@Override
	public boolean removeItem(ListItem item) {
	        if (item == null)
	        	return false;
	        
	        ListItem currentItem = root;

	        while (currentItem != null) {
	            int comparison = currentItem.compareTo(item);
	            if (comparison == 0) {
	                if (currentItem == root) {
	                    root = currentItem.next();
	                    if (root != null) {
	                        root.setPrevious(null);
	                    }
	                } 
	                else {
	                    ListItem prev = currentItem.previous();
	                    ListItem next = currentItem.next();
	                    if (prev != null) {
	                        prev.setNext(next);
	                    }
	                    if (next != null) {
	                        next.setPrevious(prev);
	                    }
	                }
	                return true;
	            }
	            else if (comparison < 0) {
	                currentItem = currentItem.next();
	            } 
	            else {
	                return false;
	            }
	        }
	        System.out.println("Element not found");
	        return false;
	    }

	    @Override
	    public void traverse(ListItem root) {
	        if (root == null) {
	            System.out.println("The list is empty");
	            return;
	        }
	        ListItem current = root;
	        while (current != null) {
	            System.out.println(current.getValue());
	            current = current.next();
	        }
	    }
}
