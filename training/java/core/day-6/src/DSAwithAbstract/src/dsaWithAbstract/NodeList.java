package dsaWithAbstract;

public interface NodeList {
	 Listitem getRoot();
	    boolean addItem(Listitem item);
	    boolean removeItem(Listitem item);
	    void traverse(Listitem root);
}
