package corejava.july1_DsaWithAbstractClasses;

public class Main {

    public static void main(String[] args) {

		
		  ListItem linkedListInitialRoot = new Node(10); 
		  NodeList myList = new MyLinkedList(linkedListInitialRoot);
		  
		  System.out.println("--- Adding items to Linked List ---");
		  myList.addItem(new Node(5)); 
		  myList.addItem(new Node(20)); 
		  myList.addItem(new Node(15));
		  myList.addItem(new Node(7)); 
		  myList.addItem(new Node(3)); 
		  myList.addItem(new Node(10));
		  
		  System.out.println("List after adding items:");
		  myList.traverse(myList.getRoot());
		  
		  System.out.println("Removing 7 from the list");
		  myList.removeItem(new Node(7));
		  
		  System.out.println("Removing 100 (non-existent) from the list");
		  myList.removeItem(new Node(100));
		  
		  System.out.println("List after removals:");
		  myList.traverse(myList.getRoot());
		  
		  NodeList myTree = new SearchTree(new Node(11));
		  
		  System.out.println("--- Adding items to Search Tree ---");
		  myTree.addItem(new Node(5));
		  myTree.addItem(new Node(20));
		  myTree.addItem(new Node(15));
		  
		  System.out.println("Tree after adding items:");
		  myTree.traverse(myTree.getRoot());
		  
		  System.out.println("Tree after removals:");
		  myTree.traverse(myTree.getRoot());
		  
		  


    }
}
