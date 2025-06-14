package com.library.service;
import com.library.dao.MemberDAO;
import com.library.domain.Member;

public class MemberService {
	MemberDAO memberdao=new MemberDAO();
	
	public void addMember(Member member)
	{
		
			//memberdao.addMember(member);
		
		//remove This
		Member temp=new Member("manoj","manireddy@gmail.com",7093063248L,'M',"ankushapoor");
		memberdao.addMember(temp);
		
	}

}
