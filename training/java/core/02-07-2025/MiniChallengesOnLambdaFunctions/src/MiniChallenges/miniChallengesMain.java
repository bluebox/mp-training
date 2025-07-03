package MiniChallenges;

public class miniChallengesMain {
	public static void main(String [] args) {
		System.out.println("MiniChallenge-1");
		Mini1 m1=new Mini1();
		m1.printAllWords("My name is gopi");
		System.out.println("MiniChallenge-2");
		Mini2 m2=new Mini2();
		System.out.println(m2.getEvenCharString("My name is gopi"));
		System.out.println("MiniChallenge-3");
		System.out.println(m2.getEvenCharString("9347004578"));
		System.out.println("MiniChallenge-4");
		Mini3 m3=new Mini3();
		m3.getEvenCharString();
		Mini4 m4=new Mini4();
		String iLoveJava=m4.getString();
		System.out.println(iLoveJava);
	}
}
