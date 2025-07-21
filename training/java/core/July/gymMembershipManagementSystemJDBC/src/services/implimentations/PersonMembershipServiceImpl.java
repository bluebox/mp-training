package services.implimentations;

import java.util.List;

import dao.PersonMembershipDao;
import models.PersonMembership;
import services.PersonMembershipService;

public class PersonMembershipServiceImpl implements PersonMembershipService {
	private final PersonMembershipDao membershipDao;

	public PersonMembershipServiceImpl(PersonMembershipDao membershipDao) {
		this.membershipDao = membershipDao;
	}

	@Override
	public void addMembership(PersonMembership membership) {
		membershipDao.addMembership(membership);
	}

	@Override
	public PersonMembership getMembershipById(int id) {
		return membershipDao.getMembershipById(id);
	}

	@Override
	public List<PersonMembership> getAllMemberships() {
		return membershipDao.getAllMemberships();
	}

	@Override
	public List<PersonMembership> getActiveMemberships() {
		return membershipDao.getActiveMemberships();
	}

	@Override
	public void deleteMembership(int id) {
		membershipDao.deleteMembership(id);
	}

	@Override
	public void deleteExpiredMemberships() {
		membershipDao.deleteExpiredMemberships();
	}

	@Override
	public void exportMembershipDetails() {
		List<PersonMembership> members = membershipDao.getActiveMemberships();

		String[] headers = new String[] { "Id", "Person Id", "Plan Id", "JoiningDate", "Plan StartDate",
				"Plan End Date" };

		CSVExporter.export(members, headers,
				s -> new String[] { String.valueOf(s.getId()), String.valueOf(s.getPersonId()),
						String.valueOf(s.getPlanId()), s.getJoiningDate().toString(), s.getPlanStartDate().toString(),
						s.getPlanEndDate().toString() },
				"output/Membership Details.csv");

	}

	@Override
	public void exportActiveMembershipDetails() {

		List<PersonMembership> members = membershipDao.getAllMemberships();

		String[] headers = new String[] { "Id", "Person Id", "Plan Id", "JoiningDate", "Plan StartDate",
				"Plan End Date" };

		CSVExporter.export(members, headers,
				s -> new String[] { String.valueOf(s.getId()), String.valueOf(s.getPersonId()),
						String.valueOf(s.getPlanId()), s.getJoiningDate().toString(), s.getPlanStartDate().toString(),
						s.getPlanEndDate().toString() },
				"output/Active Membership Details.csv");
	}
}
