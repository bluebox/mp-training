package CSVFileReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Write {
    public static void main(String[] args) {
        String filePath = "/home/karthik-malasani/output.csv"; 

        String[] headers = {"Name", "Age", "City"};
        String[][] data = {
            {"Karthik", "21", "HYD"},
            {"Karthi", "22", "AP"},
            {"Karthiee", "23", "AP"}
        };

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(String.join(",", headers));
            writer.newLine();

            for (String[] row : data) {
                writer.write(String.join(",", row));
                writer.newLine();
            }

            System.out.println("CSV file written ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        String line;
        
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {

            	String[] rdata = line.split(",");
            	
                System.out.print(Arrays.toString(rdata));


                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
