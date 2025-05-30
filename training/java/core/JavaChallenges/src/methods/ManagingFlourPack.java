package methods;
import java.util.*;
public class ManagingFlourPack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the BigCount Value 1 unit=5 kilos");
		int a=sc.nextInt();
		System.out.println("Enter the SmallCount Value 1 unit=1 kilos");
		int b=sc.nextInt();
		System.out.println("Enter the  Value of Total kilos to Pack");
		int c=sc.nextInt();
		boolean res=canPack(a,b,c);
		System.out.println(res);

	}
	public static boolean canPack(int a,int b,int c)
	{
		int tot=(a*5)+b;
		return tot>=c;
	}

}
