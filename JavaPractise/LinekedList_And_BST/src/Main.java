public class Main {
    public static void main(String[] args) {
        NodeList list = new MyLinkedList(null);
        list.addItem(new Node("Banana"));
        list.addItem(new Node("Apple"));
        list.addItem(new Node("Mango"));
        list.addItem(new Node("Custard"));
        list.traverse(list.getRoot());

        System.out.println("\n--- Tree Example ---");
        NodeList tree = new SearchTree(null);
        tree.addItem(new Node("Banana"));
        tree.addItem(new Node("Apple"));
        tree.addItem(new Node("Custard"));
        tree.addItem(new Node("Mango"));
        
        tree.traverse(tree.getRoot());
    }
}