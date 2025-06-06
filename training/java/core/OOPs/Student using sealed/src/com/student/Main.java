package com.student;

import java.time.LocalDate;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Random r=new Random();
		Student s=new Student(1, "IND", 2023, 20, "male", false, new HashMap<>(), null, LocalDate.now(), "Sincere", 0, LocalDate.now());
		Student.studentList.put(s.getStudentId(),s);
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
		HashMap<String, Course> c = randCourse();
		for(int i=0;i<100;i++) {
			s=randStudent();
			Student.studentList.put(s.getStudentId(), s);
		}
		System.out.println(Student.studentList.size());
		
	}
	public static Student randStudent() {
		Random r=new Random();
		Locale[] l=Locale.getAvailableLocales();
		String s="qwertyuiopasdfghjklzxcvbmn";
		String s1="";
		String s2="";
		for(int j=0;j<(r.nextInt(8)+5);j++) {
			s1+=s.charAt(r.nextInt(s.length()));
			s2+=s.charAt(r.nextInt(s.length()));
		}
		Map<String,CourseEngagement> e=null;
		int x = r.nextInt(3)+1;
		while(e.size()<x) {
			  String ce = randCourse().keySet().toArray()[r.nextInt(randCourse().keySet().size())].toString();
			e.put(ce,new CourseEngagement(randCourse().get(ce),LocalDate.now(),s2,r.nextInt(),LocalDate.now()));
			
		}
		return new Student(r.nextInt(), s1, r.nextInt(20)+2000, r.nextInt(25), r.nextBoolean()?"Male":"Female", r.nextBoolean(), null, null, null, null, 0, null);
	}
	public static HashMap<String, Course> randCourse() {
		Random r=new Random();
		HashMap<String,Course> c=new HashMap<>();
		while(c.size()<30) {
			String s="qwertyuiopasdfghjklzxcvbmn";
			String s1="";
			for(int j=0;j<(r.nextInt(8)+5);j++) {
				s1+=s.charAt(r.nextInt(s.length()));
			}
			c.put(s1.substring(0,2),new Course(s1.substring(0,2),s1,r.nextInt(20)+30));
		}
		return c;
	}
}
