package day8;

import java.util.Random;

public class Student {
//	static int lastId = 1;
//	private int studentId;
	private int age;
	private Gender gender;
	private boolean hasExp;
	private static Random random = new Random();

	public Student(int age, Gender gender, boolean hasExp) {
		this.age = age;
		this.gender = gender;
		this.hasExp = hasExp;
	}

	public static Student getRandomStudent() {
		int age = random.nextInt(60) + 10;
		Gender gender = Gender.values()[random.nextInt(2)];
		boolean hasExp = random.nextBoolean();
		return new Student(age,gender,hasExp); 
	}

	public int getAge() {
		return age;
	}

	public Gender getGender() {
		return gender;
	}

	public boolean isHasExp() {
		return hasExp;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setHasExp(boolean hasExp) {
		this.hasExp = hasExp;
	}

}

enum Gender {
	MALE, FEMALE
}