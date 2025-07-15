import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class RandomAccessFileChallenge {

    private static final String FILE_NAME = "employees.dat";
    private static final String RESOURCES_PATH = "resources/";

    static class Employee {
        int id;
        double salary;
        String firstName;
        String lastName;

        public Employee(int id, double salary, String firstName, String lastName) {
            this.id = id;
            this.salary = salary;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return "Employee ID: " + id +
                    ", Salary: $" + String.format("%.2f", salary) +
                    ", Name: " + firstName + " " + lastName;
        }
    }

    private Map<Integer, Long> employeeIndex = new TreeMap<>();

    private RandomAccessFile raf;

    public RandomAccessFileChallenge(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Creating new employee data file: " + filePath);
        }
        this.raf = new RandomAccessFile(file, "rw");
    }

    public void loadEmployeeIndex() throws IOException {
        if (raf.length() == 0) {
            System.out.println("File is empty, no index to load.");
            return;
        }

        raf.seek(0);
        int totalRecords = raf.readInt();
        System.out.println("Total employee records in index: " + totalRecords);

        employeeIndex.clear();
        for (int i = 0; i < totalRecords; i++) {
            int employeeId = raf.readInt();
            long filePosition = raf.readLong();
            employeeIndex.put(employeeId, filePosition);
        }
        System.out.println("Employee index loaded successfully.");
    }

    public void listEmployeeIDs() {
        if (employeeIndex.isEmpty()) {
            System.out.println("No employee IDs in the index.");
            return;
        }
        System.out.println("\n--- Employee IDs (Sorted) ---");
        for (Integer id : employeeIndex.keySet()) {
            System.out.println("ID: " + id);
        }
        System.out.println("----------\n");
    }

    public Employee retrieveEmployeeRecord(int employeeId) throws IOException {
        Long position = employeeIndex.get(employeeId);
        if (position == null) {
            System.out.println("Employee with ID " + employeeId + " not found in index.");
            return null;
        }

        raf.seek(position);

        int id = raf.readInt();
        double salary = raf.readDouble();
        String firstName = raf.readUTF();
        String lastName = raf.readUTF();

        return new Employee(id, salary, firstName, lastName);
    }

    public void printEmployeeRecord(Employee employee) {
        if (employee != null) {
            System.out.println("Retrieved: " + employee);
        } else {
            System.out.println("Employee record is null, cannot print.");
        }
    }

    public void updateEmployeeSalary(int employeeId, double newSalary) throws IOException {
        Long position = employeeIndex.get(employeeId);
        if (position == null) {
            System.out.println("Employee with ID " + employeeId + " not found for salary update.");
            return;
        }

        raf.seek(position);

        raf.skipBytes(4);

        raf.writeDouble(newSalary);
        System.out.println("Updated salary for employee ID " + employeeId + " to $" + String.format("%.2f", newSalary));
    }

    public void close() throws IOException {
        if (raf != null) {
            raf.close();
            System.out.println("RandomAccessFile closed.");
        }
    }

    private static void createDummyEmployeeFile(String filePath, int numberOfRecords) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw")) {
            raf.writeInt(0);
            long indexStartPosition = raf.getFilePointer();

            Map<Integer, Long> tempIndex = new HashMap<>();
            long currentRecordPosition = -1;

            long dataStartOffset = 4L + (12L * numberOfRecords);
            raf.seek(dataStartOffset);

            for (int i = 1; i <= numberOfRecords; i++) {
                currentRecordPosition = raf.getFilePointer();
                tempIndex.put(i, currentRecordPosition);

                int id = i;
                double salary = 50000.00 + (i * 1000);
                String firstName = "First" + i;
                String lastName = "Last" + i;

                raf.writeInt(id);
                raf.writeDouble(salary);
                raf.writeUTF(firstName);
                raf.writeUTF(lastName);
            }

            raf.seek(0);
            raf.writeInt(numberOfRecords);

            raf.seek(indexStartPosition);
            for (Map.Entry<Integer, Long> entry : tempIndex.entrySet()) {
                raf.writeInt(entry.getKey());
                raf.writeLong(entry.getValue());
            }

            System.out.println("Dummy emploe file created with " + numberOfRecords + " records at " + filePath);
        }
    }

    public static void main(String[] args) {
        File resourcesDir = new File(RESOURCES_PATH);
        if (!resourcesDir.exists()) {
            resourcesDir.mkdirs();
        }
        String fullFilePath = RESOURCES_PATH + FILE_NAME;

        File employeeFile = new File(fullFilePath);
        if (!employeeFile.exists()) {
            try {
                createDummyEmployeeFile(fullFilePath, 5);
            } catch (IOException e) {
                System.err.println("Error creating dummy file: " + e.getMessage());
                return;
            }
        }

        RandomAccessFileChallenge manager = null;
        try {
            manager = new RandomAccessFileChallenge(fullFilePath);

            System.out.println("\n--- Loading Employee Index ---");
            manager.loadEmployeeIndex();

            manager.listEmployeeIDs();

            System.out.println("\n--- Retrieving Employee ID 3 ---");
            int employeeIdToRetrieve = 3;
            Employee retrievedEmployee = manager.retrieveEmployeeRecord(employeeIdToRetrieve);

            manager.printEmployeeRecord(retrievedEmployee);

            System.out.println("\n--- Updating Salary for Employee ID 3 ---");
            double newSalary = 75000.00;
            manager.updateEmployeeSalary(employeeIdToRetrieve, newSalary);

            System.out.println("\n--- Retriving Employee ID 3 (After Update) ---");
            Employee updatedEmployee = manager.retrieveEmployeeRecord(employeeIdToRetrieve);
            manager.printEmployeeRecord(updatedEmployee);

        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (manager != null) {
                    manager.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing RandomAccessFile: " + e.getMessage());
            }
        }
    }
}