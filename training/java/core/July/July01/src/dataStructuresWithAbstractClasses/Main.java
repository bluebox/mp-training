package dataStructuresWithAbstractClasses;

public class Main {
	public static void main(String[] args) {
		
		MyLinkedList linkedList = new MyLinkedList(null);
		
		linkedList.addItem(new Node("ca"));
		linkedList.addItem(new Node("cc"));
		linkedList.addItem(new Node("a"));
		
		System.out.println("root value LinkedList: "+linkedList.getRoot().getValue());
		System.out.println("List Values:");
		linkedList.traverse(linkedList.getRoot());
		System.out.println(linkedList.removeItem(new Node("a")));
		System.out.println("after removing a value List:");
		linkedList.traverse(linkedList.getRoot());
		
		SearchTree tree = new SearchTree(null);
		
		tree.addItem(new Node("ca"));
		tree.addItem(new Node("cc"));
		tree.addItem(new Node("a"));
		
		System.out.println("root value Search Tree:"+tree.getRoot().getValue());
		System.out.println("List Values:");
		tree.traverse(tree.getRoot());
		System.out.println(tree.removeItem(new Node("a")));
		System.out.println("after removing a value List:");
		tree.traverse(tree.getRoot());
		
		
	}
}
