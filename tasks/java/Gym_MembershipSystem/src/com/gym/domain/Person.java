package com.gym.domain;

public abstract class Person {
    private String name;
    private int age;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void updateDetails(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
