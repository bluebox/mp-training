package gymWithJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;


//import com.mysql.cj.xdevapi.Statement;

public class GymManagementMain {
	    public static void main(String[] args) throws SQLException {
	        String url = "jdbc:mysql://localhost:3306/practice";
	        String user = "root";
	        String password = "Gopi@2507";

	        try (Connection conn = DriverManager.getConnection(url, user, password)) {
	            Statement stmt = conn.createStatement();
	            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS MEMBERS (" +
	                    "id INTEGER AUTO_INCREMENT NOT NULL, " +
	                    "name VARCHAR(255), " +
	                    "age INTEGER, " +
	                    "plan_id INTEGER, " +
	                    "PRIMARY KEY (id))");
	      //      stmt.executeUpdate("DROP TABLE MEMBERS");
	            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS MEMBERSHIP_PLAN (" +
	                    "Membership_id INTEGER AUTO_INCREMENT NOT NULL, " +
	                    "plan_name VARCHAR(255), " +
	                    "duration_months INTEGER, " +
	                    "FEE DECIMAL, " +
	                    "PRIMARY KEY (Membership_id))");
	       //     stmt.executeUpdate("DROP TABLE MEMBERSHIP_PLAN");

	            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS MEMBER_PLAN_HISTORY (" +
	                    "history_id INT AUTO_INCREMENT PRIMARY KEY, " +
	                    "member_id INT NOT NULL, " +
	                    "plan_id INT NOT NULL, " +
	                    "assigned_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
	                    "FOREIGN KEY (member_id) REFERENCES MEMBERS(id), " +
	                    "FOREIGN KEY (plan_id) REFERENCES MEMBERSHIP_PLAN(Membership_id))");
	            //     stmt.executeUpdate("DROP TABLE MEMBERSHIP_PLAN_HISTORY");

	            ResultSet planCheck = stmt.executeQuery("SELECT COUNT(*) FROM MEMBERSHIP_PLAN");
            if (planCheck.next() && planCheck.getInt(1) == 0) {
	                String insertPlan = "INSERT INTO MEMBERSHIP_PLAN(plan_name, duration_months, FEE) VALUES (?, ?, ?)";
	                PreparedStatement ps = conn.prepareStatement(insertPlan);
	                ps.setString(1, "Basic");
	                ps.setInt(2, 2);
	                ps.setDouble(3, 3000);
	                ps.executeUpdate();
	//            --------------------------------------------------                
	                ps.setString(1, "Premium");
	                ps.setInt(2, 6);
	                ps.setDouble(3, 6000);
	                ps.executeUpdate();
	//            ----------------------------------------------------                
	                ps.setString(1, "Gold");
	                ps.setInt(2, 12);
	                ps.setDouble(3, 10000);
	                ps.executeUpdate();
            }
	            Scanner sc = new Scanner(System.in);
	            int select;
	            System.out.println("Welcome to the Gym Management System");
	            do {
	                System.out.println("1. Add New Member");
	                System.out.println("2. Assign Membership Plan");
	                System.out.println("3. View Members");
	                System.out.println("4. View Membership Plans");
	                System.out.println("5. View Member Plan History");
	                System.out.println("6. Exit");
	                System.out.print("Choose option: ");
	                try {
	                    select = Integer.parseInt(sc.nextLine().trim());
	                   // System.out.println("\n");
	                    switch (select) {
//----------------------------------------------------------------------------------------------------------------------------------------	                    
	                        case 1 -> {
	                            System.out.print("Enter member name: ");
	                            String name = sc.nextLine().trim();
	                            if (!name.matches("[a-zA-Z ]+")) {
	                                System.out.println("Invalid name or u didn't entered any thing . Only alphabets allowed.");
	                                break;
	                            }
	                            System.out.print("Enter member age: ");
	                            int age;
	                            try {
	                                age = Integer.parseInt(sc.nextLine());
	                                if (age < 12 || age > 100) {
	                                    System.out.println("Age must be between 12 and 100.");
	                                    break;
	                                }
	                            } catch (NumberFormatException e) {
	                                System.out.println("Invalid age.");
	                               // System.out.println("\n");
	                                break;
	                            }
	                            String insertMember = "INSERT INTO MEMBERS(name, age) VALUES (?, ?)";
	                            PreparedStatement ps1 = conn.prepareStatement(insertMember);
	                            ps1.setString(1, name);
	                            ps1.setInt(2, age);
	                            ps1.executeUpdate();

	                            System.out.println(" Member added successfully.");
	                        }
//-------------------------------------------------------------------------------------------------------------------------
	                        case 2 -> {
	                            System.out.print("Enter Member ID: ");
	                            int memberId;
	                            try {
	                                memberId = Integer.parseInt(sc.nextLine());
	                            } catch (NumberFormatException e) {
	                                System.out.println("Invalid ID.");
	                                break;
	                            }
	                            PreparedStatement checkMember = conn.prepareStatement("SELECT * FROM MEMBERS WHERE id = ?");
	                            checkMember.setInt(1, memberId);
	                            ResultSet rs = checkMember.executeQuery();
	                            if (!rs.next()) {
	                                System.out.println("Member not found.");
	                                break;
	                            }
	                            System.out.print("Enter Plan Name (Basic/Premium/Gold): ");
	                            String planName = sc.nextLine().trim();

	                            PreparedStatement getPlan = conn.prepareStatement(
	                                    "SELECT Membership_id FROM MEMBERSHIP_PLAN WHERE LOWER(plan_name) = ?");
	                            getPlan.setString(1, planName.toLowerCase());
	                            ResultSet planRs = getPlan.executeQuery();
	                            if (!planRs.next()) {
	                                System.out.println("Plan not found.");
	                                break;
	                            }
	                            int planId = planRs.getInt("Membership_id");
	                            PreparedStatement assignPlan = conn.prepareStatement("UPDATE MEMBERS SET plan_id = ? WHERE id = ?");
	                            assignPlan.setInt(1, planId);
	                            assignPlan.setInt(2, memberId);
	                            assignPlan.executeUpdate();

	                            PreparedStatement insertHistory = conn.prepareStatement(
	                                    "INSERT INTO MEMBER_PLAN_HISTORY(member_id, plan_id) VALUES (?, ?)");
	                            insertHistory.setInt(1, memberId);
	                            insertHistory.setInt(2, planId);
	                            insertHistory.executeUpdate();

	                            System.out.println("Plan assigned successfully.");
	                        }
//--------------------------------------------------------------------------------------------------------------------------------------------	                        
	                        case 3 -> 
	                        {
	                            ResultSet rs = stmt.executeQuery(
	                                    "SELECT m.id, m.name, m.age, p.plan_name FROM MEMBERS m " +
	                                            "LEFT JOIN MEMBERSHIP_PLAN p ON m.plan_id = p.Membership_id");

	                            System.out.println("\n--- Member List ---");
	                            boolean found = false;
	                            while (rs.next()) {
	                                found = true;
	                                System.out.printf("ID: %d, Name: %s, Age: %d, Plan: %s%n",
	                                        rs.getInt("id"),
	                                        rs.getString("name"),
	                                        rs.getInt("age"),
	                                        rs.getString("plan_name") != null ? rs.getString("plan_name") : "None");
	                            }

	                            if (!found) System.out.println("No members registered.");
	                        }
///---------------------------------------------------------------------------------------------------------------------------------------
	                        case 4 -> {
	                            ResultSet rs = stmt.executeQuery("SELECT * FROM MEMBERSHIP_PLAN");

	                            System.out.println("\n--- Membership Plans ---");
	                            boolean hasPlan = false;
	                            while (rs.next()) {
	                                hasPlan = true;
	                                System.out.printf("ID: %d, Name: %s, Duration: %d months, Fee: ₹%.2f%n",
	                                        rs.getInt("Membership_id"),
	                                        rs.getString("plan_name"),
	                                        rs.getInt("duration_months"),
	                                        rs.getDouble("fee"));
	                            }

	                            if (!hasPlan)
	                            	{
	                            	System.out.println("No plans found.");
	                            	
	                            	}
	                        }
//-------------------------------------------------------------------------------------------------
	                        case 5 -> {
	                            System.out.print("Enter Member ID to view history: ");
	                            int mid;
	                            try {
	                                mid = Integer.parseInt(sc.nextLine());
	                            } catch (NumberFormatException e)
	                            {
	                                System.out.println("Invalid Member ID.");
	                                break;
	                            }

	                            PreparedStatement histStmt = conn.prepareStatement(
	                                    "SELECT h.assigned_date, p.plan_name FROM MEMBER_PLAN_HISTORY h " +
	                                            "JOIN MEMBERSHIP_PLAN p ON h.plan_id = p.Membership_id " +
	                                            "WHERE h.member_id = ? ORDER BY h.assigned_date DESC");
	                            histStmt.setInt(1, mid);
	                            ResultSet historyRs = histStmt.executeQuery();

	                            System.out.println("\n--- Plan History ---");
	                            boolean hasHistory = false;
	                            while (historyRs.next()) {
	                                hasHistory = true;
	                                System.out.printf("Date: %s, Plan: %s%n",
	                                        historyRs.getTimestamp("assigned_date"),
	                                        historyRs.getString("plan_name"));
	                            }

	                            if (!hasHistory) 
	                            	{System.out.println("No plan history for this member.");
	                            	}
	                            	}
	     
	                        case 6 -> System.out.println("Exiting. Thank you!");

	                        default -> System.out.println("Invalid choice. Choose between 1 to 6.");
	                    }

	                } catch (Exception e) {
	                    System.out.println(" Not entered anything  or entered other than numbers " + e.getMessage());
	                    select = 0;
	                }
	                System.out.println("\n");

	            } while (select != 6);

	            sc.close();

	        } catch (SQLException e) {
	            System.out.println("Database error: " + e.getMessage());
	        }
	    }
	}

	

	


