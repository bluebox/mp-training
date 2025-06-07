package filestasks;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class EmployeeRecordManager {
    private static final String FILE_NAME = "employee_records.dat";
    private static Map<Integer, Long> employeeIndex = new HashMap<>();

    public static void main(String[] args) {
        try {
            // Initialize the file with sample data if it doesn't exist
            initializeFile();
            
            // Load the employee index into memory
            loadEmployeeIndex();
            
            // List employee IDs in order
            listEmployeeIds();
            
            // Retrieve and display an employee record
            int sampleId = new TreeSet<>(employeeIndex.keySet()).first();
            System.out.println("\nRetrieving employee with ID: " + sampleId);
            Employee emp = getEmployeeRecord(sampleId);
            System.out.println("Before update: " + emp);
            
            // Update the employee's salary
            double newSalary = emp.getSalary() * 1.1; // 10% raise
            updateEmployeeSalary(sampleId, newSalary);
            
            // Retrieve again to confirm update
            emp = getEmployeeRecord(sampleId);
            System.out.println("After update: " + emp);
            
        } catch (IOException e) {
            System.err.println("Error processing employee records: " + e.getMessage());
        }
    }

    private static void initializeFile() throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
            if (file.length() == 0) {
                // Create sample data if file is empty
                file.writeInt(3); // 3 employee records
                
                // Index entries
                file.writeInt(101); // Employee ID
                file.writeLong(12); // Position (after header)
                
                file.writeInt(102);
                file.writeLong(12 + Employee.RECORD_SIZE);
                
                file.writeInt(103);
                file.writeLong(12 + 2 * Employee.RECORD_SIZE);
                
                // Write employee records
                writeEmployeeRecord(file, new Employee(101, 50000.0, "John", "Doe"));
                writeEmployeeRecord(file, new Employee(102, 60000.0, "Jane", "Smith"));
                writeEmployeeRecord(file, new Employee(103, 75000.0, "Robert", "Johnson"));
            }
        }
    }

    private static void loadEmployeeIndex() throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")) {
            int recordCount = file.readInt();
            for (int i = 0; i < recordCount; i++) {
                int id = file.readInt();
                long position = file.readLong();
                employeeIndex.put(id, position);
            }
        }
    }

    private static void listEmployeeIds() {
        System.out.println("Employee IDs in order:");
        new TreeSet<>(employeeIndex.keySet()).forEach(System.out::println);
    }

    private static Employee getEmployeeRecord(int employeeId) throws IOException {
        Long position = employeeIndex.get(employeeId);
        if (position == null) {
            throw new IllegalArgumentException("Employee ID not found");
        }
        
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")) {
            file.seek(position);
            return readEmployeeRecord(file);
        }
    }

    private static void updateEmployeeSalary(int employeeId, double newSalary) throws IOException {
        Long position = employeeIndex.get(employeeId);
        if (position == null) {
            throw new IllegalArgumentException("Employee ID not found");
        }
        
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
            file.seek(position + 4); // Skip employee ID (4 bytes)
            file.writeDouble(newSalary);
        }
    }

    private static Employee readEmployeeRecord(RandomAccessFile file) throws IOException {
        int id = file.readInt();
        double salary = file.readDouble();
        String firstName = file.readUTF();
        String lastName = file.readUTF();
        return new Employee(id, salary, firstName, lastName);
    }

    private static void writeEmployeeRecord(RandomAccessFile file, Employee emp) throws IOException {
        file.writeInt(emp.getId());
        file.writeDouble(emp.getSalary());
        file.writeUTF(emp.getFirstName());
        file.writeUTF(emp.getLastName());
    }

    static class Employee {
        private final int id;
        private double salary;
        private final String firstName;
        private final String lastName;
        public static final int RECORD_SIZE = 4 + 8 + 50 + 50; // Estimated max size

        public Employee(int id, double salary, String firstName, String lastName) {
            this.id = id;
            this.salary = salary;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public int getId() { return id; }
        public double getSalary() { return salary; }
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }

        @Override
        public String toString() {
            return String.format("ID: %d, Name: %s %s, Salary: $%,.2f", 
                    id, firstName, lastName, salary);
        }
    }
}