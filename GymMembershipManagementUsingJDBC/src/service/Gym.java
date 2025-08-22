package service;

import dao.MemberDAO;
import model.Member;
import model.MembershipPlan;
import java.sql.SQLException;
import java.util.List;

public class Gym {
    private MemberDAO dao;
    public Gym() {
        dao = new MemberDAO();
    }
    public void addMember(Member member) {
        try {
            dao.addMember(member);
        } catch (SQLException e) {
            System.out.println("Error adding member: " + e.getMessage());
        }
    }
    public void updatePlan(int memberId, MembershipPlan plan) {
        try {
            dao.updatePlanForMember(memberId, plan);
        } catch (SQLException e) {
            System.out.println("Error updating plan: " + e.getMessage());
        }
    }
    public void deleteMember(int memberId) {
        try {
            dao.deleteMember(memberId);
        } catch (SQLException e) {
            System.out.println("Error deleting member: " + e.getMessage());
        }
    }
    public void cancelMembership(int memberId) {
        try {
            dao.cancelMembership(memberId);
        } catch (SQLException e) {
            System.out.println("Error cancelling membership: " + e.getMessage());
        }
    }
    public void showAvailablePlans() {
        try {
            List<MembershipPlan> plans = dao.getAllPlans();
            System.out.println("\n Available Membership Plans:");
            for (MembershipPlan p : plans) {
                System.out.println("- " + p.getPlanName() + "  " +
                        p.getDuration() + " months   ₹" + p.getFee());
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch plans: " + e.getMessage());
        }
    }
    public MembershipPlan getPlanByName(String planName) {
        try {
            return dao.getPlanByName(planName);
        } catch (SQLException e) {
            System.out.println("Error fetching plan: " + e.getMessage());
            return null;
        }
    }
    public void viewAllMembers() {
        try {
            List<String> members = dao.viewAllMembers();
            if (members.isEmpty()) {
                System.out.println("No members found.");
            } else {
                System.out.println("\n All Members:");
                for (String member : members) {
                    System.out.println(member);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching members: " + e.getMessage());
        }
    }
    public void close() {
        dao.close();
    }
}
