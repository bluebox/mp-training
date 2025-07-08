import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        TaskData td=new TaskData();
        Set<Task> set=td.getTasks("Ann");
        System.out.println(set);
        set.forEach(s->System.out.println(s));
         
    }
}
