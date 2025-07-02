package Project;

public class Main {
    public static void main(String[] args) {
        // Initialize the tree with a root node
        ListItem rootItem = new Node("50");
        MyLinkedList tree = new MyLinkedList(rootItem);

        // Insert nodes
        tree.addItem(new Node("30"));
        tree.addItem(new Node("70"));
        tree.addItem(new Node("20"));
        tree.addItem(new Node("40"));
        tree.addItem(new Node("60"));
        tree.addItem(new Node("80"));

        System.out.println("In-order Traversal (Before Deletion):");
        tree.traverse(tree.getRoot());

        // Remove leaf node
        tree.removeItem(new Node("20"));
        // Remove node with one child
        tree.removeItem(new Node("30"));
        // Remove node with two children
        tree.removeItem(new Node("50"));

        System.out.println("\nIn-order Traversal (After Deletion):");
        tree.traverse(tree.getRoot());
    }
}
