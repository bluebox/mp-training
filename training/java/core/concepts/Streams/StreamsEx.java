package JAVAtraining;
//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
public class StreamsEx {

	public static void main(String[] args) {
		List<Integer> nums=Arrays.asList(4,5,7,3,2,6);
		/*int sum=0;
		for(int n: nums) {
			if(n%2==0) {
				n=n*2;
				sum=sum+n;
			}
		}
		nums.forEach(n->System.out.println(n));
		System.out.println(sum);
		*/
		Stream<Integer> sn=nums.stream();
		sn.forEach(n->System.out.println(n));
	}

}
