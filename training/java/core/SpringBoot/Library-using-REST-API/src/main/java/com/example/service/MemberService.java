package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Member;
import com.example.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository repo;
	public String addMember(Member m) {
		if(repo.add(m)>0) {
			return "inserted";
		}
		else {
			return "not inserted";
		}
	}
	public ArrayList<Member> showMembers(){
		ArrayList<Member> l = new ArrayList<Member>();
		 List<Map<String, Object>> m = repo.showAll();
		 for(Map<String, Object> i:m) {
			 l.add(new Member(Integer.parseInt(String.valueOf(i.get("MemberId"))), String.valueOf(i.get("Name")), String.valueOf(i.get("Email")), Long.parseLong(String.valueOf(i.get("Mobile"))), String.valueOf(i.get("Gender")).charAt(0), String.valueOf(i.get("Address"))));
		 }
		 return l;
		
	}
	public String update(int memberId,String name,String email,Long mobile,char gender,String address) {
		if(repo.update(memberId, name, email, mobile, gender, address)>0) {
			return "updated";
		}
		else {
			return "not updated";
		}
	}
	public String delete(int memberId) {
		if(repo.delete(memberId)>0) {
			return "deleted";
		}
		return "not deleted";
	}
}
