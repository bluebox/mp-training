package Comparable_Comparator;

import java.util.*;

public class StudentA {
    int id;
    String name;

    StudentA(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return id + " " + name;
    }

    public static void main(String[] args) {
        StudentA[] students = {
            new StudentA(2, "Alice"),
            new StudentA(1, "Bob"),
            new StudentA(3, "Charlie")
        };

        // Sort by name using Comparator
        Arrays.sort(students, new Comparator<StudentA>() {
            public int compare(StudentA s1, StudentA s2) {
                return s1.name.compareTo(s2.name);
            }
        });

        for (StudentA s : students)
            System.out.println(s);
    }
}

