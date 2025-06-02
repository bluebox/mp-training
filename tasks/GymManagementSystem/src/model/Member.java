package model;

import java.time.LocalDate;

public class Member extends Person {

    private Subscription subscribe;
    private LocalDate startDate;

    public Member(String name, int age, String gender, String address, Subscription subscribe) {
        super(name, age, gender, address);
        this.startDate = LocalDate.now(); 
        this.subscribe = subscribe;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Subscription getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Subscription subscribe) {
        this.subscribe = subscribe;
    }

    @Override
    public void getPersonalDetails() {
        // TODO: Display details
    }
}