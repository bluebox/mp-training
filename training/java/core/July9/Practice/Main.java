package July9.Practice;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
        //System.out.println("Current working directory: " + System.getProperty("user.dir"));
		
		File file = new File("C:\\Users\\DELL\\OneDrive\\Desktop\\Medplus\\Java\\July\\src\\July9\\Practice\\test.txt");
		try {
			if(file.createNewFile()) {
				System.out.println("File created");
			}
			else {
				System.out.println("File already exist");
			}
			
			FileWriter writer = new FileWriter("C:\\Users\\DELL\\OneDrive\\Desktop\\Medplus\\Java\\July\\src\\July9\\Practice\\test.txt");
			writer.write("Hello World");
			writer.append("\nI'm Sahithi");
			writer.close();
			
			Scanner Reader = new Scanner(file);
			System.out.println("Printing");
          	while (Reader.hasNextLine()) {
                String data = Reader.nextLine();
                System.out.println(data);
            }
          	Reader.close();
          	
          	if(file.delete()) System.out.println("Deleting File");
          	else System.out.println("Cannot delete file");
          	
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("Finally block is executed");
		}
	}
}
