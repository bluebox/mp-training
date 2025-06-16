import java.util.*;
public class OddSum{
    int start;
    int end;
    public OddSum(int start,int end){
       this.start=start;
       this.end=end;
    }
    public static void main(String[] args){
       Scanner sc=new Scanner(System.in);
       System.out.println("enter range");
       OddSum oddsum=new OddSum(sc.nextInt(),sc.nextInt());
       int sum=oddsum.findSum(oddsum.start,oddsum.end);
       System.out.println("Sum of odd btwn "+oddsum.start+"&"+oddsum.end+" = "+sum);
    }
    public int findSum(int start,int end){
       int sum=0;
       for(int i=start;i<=end;i++){
          if(i%2!=0){
             sum=sum+i;
          }
       }
       return sum;
    }
}





