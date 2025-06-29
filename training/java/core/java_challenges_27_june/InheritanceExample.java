
public class InheritanceExample {
	public static void main(String args[]) {
		  
	     Dog myDog = new Dog();
	     myDog.species = "Canine";
	     myDog.eat(); 
	     myDog.breed = "Golden Retriever";
	     myDog.bark();
	}
}

//Parent class
class Animal {
 String species;

 void eat() {
     System.out.println(species + " is eating.");
 }
}

//Child class 
class Dog extends Animal {
 String breed;
 void bark() {
     System.out.println(breed + " dog is barking.");
 }
}


