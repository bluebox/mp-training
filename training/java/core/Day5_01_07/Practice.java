package Day5_01_07;

public class Practice {
	public static void main(String args[]) {
		System.out.println(1+"2");//12
		System.out.println(1+2+"2");//32
		System.out.println(1+2.5+"2");//3.52
		System.out.println(1+2.5+"2"+3);//3.523
		
		System.out.println(8>>>2);//2
		System.out.println(8>>2);//2
		
		System.out.println(-8>>>2);//1073741822
		System.out.println(-8>>2);//-2
		
		System.out.println(ret());
		
		Integer n = 127;
		Integer b = 127;
		System.out.println(n == b); // true — uses Integer cache

		Integer c = 128;
		Integer d = 128;
		System.out.println(c == d); 

	}
	public static String ret() {
		int a=5;
//		switch(a) {
//		case 1:
//			return 6;
//		case 2:
//			return 9;
//		}
		return switch(a) {
		case 1:
			yield "6";
		case 2:
			yield "7";
		default:
			yield "5";
		};
		
	}
}
