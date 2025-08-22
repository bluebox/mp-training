package com.prana;

import java.io.*;
import java.util.*;

public class EmployeeMana {

    static class Employee {
        int id;
        double salary;
        String firstName;
        String lastName;

        Employee(int id, double salary, String firstName, String lastName) {
            this.id = id;
            this.salary = salary;
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    public static void main(String[] args) {
    	// TODO Auto-generated method stub
    	System.out.println("Working directory → " + System.getProperty("user.dir"));
        List<Employee> employees = List.of(
            new Employee(101, 55000.0, "Alice", "Johnson"),
            new Employee(102, 62000.0, "Bob", "Smith"),
            new Employee(103, 47000.0, "Charlie", "Brown"),
            new Employee(104, 73000.0, "Diana", "Prince"),
            new Employee(105, 51000.0, "Ethan", "Hunt")
        );

        Map<Integer, Long> indexMap = new LinkedHashMap<>();

        try (RandomAccessFile dataFile = new RandomAccessFile("employee_data.dat", "rw")) {
            for (Employee emp : employees) {
                long position = dataFile.getFilePointer();
                indexMap.put(emp.id, position);

                dataFile.writeInt(emp.id);
                dataFile.writeDouble(emp.salary);
                dataFile.writeUTF(emp.firstName);
                dataFile.writeUTF(emp.lastName);
            }
        } catch (IOException e) {
            System.err.println("Error writing employee data: " + e.getMessage());
        }

        try (RandomAccessFile indexFile = new RandomAccessFile("employee_index.dat", "rw")) {
            indexFile.writeInt(indexMap.size());
            for (Map.Entry<Integer, Long> entry : indexMap.entrySet()) {
                indexFile.writeInt(entry.getKey());
                indexFile.writeLong(entry.getValue());
            }
        } catch (IOException e) {
            System.err.println("Error writing index file: " + e.getMessage());
        }

        System.out.println(" Files generated: employee_data.dat and employee_index.dat");
    }
}