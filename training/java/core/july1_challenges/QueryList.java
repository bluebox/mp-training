package july_1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QueryList<T extends QueryItem> extends ArrayList<T> {

    public List<T> getMatches(String field, String value) {
        return this.stream()
                   .filter(item -> item.matchFieldValue(field, value))
                   .collect(Collectors.toList());
    }
}
