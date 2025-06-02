//package model;
//
//public record Subscription(String subscriptionName,int amount,double duration,String details, Trainer trainer) {
//	
//	
//
//}


package model;

public class Subscription {
    private String subscriptionName;
    private int amount;
    private double duration;
    private String details;
    private Trainer trainer;

    public Subscription(String subscriptionName, int amount, double duration, String details, Trainer trainer) {
        this.subscriptionName = subscriptionName;
        this.amount = amount;
        this.duration = duration;
        this.details = details;
        this.trainer = trainer;
    }

    // Getters
    public String getSubscriptionName() { return subscriptionName; }
    public int getAmount() { return amount; }
    public double getDuration() { return duration; }
    public String getDetails() { return details; }
    public Trainer getTrainer() { return trainer; }

    // Setters
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
}
