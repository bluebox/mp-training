package com.prana;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Main2 {
    public static void main(String[] args) {
        Random rand = new Random();
        QueryList<LPAStudent> students = new QueryList<>();

        for (int i = 1; i <= 25; i++) {
            students.add(new LPAStudent(
                    "Student" + i,
                    "Course" + (i % 3 + 1),
                    2020 + (i % 4),
                    "ID" + String.format("%03d", i),
                    rand.nextInt(101)));
        }

        System.out.println(" Students with <= 50% completion:\n");
        List<LPAStudent> filtered = students.getMatches("PERCENTCOMPLETE", "50");

        System.out.println(" Sorted by natural order (Student ID):");
        filtered.sort(Comparator.naturalOrder());
        filtered.forEach(System.out::println);

        System.out.println("\n Sorted by percent complete:");
        filtered.sort(new PercentCompleteComparator());
        filtered.forEach(System.out::println);
    }
}
