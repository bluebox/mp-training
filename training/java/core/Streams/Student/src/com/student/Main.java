package com.student;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		HashMap<String, Course> c = randCourse();
		for(int i=0;i<10;i++) {
			Student s = randStudent();
			Student.studentList.put(s.getStudentId(), s);
		}
		System.out.println("No of students : "+Student.studentList.size());
		System.out.println("----------------------------");
		Student.studentList.values().stream()
		.sorted()
		.limit(10)
		.forEach(s->s.engagementMap.put("NC", new CourseEngagement(new Course("NC","New Course",47),LocalDate.now(),"Intrested",0,LocalDate.now())));
		Student.studentList.values().stream()
		.forEach(s->System.out.println(s.engagementMap));
		System.out.println("----------------------------");
		Map<String, Long> coll = Student.studentList.values().stream()
		.flatMap(s->s.engagementMap.keySet().stream())
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		coll.forEach((course,count)->System.out.println("Course : "+course+"\nNo of students : "+count+"\n"));
		System.out.println("----------------------------");
		Map<Object, Long> col1 = Student.studentList.values().stream()
		.collect(Collectors.groupingBy(s->s.engagementMap.size(),Collectors.counting()));
		System.out.printf("%20s%20s\n", "No of courses","No of students");
		col1.forEach((obj1,count)->System.out.printf("%20s%20s\n",obj1,count));
		System.out.println("----------------------------");
		Map<Object, Double> col2 = Student.studentList.values().stream()
		.flatMap(s->s.engagementMap.entrySet().stream())
		.collect(Collectors.groupingBy(Map.Entry::getKey,Collectors.averagingDouble(s->s.getValue().getPercentageComplete())));
		System.out.printf("%20s%20s\n", "Course","Average of percentage completed");
		col2.forEach((obj1,count)->System.out.printf("%20s%20s\n",obj1,count));
		System.out.println("----------------------------");
		
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
		Map<String,CourseEngagement> e=new HashMap<>();
		int x = r.nextInt(3)+1;
		while(e.size()<x) {
			  String ce = randCourse().keySet().toArray()[r.nextInt(randCourse().keySet().size())].toString();
			e.put(ce,new CourseEngagement(randCourse().get(ce),LocalDate.now(),s2,r.nextInt(),LocalDate.now()));
			
		}
		return new Student(r.nextInt(), s1, r.nextInt(5)+2021, r.nextInt(25), r.nextBoolean()?"Male":"Female", r.nextBoolean(),e);
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
