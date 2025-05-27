
public class Sum3And5 {
	public static int digitSum(long num) {
		int sum=0;
		while(num>0) {
			sum+=num%10;
			num/=10;
		}
		return sum;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sum=0;
		for(int i=1;i<=1000;i++) {
			if(i%3==0 || i%5==0) {
				sum+=i;
			}
		}
		System.out.print(sum);
	}

}
