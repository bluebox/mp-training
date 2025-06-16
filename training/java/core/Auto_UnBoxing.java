import java.util.*;
class Auto_UnBoxing{
    public int findSum(List<Integer> list){
        int sum=0;
        for(Integer i:list){
            sum=sum+i;     //unboxing
        }
        return sum;
    }
    public static void main(String[] args){
        List<Integer> list=Arrays.asList(1,2,3,Integer.valueOf(4),Integer.valueOf(5),6);   //auto-boxing
        Auto_UnBoxing ab=new Auto_UnBoxing();
        System.out.println("Sum is="+(ab.findSum(list)));
    }
}