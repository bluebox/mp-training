package com.saketh.day2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages="com.saketh.day2")
public class ProjectConfig {
//	@Bean(name="saketh")
//	public Student student() {
//		Student std=new Student();
//		std.setName("saketh");
//		return std;
//	}
//	@Primary
//	@Bean(value="vardhan")
//	public Student student2() {
//		Student std=new Student();
//		std.setName("vardhan");
//		return std;
//	}
//	
//	@Bean("Tulsi")
//	public Student student3(){
//		Student std=new Student();
//		std.setName("Tulsi");
//		return std;
//	}
}
