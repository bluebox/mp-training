package abstractClassLinkedListAndBinaryTrees;

public interface NodeList {
	ListItem getRoot();
	boolean addItem(ListItem item);
	boolean removeItem(ListItem removeItem);
	void traverse(ListItem root);
}
