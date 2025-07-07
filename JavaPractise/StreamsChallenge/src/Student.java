
public class Student {
	private String name;
	private int age;
	private float grade;
	public Student(String name, int age, float grade) {
		this.name = name;
		this.age = age;
		this.grade = grade;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public float getGrade() {
		return grade;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setGrade(float grade) {
		this.grade = grade;
	}
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", grade=" + grade + "]";
	}
	
	
}
