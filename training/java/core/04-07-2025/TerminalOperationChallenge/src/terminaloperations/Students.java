package terminaloperations;

import java.util.Random;

public class Students {
private String name;
private String gender;
private int age;
private String code;
private boolean isActive;
private int year;
public Students(String name, String gender, int age, String code, boolean isActive, int year) {
	super();
	this.name = name;
	this.gender = gender;
	this.age = age;
	this.code = code;
	this.isActive = isActive;
	this.year = year;
}
public static Students generateRandomStudent()
{
	String[] names= { "Gopi","Raju","Nani","Ram","Nadh"};
	String[] genders= {"Male","Female"};
	String[] codes= {"us","uk","au","in","ch"};
	Random random=new Random();
	String name=names[random.nextInt(names.length)];
	String gender=genders[random.nextInt(genders.length)];
	int age=18+random.nextInt(50);
	String country=codes[random.nextInt(codes.length)];
	boolean active=random.nextBoolean();
	int year=random.nextInt(11); 
	return new Students(name,gender,age,country,active,year);

}
public String getGender() {
	return gender;
}
public int getAge() {
	return age;
}
public String getCode() {
	return code;
}
public boolean isActive() {
	return isActive;
}
public int getYear() {
	return year;
}
public String toString() {
	return "Students [name=" + name + ", gender=" + gender + ", age=" + age + ", code=" + code + ", isActive="
			+ isActive + ", year=" + year + "]";
}

}
