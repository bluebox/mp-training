
public class SumOf3and5 {
	public static void main(String[] args) {
		
		int sum=0,counter=0;
		
		for(int i=1;i<1000;i++) {
			if(i%3==0 && i%5==0) {
				
				if(counter<=5) {
					System.out.println("Number divisible by 3 and 5 "+": "+i);
					sum+=i;
				}
				else {
					break;
				}
				counter++;
			}
		}
		
		System.out.println("Sum of 5 common divisibles :"+sum);
		
	}

}
