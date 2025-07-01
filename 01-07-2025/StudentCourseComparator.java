package com.prana;

import java.util.Comparator;

public class StudentCourseComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getCourse().compareToIgnoreCase(s2.getCourse());
    }
}


