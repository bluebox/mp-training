package challenge_1st_july;

public interface NodeList {
    ListItem getRoot();

    boolean addItem(ListItem item);

    boolean removeItem(ListItem item);

    void traverse(ListItem root);
}
