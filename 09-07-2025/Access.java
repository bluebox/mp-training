package com.prana;

import java.io.*;
import java.util.*;

public class Access {

    public static void main(String[] args) {
    	// TODO Auto-generated method stub
        Map<Integer, Long> employeeIndex = new TreeMap<>();
        try (RandomAccessFile indexFile = new RandomAccessFile("employee_data.dat", "r"))
        {
            int totalRecords = indexFile.readInt();
            for (int i = 0; i < totalRecords; i++) {
                int empId = indexFile.readInt();
                long position = indexFile.readLong();
                employeeIndex.put(empId, position);
            }
            System.out.println("Employee IDs:");
            employeeIndex.keySet().forEach(System.out::println);
            int targetId = 102; 
            long position = employeeIndex.get(targetId);
            try (RandomAccessFile dataFile = new RandomAccessFile("employee_data.dat", "rw")) 
            {
                dataFile.seek(position);
                int empId = dataFile.readInt();
                double salary = dataFile.readDouble();
                String firstName = dataFile.readUTF();
                String lastName = dataFile.readUTF();
                System.out.printf("Before Update → ID: %d, Salary: %.2f, Name: %s %s%n",
                        empId, salary, firstName, lastName);
                double newSalary = salary + 5000.0;
                dataFile.seek(position + Integer.BYTES); 
                dataFile.writeDouble(newSalary);
                dataFile.seek(position);
                empId = dataFile.readInt();
                salary = dataFile.readDouble();
                firstName = dataFile.readUTF();
                lastName = dataFile.readUTF();
                System.out.printf("After Update → ID: %d, Salary: %.2f, Name: %s %s%n",
                        empId, salary, firstName, lastName);
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}