package DataStructurewithabstractclasses;
public class MyLinkedList implements NodeList {
    private Listitem root;
    public MyLinkedList(Listitem root) {
        this.root = root;
    }
    public Listitem getRoot() {
        return this.root;
    }
    @Override
    public boolean addItem(Listitem newItem) {
        if (this.root == null) {
            this.root = newItem;
            return true;
        }
        Listitem currentItem = this.root;
        while (currentItem != null) {
            int comparison = currentItem.compareTo(newItem);
            if (comparison < 0) {
                if (currentItem.next() != null) {
                    currentItem = currentItem.next();
                } else {
                    currentItem.setNext(newItem);
                    newItem.setPrevious(currentItem);
                    return true;
                }
            } else if (comparison > 0) {
                if (currentItem.previous() != null) {
                    currentItem.previous().setNext(newItem);
                    newItem.setPrevious(currentItem.previous());
                    newItem.setNext(currentItem);
                    currentItem.setPrevious(newItem);
                } else {
                    newItem.setNext(this.root);
                    this.root.setPrevious(newItem);
                    this.root = newItem;
                }
                return true;
            } else {
                // Equal, don't add
                return false;
            }
        }
        return false;
    }
    @Override
    public boolean removeItem(Listitem item) {
        if (item != null) {
            Listitem currentItem = this.root;

            while (currentItem != null) {
                int comparison = currentItem.compareTo(item);
                if (comparison == 0) {
                    if (currentItem == this.root) {
                        this.root = currentItem.next();
                    } else {
                        Listitem previous = currentItem.previous();
                        Listitem next = currentItem.next();
                        if (previous != null) {
                            previous.setNext(next);
                        }
                        if (next != null) {
                            next.setPrevious(previous);
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
    public String traverse(Listitem root) {
        if (root == null) {
            return "The list is empty";
        }
        StringBuilder output = new StringBuilder();
        Listitem current = root;
        while (current != null) {
            output.append(current.getValue()).append("\n");
            current = current.next();
        }
        return output.toString();
    }
}
