package july_1;



public class Main1{
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList(null);

        list.addItem(new Node("apple"));
        list.addItem(new Node("banana"));
        list.addItem(new Node("watermelon"));
        list.addItem(new Node("guva"));
        list.addItem(new Node("mango"));

        System.out.println("Traversing the list:");
        list.traverse(list.getRoot());

        System.out.println("\nRemoving 'mango':");
        list.removeItem(new Node("mango"));
        list.traverse(list.getRoot());
    }
}
