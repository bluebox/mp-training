package com.spring.gym.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

	public String addMember(Member member, String days) {
		List<String> activities = Arrays.asList("swimming", "gym", "zumba", "batmintain");
		Set<String> seen = new HashSet<>();
		if (days.equals("month") || days.equals("year")) {
			for (String activity : member.getMemberships()) {
				if (!activities.contains(activity)) {
					return "Enter correct memberships";
				}
				if (!seen.add(activity)) {
					return "An activity is allowed only once";
				}
			}
			int amount = 500;
			if (days.equals("month")) {
				amount = amount * member.getMemberships().size() * 90 / 100;
				if (memberDao.addMember(member, amount, days)) {
					return "success";
				} else {
					return "error";
				}
			} else {
				amount = amount * member.getMemberships().size() * 80 * 12;
				amount = amount / 100;
				if (memberDao.addMember(member, amount, days)) {
					return "success";
				} else {
					return "error";
				}
			}
		} else {
			return "Error days";
		}
	}

	public String updateMember(int id, String days) {
		if (id == 0) {
			return "Error Id";
		}
		if (days.equals("month") || days.equals("year")) {
			if (memberDao.findMember(id)) {
				if (memberDao.updateMember(id, days)) {
					return "Success";
				} else {
					return "Error";
				}
			} else {
				return "Not Found";
			}
		} else {
			return "Error days";
		}
	}

	public String deleteMember(Member member) {
		if (member.getId() == 0) {
			return "Error Id";
		}
		if (memberDao.findMember(member.getId())) {
			if (memberDao.deleteMember(member)) {
				return "Success";
			} else {
				return "Error";
			}
		} else {
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
		deactivateExpiredMembers();
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

	public List<Member> findByMembership(String membership) {
		return memberDao.findByMembership(membership);
	}

	public void deactiveRefund() {
		List<Member> allMembers = memberDao.viewAllMembersByRefund();

		Date today = new Date();
		System.out.println(allMembers.toString());

		for (Member member : allMembers) {
			Date join = member.getJoinDate();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(join);
			calendar.add(Calendar.DATE, 10);
			join = calendar.getTime();
			if (join.before(today)) {
				System.out.println(member.toString());
				memberDao.updateMemberByRefund(member);
			}
		}
	}

	public List<String> cancel(int id) {
		deactiveRefund();
		List<String> sol = new ArrayList<>();
		List<String> val = memberDao.amountAndRefund(id);
		System.out.println(val.toString());
		if (val.get(0).equals("ACTIVE")) {
			memberDao.cancel(id);
			sol.add("success");
			int amount = Integer.parseInt(val.get(1));
			String somedayStr = val.get(3);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println("sdf");
			Date someday = null;
			try {
				someday = sdf.parse(somedayStr);
			}catch(Exception e){
				
			}
				Date today = new Date(); // current date
				long diffInMillis = someday.getTime() - today.getTime();
				int days = (int) TimeUnit.MILLISECONDS.toDays(diffInMillis);
				System.out.println(days);
				System.out.println(val.get(1));
				if (val.get(2).equals("month")) {
					amount = amount / 30;
					amount = amount * (30 + days);
				} else {
					amount = amount / 365;
					amount = amount * (365 + days);
				}
				sol.add("" + amount);
			}else {
				sol.add("not Success");
			}
		return sol;
		}
	

}
