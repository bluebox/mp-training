package challenges_4th_july;

import java.util.Random;

public class Student {
    private String name;
    private String gender; // "male" or "female"
    private int age;
    private String countryCode;
    private boolean isActive;
    private int yearsEnrolled;

    // Getters and constructor

    public Student(String name, String gender, int age, String countryCode, boolean isActive, int yearsEnrolled) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.countryCode = countryCode;
        this.isActive = isActive;
        this.yearsEnrolled = yearsEnrolled;
    }

    public String getName() { 
    	return name; 
    	}
    public String getGender() {
    	return gender; 
    	}
    public int getAge() {
    	return age; 
    	}
    public String getCountryCode() {
    	return countryCode; 
    	}
    public boolean isActive() {
    	return isActive; 
    	}
    public int getYearsEnrolled() {
    	return yearsEnrolled; 
    	}

    public static Student getRandomStudent() {
        String[] names = {"Adheesh", "Tharun", "Venkat", "Kiran", "Lakshmi"};
        String[] genders = {"male", "female"};
        String[] countries = {"IN", "US", "UK", "AU", "CA"};

        Random rand = new Random();
        return new Student(
            names[rand.nextInt(names.length)],
            genders[rand.nextInt(genders.length)],
            18 + rand.nextInt(50),
            countries[rand.nextInt(countries.length)],
            rand.nextBoolean(),
            rand.nextInt(10)
        );
    }
}