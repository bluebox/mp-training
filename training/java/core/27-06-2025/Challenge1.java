//String Manipulation

public class Challenge1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="Hello welcome";
		String str1="Tarun welcome";
		
		System.out.println("Character at certain position: "+str.charAt(2));
		System.out.println("Uniocode of a character: "+str.charAt(4)+" "+str.codePointAt(4));
		System.out.println("Comparision: "+str.compareTo(str1));
		System.out.println("Concatination: "+str.concat(str1));
		System.out.println("Contains: "+str.contains("welcome"));
		System.out.println("Content Equals :"+str.contentEquals(str1));
		System.out.println("Ends with :"+str.endsWith("o"));
		System.out.println("Is stringd are Equal :"+str.equals(str1));
	    System.out.println("Index of a character :"+str.indexOf("c"));
	    System.out.println("Is Empty?? : "+ str.isEmpty());
	    System.out.println("Length of the string : "+str.length());

	    String delimiter="--";
	    String []elements= {"tarun","sai","ravi","raju"};
	    String newString=String.join(delimiter,elements);
	    System.out.println("Join the strings : "+newString);
	    
	    System.out.println("Last Index of "+str.lastIndexOf('l'));
	    System.out.println("Replacing a string :"+str.replace('l','h'));
	    
	    
		
	}

}
