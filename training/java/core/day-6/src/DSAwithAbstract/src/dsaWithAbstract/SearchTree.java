package dsaWithAbstract;
public class SearchTree implements NodeList{
	private Listitem root;

    public SearchTree(Listitem root)
    {
        this.root = root;
    }
    public Listitem getRoot() {
        return this.root;
    }

    public boolean addItem(Listitem newItem) 
    {
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
            } else if (comparison < 0) 
            {
                if (currentItem.next()!= null)
                {
                    currentItem = currentItem.next();
                } else
                {
                    currentItem.setNext(newItem);
                    return true;
                }
            } else 
            {
                if (currentItem.previous() != null)
                {
                    currentItem = currentItem.previous();
                } else {
                    currentItem.setPrevious(newItem);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean removeItem(Listitem item) {
        if (item == null) {
            return false;
        }

        Listitem currentItem = this.root;
        Listitem parentItem = null;

        while (currentItem != null) 
        {
            int comparison = currentItem.compareTo(item);
            if (comparison == 0) 
            {
                performRemoval(currentItem, parentItem);
                return true;
            }
            else if (comparison < 0) 
            {
                parentItem = currentItem;
                currentItem = currentItem.next();
            }
            else 
            {
                parentItem = currentItem;
                currentItem = currentItem.previous();
            }
        }
        return false;
    }

    private void performRemoval(Listitem item, Listitem parent)
    {
        if (item.previous() == null)
        {
            if (parent == null)
            {
                this.root = item.next();
            } 
            else 
            {
                if (parent.compareTo(item) < 0) 
                {
                    parent.setNext(item.next());
                }
                else 
                {
                    parent.setPrevious(item.next());
                }
            }
        }
        else if (item.next() == null) 
        {
            if (parent == null)
            {
                this.root = item.previous();
            } 
            else 
            {
                if (parent.compareTo(item) < 0) 
                {
                    parent.setNext(item.previous());
                }
                else
                {
                    parent.setPrevious(item.previous());
                }
            }
        }
        else 
        {
            Listitem current = item.next();
            Listitem leftmostParent = item;
            while (current.previous() != null) 
            {
                leftmostParent = current;
                current = current.previous();
            }
            item.setValue(current.getValue());

            if (leftmostParent == item) {
                item.setNext(current.next());
            } else {
                leftmostParent.setPrevious(current.next());
            }
        }
    }

    public void traverse(Listitem root) {
        if (root != null) {
            traverse(root.previous());
            System.out.println(root.getValue());
            traverse(root.next());
        }
    }
}
