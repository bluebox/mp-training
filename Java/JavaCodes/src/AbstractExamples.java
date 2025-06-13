abstract class Animals{
	public abstract void run();
	abstract void jump();
}
abstract class Human extends Animals{
	abstract void runACar();
}
class Students extends Human{
    public String name;
    public Students(String name) {
    	this.name=name;
    }
	@Override
	void runACar() {
		System.out.println(this.name +" can drive a car");
	}

	@Override
	public void run() {
		System.out.println(this.name +" can run");
		
	}

	@Override
	void jump() {
		System.out.println(this.name +" can jump");
	}
	
}
public class AbstractExamples {

	public static void main(String[] args) {
		Students a=new Students("Abhi");
		a.run();
		a.jump();
		a.runACar();
   }
}
