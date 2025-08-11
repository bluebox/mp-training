package com.example.demo.lombok;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LombokMain {
	private static Logger logger = LoggerFactory.getLogger(LombokMain.class);
	public static void main(String[] args) {

		Member member1 = new Member();
		member1.setName("kaushik");
		member1.setAge(22);
		System.out.println(member1);
		member1 = Member.builder().name("kashyap").age(18).build();
		System.out.println(member1);
		Book book = new Book("harry potter", 500);
		System.out.println(book);
        logger.debug("Debug log message");
        logger.info("Info log message");
        logger.error("Error log message");
	}

}
