
public class GreatestCommonDivisor {
		public static int greatestCommonDivisor(int first,int second) {
			 if (first < 10 || second < 10) {
		            return -1;
		        }
		        
		        first = Math.abs(first);
		        second = Math.abs(second);

		        while (second != 0) {
		            int temp = second;
		            second = first % second;
		            first = temp;
		        }
		        return first;
	   }
}
