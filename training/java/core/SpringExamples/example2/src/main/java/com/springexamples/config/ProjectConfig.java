package com.springexamples.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.springexamples.beans.Student;

@Configuration
public class ProjectConfig {
	
	@Bean
	@Primary
	Student student1() {
		Student student=new Student();
		student.setId(234);
		student.setName("babu");
		return student;
	}
	
	@Bean
	Student student2() {
		Student student=new Student();
		student.setId(235);
		student.setName("Ravi");
		return student;
	}
	
	@Bean
	Student student3() {
		Student student=new Student();
		student.setId(236);
		student.setName("Suri");
		return student;
	}
}
