import java.util.Scanner;
public class Double {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        double pounds=sc.nextDouble();
        double kgs=pounds*0.45359237;
        System.out.println(kgs);
        double value=sc.nextDouble();
        double value1=sc.nextDouble();
        double result=(value+value1)*100.00;
        result=result%40.00;
        boolean iszero=true;
        if(result!=0.0)
        iszero=false;
        System.out.println(iszero);
        if(!iszero){
            System.out.println("got some remainder");
        }




    }
    
}
