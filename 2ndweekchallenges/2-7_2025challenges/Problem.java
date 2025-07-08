import java.util.HashMap;

public class Problem {
    public static void main(String[] args) {
        int num=100;
        int totalnoofones=0;
        System.out.println("hi");
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=1;i<=num;i++){
            int nofones=0;
            System.out.println("1");
            int a=i;
            while(a>0){
                System.out.println("helo");
                if(map.containsKey(a)){
                    totalnoofones+=map.get(a);
                    totalnoofones+=nofones;
                    break;
                }
                a=a&(a-1);
                nofones+=1;
                
                
            }
            map.put(a, nofones);
            totalnoofones+=nofones;
        }
        System.out.println(totalnoofones);
        map.forEach((s,a)->System.out.println(s+"  "+a));
    }
    
}
