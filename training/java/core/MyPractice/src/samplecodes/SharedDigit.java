package samplecodes;

public class SharedDigit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(hasSharedDigit(34,74));

	}
	public static boolean hasSharedDigit(int a ,int b) {
		if(a<10 || b<10) return false;
		if(a>99 || b>99) return false;
		String s1=Integer.toString(a);
		String s2=Integer.toString(b);
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				if(s1.charAt(i)==s2.charAt(j)) return true;
			}
		}
		return false;
	}

}
