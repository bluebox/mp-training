package GymPackage;

import java.sql.*;
import java.util.ArrayList;

public class GymDatabaseDAO implements Interface_DAO {

    private ArrayList<MembershipPlan> plans = new ArrayList<>();

    public GymDatabaseDAO() {
        plans.add(new MembershipPlan("Basic", 3, 100));
        plans.add(new MembershipPlan("Premium", 6, 180));
        plans.add(new MembershipPlan("Gold", 12, 300));
    }

    @Override
    public void addMember(Member member) {
        String sql = "INSERT INTO members (memberId, name, age) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, member.getMemberId());
            pstmt.setString(2, member.getName());
            pstmt.setInt(3, member.getAge());
            pstmt.executeUpdate();
            System.out.println("Member added to database successfully.");

        } catch (SQLException e) {
            System.out.println("Error adding member to database: " + e.getMessage());
        }
    }

    @Override
    public void updateMemberPlan(Member member) {
        String sql = "UPDATE members SET planName = ?, planDuration = ?, planFee = ? WHERE memberId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            MembershipPlan plan = member.getPlan();
            if (plan != null) {
                pstmt.setString(1, plan.getPlanName());
                pstmt.setInt(2, plan.getDurationMonths());
                pstmt.setDouble(3, plan.getFee());
                pstmt.setInt(4, member.getMemberId());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error updating member plan in database: " + e.getMessage());
        }
    }


    @Override
    public Member getMemberById(int id) {
        String sql = "SELECT * FROM members WHERE memberId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String planName = rs.getString("planName");

                Member member = new Member(name, age, id);

                if (planName != null) {
                    int planDuration = rs.getInt("planDuration");
                    double planFee = rs.getDouble("planFee");
                    member.assignPlan(new MembershipPlan(planName, planDuration, planFee));
                }
                return member;
            }
        } catch (SQLException e) {
            System.out.println("Error fetching member by ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Member> getMembers() {
        ArrayList<Member> membersList = new ArrayList<>();
        String sql = "SELECT * FROM members";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("memberId");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String planName = rs.getString("planName");

                Member member = new Member(name, age, id);

                if (planName != null) {
                    int planDuration = rs.getInt("planDuration");
                    double planFee = rs.getDouble("planFee");
                    member.assignPlan(new MembershipPlan(planName, planDuration, planFee));
                }
                membersList.add(member);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all members: " + e.getMessage());
        }
        return membersList;
    }

    @Override
    public ArrayList<MembershipPlan> getPlans() {
        return this.plans;
    }

    // These methods from the interface are not needed for the database approach
    @Override
    public void save() { }

    @Override
    public void load() { }

	@Override
	public void saveMembersToFile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadMembersFromFile() {
		// TODO Auto-generated method stub
		
	}
}