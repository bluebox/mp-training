import java.util.Arrays;
import java.util.List;

public class Practice2 {
    public static void main(String[] args) {
        List<Integer> marks = Arrays.asList(10,50,75,35,25,20,40);
        List<Integer> sortedmarks= marks.stream().sorted((a,b)->(a<b)?1: (a>b)?-1:0).toList();
        System.out.println(sortedmarks);
    }
}
