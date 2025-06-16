class Animal{
public void sound(){
System.out.println("Some generic Animal sound");
}
public void eat(){
System.out.println("Some Animal Eating..");
}
}
class Dog extends Animal{
@Override
public void sound(){
System.out.println("Dog making sound");
}
}
class Cat extends Animal{
@Override
public void sound(){
System.out.println("Cat making sound");
}
}
class AnimalSound{
public static void main(String[] args){
Dog d=new Dog();
Cat c=new Cat();
Animal a=new Animal();
d.sound();
d.eat();
c.sound();
c.eat();
a.sound();
a.eat();
}
}


