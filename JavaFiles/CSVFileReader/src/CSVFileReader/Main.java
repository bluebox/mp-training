package CSVFileReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String csvFile = "/home/karthik-malasani/Downloads/employee_data.csv";
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {

            	String[] data = line.split(cvsSplitBy);
            	
                System.out.print(Arrays.toString(data));


                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}