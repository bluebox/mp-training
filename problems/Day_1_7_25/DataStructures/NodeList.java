package Day_1_7_25.DataStructures;

public interface NodeList {
   ListItem getRoot();
   boolean addItem(ListItem node);
   boolean removeItem(ListItem node);
   void traverse(ListItem node);
}
