package NestedClasses;

public class OuterClass {
	String outerField = "Outer field";
    static String staticOuterField = "Static outer field";
    
    class Inner {
    	void getFields() {
    		System.out.println(outerField);
    		System.out.println(staticOuterField);
    	}
    }
    
    static class StaticNested{
    	void getFields(OuterClass outer) {
//    		try {
//				System.out.println(outerField);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//	    		System.out.println("Can't get outer Field");
//				e.printStackTrace();
//			}
    		System.out.println(outer.outerField);
    		System.out.println(staticOuterField);
    	}
    }

	public static void main(String[] args) {
		
		System.out.println("Inner class:");
        System.out.println("------------");
        OuterClass outerObject = new OuterClass();
        OuterClass.Inner innerObject = outerObject.new Inner();
        innerObject.getFields();

        System.out.println("\nStatic nested class:");
        System.out.println("--------------------");
        StaticNested staticNestedObject = new StaticNested();        
        staticNestedObject.getFields(outerObject);
        
        System.out.println("\nTop-level class:");
        System.out.println("--------------------");
        TopLevel topLevelObject = new TopLevel();        
        topLevelObject.getFields(outerObject);

	}

}
