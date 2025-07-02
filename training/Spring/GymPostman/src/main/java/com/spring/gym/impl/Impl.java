package com.spring.gym.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.spring.gym.beans.Member;
import com.spring.gym.dao.MemberDao;

@Service
public class Impl {
	
	private MemberDao memberDao;
	
	@Autowired
	public Impl(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public String addMember(Member member) {
		List<String> activities = Arrays.asList("swimming", "gym", "zumba", "batmintain");
		Set<String> seen = new HashSet<>();

		for (String activity : member.getMemberships()) {
		    if (!activities.contains(activity)) {
		        return "Enter correct memberships";
		    }
		    if (!seen.add(activity)) {
		        return "An activity is allowed only once";
		    }
		}
		if(memberDao.addMember(member)) {
			return "success";
		}else {
			return "error";
		}
	}
	
	public String updateMember(Member member) {
		if (member.getId()==0){
			return "Error Id";
		}
		if(memberDao.findMember(member)) {
			if(memberDao.updateMember(member)) {
				return "Success";
			}else {
				return "Error";
			}
		}else {
			return "Not Found";
		}
		
	}
	
	public String deleteMember(Member member) {
		if (member.getId()==0){
			return "Error Id";
		}
		if(memberDao.findMember(member)) {
			if(memberDao.deleteMember(member)) {
				return "Success";
			}else {
				return "Error";
			}
		}else {
			return "Not Found";
		}
		
	}

	public List<Member> findMembersByJoinDateBetween(Date startDate, Date endDate) {
		return memberDao.findMembersByJoinDateBetween(startDate, endDate);
	}
	
	public Member findMemberById(int id) {
	    return memberDao.findMemberById(id);
	}
	public List<Member> viewAllMembers() {
	    return memberDao.viewAllMembers();
	}
	public List<Member> findMembersByStatus(String status) {
	    return memberDao.findMembersByStatus(status);
	}
	@Scheduled(cron = "0 0 0 * * ?")
	public void deactivateExpiredMembers() {
	    List<Member> allMembers = memberDao.viewAllMembers();
	    List<Member> expiredMembers = new ArrayList<>();

	    Date today = new Date();

	    for (Member member : allMembers) {
	        Date expiry = member.getExpireDate();
	        if (expiry.before(today) && !"INACTIVE".equals(member.getStatus())) {
	            boolean updated = memberDao.updateMemberStatus(member.getId(), "INACTIVE");
	            if (updated) {
	                member.setStatus("INACTIVE");
	                expiredMembers.add(member);
	            }
	        }
	    }

	}


}
