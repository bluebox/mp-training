package com.prana;

import java.util.ArrayList;
import java.util.List;

public class QueryList<T extends QueryItem> extends ArrayList<T> {

    public QueryList() {
        super();
    }

    public List<T> getMatches(String field, String value) {
        List<T> matches = new ArrayList<>();
        for (T item : this) {
            if (item.matchFieldValue(field, value)) {
                matches.add(item);
            }
        }
        return matches;
    }
}
