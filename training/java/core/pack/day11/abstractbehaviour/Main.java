package day11.abstractbehaviour;

public class Main {
    public static void main(String[] args) {
        // Testing MyLinkedList
        System.out.println("Testing MyLinkedList:");
        NodeList list = new MyLinkedList(null);

        list.addItem(new Node("Apple"));
        list.addItem(new Node("Banana"));
        list.addItem(new Node("Mango"));
        list.addItem(new Node("Grapes"));
        list.addItem(new Node("Banana")); // duplicate, should not be added

        list.traverse(list.getRoot());

        System.out.println("\nAfter removing 'Mango':");
        list.removeItem(new Node("Mango"));
        list.traverse(list.getRoot());

        // Testing SearchTree
        System.out.println("\n\nTesting SearchTree (Binary Search Tree):");
        NodeList tree = new SearchTree(null);

        tree.addItem(new Node("50"));
        tree.addItem(new Node("30"));
        tree.addItem(new Node("70"));
        tree.addItem(new Node("20"));
        tree.addItem(new Node("40"));
        tree.addItem(new Node("60"));
        tree.addItem(new Node("80"));
        tree.addItem(new Node("70")); // duplicate, should not be added

        tree.traverse(tree.getRoot());

        System.out.println("\nAfter removing '30':");
        tree.removeItem(new Node("30"));
        tree.traverse(tree.getRoot());
    }
}
