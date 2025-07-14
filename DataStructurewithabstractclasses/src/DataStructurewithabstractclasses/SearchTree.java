package DataStructurewithabstractclasses;



public class SearchTree implements NodeList {
    private Listitem root;

    public SearchTree(Listitem root) {
        this.root = root;
    }

    @Override
    public Listitem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(Listitem newItem) {
        if (root == null) {
            root = newItem;
            return true;
        }

        Listitem currentItem = root;
        while (currentItem != null) {
            int comparison = currentItem.compareTo(newItem);
            if (comparison < 0) {
                if (currentItem.next() != null) {
                    currentItem = currentItem.next();
                } else {
                    currentItem.setNext(newItem);
                    return true;
                }
            } else if (comparison > 0) {
                if (currentItem.previous() != null) {
                    currentItem = currentItem.previous();
                } else {
                    currentItem.setPrevious(newItem);
                    return true;
                }
            } else {
                // Item already exists
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean removeItem(Listitem item) {
        if (item != null) {
            root = removeRecursive(root, item);
            return true;
        }
        return false;
    }

    private Listitem removeRecursive(Listitem current, Listitem item) {
        if (current == null) return null;

        int comparison = current.compareTo(item);

        if (comparison > 0) {
            current.setPrevious(removeRecursive(current.previous(), item));
        } else if (comparison < 0) {
            current.setNext(removeRecursive(current.next(), item));
        } else {
            // Node found
            if (current.previous() == null) {
                return current.next();
            } else if (current.next() == null) {
                return current.previous();
            }

            // Node with two children
            Listitem smallest = findMin(current.next());
            current.setValue(smallest.getValue());
            current.setNext(removeRecursive(current.next(), smallest));
        }
        return current;
    }

    private Listitem findMin(Listitem item) {
        while (item.previous() != null) {
            item = item.previous();
        }
        return item;
    }

    @Override
    public String traverse(Listitem root) {
        if (root == null) {
            return "The tree is empty";
        }

        StringBuilder output = new StringBuilder();
        inOrder(root, output);
        return output.toString();
    }

    private void inOrder(Listitem node, StringBuilder output) {
        if (node != null) {
            inOrder(node.previous(), output);
            output.append(node.getValue()).append("\n");
            inOrder(node.next(), output);
        }
    }
}
