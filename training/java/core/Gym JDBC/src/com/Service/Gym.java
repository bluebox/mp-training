package com.Service;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.time.LocalDate;

import com.Class.Gym_Member;
import com.Class.Gym_MembershipPlan;
import com.Exceptions.Gym_Member_Already_Exists;
import com.Exceptions.Gym_Member_Not_Found;
import com.Gym_Dao.Gym_Dao;

public class Gym {
    private Gym_Dao dao = new Gym_Dao();

    public void addMember(Gym_Member member) throws Exception {
        validateMember(member);
        List<Gym_Member> members = dao.getAllMembers();
        for (Gym_Member m : members) {
            if (m.getId().equals(member.getId())) {
                throw new Gym_Member_Already_Exists("Member with ID " + member.getId() + " already exists.");
            }
        }
        dao.addMember(member);
        System.out.println("Member added successfully!");
    }

    public void removeMember(String id) throws Exception {
        dao.removeMember(id);
        System.out.println("Member removed successfully!");
    }

    public void assignPlan(String id, Scanner sc) throws Exception {
        Gym_Member member = dao.findMemberById(id);
        if (member == null) throw new Gym_Member_Not_Found("Member not found with ID: " + id);

        displayPlans();
        System.out.print("Enter Plan Number (1-3): ");
        int choice = Integer.parseInt(sc.nextLine());

        String plan = Gym_MembershipPlan.getPlan(choice);
        member.setMembershipPlan(plan);
        member.setUpdatedDate(LocalDate.now());
        dao.updateMemberPlan(id, plan);
        System.out.println("Plan assigned successfully!");
    }

    public void updateMembershipPlan(String id, Scanner sc) throws Exception {
        Gym_Member member = dao.findMemberById(id);
        if (member == null) {
            throw new Gym_Member_Not_Found("Member not found.");
        }

        String currentPlan = member.getMembershipPlan();
        int currentPlanLevel = Gym_MembershipPlan.getPlanLevel(currentPlan);

        displayPlans();
        System.out.print("Enter new Plan Number (1-3): ");
        int newChoice = Integer.parseInt(sc.nextLine());
        String selectedPlan = Gym_MembershipPlan.getPlan(newChoice);

        int newPlanLevel = Gym_MembershipPlan.getPlanLevel(selectedPlan);

        if (newPlanLevel <= currentPlanLevel) {
            System.out.println("You can only upgrade to a higher plan. Downgrading is not allowed.");
            return;
        }

        member.setMembershipPlan(selectedPlan);
        member.setMembershipPlan(selectedPlan);
        member.setUpdatedDate(LocalDate.now());
        dao.updateMemberPlan(id, selectedPlan); 
        System.out.println("Membership plan upgraded successfully!");

    }

    public void displayAllMembers() throws Exception {
        List<Gym_Member> members = dao.getAllMembers();
        if (members.isEmpty()) {
            System.out.println("No members found.");
        } else {
            members.forEach(System.out::println);
        }
    }

    public void displayPlans() {
        System.out.println("\nAvailable Plans:");
        System.out.println("1. Basic - 1 month - $29.99");
        System.out.println("2. Standard - 2 months - $49.99");
        System.out.println("3. Premium - 3 months - $79.99");
    }

    private void validateMember(Gym_Member member) throws Exception {
        if (member.getId().isEmpty()) throw new Exception("ID cannot be empty.");
        if (!Pattern.matches("^[A-Za-z. ]+$", member.getName())) throw new Exception("Name must contain only alphabets.");
        if (member.getAge() <= 0 || member.getAge() > 120) throw new Exception("Invalid age.");
        if (!member.getGender().matches("(?i)Male|Female|Other")) throw new Exception("Gender must be Male, Female, or Other.");
        if (!member.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) throw new Exception("Invalid email format.");
    }
}
