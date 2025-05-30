package Case_Study;

enum Gender{MALE,FEMALE};

public abstract class Person {
	String name;
	int age;
	Gender gender;
	Person(String name,int age,Gender gender){
		this.name=name;
		this.age=age;
		this.gender=gender;
	}
	abstract void showDetails();
}
