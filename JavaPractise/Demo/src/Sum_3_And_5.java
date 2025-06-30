
public class Sum_3_And_5 {

	public static void main(String[] args) {
		long sum=0;
		int count=0;
		for(int i=1;i<=1000;i++) {
			if(i%3==0 && i%5==0) {
				count++;
				// if(count>5) break;
				sum+=i;
				System.out.println(i);
			}
			
		}
		System.out.println("The Sum is :"+sum);
		

	}

}
