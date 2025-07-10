import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;

public class ReadingText {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter Id,Name, and Age:");
		String id=sc.next();
		String name=sc.next();
		int age=sc.nextInt();
		addToFile(id,name,age);
		addToFile(id,name,age);
		loadFromFile("abc.txt");
	}
	public static void loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("No saved data found.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                System.out.println("id :"+id +"  name :"+name +"  age :"+age);
            }
            System.out.println("Data loaded from text file.");
        } 
        catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
	public static void addToFile(String id,String name, int age) {
		   try {
			   BufferedWriter writer = new BufferedWriter(new FileWriter("abc.txt",true));
	           String res=String.format("%s,%s,%d", id, name, age);
	           writer.write(res);
	           writer.newLine();
	           writer.close();
	           System.out.println("Data saved to text file.");
	       }
		   catch (IOException e) {
		       System.out.println("Error saving file: " + e.getMessage());
		   }
	}

}
