import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Problem {
    public static void main(String[] args) {

        List<List<Integer>>set=new ArrayList<>();
        List<Integer> list=new ArrayList<>();
        List<Integer> combinations=new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        List<List<Integer>> result=recursion(0, list, set,combinations);
        for(List<Integer> li:result)
        System.out.println(li);



    }
    public static List<List<Integer>> recursion(int j,List<Integer> list,List<List<Integer>> set,List<Integer> combinations){
        if(j==list.size()){
            set.add(new ArrayList<>(combinations));
            return set;
        }
        
            combinations.add(list.get(j));
            recursion(j+1, list, set, combinations);
            combinations.remove(combinations.size()-1);
            recursion(j+1, list, set, combinations);
        


        return set;
        

    }
}
