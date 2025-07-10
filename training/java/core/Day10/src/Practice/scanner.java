package Practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class scanner {
    public static void main(String[] args) {
        try {
            
            File myFile = new File("//home/vardhan/eclipse-workspace/sample.txt"); 

           
            Scanner fileScanner = new Scanner(myFile);

            
            for (int i = 0; i < 10; i++) {
				if (fileScanner.hasNext()){
					System.out.println(fileScanner.next());
				}
			}
            
            System.out.println("==========WholeFile==========");
            
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                System.out.println(data);
            }

            
            fileScanner.close();

        } catch (FileNotFoundException e) {
            
            System.out.println("An error occurred: The file was not found.");
            e.printStackTrace(); 
        }
    }
}