package service;

import java.util.Scanner;

import serviceImplementation.MemberServiceImplementation;
import serviceImplementation.MembershipPlanServiceImplementation;

public interface GymService {
	void addNewMember(MemberServiceImplementation member);
	
	void addPlan(MembershipPlanServiceImplementation plan);
	
	MemberServiceImplementation getMemberById(int id);
	
	MemberServiceImplementation getMemberByPhone(String phone);
	
	void assignMembershipPlan(int id, MembershipPlanServiceImplementation mp);
	
	MembershipPlanServiceImplementation getPlanById(int planId);
	
	void generateReport();
	
	void editUserDetails(MemberServiceImplementation existing, Scanner sc);
	
	void deleteMemberByPhone(String phone);
}
