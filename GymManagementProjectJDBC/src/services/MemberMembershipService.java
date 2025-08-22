package services;

import java.util.List;

import models.PersonMembership;

public interface MemberMembershipService {

	void addMembership(PersonMembership membership);

	PersonMembership getMembershipById(int id);

	List<PersonMembership> getAllMemberships();

	List<PersonMembership> getActiveMemberships();

	void deleteMembership(int id);

	void deleteExpiredMemberships();

	void exportMembershipDetails();

	void exportActiveMembershipDetails();
}
