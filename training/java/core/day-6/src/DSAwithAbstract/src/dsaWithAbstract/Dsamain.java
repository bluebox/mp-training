package dsaWithAbstract;

public class Dsamain {
	 public static void main(String[] args) {
	        System.out.println("LinkedList:");
	        MyLinkedList myLinkedList = new MyLinkedList(null);
	        myLinkedList.addItem(new Node(50));
	        myLinkedList.addItem(new Node(30));
	        
	        myLinkedList.addItem(new Node(70));
	        System.out.println("\nTraversing linked list:");
	        myLinkedList.traverse(myLinkedList.getRoot());
	        System.out.println("\nRemoving 30 from linked list:");
	        myLinkedList.removeItem(new Node(30));
	        System.out.println("Traversing linked list after removing 30:");
	        myLinkedList.traverse(myLinkedList.getRoot());
	        System.out.println(""); 
	        System.out.println(" SearchTree:");
	        SearchTree searchTree = new SearchTree(null);
	        searchTree.addItem(new Node(50));
	        searchTree.addItem(new Node(30));
	        searchTree.addItem(new Node(70));
	        System.out.println("\nTraversing search tree (Inorder):");
	        searchTree.traverse(searchTree.getRoot());
	        System.out.println("\nRemoving 30 :");
	        searchTree.removeItem(new Node(30));
	        System.out.println("Traversing search tree after removing 30:");
	        searchTree.traverse(searchTree.getRoot());
	    }
}
