import java.util.Scanner; 
public class digitsum {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter a number :");
        int n=sc.nextInt();
        System.out.println(dsum(n));
        
    }
    public static int dsum(int n){
        if (n<0){
            return -1;

        }
        int res=0;
        String s=Integer.toString(n);
        for(char ch:s.toCharArray()){
            res+=Character.getNumericValue(ch);

        }
        return res;


    }
    
}
