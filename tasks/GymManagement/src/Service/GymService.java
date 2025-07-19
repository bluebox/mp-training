package Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import DAO.MemberDao;
import DAO.PlanDao;
import GymManagement.Member;
import GymManagement.MembershipPlan;

public class GymService {
    private MemberDao memberDao;
    private PlanDao planDao;
    private Connection conn;

    public GymService(Connection conn) {
        this.conn = conn;
        this.memberDao = new MemberDao(conn);
        this.planDao = new PlanDao(conn);
    }

    public void addMember(Member member) {
        try {
            conn.setAutoCommit(false);
            memberDao.insertMember(member);
            conn.commit();
            System.out.println("Member added successfully.");
        } catch (SQLException e) {
            try {
                conn.rollback();
                System.out.println("Transaction failed. Rolled back.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void assignPlan(String memberId, int planIndex) {
        try {
            conn.setAutoCommit(false);
            MembershipPlan plan = planDao.getPlanByIndex(planIndex);
            if (plan == null) {
                System.out.println("Invalid plan index.");
                return;
            }
            memberDao.updateMemberPlan(memberId, plan.getPlanName());
            conn.commit();
            System.out.println("Plan assigned successfully.");
        } catch (SQLException e) {
            try {
                conn.rollback();
                System.out.println("Failed to assign plan. Rolled back.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void showAllMembers() {
        try {
            List<Member> members = memberDao.getAllMembers();
            if (members.isEmpty()) {
                System.out.println("No members found.");
            } else {
                for (Member member : members) {
                    member.showDetails();  
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<MembershipPlan> getAllPlans() throws SQLException {
        return planDao.getAllPlans();
    }
}
