public class EqualSum {

	public static void main(String[] args) {
		System.out.println(hasEqualSum(1,2,3));
		System.out.println(hasEqualSum(1,-1,3));
		System.out.println(hasEqualSum(1,1,3));
		System.out.println(hasEqualSum(2,2,3));
		System.out.println(hasEqualSum(1,2,9));
		System.out.println(hasEqualSum(2,2,4));
		// TODO Auto-generated method stub

	}
	public static boolean hasEqualSum(int a,int b,int c) {
		if( (a+b)==c) {return true;}
		return false;
	}

}
