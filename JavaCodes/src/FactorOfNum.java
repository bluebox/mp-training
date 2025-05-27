import java.util.*;
public class FactorOfNum {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long val=sc.nextLong();
		for(long i=1;i<=val;i++) {
			if(val%i==0)System.out.print(i+" ");
		}
	}

}
