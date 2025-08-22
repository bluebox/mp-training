package Controller;

import Model.Membership;
import Service.MembershipService;

import java.util.List;

public class MembershipController {
    private MembershipService membershipService = new MembershipService();

    public void addNewMembership(String name, int durationMonths, double price) {
        Membership membership = new Membership();
        membership.setName(name);
        membership.setDurationMonths(durationMonths);
        membership.setPrice(price);
        membershipService.createMembership(membership);
    }

    public void showAllMemberships() {
        List<Membership> memberships = membershipService.getAllMemberships();
        if (memberships.isEmpty()) {
            System.out.println("No membership plans found.");
        } else {
            for (Membership m : memberships) {
                System.out.println(m.getId() + " | " + m.getName() + " | " + m.getDurationMonths() + " months | Rs." + m.getPrice());
            }
        }
    }

    public void updateMembership(int id, String name, int durationMonths, double price) {
        Membership membership = new Membership();
        membership.setId(id);
        membership.setName(name);
        membership.setDurationMonths(durationMonths);
        membership.setPrice(price);
        membershipService.updateMembership(membership);
    }

    public void deleteMembership(int id) {
        membershipService.deleteMembership(id);
    }
}
