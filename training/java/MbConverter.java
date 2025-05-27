import java.util.Scanner;

public class MbConverter {
	
	public static String printMegaBytesAndKiloBytes(int kiloBytes) {
		if(kiloBytes < 0) return "Ivalid value";
		int mb=kiloBytes/1024;
		int kb=kiloBytes%1024;
		return kiloBytes+" KB = "+mb+" MB and "+kb+" KB";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int kiloBytes=sc.nextInt();
		String result=printMegaBytesAndKiloBytes(kiloBytes);
		System.out.print(result);

	}

}
