import java.util.*;
public class SummationAndAvg{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int sum=0;
        int count=0;
        while(true){
            String num=sc.next();
            int num1=0;
            try{
                num1=Integer.parseInt(num);
            }catch(NumberFormatException e){
                break;
            }
            sum+=num1;
            count++;
        }
        System.out.println("SUM: "+sum);
        System.out.println("AVG: "+(sum/count));
    }
}