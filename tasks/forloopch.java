import java.util.Scanner;
public class forloopch {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the range:");
        int start=sc.nextInt();
        int end=sc.nextInt();
        System.out.println("enter the count:");
        int count=sc.nextInt();
        
        
        for(int i=start;i<=end;i++){
            if(isprime(i)){
                System.out.println(i);
                count--;
            }
            if (count==0){
                break;
            }
        }
    }
    public static boolean isprime(int n){
        if (n<2){
            return false;
        }
        if (n==2){
            return true;
        }
        for(int i=2;i<Math.sqrt(n)+1;i++){
            if (n%i==0){
                return false;
            }

        }
        return true;
    }
    
}
