
public class ForLoopChallenge {

	public static void main(String[] args) {
		int cnt=0,sum=0;
		for(int i=1;i<=1000;i++) {
			if(i%3==0 && i%5==0) {
				System.out.println(i);
				sum+=i;
				cnt++;
			}
			if(cnt>=5)break;
		}
		System.out.print(sum);
	}

}
