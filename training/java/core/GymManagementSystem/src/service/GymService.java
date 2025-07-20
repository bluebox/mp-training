package service;

import dao.MemberDAO;
import dao.PlanDAO;
import model.Member;
import model.MembershipPlan;

import java.sql.SQLException;
import java.util.List;

public class GymService {
    private final MemberDAO memberDAO = new MemberDAO();
    private final PlanDAO planDAO = new PlanDAO();

    public void addMember(String name, int age) throws Exception {
        if (name == null || name.trim().isEmpty()) throw new Exception("Name cannot be empty");
        for (char c : name.toCharArray()) {
            if (!Character.isLetter(c)) { 
            	throw new Exception("Name cannot have numbers");
            }
        }
        if (age < 14 || age >100) throw new Exception("Age must be above 14 and valid");

        Member member = new Member(0, name, age);
        memberDAO.addMember(member);
    }

    public void assignPlanToMember(int memberId, int planId) throws Exception {
        if (memberId <= 0 || planId <= 0) throw new Exception("Invalid member ID or plan ID");
        memberDAO.assignPlan(memberId, planId);
    }
    public boolean deleteMemberById(int memberId) throws Exception {
        return memberDAO.deleteMember(memberId);
    }


    public List<Member> getAllMembers() throws SQLException {
        return memberDAO.getAllMembers();
    }

    public List<MembershipPlan> getAllPlans() throws SQLException {
        return planDAO.getAllPlans();
    }
}
