public class MyLinkedList implements NodeList {
    private ListItem root;

    public MyLinkedList(ListItem root) {
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
                    current.setNext(newItem).setPrevious(current);
                    return true;
                }
            } 
            else if (compare > 0) {
                if (current.previous() != null) {
                    current.previous().setNext(newItem).setPrevious(current.previous());
                    newItem.setNext(current).setPrevious(newItem);
                } 
                else {
                    newItem.setNext(root).setPrevious(newItem);
                    root = newItem;
                }
                return true;
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
        while (current != null) {
            int compare = current.compareTo(item);
            if (compare == 0) {
                if (current == root) {
                    root = current.next();
                } 
                else {
                    current.previous().setNext(current.next());
                    if (current.next() != null) {
                        current.next().setPrevious(current.previous());
                    }
                }
                return true;
            } 
            else if (compare < 0) {
                current = current.next();
            } 
            else {
                return false;
            }
        }
        return false;
    }

    public void traverse(ListItem root) {
        if (root == null) {
            System.out.println("The list is empty");
        } 
        else {
            while (root != null) {
                System.out.println(root.getValue());
                root = root.next();
            }
        }
    }
}