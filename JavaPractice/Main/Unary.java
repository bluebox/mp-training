package Main;

public class Unary {
	public static void main(String args[]) {
		int incrNumber = 8;
		
		System.out.println("Pre-Increment of "+incrNumber+" is: "+(++incrNumber));
		System.out.println("Post-Increment of "+incrNumber+" is:"+(incrNumber++));
		System.out.println("Currrent value of incrNumber is: "+(incrNumber));
		 
		int decrNumber = 6;
		
		System.out.println("Pre-decrement of "+decrNumber+" is: "+(--decrNumber));
		System.out.println("Post-decrement of "+decrNumber+" is:"+(decrNumber--));
		System.out.println("Currrent value of decrNumber is: "+(decrNumber));
	}
}
