package Interface;
//extending two interface using extends keywords
//don't need to implements the method of Animal in this interface 
//whatever the class implements Human need to write the code body of it
public interface Human extends Animal{
    public void drive();
    public static void fun() {
    	System.out.println("function in Human");
    }
}
