
public class While_Loop_Challenge {

	public static void main(String[] args) {
		int start =5;
		int count =0;
		while(start<=20) {
			if(isEven(start)) {
				System.out.println(start);
				count++;
			}
			start++;
		}
		System.out.println("The total count of even numberes is :"+ count);
		System.out.println("STEP - 2");
		step2();

	}
	public static boolean isEven(int num) {
		if(num%2==0) return true;
		return false;
	}
	public static void step2() {
		int start =5;
		int count =0;
		while(start<=20) {
			if(isEven(start)) {
				System.out.println(start);
				count++;
			}
			if(count==5) {
				break;
			}
			start++;
		}
	}

}
