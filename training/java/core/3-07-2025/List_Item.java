public abstract class List_Item {

    protected List_Item next = null;
    protected List_Item previous = null;

    protected Object value;

    public List_Item(Object value) {
        this.value = value;
    }

    abstract List_Item next();

    abstract List_Item setNext(List_Item item);

    abstract List_Item previous();

    abstract List_Item setPrevious(List_Item item);

    abstract int compareTo(List_Item item);

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}