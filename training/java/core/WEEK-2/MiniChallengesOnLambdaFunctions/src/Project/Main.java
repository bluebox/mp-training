package Project;

public class Main {
	public static void main(String [] args) {
		System.out.println("======MiniChallenge 1=======");
		MiniChallenge1 m1=new MiniChallenge1();
		m1.printAllWords("My name is Rohit Varma");
		
		System.out.println("======MiniChallenge 2=======");
		MiniChallenge2 m2=new MiniChallenge2();
		System.out.println(m2.getEvenCharString("My name is Rohit Varma"));
		
		System.out.println("======MiniChallenge 3=======");
		System.out.println(m2.getEvenCharString("1234567890"));
		
		System.out.println("======MiniChallenge 4=======");
		MiniChallenge4 m4=new MiniChallenge4();
		m4.getEvenCharString();
		
		System.out.println("======MiniChallenge 5=======");
		m4.getEvenCharString("1234567890");
		
		System.out.println("======MiniChallenge 6=======");
		MiniChallenge5 m5=new MiniChallenge5();
		String iLoveJava=m5.getString();
		System.out.println(iLoveJava);
	}
}
