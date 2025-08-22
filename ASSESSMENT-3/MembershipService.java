package Service;

import DataAccessObject.MembershipDAO;
import Model.Membership;

import java.util.List;

public class MembershipService {
    private MembershipDAO membershipDAO = new MembershipDAO();

    public void createMembership(Membership membership) { membershipDAO.addMembership(membership); }

    public List<Membership> getAllMemberships() { return membershipDAO.getAllMemberships(); }

    public void updateMembership(Membership membership) { membershipDAO.updateMembership(membership); }

    public void deleteMembership(int id) { membershipDAO.deleteMembership(id); }
}
