
public class GreatestCommonFactor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getGreatestCommonDivisor(6,12));

	}
	public static int getGreatestCommonDivisor(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
}
}
