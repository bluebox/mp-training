import java.util.*;
public class SetOperations{
    public static Set getUnion(List sets){
        Set result=new HashSet();
        for(Object obj:sets) {
            result.addAll((Set) obj);
        }
        return result;
    }
    public static Set getIntersect(Set a,Set b){
        Set result=new HashSet(a);
        result.retainAll(b);
        return result;
    }
    public static Set getDifference(Set a,Set b){
        Set result=new HashSet(a);
        result.removeAll(b);
        return result;
    }
    public static void main(String[] args){
        Set set1=new HashSet(Arrays.asList(1,2,3,4));
        Set set2=new HashSet(Arrays.asList(3,4,5,6));
        Set set3=new HashSet(Arrays.asList(6,7));
        List sets=Arrays.asList(set1,set2,set3);
        Set union=getUnion(sets);
        System.out.println("Union: "+union);
        Set intersect=getIntersect(set1,set2);
        System.out.println("Intersection: "+intersect);
        Set diff=getDifference(set1,set2);
        System.out.println("Difference: "+diff);
    }
}
