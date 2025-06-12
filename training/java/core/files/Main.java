import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String file = "example.txt";
        String text = "Hello World !!";

//Writing to a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(text);
            System.out.println("Successfully wrote the text to the file.");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }

// Reading from a file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.print("Reading from the file...\nText in the File is : ");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from the file: " + e.getMessage());
        }
    }
}