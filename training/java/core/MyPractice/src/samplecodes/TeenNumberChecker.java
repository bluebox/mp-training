package samplecodes;

public class TeenNumberChecker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(hasTeen(9,99,13));
	}
	public static boolean hasTeen(int a,int b,int c) {
		if(a>=13 && a<=19) return true;
		if(b>=13 && b<=19) return true;
		if(c>=13 && c<=19) return true;
		return false;
	}

}
