public class SearchTree implements NodeList {
    private ListItem root;

    public SearchTree(ListItem root) {
        this.root = root;
    }

    public ListItem getRoot() {
        return root;
    }

    public boolean addItem(ListItem newItem) {
        if (root == null) {
            root = newItem;
            return true;
        }

        ListItem current = root;
        while (current != null) {
            int compare = current.compareTo(newItem);
            if (compare < 0) {
                if (current.next() != null) {
                    current = current.next();
                }
                else {
                    current.setNext(newItem);
                    return true;
                }
            } 
            else if (compare > 0) {
                if (current.previous() != null) {
                    current = current.previous();
                } 
                else {
                    current.setPrevious(newItem);
                    return true;
                }
            }
            else {
                System.out.println("Item already exists.");
                return false;
            }
        }
        return false;
    }

    public boolean removeItem(ListItem item) {
        if (item == null) return false;

        ListItem current = root;
        ListItem parent = null;

        while (current != null) {
            int compare = current.compareTo(item);

            if (compare < 0) {
                parent = current;
                current = current.next();
            } 
            else if (compare > 0) {
                parent = current;
                current = current.previous();
            } 
            else {
                performRemoval(current, parent);
                return true;
            }
        }
        return false;
    }

    private void performRemoval(ListItem item, ListItem parent) {
        if (item.next() == null && item.previous() == null) {
            if (parent.next() == item) parent.setNext(null);
            else if (parent.previous() == item) parent.setPrevious(null);
            else root = null;
        } 
        else if (item.previous() == null) {
            if (parent == null) root = item.next();
            else if (parent.next() == item) parent.setNext(item.next());
            else parent.setPrevious(item.next());
        } 
        else if (item.next() == null) {
            if (parent == null) root = item.previous();
            else if (parent.next() == item) parent.setNext(item.previous());
            else parent.setPrevious(item.previous());
        } 
        else {
            ListItem current = item.next();
            ListItem currentParent = item;

            while (current.previous() != null) {
                currentParent = current;
                current = current.previous();
            }

            item.setValue(current.getValue());
            if (currentParent == item) {
                item.setNext(current.next());
            } 
            else {
                currentParent.setPrevious(current.next());
            }
        }
    }

    public void traverse(ListItem root) {
        if (root != null) {
            traverse(root.previous());
            System.out.println(root.getValue());
            traverse(root.next());
        }
    }
}