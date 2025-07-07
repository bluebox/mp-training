package com.befit.app;

import com.befit.app.beans.Member;
import com.befit.app.beans.MemberShip;
import com.befit.app.impl.Implementation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class BefitApplication {

    public static void main(String[] args) throws ParseException {
        ApplicationContext context = SpringApplication.run(BefitApplication.class, args);
//        Implementation impl = context.getBean(Implementation.class);
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//
//        Date joinDate = sdf.parse("01-07-2025");
//        Date expiryDate = sdf.parse("01-07-2026");
//
//        Member member = new Member(
//                1, "John Doe", 9876543210L, 30, 75.5f, 1.75f, "123 Main Street", "Gold", joinDate, expiryDate, "ACTIVE"
//        );
//
//        System.out.println("Adding member: " + member);
//        boolean added = impl.addMember(member);
//        System.out.println("Add member result: " + added);
//
//        List<Member> members = impl.viewAllMembers();
//        System.out.println("All members: " + (members != null && !members.isEmpty() ? members : "No members found"));
//
//        Member updateMember = new Member(
//                1, "Saketh Maryala", 9959062620L, 22, 87.20f, 2.1f, "slkdfslkf", "Gold",
//                sdf.parse("12-07-2025"), sdf.parse("12-07-2026"), "ACTIVE"
//        );
//
//        System.out.println("Updating member: " + updateMember);
//        boolean updated = impl.updateMember(updateMember);
//        System.out.println("Update member result: " + updated);
//
//        members = impl.viewAllMembers();
//        System.out.println("All members after update: " + (members != null && !members.isEmpty() ? members : "No members found"));
//
//        MemberShip m = new MemberShip("Gold ", 200.89f, sdf.parse("12-07-2025"), sdf.parse("12-07-2026"),
//                Arrays.asList("Gym", "Swimming"), "ACTIVE");
//        System.out.println("Adding membership: " + impl.addMemberShip(m));
//        System.out.println("All memberships: " + impl.viewAllMemberShip());
    }
}