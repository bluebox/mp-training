import java.util.ArrayList;
import java.util.Scanner;

public class Gym {
    private ArrayList<Member> members = new ArrayList<>();

    public void addMember(int id,String name,int age) {
        members.add(new Member(id,name,age));
        System.out.println("The total members enrolled in the gym are now " + members.size());
    }

    public Member findMember(int id){
        for (Member member : members) {
            if (member.getMemberId() == id) {
                return member;
            }
        }
        return null;
    }

    public void assignPlan(int id,String plan,int durationInMonths){
        Member member = findMember(id);
        if(member.membershipPlan != null) {
            System.out.println("The person is already enrolled in a plan. Do you want to update the details ? (Type 'yes' or 'no')");
            try {
                String promptForUpdation = new Scanner(System.in).nextLine();
                if(promptForUpdation.equalsIgnoreCase("YES")) {
                    member.updatePlan(plan, durationInMonths);
                    System.out.println("The plan is updated and the updated details of the person are:\n" + member);
                }
                else if(promptForUpdation.equalsIgnoreCase("NO")){
                    System.out.println("Not changing the plan for the user...");
                }
            }
            catch(Exception e) {
                System.out.println("Please type only 'yes' or 'no'!");
            }
        }
        else member.assignPlan(plan,durationInMonths);
    }

    public void changePlan(int id,String plan,int durationInMonths){
        Member member = findMember(id);
        member.updatePlan(plan,durationInMonths);
    }

    public void displayRecords() {
        System.out.println("Gym freaks at our gym right now: ");
        System.out.println("_".repeat(130));
        System.out.println();
        int numberOfRecords = 0;
        for (Member member : members) {
            numberOfRecords++;
            System.out.println(member.toString());
        }
        if(numberOfRecords == 0) System.out.println("Uh oh! No records at the moment. Try again after adding persons to the gym!");
        System.out.println("_".repeat(130));
    }

    public void removeRecord(Member member) {
        members.remove(member);
        System.out.println("The member is successfully removed from the gym!");
        System.out.println("The remaining count of fitness freaks in our gym are " + members.size());
    }

    public void updatePersonalDetails(Member member) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the updated name:");
        String name = scanner.nextLine();
        System.out.println("Enter the updated age");
        int age = scanner.nextInt();
        member.updateDetails(name, age);
        System.out.println("The personal details of the person are updated as: ");
        System.out.println(member);
    }
}