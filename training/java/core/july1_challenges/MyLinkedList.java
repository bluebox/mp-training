package july_1;



import july_1.ListItem;

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
    public boolean addItem(ListItem newItem) {
        if (this.root == null) {
            this.root = newItem;
            return true;
        }

        ListItem currentItem = this.root;
        while (currentItem != null) {
            int comparison = currentItem.compareTo(newItem);
            if (comparison < 0) {
                // New item is greater, move right
                if (currentItem.next() != null) {
                    currentItem = currentItem.next();
                } else {
                    currentItem.setNext(newItem).setPrevious(currentItem);
                    return true;
                }
            } else if (comparison > 0) {
                // New item is less, insert before
                if (currentItem.previous() != null) {
                    currentItem.previous().setNext(newItem).setPrevious(currentItem.previous());
                    newItem.setNext(currentItem).setPrevious(newItem);
                } else {
                    // Insert before root
                    newItem.setNext(this.root).setPrevious(newItem);
                    this.root.setPrevious(newItem);
                    this.root = newItem;
                }
                return true;
            } else {
                // Duplicate
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean removeItem(ListItem item) {
        if (item != null) {
            ListItem currentItem = this.root;

            while (currentItem != null) {
                int comparison = currentItem.compareTo(item);

                if (comparison == 0) {
                    // Item found
                    if (currentItem == this.root) {
                        this.root = currentItem.next();
                    } else {
                        currentItem.previous().setNext(currentItem.next());
                        if (currentItem.next() != null) {
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
        }
        return false;
    }

    @Override
    public void traverse(ListItem root) {
        if (root == null) {
            System.out.println("The list is empty");
        } else {
            while (root != null) {
                System.out.println(root.getValue());
                root = root.next();
            }
        }
    }
}
