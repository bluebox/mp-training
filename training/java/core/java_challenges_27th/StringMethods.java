
public class StringMethods {

	public static void main(String[] args) {
		
        String exampleString = "Hello Java World";

        // charAt(int index)
        char charAtIndex = exampleString.charAt(6);
        System.out.println("charAt(6): " + charAtIndex);

        // length()
        int stringLength = exampleString.length();
        System.out.println("length(): " + stringLength);

        // substring(int beginIndex)
        String subStringFromIndex = exampleString.substring(6);
        System.out.println("substring(6): " + subStringFromIndex);

        // substring(int beginIndex, int endIndex)
        String subStringWithEndIndex = exampleString.substring(6, 10);
        System.out.println("substring(6, 10): " + subStringWithEndIndex);

        // equals(Object obj)
        boolean isEqual = exampleString.equals("Hello Java World");
        System.out.println( isEqual);

        // equalsIgnoreCase(String anotherString)
        boolean isEqualIgnoreCase = exampleString.equalsIgnoreCase("hello java world");
        System.out.println("equalsIgnoreCase(\"hello java world\"): " + isEqualIgnoreCase);

        // startsWith(String prefix)
        boolean startsWithHello = exampleString.startsWith("Hello");
        System.out.println("startsWith(\"Hello\"): " + startsWithHello);

        // endsWith(String suffix)
        boolean endsWithWorld = exampleString.endsWith("World");
        System.out.println("endsWith(\"World\"): " + endsWithWorld);

        // indexOf(int ch)
        int indexOfJ = exampleString.indexOf('J');
        System.out.println("indexOf('J'): " + indexOfJ);

        // indexOf(String str)
        int indexOfJava = exampleString.indexOf("Java");
        System.out.println("indexOf(\"Java\"): " + indexOfJava);

        // toLowerCase()
        String lowerCaseString = exampleString.toLowerCase();
        System.out.println("toLowerCase(): " + lowerCaseString);

        // toUpperCase()
        String upperCaseString = exampleString.toUpperCase();
        System.out.println("toUpperCase(): " + upperCaseString);

        // replace(char oldChar, char newChar)
        String replacedString = exampleString.replace('o', 'X');
        System.out.println("replace('o', 'X'): " + replacedString);

        // contains(CharSequence s)
        boolean containsJava = exampleString.contains("Java");
        System.out.println("contains(\"Java\"): " + containsJava);

        // trim()
        String spacedString = "  Trim Me  ";
        String trimmedString = spacedString.trim();
        System.out.println("trim(): '" + trimmedString + "'");

        // concat()
        String concatenatedString = exampleString.concat("!");
        System.out.println("concat(\"!\"): " + concatenatedString);

		
		
	}

}



