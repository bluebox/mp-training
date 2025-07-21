package model;

public enum MembershipType {
    BASIC("Basic", 1, 500),
    PREMIUM("Premium", 3, 1200),
    GOLD("Gold", 6, 2000);

    private final String name;
    private final int months;
    private final double fee;

    MembershipType(String name, int months, double fee) {
        this.name = name;
        this.months = months;
        this.fee = fee;
    }

    public String getName() { return name; }
    public int getMonths() { return months; }
    public double getFee() { return fee; }
}
