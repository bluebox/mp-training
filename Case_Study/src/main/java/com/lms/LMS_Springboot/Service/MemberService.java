package com.lms.LMS_Springboot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lms.LMS_Springboot.DAO.MemberDAO;
import com.lms.LMS_Springboot.Model.Member;
@Component
@Service
public class MemberService {
	@Autowired
	MemberDAO memberdao;
	public int addmember(Member member) {
		return memberdao.addMember(member);
	}
	
	public int updatemember(Member member) {
		return memberdao.updateMember(member);
	}
	public List<Member> viewallmembers(){
		return memberdao.viewAllMembers();
	}
	public Member getbyid(int memberid) {
		return memberdao.getById(memberid);
	}
	public List<Member> viewjoinmembers(){
		return memberdao.viewjoinMembers();
	}

}
