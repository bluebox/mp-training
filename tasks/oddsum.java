import java.util.Scanner;
public class oddsum {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter range:");
        int start=sc.nextInt();
        int end=sc.nextInt();
        System.out.println("sum of odd numbers in the range is :"+sumodd(start,end));
        
    }
    public static boolean isodd(int n){
        if (n>0 && n%2!=0){
            return true;

        }
        return false;
    }
    public static Integer sumodd(int start ,int end){
        int sum=0;
        if (start>0 && end>0 && start<=end){
        for (int i=start;i<=end;i++){
            if(isodd(i)){
                sum+=i;
            }
        }}
        else{
            System.out.println("Invalid range");
            return -1;
        }
        return sum;

    }
    
}
