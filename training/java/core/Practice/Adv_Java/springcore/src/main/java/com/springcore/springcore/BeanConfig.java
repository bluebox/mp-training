package com.springcore.springcore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("com.springcore")
public class BeanConfig {
	@Bean
	Teacher teacher1() {
		Teacher t1=new Teacher();
		t1.setTeacherId(1);
		t1.setTeacherName("TeluguTeacher");
		t1.setSubject("Telugu");
		return t1;
	}
	@Bean
	Teacher teacher2() {
		Teacher t2=new Teacher();
		t2.setTeacherId(2);
		t2.setTeacherName("HindiTeacher");
		t2.setSubject("Hindi");
		return t2;
	}
	@Bean(name="3")
	Teacher teacher3() {
		Teacher t3=new Teacher();
		t3.setTeacherId(3);
		t3.setTeacherName("EnglishTeacher");
		t3.setSubject("English");
		return t3;
	}
	@Bean
	@Primary
	Teacher teacher4() {
		Teacher t4=new Teacher();
		t4.setTeacherId(4);
		t4.setTeacherName("MathsTeacher");
		t4.setSubject("Mathematics");
		return t4;
	}
	@Bean(value="5")
	Teacher teacher5() {
		Teacher t5=new Teacher();
		t5.setTeacherId(5);
		t5.setTeacherName("ScienceTeacher");
		t5.setSubject("Science");
		return t5;
	}
	@Bean("6")
	Teacher teacher6() {
		Teacher t6=new Teacher();
		t6.setTeacherId(6);
		t6.setTeacherName("SocialeTeacher");
		t6.setSubject("Social");
		return t6;
	}
}
