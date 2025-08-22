package serviceImpl;

import java.util.List;

import dao.PersonMemberShipDAO;
import daoImpl.PersonMembershipDAOImpl;
import models.PersonMembership;
import services.MemberMembershipService;
import utils.CSVExporter;

public class MemberMebershipPlanImpl implements MemberMembershipService {

	private final PersonMemberShipDAO membershipDao = new PersonMembershipDAOImpl();

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
						String.valueOf(s.getPlanId()), s.getJoiningDate().toString(), s.getPlanStartedDate().toString(),
						s.getPlanEndDate().toString() },
				"output/Membership Details.csv");

	}

	@Override
	public void exportActiveMembershipDetails() {
		// TODO Auto-generated method stub

	}

}
