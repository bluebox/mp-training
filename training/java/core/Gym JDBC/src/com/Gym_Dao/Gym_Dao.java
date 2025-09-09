package com.Gym_Dao;

import com.Class.Gym_Member;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Gym_Dao {

    private static String DB_URL;
    private static String DB_USER;
    private static String DB_PASS;

    static {
        try {
            File file = new File("creds.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            DB_URL = br.readLine();
            DB_USER = br.readLine();
            DB_PASS = br.readLine();
            br.close();

            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Database Connected.");
        } catch (Exception e) {
            System.out.println("Error loading DB config: " + e.getMessage());
        }
    }
    public void addMember(Gym_Member member) throws Exception {
        String sql = "INSERT INTO gym_members VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, member.getId());
            stmt.setString(2, member.getName());
            stmt.setInt(3, member.getAge());
            stmt.setString(4, member.getGender());
            stmt.setString(5, member.getEmail());
            stmt.setString(6, member.getMembershipPlan());
            stmt.setDate(7, Date.valueOf(member.getJoiningDate()));
            stmt.setDate(8, Date.valueOf(member.getUpdatedDate()));
            stmt.executeUpdate();
        }
    }

    public void removeMember(String id) throws Exception {
        String sql = "DELETE FROM gym_members WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

    public void updateMemberPlan(String id, String newPlan) throws Exception {
        String sql = "UPDATE gym_members SET membership_plan = ?, updated_date = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newPlan);
            stmt.setDate(2, Date.valueOf(LocalDate.now()));
            stmt.setString(3, id);
            stmt.executeUpdate();
        }
    }
    public Gym_Member findMemberById(String id) throws Exception {
        String sql = "SELECT * FROM gym_members WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Gym_Member(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("email"),
                    rs.getString("membership_plan"),
                    rs.getDate("joining_date").toLocalDate(),
                    rs.getDate("updated_date").toLocalDate()
                );
            }
        }
        return null;
    }
    public List<Gym_Member> getAllMembers() throws Exception {
        List<Gym_Member> members = new ArrayList<>();
        String sql = "SELECT * FROM gym_members";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                members.add(new Gym_Member(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("email"),
                    rs.getString("membership_plan"),
                    rs.getDate("joining_date").toLocalDate(),
                    rs.getDate("updated_date").toLocalDate()
                ));
            }
        }
        return members;
    }
}
