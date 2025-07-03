package com.tulasidhar.july1.datastructwithabstract;

public class SearchTree implements NodeList {

    private ListItem root;

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
                // newItem is greater, move right
                if (currentItem.rightLink != null) {
                    currentItem = currentItem.rightLink;
                } else {
                    currentItem.rightLink = newItem;
                    return true;
                }
            } else if (comparison > 0) {
                // newItem is smaller, move left
                if (currentItem.leftLink != null) {
                    currentItem = currentItem.leftLink;
                } else {
                    currentItem.leftLink = newItem;
                    return true;
                }
            } else {
                // equal values, do not add
                System.out.println(newItem.getValue() + " is already present.");
                return false;
            }
        }

        return false;
    }

    @Override
    public boolean removeItem(ListItem item) {
        if (root == null) {
            return false;
        }

        ListItem currentItem = root;
        ListItem parentItem = root;

        while (currentItem != null) {
            int comparison = currentItem.compareTo(item);

            if (comparison < 0) {
                parentItem = currentItem;
                currentItem = currentItem.rightLink;
            } else if (comparison > 0) {
                parentItem = currentItem;
                currentItem = currentItem.leftLink;
            } else {
                // Found the node to delete
                performRemoval(currentItem, parentItem);
                return true;
            }
        }

        return false;
    }

    private void performRemoval(ListItem item, ListItem parent) {
        // Node has no children (leaf node)
        if (item.leftLink == null && item.rightLink == null) {
            if (parent.leftLink == item) {
                parent.leftLink = null;
            } else if (parent.rightLink == item) {
                parent.rightLink = null;
            } else {
                root = null;
            }
        }
        // Node has only right child
        else if (item.leftLink == null) {
            if (parent.leftLink == item) {
                parent.leftLink = item.rightLink;
            } else if (parent.rightLink == item) {
                parent.rightLink = item.rightLink;
            } else {
                root = item.rightLink;
            }
        }
        // Node has only left child
        else if (item.rightLink == null) {
            if (parent.leftLink == item) {
                parent.leftLink = item.leftLink;
            } else if (parent.rightLink == item) {
                parent.rightLink = item.leftLink;
            } else {
                root = item.leftLink;
            }
        }
        // Node has two children
        else {
            ListItem current = item.rightLink;
            ListItem leftmostParent = item;

            while (current.leftLink != null) {
                leftmostParent = current;
                current = current.leftLink;
            }

            item.setValue(current.getValue());

            if (leftmostParent == item) {
                leftmostParent.rightLink = current.rightLink;
            } else {
                leftmostParent.leftLink = current.rightLink;
            }
        }
    }

    @Override
    public void traverse() {
        if (root != null) {
            traverseInOrder(root);
        } else {
            System.out.println("The tree is empty");
        }
    }

    private void traverseInOrder(ListItem node) {
        if (node != null) {
            traverseInOrder(node.leftLink);
            System.out.println(node.getValue());
            traverseInOrder(node.rightLink);
        }
    }
}
