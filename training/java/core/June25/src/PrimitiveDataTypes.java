public class PrimitiveDataTypes{
    public static void main (String[] args){

        // Primitive data type declare and initialization

        // Integer Types
        byte byteVar = 3;
        short shortVar = 10;
        int integer = 40 ;
        long longVar = 1234L;
        System.out.println("integer type vlaues\n byteVarable value: "+byteVar +"\n short Varable: " + shortVar);
        System.out.println("integer Varable value: "+integer +"\n long Varable: " + longVar);
        
        // Floating Point Types
        float floatVar = 30.34f;
        double doubleVar = 200.890d; // doubleVar = 200.340 - valid
        System.out.println("Floating Point type vlaues\n float Varable value: "+floatVar +"\n double Varable: " + doubleVar);
       
        // Character Type
        char character = 'S';
        System.out.println("Character type vlaues\n character Varable value: "+ character);
        
        //Boolean Type
        boolean booleanValue = true;
        System.out.println("Boolean type vlaues\n boolean Varable value: "+ booleanValue);
        
       
        
        //Datatype storing Minimum and Maximum range printing using Wrapper classes

        System.out.println("Byte range is "+ Byte.MIN_VALUE + " to " + Byte.MAX_VALUE);
        System.out.println("Short range is "+ Short.MIN_VALUE + " to " + Short.MAX_VALUE);
        System.out.println("Integer range is "+ Integer.MIN_VALUE + " to " + Integer.MAX_VALUE);
        System.out.println("long range is "+ Long.MIN_VALUE + " to " + Long.MAX_VALUE);


        System.out.println("float range is "+ Float.MIN_VALUE + " to " + Float.MAX_VALUE);
        System.out.println("double range is "+ Double.MIN_VALUE + " to " + Double.MAX_VALUE);


        System.out.println("Character range is "+(int) Character.MIN_VALUE + " to " + (int) Character.MAX_VALUE);

        short shortMinValue = Short.MIN_VALUE;
        short shortvalue = (short) ( shortMinValue / 2);  // here shortMinValue is not a constant value like short.Minvalue or number
        System.out.println("short value is "+ shortvalue);

    }
}

