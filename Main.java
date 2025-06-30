public class Main {
    public static void main(String[] args) {
        MembershipPlan basic = new MembershipPlan("Basic", 3, 1000);
        MembershipPlan premium = new MembershipPlan("Premium", 6, 2500);

        Member m1 = new Member("Pranamya", 24, "M001");
        Member m2 = new Member("Praneela", 30, "M002");

        m1.assignPlan(basic);
        m2.assignPlan(premium);

        m1.showDetails();
        m2.showDetails();
    }
}