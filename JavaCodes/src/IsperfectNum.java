import java.util.*;
public class IsperfectNum {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int val=sc.nextInt();
		int sum=0;
		for(int i=1;i<val;i++) {
			if(val%i==0)sum+=i;
		}
		if(sum==val) {
			System.out.print(val+" is a perfect number");
		}
		else System.out.print(val +" is not a perfect Number");
	}

}
