package problems;
import java.util.*;
public class day1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc=new Scanner(System.in);
       double num=10d;
       System.out.println(num);
       
       long num2=(long)10f;
       System.out.print(num2);
       
       long n=(long)(10d/3f);
       System.out.println(n);
       
       if(n==3)
    	   System.out.println(n);
       
       if(n==3) {
    	   System.out.println("block level if execution");
       }else {
    	   System.out.println("block level else execution");
       }
       
       //challenge1
       System.out.println("Eneter the weight of the person in Pounds");
       long poundweight=sc.nextLong();
       float constant=0.45359237f;
       double kilogramweight=poundweight*constant;
       System.out.println("weight of the person in  kg"+kilogramweight);
        
       //challenge2
       System.out.println("Eneter the variable");
       double var=sc.nextDouble();
       System.out.println("Eneter the variable");
       double var1=sc.nextDouble();
       double sum=(var+var1)*100.00f;
       long rem=(long)(sum%40.00f);
       boolean isvalid=false;
       if((double)rem==0.00) {
    	   isvalid=true;
       }
       
       System.out.println(rem+" remainder and is "+isvalid);
       
       if(rem==0) 
    	   System.out.println("got some remainder");
       
       
       
       //challenge3
       System.out.println("Eneter the variable in byte range");
       byte number=sc.nextByte();
       System.out.println("Eneter the variable in short range");
       short number1=sc.nextShort();
       System.out.println("Eneter the variable in int range");
       int number2=sc.nextInt();
       
       long number4=5_000_0L+10L*(number+number1+number2);
       
       System.out.println(number4+"is the required result");
       
       
       
       //challenge4
       System.out.println("Eneter the variable score in long range");
       long score=sc.nextLong();
       System.out.println("Eneter the levelcompleted in int range");
       int levelcompleted=sc.nextInt();
       System.out.println("Eneter the bonus in int range");
       int bonus=sc.nextInt();
       System.out.println("Eneter the true if game isover else enter false");
       boolean isgamecompleted=sc.nextBoolean();
       long finalscore=calculate(score,levelcompleted,bonus,isgamecompleted);
       long finalscore2=calculate(score=20000,levelcompleted=4,bonus,isgamecompleted);
       System.out.println(finalscore+"is the final score");
       System.out.println(finalscore2+"is the final score");
	}

	public static long calculate(long score,int levelcompleted,int bonus,boolean isgameover) {
		return (long)((score)+(bonus)*levelcompleted);
	}
}
