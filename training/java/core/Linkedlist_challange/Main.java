package Day5_01_07.Linkedlist_challange;

public class Main {
    public static void main(String[] args) {
    	MyLinkedList list = new MyLinkedList(null);

    	list.traverse(list.getRoot());

    	String stringData = "25 27 23 29 28 22 21 20 24 26";
    	String[] data = stringData.split(" ");

    	for (String s : data) {
    	    list.addItem(new Node(s));
    	}

    	list.traverse(list.getRoot());
    	list.removeItem(new Node("23"));
    	list.traverse(list.getRoot());
    	list.removeItem(new Node("25"));
    	list.traverse(list.getRoot());
    	list.removeItem(new Node("20"));
    	list.removeItem(new Node("24"));
    	list.removeItem(new Node("22"));
    	list.traverse(list.getRoot());
    	list.removeItem(new Node("29"));
    	list.traverse(list.getRoot());
    	list.removeItem(new Node("28"));
    	list.traverse(list.getRoot());
    	list.removeItem(new Node("26"));
    	list.traverse(list.getRoot());
    	list.removeItem(new Node("27"));
    	list.traverse(list.getRoot());
    	list.removeItem(list.getRoot());
    	list.traverse(list.getRoot());
    
    	SearchTree bst = new SearchTree(null);

     
        String linkedListData = "25 27 23 29 28 22 21 20 24 26";
        String[] linkedItems = linkedListData.split(" ");
        System.out.println("-".repeat(60));

        for (String item : linkedItems) {
            bst.addItem(new Node(item));
        }

        bst.traverse(bst.getRoot());
        System.out.println();

        bst.removeItem(new Node("23"));
        bst.traverse(bst.getRoot());
        System.out.println();

        bst.removeItem(new Node("25"));
        bst.traverse(bst.getRoot());
        System.out.println();

        bst.removeItem(new Node("20"));
        bst.removeItem(new Node("24"));
        bst.removeItem(new Node("22"));
        bst.traverse(bst.getRoot());
        System.out.println();

        bst.removeItem(new Node("29"));
        bst.traverse(bst.getRoot());
        System.out.println();

        bst.removeItem(new Node("28"));
        bst.traverse(bst.getRoot());
        System.out.println();

        bst.removeItem(new Node("26"));
        bst.traverse(bst.getRoot());
        System.out.println();

        bst.removeItem(new Node("27"));
        bst.traverse(bst.getRoot());
        System.out.println();

        bst.removeItem(bst.getRoot());
        bst.traverse(bst.getRoot());
        System.out.println();
    }
}