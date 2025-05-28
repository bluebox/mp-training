package Interface;

public interface Animal {
     void run();
     void jump();
     public static void methodUsingStatic() {
    	 System.out.println("method in interface using static");
     }
     public default void methodUsingDefault() {
    	 System.out.println("method in interface using Default");
     }
}
