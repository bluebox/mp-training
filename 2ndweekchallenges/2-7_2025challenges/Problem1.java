import java.util.HashMap;

public class Problem1 {

     public static void main(String[] args) {
        int num=100;
        int totalnoofones=0;
        System.out.println("hi");
       
        for(int i=1;i<=num;i++){
            int nofones=0;
            System.out.println("1");
            int a=i;
            while(a>0){
                System.out.println("helo");
                
                a=a&(a-1);
                nofones+=1;

            }
           
            totalnoofones+=nofones;
        }
        System.out.println(totalnoofones);
        
    
}
}
