package July1;

import java.util.ArrayList;
import java.util.List;

public class QueryList<T extends QueryItem> {
    private List<T> items;

    public QueryList() {
        this.items = new ArrayList<>();
    }

    public void addItem(T item) {
        items.add(item);
    }

    public List<T> getItems() {
        return items;
    }

    public List<T> getMatches(String field) {
        List<T> matches = new ArrayList<>();
        for (T item : items) {
            if (item.matches(field)) {
                matches.add(item);
            }
        }
        return matches;
    }
}

