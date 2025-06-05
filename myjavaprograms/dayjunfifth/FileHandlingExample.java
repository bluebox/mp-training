package dayjunfifth;

import java.io.*;

public class FileHandlingExample {
    public static void main(String[] args) {
        try {
            // Create File object
            File myFile = new File("example.txt");

            // Create new file if it doesn't exist
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists.");
            }

            // Write to file using FileWriter and BufferedWriter
            FileWriter fw = new FileWriter("example.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Hello, this is a sample file.");
            bw.newLine();
            bw.write("Java file handling is easy!");
            bw.close();
            fw.close();
            System.out.println("Successfully wrote to the file.");

            // Read from file using FileReader and BufferedReader
            FileReader fr = new FileReader("example.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            System.out.println("\nFile contents:");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
            fr.close();

            // File Info
            if (myFile.exists()) {
                System.out.println("\nFile Name: " + myFile.getName());
                System.out.println("Absolute Path: " + myFile.getAbsolutePath());
                System.out.println("File Size: " + myFile.length() + " bytes");
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

