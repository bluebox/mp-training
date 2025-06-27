package corejavaday_Two;

public class SumThree {
    public static void main(String[] args){
        int sum=0,count=0;
        int num=1;
        while(count<5){
            if(num%3==0&&num%5==0){
                sum+=num;
                count+=1;
            }
            num+=1;
        }
        System.out.println("sum="+sum);
    }
}
