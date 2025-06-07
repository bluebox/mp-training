package filestasks;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateEmployeeFile {
    public static void main(String[] args) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("employee_records.dat"))) {
            // Write header (3 records)
            out.writeInt(3);
            
            // Write index entries
            out.writeInt(101);  // Employee ID 1
            out.writeLong(12);  // Position (after 4-byte count + 3*(4+8)=36 bytes index)
            
            out.writeInt(102);  // Employee ID 2
            out.writeLong(12 + Employee.RECORD_SIZE);  // Next position
            
            out.writeInt(103);  // Employee ID 3
            out.writeLong(12 + 2 * Employee.RECORD_SIZE);  // Next position
            
            // Write employee records
            writeEmployee(out, 101, 50000.0, "John", "Doe");
            writeEmployee(out, 102, 60000.0, "Jane", "Smith");
            writeEmployee(out, 103, 75000.0, "Robert", "Johnson");
            
            System.out.println("employee_records.dat created successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void writeEmployee(DataOutputStream out, int id, double salary, 
                                    String firstName, String lastName) throws IOException {
        out.writeInt(id);
        out.writeDouble(salary);
        out.writeUTF(firstName);
        out.writeUTF(lastName);
    }
    
    static class Employee {
        public static final int RECORD_SIZE = 4 + 8 + 50 + 50; // Estimated max size
    }
}
