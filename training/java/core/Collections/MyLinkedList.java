package Collections;

import java.util.*;

abstract class ListItem {
    protected ListItem rightLink;
    protected ListItem leftLink;
    protected Object value;

    public ListItem(Object value) {
        this.value = value;
    }

    abstract ListItem next();
    abstract ListItem setNext(ListItem item);
    abstract ListItem previous();
    abstract ListItem setPrevious(ListItem item);
    abstract int compareTo(ListItem item);

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}

class Node extends ListItem {
    public Node(Object value) {
        super(value);
    }

    ListItem next() {
        return rightLink;
    }

    ListItem setNext(ListItem item) {
        rightLink = item;
        return rightLink;
    }

    ListItem previous() {
        return leftLink;
    }

    ListItem setPrevious(ListItem item) {
        leftLink = item;
        return leftLink;
    }

    int compareTo(ListItem item) {
        if (item != null && value instanceof Comparable<?>) {
            return ((Comparable) value).compareTo(item.getValue());
        }
        return 0;
    }
}

interface NodeList {
    ListItem getRoot();
    boolean addItem(ListItem item);
    boolean removeItem(ListItem item);
    void traverse(ListItem root);
}

public class MyLinkedList implements NodeList {
    private ListItem root;

    public MyLinkedList(ListItem root) {
        this.root = root;
    }

    public ListItem getRoot() {
        return root;
    }

    public boolean addItem(ListItem item) {
        if (item == null) return false;
        if (root == null) {
            root = item;
            return true;
        }
        ListItem current = root;
        while (current != null) {
            int cmp = current.compareTo(item);
            if (cmp < 0) {
                if (current.next() != null) {
                    current = current.next();
                } else {
                    current.setNext(item).setPrevious(current);
                    return true;
                }
            } else if (cmp > 0) {
                if (current.previous() != null) {
                    current.previous().setNext(item).setPrevious(current.previous());
                    item.setNext(current);
                    current.setPrevious(item);
                } else {
                    item.setNext(root);
                    root.setPrevious(item);
                    root = item;
                }
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean removeItem(ListItem item) {
        if (item == null || root == null) return false;
        ListItem current = root;
        while (current != null) {
            int cmp = current.compareTo(item);
            if (cmp == 0) {
                if (current == root) {
                    root = current.next();
                    if (root != null) root.setPrevious(null);
                } else {
                    ListItem prev = current.previous();
                    ListItem next = current.next();
                    if (prev != null) prev.setNext(next);
                    if (next != null) next.setPrevious(prev);
                }
                return true;
            } else if (cmp < 0) {
                current = current.next();
            } else {
                return false;
            }
        }
        return false;
    }

    public void traverse(ListItem root) {
        if (root == null) {
            System.out.println("The list is empty");
            return;
        }
        ListItem current = root;
        while (current != null) {
            System.out.println(current.getValue());
            current = current.next();
        }
    }
}

class SearchTree implements NodeList {
    private ListItem root;

    public SearchTree(ListItem root) {
        this.root = root;
    }

    public ListItem getRoot() {
        return root;
    }

    public boolean addItem(ListItem item) {
        if (item == null) return false;
        if (root == null) {
            root = item;
            return true;
        }
        ListItem current = root;
        while (true) {
            int cmp = current.compareTo(item);
            if (cmp < 0) {
                if (current.next() != null) {
                    current = current.next();
                } else {
                    current.setNext(item);
                    return true;
                }
            } else if (cmp > 0) {
                if (current.previous() != null) {
                    current = current.previous();
                } else {
                    current.setPrevious(item);
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    public boolean removeItem(ListItem item) {
        if (item == null) return false;
        ListItem current = root;
        ListItem parent = null;
        while (current != null) {
            int cmp = current.compareTo(item);
            if (cmp < 0) {
                parent = current;
                current = current.next();
            } else if (cmp > 0) {
                parent = current;
                current = current.previous();
            } else {
                performRemoval(current, parent);
                return true;
            }
        }
        return false;
    }

    private void performRemoval(ListItem item, ListItem parent) {
        if (item.previous() == null && item.next() == null) {
            if (parent == null) {
                root = null;
            } else if (parent.previous() == item) {
                parent.setPrevious(null);
            } else {
                parent.setNext(null);
            }
        } else if (item.previous() == null) {
            if (parent == null) {
                root = item.next();
            } else if (parent.previous() == item) {
                parent.setPrevious(item.next());
            } else {
                parent.setNext(item.next());
            }
        } else if (item.next() == null) {
            if (parent == null) {
                root = item.previous();
            } else if (parent.previous() == item) {
                parent.setPrevious(item.previous());
            } else {
                parent.setNext(item.previous());
            }
        } else {
            ListItem successorParent = item;
            ListItem successor = item.next();
            while (successor.previous() != null) {
                successorParent = successor;
                successor = successor.previous();
            }
            item.setValue(successor.getValue());
            performRemoval(successor, successorParent);
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
