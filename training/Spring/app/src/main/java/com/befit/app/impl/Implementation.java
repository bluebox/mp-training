package com.befit.app.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.befit.app.beans.Member;
import com.befit.app.beans.MemberShip;
import com.befit.app.repositories.MemberRepo;
import com.befit.app.repositories.MemberShipRepo;

@Service
public class Implementation {

	private MemberRepo memberRepo;
	private MemberShipRepo memberShipRepo;
	
	@Autowired
	public Implementation(MemberRepo memberRepo,MemberShipRepo memberShipRepo) {
		this.memberRepo = memberRepo;
		this.memberShipRepo = memberShipRepo;
	}
	
	public boolean addMember(Member member) {
		return memberRepo.addMember(member);
	}
	
	public boolean updateMember(Member member) {
		return memberRepo.updateMember(member);
	}
	
	public String renew(Member member,String days) {
		Member tempMember = memberRepo.viewMember(member);
		if  (tempMember != null) {
			Date expiryDate = member.getExpiryDate();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(member.getJoinDate());
			calendar.add(Calendar.MONTH, 1);
			if (days.equals("year")) {
				calendar.add(Calendar.MONTH, 11);
			}
			try {
				memberRepo.renew(tempMember, expiryDate);
				return "Success";
			}catch(Exception e) {
				return "Something Went Wrong";
				
			}
		}else {
			return "Member Not Found";
		}
		
	}
	
	public Member viewMember(Member member) {
		return  memberRepo.viewMember(member);
	}
	
	public List<Member> viewAllMembers(){
		return memberRepo.viewAllMember();
	}
	
	public List<Member> viewByDates(Date start,Date end){
		return memberRepo.findMembersByJoinDateBetween(start, end);
	}
	
	public boolean addMemberShip(MemberShip memberShip) {
		return memberShipRepo.addMembership(memberShip);
	}
	
	public List<MemberShip> viewAllMemberShip(){
		return memberShipRepo.viewAll();
	}
	
	public List<MemberShip> viewAllMemberShipByJoinDate(Date start,Date end){
		return memberShipRepo.findMemberShipsByJoinDateBetween(start, end);
	}
}
