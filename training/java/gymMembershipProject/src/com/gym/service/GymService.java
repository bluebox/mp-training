package com.gym.service;

import com.gym.exceptions.InvalidDateException;
import com.gym.models.Member;

public interface GymService {
	public void addNewMember(Member newMember);
	public void assignPlanToMember(int memberId, int planId,String date)  throws InvalidDateException;
	public void showAllMembers();
	public boolean validateNewMember(Member newMember);
	public boolean deleteMember(int idToDelete);
}
