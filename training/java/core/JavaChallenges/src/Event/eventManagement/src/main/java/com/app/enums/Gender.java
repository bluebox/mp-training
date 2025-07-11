package com.app.enums;

public enum Gender {
    MALE("M"), FEMAL("F"), OTHER("O");

    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
