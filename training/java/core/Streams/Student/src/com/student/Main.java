package com.student;

import java.time.LocalDate;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Student s=new Student(1, "IND", 2023, 20, "male", false, new HashMap<>(), null, LocalDate.now(), "Sincere", 0, LocalDate.now());
		Student.studentList.put(s.getStudentId(),s);
		s.addCourse(new Course("Py","Python course",30));
		s.addCourse(new Course("Jb","Python course",30));
//		System.out.println(s.toString());
		s.addCourse(new Course("Jb","Java course",43), LocalDate.now().minusDays(385));
//		System.out.println(s.toString());
		s=new Student(2, "IND", 2021, 23, "male", true, new HashMap<>(), null, LocalDate.now(), "Sincere", 0, LocalDate.now());
		System.out.println("-------------------------------------------");
		Student.studentList.put(s.getStudentId(),s);
		for(Long i:Student.studentList.keySet()) {
			System.out.println(Student.studentList.get(i).toString());
			System.out.println("-------------------------------------------");
		}
		System.out.println(Student.studentList);
		
	}
}
