package com;
    
public class Worker {
    private String name;
    private String birthDate;
    private String endDate;

    Worker() {
        this("Mourya", "07/02/2003", "00/00/0000");
    }

    Worker(String name) {
        this(name, "07/02/2003", "00/00/0000");
    }

    Worker(String name, String birthDate) {
        this(name, birthDate, "00/00/0000");
    }

    Worker(String name, String birthDate, String endDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.endDate = endDate;
    }

    public int getAge() {
    	int age = 2025 - Integer.parseInt(birthDate.substring(birthDate.lastIndexOf('/') + 1));
        String messageString = String.format("The Age of the employee %s is %d", name, age);
        System.out.println(messageString);
        return age;
    }

    public double collectPay() {
        System.out.println("Collecting the salary...!");
        return 0.0;
    }

    public String getEndDate() {
        return endDate;
    }

    private void terminate(String endDate) {
        System.out.println("The Worker " + name + " is terminated from the Job on " + endDate);
    }
}