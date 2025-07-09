package DayP_07_07_practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Main {
	private static final String FILE_NAME = "counter.txt";
	static int count=0;
	 {
	File file = new File(FILE_NAME);
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        count=Integer.parseInt(reader.readLine());
    } catch (Exception e) {
        System.out.println("Error reading from file: " + e.getMessage());
    }
	}
	static String id="ID"+count;
	public Main() {
		count++;
		id="ID"+count;
		System.out.println("Object id is :"+ id);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(Integer.toString(count));
    } catch(IOException e) {
        System.out.println("Error writing to file: " + e.getMessage());
    }
	}
	public static void main(String args[]) {
		LocalDate ld=LocalDate.of(2025,12,25);
		System.out.println(ld);
		Main m1=new Main();
		new Main();
		new Main();
		new Main();
		new Main();
		new Main();
		new Main();
		System.out.println(Main.id);
	}
}
