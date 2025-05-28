package samplecodes;

public class sum3And5Challenge {
	public static void main(String[] args) {
		int sum=0;
		int cnt=0;
		for(int i=1;i<=1000;i++) {
			if(i%3==0 && i%5==0) {
				sum+=i;
				cnt++;
			}
			if(cnt==5)break;
		}
		System.out.println(sum);
		
	}
}
