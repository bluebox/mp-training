import java.util.Arrays;

class MyArrayList<T> {
    private final static int DefaultCapacity = 10;
    private int size = 0;
    private Object[] ele;

    public MyArrayList() {
        ele = new Object[DefaultCapacity];
    }

    public void add(T a) {
        ensureCapacity();
        ele[size++] = a;
    }

    private void ensureCapacity() {
        if (size == ele.length) {
            ele = Arrays.copyOf(ele, size * 2);
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) ele[index];
    }

    public int size() {
        return this.size;
    }
}

public class Examples {
    public static void main(String[] args) {
        MyArrayList<String> ans = new MyArrayList<>();
        ans.add("anand");
        ans.add("kumar");
        for (int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i));
        }
    }
}
