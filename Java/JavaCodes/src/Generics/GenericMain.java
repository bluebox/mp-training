package Generics;

import java.util.*;

class Animal{
	public String name;
	public int age;
	public Animal(String name,int age) {
		this.name=name;
		this.age=age;
	}
}
class Human{
	public String name;
	public int age;
	public Human(String name,int age) {
		this.name=name;
		this.age=age;
	}
}
class Gen<T>{
	List<T>ans;
	public Gen() {
		ans=new ArrayList<>();
	}
	public void addGen(T a) {
		ans.add(a);
	}
	public void getGen(){
		for(T ele:ans) {
			System.out.println(ele.getClass().getSimpleName());
		}
	}
}
// in this we are storing two different types of data in List using genrics 
public class GenericMain {

	public static void main(String[] args) {
		  Gen<Animal> g=new Gen<>();
		  g.addGen(new Animal("dog",20));
		  Gen<Human> b=new Gen<>();
		  b.addGen(new Human("abhi",22));
		   
	}

}
