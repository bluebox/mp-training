package com.example.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.model.Books;
import com.example.model.Member;
import com.library.exception.InvalidInputException;

@Service
public class MemberService {
	public String addMember(Member m) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu","practice","Vbhanu@123");
			PreparedStatement ps= conn.prepareStatement("insert into members values(?,?,?,?,?,?)");
			ps.setInt(1, m.getMemberId());
			ps.setString(2, m.getName());
			ps.setString(3, m.getEmail());
			ps.setLong(4, m.getMobile());
			ps.setString(5, Character.toString(m.getGender()));
			ps.setString(6, m.getAddress());
			if(!ps.execute()) {
				return "inserted";
			}
			else {
				return "not inserted";
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "not inserted";
	}
	public ArrayList<Member> showMembers(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu","practice","Vbhanu@123");
			Statement s= conn.createStatement();
			ResultSet res = s.executeQuery("select * from members");
			ArrayList<Member> l=new ArrayList<Member>();
			while(res.next()) {
				l.add(new Member(res.getInt(1), res.getString(2), res.getString(3), res.getLong(4),res.getString(5).charAt(0),res.getString(6)));
				System.out.println(res.getLong(1)+" "+res.getString(6));
			}
			System.out.println(l);
			return l;
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String update(int memberId,String name,String email,Long mobile,char gender,String address) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu","practice","Vbhanu@123");
			Statement s=conn.createStatement();
			ResultSet res=s.executeQuery("select * from members where memberId="+memberId);
			if(res.next()) {
				PreparedStatement ps = conn.prepareStatement("UPDATE members SET name=?, email=?, mobile=?, gender=?, address=? WHERE memberId=?");
				ps.setString(1, name);
				ps.setString(2, email);
				ps.setLong(3, mobile);
				ps.setString(4, String.valueOf(gender));
				ps.setString(5, address);
				ps.setInt(6, memberId);
				int rows = ps.executeUpdate();
				if(rows>0) {
					return "updated";
				}
				else {
					return "not updated";
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "not updated";
	}
	public String delete(int memberId) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu","practice","Vbhanu@123");
			PreparedStatement ps = conn.prepareStatement("delete from members where memberId=?");
			ps.setInt(1, memberId);
			if(ps.executeUpdate()>0) {
				return "deleted";
			}
			return "not deleted";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "not deleted";
	}
}
