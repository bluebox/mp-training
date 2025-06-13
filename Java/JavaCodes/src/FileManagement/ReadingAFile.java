package FileManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadingAFile {

	public static void main(String[] args) throws FileNotFoundException {
		File f=new File("/home/developer/eclipse-workspace/JavaCodes/src/FileManagement/example.txt");
		Scanner sc=new Scanner(f);
	    while(sc.hasNextLine()) {
	    	String s=sc.nextLine();
	    	System.out.println(s);
	    }
	}

}
