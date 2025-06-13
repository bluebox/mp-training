import java.util.*;
public class SumAndAvgFromInput {

	public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long sum=0,cnt=0;
        while(true) {
        	try {
        		long value=sc.nextLong();
        		sum+=value;
        		cnt++;
        	}
        	catch(Exception e) {
        		break;
        	}
        }
        if(cnt==0) {
        	System.out.print("sum="+0+" avg ="+0);
        	return ;
        }
        System.out.print("sum="+sum+" "+"avg="+(sum/cnt));
	}

}
