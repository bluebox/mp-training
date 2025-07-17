package randomAcessFile;

import java.io.*;
import java.util.*;

public class EmployeeService {
    private final String datPath;
    private final Map<Integer, Long> indexMap;

    public EmployeeService(String datPath, Map<Integer, Long> indexMap) {
        this.datPath = datPath;
        this.indexMap = indexMap;
    }

    public void listEmployeeIds() {
        System.out.println("Employee IDs:");
        for (Integer id : indexMap.keySet()) {
            System.out.println(" - " + id);
        }
    }

    public void displayEmployee(int empId) throws IOException {
        Long pos = indexMap.get(empId);
        if (pos == null) {
            System.out.println("Employee not found!");
            return;
        }

        try (RandomAccessFile raf = new RandomAccessFile(datPath, "r")) {
            raf.seek(pos);
            int id = raf.readInt();
            double salary = raf.readDouble();

            int fnameLen = raf.readInt();
            byte[] fnameBytes = new byte[fnameLen];
            raf.readFully(fnameBytes);
            String fname = new String(fnameBytes);

            int lnameLen = raf.readInt();
            byte[] lnameBytes = new byte[lnameLen];
            raf.readFully(lnameBytes);
            String lname = new String(lnameBytes);

            System.out.println("ID: " + id + ", Salary: " + salary + ", Name: " + fname + " " + lname);
        }
    }

    public void updateSalary(int empId, double newSalary) throws IOException {
        Long pos = indexMap.get(empId);
        if (pos == null) {
            System.out.println("Employee not found!");
            return;
        }

        try (RandomAccessFile raf = new RandomAccessFile(datPath, "rw")) {
            raf.seek(pos + 4); 
            raf.writeDouble(newSalary);
        }
    }
    
    public double getSalary(int empId) throws IOException {
        Long pos = indexMap.get(empId);
        if (pos == null) return -1;

        try (RandomAccessFile raf = new RandomAccessFile(datPath, "r")) {
            raf.seek(pos + 4); 
            return raf.readDouble();
        }
    }

}
