package model;

import java.time.LocalDate;

public class Member extends Person {

    private Subscription subscribe;
    private LocalDate startDate;

    public Member(String name, int age, String gender, String address, Subscription subscribe) {
        super(name, age, gender, address);
        this.startDate = null;//LocalDate.now(); 
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
        System.out.println("Name of the Member :: "+super.getName());
        System.out.println("Age of the Memeber :: "+super.getAge());
        System.out.println("Gender of the Member :: "+super.getGender());
        System.out.println("Address of the member :: "+super.getAddress());
        System.out.println("Subscription details :: "+this.getSubscribe());
    }
}