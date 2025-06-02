public class MemberShipPlan implements Display, Identity {

    private String plan;
    private int duration;
    private double fee;

    public MemberShipPlan(String name, int duration, double fee) {
        this.plan = name;
        this.duration = duration;
        this.fee = fee;
    }

    public String getPlan() {
        return plan;

    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        if (fee < 0) {
            throw new IllegalArgumentException("Fee cannot be negative.");
        }
        this.fee = fee;
    }

    @Override
    public String getId() {
        return plan;
    }

    @Override
    public void showDetails() {

        System.out.printf(" Plan: %-10s | Duration: %2d months | Fee: Rs.%.2f%n", plan, duration, fee);
    }
}
