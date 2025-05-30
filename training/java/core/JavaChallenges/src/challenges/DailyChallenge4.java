package challenges;

public class DailyChallenge4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {1,2,2,3,4,4};
		int xor = 0;
		for (int i : arr) {
			xor ^= i;
		}
		int setBitIndex = xor & ~(xor - 1);
		int res=0;
		while((xor&1)==0)
		{
			xor>>=1;
			res++;
		}
		int v=1>>res;

		int x = 0, y = 0;

		for (int i = 0; i < arr.length; i++) {

			if ((arr[i] & setBitIndex) != 0) {
				x ^= arr[i];
			} else {
				y ^= arr[i];
			}
		}
		System.out.println(x+" "+y);

	}

}
