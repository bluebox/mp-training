package service;

import model.Member;
import model.MembershipPlan;
import repository.MemberRepository;
import repository.PlanRepository;

import java.util.List;

public class GymService {
    private final MemberRepository memberRepo;
    private final PlanRepository planRepo;

    public GymService() {
        this.memberRepo = new MemberRepository();
        this.planRepo = new PlanRepository();
    }

    public void addMember(String id, String name, int age, int planId) {
        memberRepo.addMember(new Member(id, name, age, planId));
    }

    public void updateMemberPlan(String memberId, int planId) {
        memberRepo.updateMemberPlan(memberId, planId);
    }

    public void viewAllMembers() {
        List<Member> members = memberRepo.getAllMembers();
        for (Member m : members) 
        	System.out.println(m);
    }

    public void updateMemberDetails(String memberId, String name, int age) {
        memberRepo.updateMemberDetails(memberId, name, age);
    }

    public void deleteMember(String memberId) {
        memberRepo.deleteMember(memberId);
    }

    public void displayMemberDetails(String memberId) {
        Member m = memberRepo.getMemberById(memberId);
        System.out.println(m != null ? m : "Member not found.");
    }

    public void viewAllPlans() {
        List<MembershipPlan> plans = planRepo.getAllPlans();
        for (MembershipPlan plan : plans) 
        	System.out.println(plan);
    }
}
