abstract class Animal{
	public abstract void run();
	public abstract void jump();
}
class Dog extends Animal{
	public String name;
	public Dog(String name) {
		this.name=name;
	}
	@Override
	public void run() {
		System.out.println(this.name+" is running");
	}

	@Override
	public void jump() {
		System.out.println(this.name+" is jumming");
	}
	
}
public class Abstract {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Dog d=new Dog("A");
        d.run();
        //cannot create a abstract instance
        //Animal c=new Animal(); this gives error instead we can use the concept of inheritance
        Animal c=new Dog("B");
        c.run();
        c.jump();
	}

}
