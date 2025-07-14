package DataStructurewithabstractclasses;

interface NodeList {
    Listitem getRoot();
    boolean addItem(Listitem item);
    boolean removeItem(Listitem item);
    String traverse(Listitem root);
}
