
public class GreatestCommonDivisor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getGreatestCommonDivisor(25, 15));
        System.out.println(getGreatestCommonDivisor(12, 30));  
        System.out.println(getGreatestCommonDivisor(9, 18));   
        System.out.println(getGreatestCommonDivisor(-5, 15));  
	}

	public static int getGreatestCommonDivisor(int first, int second) {
		if (first < 10 || second < 10) {
            return -1;
        }

        while (second != 0) {
            int temp = second;
            second = first % second;
            first = temp;
        }

        return first;
	}
}
