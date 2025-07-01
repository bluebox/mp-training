package com.prana;
public class Main {
    public static void main(String[] args) {
        NodeList list = new MyLinkedList(null);

        list.addItem(new Node("Delta"));
        list.addItem(new Node("Alpha"));
        list.addItem(new Node("Echo"));
        list.addItem(new Node("Charlie"));
        list.addItem(new Node("Bravo"));

        list.traverse(list.getRoot());

        list.removeItem(new Node("Echo"));
        System.out.println("\nAfter deleting Echo:");
        list.traverse(list.getRoot());

        System.out.println("\nUsing Binary Search Tree:");
        NodeList tree = new SearchTree(null);
        tree.addItem(new Node("Mango"));
        tree.addItem(new Node("Apple"));
        tree.addItem(new Node("Banana"));
        tree.addItem(new Node("Peach"));

        tree.traverse(tree.getRoot());
    }
}
