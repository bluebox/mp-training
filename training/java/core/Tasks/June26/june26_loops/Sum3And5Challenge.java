package corejava.june26_loops;

public class Sum3And5Challenge {

	public static void main(String[] args) {
		int sum=0;
		int count=0;
		for(int i=1; i<=1000;i++) {
			if(i%3 ==0 && i%5 ==0) {
				count++;
				sum += i;
				System.out.println(i);
				if(count==5)
					break;
			}
		}
		System.out.print("Sum of the numbers(listed above are the first 5 numbers those met the condition) is:"+sum);
	}

}
