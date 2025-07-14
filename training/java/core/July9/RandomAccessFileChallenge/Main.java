package July9.RandomAccessFileChallenge;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
	static final String employeeFile = "D:/New folder/Learning/employees.dat";

    public static void main(String[] args) {
        try (RandomAccessFile file = new RandomAccessFile(employeeFile, "rw")) {
            int totalRecords = file.readInt();
            Map<Integer, Long> indexMap = new TreeMap<>();
            for (int i = 0; i < totalRecords; i++) {
                int empId = file.readInt();
                long position = file.readLong();
                indexMap.put(empId, position);
            }
            printFile(file,indexMap);
            System.out.println("Employee IDs:");
            for (int id : indexMap.keySet()) {
                System.out.println(" - " + id);
            }
            
            System.out.print("Enter employee ID to retrieve: ");
            Scanner sc = new Scanner(System.in);
            int targetId = sc.nextInt();

            if (!indexMap.containsKey(targetId)) {
                System.out.println("Employee ID not found.");
                sc.close();
                return;
            }
            
            long recordPosition = indexMap.get(targetId);
            file.seek(recordPosition);
            int id = file.readInt();
            double salary = file.readDouble();
            String firstName = file.readUTF();
            String lastName = file.readUTF();

            System.out.println("Original Record:");
            System.out.println("ID: "+id+", Name: "+firstName+" "+lastName+", Salary: "+salary);

            System.out.print("Enter new salary: ");
            double newSalary = sc.nextDouble();

            file.seek(recordPosition + 4); 
            file.writeDouble(newSalary);

            file.seek(recordPosition);
            id = file.readInt();
            salary = file.readDouble();
            firstName = file.readUTF();
            lastName = file.readUTF();

            System.out.println("Updated Record:");
            System.out.println("ID: "+id+", Name: "+firstName+" "+lastName+", Salary: "+salary);
            sc.close();
        } 
        catch (IOException e) {
            System.err.println("Error reading or writing file: " + e.getMessage());
        }
        
    }
    public static void printFile(RandomAccessFile employeeFile,Map<Integer, Long> indexMap) throws IOException {
    	System.out.println("Employee data");
    	for(Long recordposition:indexMap.values()) 
    	{
    		employeeFile.seek(recordposition);
    		int id=employeeFile.readInt();
    		Double salary = employeeFile.readDouble();
            String firstName = employeeFile.readUTF();
            String lastName = employeeFile.readUTF();
    		System.out.println("ID: "+id+", Name: "+firstName+" "+lastName+", Salary: "+salary);
    	}
    }
}
