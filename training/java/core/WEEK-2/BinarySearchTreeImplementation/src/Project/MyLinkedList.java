package Project;

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
        if (root == null) {
            root = newItem;
            return true;
        }

        ListItem current = root;

        while (true) {
            int comparison = current.compareTo(newItem);

            if (comparison < 0) {
                // Go right
                if (current.next() == null) {
                    current.setNext(newItem);
                    return true;
                } else {
                    current = current.next();
                }

            } else if (comparison > 0) {
                // Go left
                if (current.previous() == null) {
                    current.setPrevious(newItem);
                    return true;
                } else {
                    current = current.previous();
                }

            } else {
                // Duplicate
                return false;
            }
        }
    }

    @Override
    public boolean removeItem(ListItem item) {
        if (item == null || root == null) return false;

        ListItem current = root;
        ListItem parent = null;

        while (current != null) {
            int comparison = current.compareTo(item);
            if (comparison < 0) {
                parent = current;
                current = current.next();
            } else if (comparison > 0) {
                parent = current;
                current = current.previous();
            } else {
                // Node found
                performRemoval(current, parent);
                return true;
            }
        }
        return false;
    }

    private void performRemoval(ListItem item, ListItem parent) {
        // Case 1: No children
        if (item.next() == null && item.previous() == null) {
            if (parent == null) {
                root = null;
            } else if (parent.next() == item) {
                parent.setNext(null);
            } else {
                parent.setPrevious(null);
            }
        }

        // Case 2: One child
        else if (item.next() == null) {
            if (parent == null) {
                root = item.previous();
            } else if (parent.next() == item) {
                parent.setNext(item.previous());
            } else {
                parent.setPrevious(item.previous());
            }
        } else if (item.previous() == null) {
            if (parent == null) {
                root = item.next();
            } else if (parent.next() == item) {
                parent.setNext(item.next());
            } else {
                parent.setPrevious(item.next());
            }
        }

        // Case 3: Two children
        else {
            // Use in-order successor
            ListItem successorParent = item;
            ListItem successor = item.next();

            while (successor.previous() != null) {
                successorParent = successor;
                successor = successor.previous();
            }

            item.setValue(successor.getValue());

            if (successorParent == item) {
                successorParent.setNext(successor.next());
            } else {
                successorParent.setPrevious(successor.next());
            }
        }
    }

    @Override
    public void traverse(ListItem node) {
        if (node != null) {
            traverse(node.previous());
            System.out.println(node.getValue());
            traverse(node.next());
        }
    }
}
