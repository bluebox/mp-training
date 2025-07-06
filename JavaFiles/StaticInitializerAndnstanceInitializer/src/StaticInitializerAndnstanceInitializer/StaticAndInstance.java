package StaticInitializerAndnstanceInitializer;

public class StaticAndInstance {
	
	static {
		System.out.println("Static initializer: runs once when class is loaded.");
	}
	{
		System.out.println("Instance initializer: runs each time an object is created.");
	}


	    public StaticAndInstance() {
	    	System.out.println("Constructor: runs after instance initializer.");
	    }
}
