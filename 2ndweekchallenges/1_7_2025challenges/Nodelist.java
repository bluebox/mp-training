public interface Nodelist {
    Listitem getRoot();
    boolean additem(Listitem li);
    boolean removeitem(Listitem li);
    void traverse(Listitem li);
    int compareTo(Listitem li);
}
