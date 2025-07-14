import java.util.Arrays;
import java.util.List;

public class Practice {
    public static void main(String[] args) {
        List<Integer> list =Arrays.asList(5,10,15,12);
        List<Integer> filterdList= list.stream().filter(i->i%2==0).toList();
        System.out.println(filterdList);
        
    }
}
