package corejava.july1_DsaWithAbstractClasses;

public class SearchTree implements NodeList{
	
	private ListItem root;
	
	public SearchTree(ListItem root) {
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
		
		while (true) {
			
			int comparison = currentItem.compareTo(newItem);
			
			if (comparison < 0) {
				if (currentItem.next() != null) {
	        		currentItem = currentItem.next();
	            }
				else
	        	{
	        		currentItem.setNext(newItem);
	        		return true;
	        	}
			}
			
			else if (comparison > 0) {
	        	if (currentItem.previous() != null) {
	        		currentItem=currentItem.previous();
	            }
	        	else {
	        		currentItem.setPrevious(newItem);
	        		return true;
	            }
	        } 
	        else {
	        	System.out.println(newItem.getValue() + " is already present, not added.");
	        	return false;
	        }
	    }
	}

	@Override
	public boolean removeItem(ListItem item) {

        if (item == null)
        	return false;
        
        ListItem currentItem = root;

        while (currentItem != null) {
            int comparison = currentItem.compareTo(item);
            if (comparison == 0) {
                if (currentItem.previous()!=null && currentItem.next()!=null) {
                	ListItem previous=item.previous();
                	ListItem next=item.next();
                	currentItem=previous;
                    while(currentItem.next()!=null) {
                    	currentItem=currentItem.next();
                    }
                    currentItem.previous().setNext(null);
                    root=currentItem;
                    currentItem.setNext(next);
                    currentItem.setPrevious(previous);
                } 
                else if(currentItem.previous()== null || currentItem.next()==null) {
                	if(currentItem.previous()==null) {
                		currentItem=currentItem.next();
                		root=currentItem;
                	}
                	if(currentItem.next()==null) {
                		currentItem=currentItem.previous();
                		root=currentItem;
                	}
                }
                else {
                	root=null;
                }
                return true;
            }
            else if (comparison < 0) {
                currentItem = currentItem.next();
            } 
            else {
            	currentItem = currentItem.previous();
            }
        }
        System.out.println("Element Not Found");
		return false;
	}

	@Override
	public void traverse(ListItem root) {
		ListItem current=root;
		if(current !=null) {
			traverse(current.next());
			System.out.println(current.getValue()+" ");
			traverse(current.next());
		}
	}
	
}
