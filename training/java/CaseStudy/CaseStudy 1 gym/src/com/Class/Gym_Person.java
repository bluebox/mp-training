package com.Class;

public abstract class Gym_Person {
    private String name;
    private int age;
    private String gender;
    private String email;

    public Gym_Person(String name, int age, String gender, String email) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGender(String gender) { this.gender = gender; }
    public void setEmail(String email) { this.email = email; }

    public abstract void showDetails();
}


