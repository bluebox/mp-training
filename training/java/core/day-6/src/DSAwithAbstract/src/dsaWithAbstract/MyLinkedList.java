package dsaWithAbstract;

public class MyLinkedList implements NodeList{
	 private Listitem root;

	    public MyLinkedList(Listitem root) 
	    {
	        this.root = root;
	    }
	    public Listitem getRoot()
	    {
	        return this.root;
	    }


	    public boolean addItem(Listitem newItem) {
	        if (this.root == null)
	        {
	            this.root = newItem;
	            return true;
	        }

	        Listitem currentItem = this.root;
	        while (currentItem != null) 
	        {
	            int comparison = currentItem.compareTo(newItem);
	            if (comparison == 0) 
	            {
	                System.out.println(newItem.getValue() + " is already present, not added.");
	                return false;
	            }
	            
	            else if (comparison < 0)
	            {
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
	            else 
	            {
	                if (currentItem.previous() != null)
	                {
	                    currentItem.previous().setNext(newItem);
	                    newItem.setPrevious(currentItem.previous());
	                    newItem.setNext(currentItem);
	                    currentItem.setPrevious(newItem);
	                }
	                else
	                {
	                    newItem.setNext(this.root);
	                    this.root.setPrevious(newItem);
	                    this.root = newItem;
	                }
	                return true;
	            }
	        }
	        return false;
	    }

	  
	    public boolean removeItem(Listitem item) {
	        if (item == null) {
	            return false;
	        }

	        Listitem currentItem = this.root;
	        while (currentItem != null) {
	            int comparison = currentItem.compareTo(item);
	            if (comparison == 0) {
	                if (currentItem == this.root) 
	                {
	                    this.root = currentItem.next();
	                } 
	                else 
	                {
	                    currentItem.previous().setNext(currentItem.next());
	                    if (currentItem.next() != null) 
	                    {
	                        currentItem.next().setPrevious(currentItem.previous());
	                    }
	                }
	                return true;
	            } else if (comparison < 0) {
	                currentItem = currentItem.next();
	            } else {
	                return false;
	            }
	        }
	        return false;
	    }

	    public void traverse(Listitem root) {
	        if (root == null) {
	            System.out.println("The list is empty");
	            return;
	        }

	        Listitem currentItem = root;
	        while (currentItem != null) {
	            System.out.println(currentItem.getValue());
	            currentItem = currentItem.next();
	        }
	    }
}
