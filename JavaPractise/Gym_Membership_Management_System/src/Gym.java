import java.util.*;
import java.io.*;

public class Gym {
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<MembershipPlan> plans = new ArrayList<>();

    public Gym() {
        plans.add(new MembershipPlan("Basic", 1, 500));
        plans.add(new MembershipPlan("Premium", 3, 1200));
        plans.add(new MembershipPlan("Gold", 6, 2000));
    }

    public void addMember(Member member) {
        members.add(member);
        System.out.println("Member added successfully.");
    }

    public void assignPlan(String memberId, int planIndex) {
        for (Member m : members) {
            if (m.getMemberId().equals(memberId)) {
                if (planIndex >= 0 && planIndex < plans.size()) {
                    m.setMembershipPlan(plans.get(planIndex));
                    System.out.println("Plan assigned successfully.");
                } 
                else {
                    System.out.println("Invalid plan index.");
                }
                return;
            }
        }
        System.out.println("Member not found.");
    }
    
    public void displayMemberDetails(String memberId) {
        for (Member m : members) {
            if (m.getMemberId().equals(memberId)) {
                System.out.println("=== Member Details ===");
                System.out.println("ID   : " + m.getMemberId());
                System.out.println("Name : " + m.getName());
                System.out.println("Age  : " + m.getAge());

                if (m.getMembershipPlan() != null) {
                    MembershipPlan p = m.getMembershipPlan();
                    System.out.println("Plan : " + p.getPlanName());
                    System.out.println("Duration : " + p.getDurationMonths() + " months");
                    System.out.println("Fee : ₹" + p.getFee());
                } 
                else {
                    System.out.println("Plan : Not Assigned");
                }

                System.out.println("======================");
                return;
            }
        }
        System.out.println("Member ID not found.");
    }

    public void viewAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered yet.");
            return;
        }
        for (Member m : members) {
            m.showDetails();
            System.out.println("-------------------------");
        }
    }

    public void updateMember(String memberId, String newName, int newAge) {
        for (Member m : members) {
            if (m.getMemberId().equals(memberId)) {
                m.setName(newName);
                m.setAge(newAge);
                System.out.println("Member updated.");
                return;
            }
        }
        System.out.println("Member not found.");
    }
    public void updateMemberPlan(String memberId, int newPlanIndex) {
        for (Member m : members) {
            if (m.getMemberId().equals(memberId)) {
                if (newPlanIndex >= 0 && newPlanIndex < plans.size()) {
                    m.setMembershipPlan(plans.get(newPlanIndex));
                    System.out.println("Membership plan updated successfully.");
                } 
                else {
                    System.out.println("Invalid plan index.");
                }
                return;
            }
        }
        System.out.println("Member not found.");
    }


    public void deleteMember(String memberId) {
        Iterator<Member> iterator = members.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getMemberId().equals(memberId)) {
                iterator.remove();
                System.out.println("Member deleted.");
                return;
            }
        }
        System.out.println("Member not found.");
    }

    public void showPlans() {
        for (int i = 0; i < plans.size(); i++) {
            System.out.print(i + ": ");
            plans.get(i).showPlanDetails();
        }
    }

   
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Member m : members) {
                String plan = (m.getMembershipPlan() != null) ? String.format("%s,%d,%.2f",
                    m.getMembershipPlan().getPlanName(),
                    m.getMembershipPlan().getDurationMonths(),
                    m.getMembershipPlan().getFee()) : "null";

                writer.write(String.format("%s,%s,%d,%s", m.getMemberId(), m.getName(), m.getAge(), plan));
                writer.newLine();
            }
            System.out.println("Data saved to text file.");
        } 
        catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }


    public void loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("No saved data found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            members.clear(); 
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);

                Member member = new Member(id, name, age);

                if (!parts[3].equals("null")) {
                    String planName = parts[3];
                    int duration = Integer.parseInt(parts[4]);
                    double fee = Double.parseDouble(parts[5]);
                    MembershipPlan plan = new MembershipPlan(planName, duration, fee);
                    member.setMembershipPlan(plan);
                }

                members.add(member);
            }
            System.out.println("Data loaded from text file.");
        } 
        catch (IOException | NumberFormatException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

}
