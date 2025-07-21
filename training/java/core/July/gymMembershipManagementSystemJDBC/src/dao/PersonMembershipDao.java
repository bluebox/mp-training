package dao;

import java.util.List;

import models.PersonMembership;

public interface PersonMembershipDao {

	void addMembership(PersonMembership membership);

	PersonMembership getMembershipById(int id);

	List<PersonMembership> getAllMemberships();

	List<PersonMembership> getActiveMemberships();

	void deleteMembership(int id);

	void deleteExpiredMemberships();

}
