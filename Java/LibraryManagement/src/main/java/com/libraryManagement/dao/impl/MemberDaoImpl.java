package com.libraryManagement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.libraryManagement.dao.MemberDao;
import com.libraryManagement.utility.DBConnection;
import com.libraryManagement.utility.DBQueries;
import com.libraryManagement.utility.pojos.Member;

public class MemberDaoImpl implements MemberDao {

	@Override
	public boolean addMember(Member mem) {
		try (Connection conn = DBConnection.getConnection()) {
			String addmember = DBQueries.addmember;
			PreparedStatement st = conn.prepareStatement(addmember);
			st.setString(1, mem.getName());
			st.setString(2, mem.getEmail());
			st.setLong(3, mem.getMobile());
			st.setString(4, String.valueOf(mem.getGender()));
			int cnt = st.executeUpdate();
			return cnt > 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean updateMember(Member mem) {
		try (Connection conn = DBConnection.getConnection()) {
			String updatemember = DBQueries.updatemember;
			PreparedStatement st = conn.prepareStatement(updatemember);
			st.setInt(1, mem.getMemberId());
			st.setString(2, mem.getName());
			st.setString(3, mem.getEmail());
			st.setString(4, mem.getMobile().toString());
			st.setString(5, String.valueOf(mem.getGender()));
			st.setInt(6, mem.getMemberId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean addMemberLog(Member mem) {
		try (Connection conn = DBConnection.getConnection()) {
			String addmemberlog = DBQueries.addmemberlog;
			PreparedStatement st = conn.prepareStatement(addmemberlog);
			st.setInt(1, mem.getMemberId());
			st.setString(2, mem.getName());
			st.setString(3, mem.getEmail());
			st.setLong(4, mem.getMobile());
			st.setString(5, String.valueOf(mem.getGender()));
			int cnt = st.executeUpdate();
			return cnt > 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public List<Member> allMember() {
		List<Member> ans = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection()) {
			String allmember = DBQueries.allmember;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(allmember);
			ResultSetMetaData ms = rs.getMetaData();
			int cnt = ms.getColumnCount();
			boolean temp = true;
			while (rs.next()) {
				if (temp) {
					temp = false;
					continue;
				}
				Member mem = new Member();
				for (int i = 1; i <= cnt; i++) {
					if (i == 1) {
						mem.setMemberId(rs.getInt(i));
					} else if (i == 2) {
						mem.setName(rs.getString(i));
					} else if (i == 3) {
						mem.setEmail(rs.getString(i));
					} else if (i == 4) {
						mem.setMobile(rs.getLong(i));
					} else {
						mem.setGender(rs.getString(i).charAt(0));
					}
				}
				ans.add(mem);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ans;
	}
}
