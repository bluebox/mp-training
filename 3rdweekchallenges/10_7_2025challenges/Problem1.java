import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Problem1 {
    public static void main(String[] args) {
         List<Integer> list=new ArrayList<>();
        List<Integer> combinations=new ArrayList<>();
        Set<List<Integer>> set=new LinkedHashSet<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        set=combination(list);
        for(List<Integer> li: set){
            System.out.println(li);
        }

    }
    public static Set<List<Integer>> combination(List<Integer> list){
        Set<List<Integer>> set=new LinkedHashSet<>();
        int totalcombinations=(int)Math.pow(2, list.size());
        for(int i=0;i<totalcombinations;i++){
            List<Integer> combinationlist=new ArrayList<>();
            int index=0;
            int bit=i;
            while (bit>0) {
                if((bit&1) == 1){
                    combinationlist.add(list.get(index));
                }
                bit=bit>>1;
                System.out.println("i is "+bit);
                index++;
                System.out.println(combinationlist);
            }
            set.add(new ArrayList<>(combinationlist));
            
        }
        return set;

        
    

    }
}
