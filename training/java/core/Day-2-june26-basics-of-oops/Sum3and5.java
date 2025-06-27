package day_2_june26_basics_of_oops;

public class Sum3and5 {

	public static void main(String[] args) {
		int sum=0;
		int counter=1;
		int i=1;
		while(counter<=5) {
			if(i%3==0 && i%5==0) {
				System.out.println(i);
				counter++;
				sum+=i;
			}
			i++;
		}
		System.out.println("Sum is "+sum);
	}

}
