package logical;

public class EqualSum {
	public static void main( String[] args) {
	int parameter1 = 1;
	int parameter2 = -1;
	int total = 0;
	boolean result = hasEqualSum( parameter1,parameter2, total );
	if(result) {
		System.out.println("same");
	}
	else {
		System.out.println(" not same");
	}
	}
public static boolean hasEqualSum(int parameter1,int parameter2, int sum){
	if(parameter1 + parameter2 == sum) {
		return true;
	}
	return false;
}
}

