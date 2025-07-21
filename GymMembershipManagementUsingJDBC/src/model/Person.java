package model;
public abstract class Person {
		private String name;
		private int age;
		Person(String name,int age)
		{
			this.setName(name);
			this.setAge(age);
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
}
