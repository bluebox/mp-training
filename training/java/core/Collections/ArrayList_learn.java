package Collections;
import java.util.ArrayList;

public class ArrayList_learn {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(5);

        System.out.println(list.size());
        System.out.println(list);

        list.add(6);
        System.out.println(list);
        list.remove(2);

        System.out.println(list);

        list.add(0,10);
        System.out.println(list);

        list.set(0, 1);
        System.out.println(list);

        System.out.println(list.get(2));




    }
}
