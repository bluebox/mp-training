package com.befit.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.befit.app.beans.Member;
import com.befit.app.impl.Implementation;

@SpringBootApplication
public class BefitApplication {

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(BefitApplication.class, args);
		Implementation impl = new Implementation();
		Member member = new Member(0,"saketh",9959062620L,22,87.20f,2.1f,"slkdfslkf","gold",new SimpleDateFormat("dd-MM-yyyy").parse("12-07-2025"),new SimpleDateFormat("dd-MM-yyyy").parse("12-07-2025"),"ACTIVE");
		System.out.println(impl.addMember(member));
		System.out.println(impl.viewAllMembers().toString());
		 member = new Member(0,"saketh maryala",9959062620L,22,87.20f,2.1f,"slkdfslkf","gold",new SimpleDateFormat("dd-MM-yyyy").parse("12-07-2025"),new SimpleDateFormat("dd-MM-yyyy").parse("12-07-2025"),"ACTIVE");
		 System.out.println(impl.updateMember(member));
		System.out.println(impl.viewAllMembers().toString());
		
	}

}
