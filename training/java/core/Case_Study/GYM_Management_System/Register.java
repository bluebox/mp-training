import java.util.ArrayList;
import java.util.List;

public class Register<T extends Display & Identity> {
    private List<T> items;

    public Register() {
        this.items = new ArrayList<>();
    }

    public void add(T item) {
        items.add(item);
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    public T findById(String id) throws Exception {
        for (T item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        throw new Exception("Not Found Record");
    }
}
