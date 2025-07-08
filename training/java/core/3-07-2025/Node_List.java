public interface Node_List {
    List_Item getRoot();

    boolean addItem(List_Item item);

    boolean removeItem(List_Item item);

    void traverse(List_Item root);
}