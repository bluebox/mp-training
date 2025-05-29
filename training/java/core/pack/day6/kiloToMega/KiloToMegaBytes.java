package day6.kiloToMega;
import java.util.*;

//1MB=1024KB
public class KiloToMegaBytes {
public static void main(String args[]) {
	Scanner sc=new Scanner(System.in);
	int t=0;
	
	while(true) {
		t=sc.nextInt();
		if(t==-1)
			break;
		printMegaBytesAndKiloBytes(t);
		
	}
	
	
}
public static void  printMegaBytesAndKiloBytes(int t) {
	int var=1024;
	int mega=t/var;
	int kilo=t%var;
	
	System.out.println(t+" KB = "+mega +" MB and "+kilo +"KB");
}
}
