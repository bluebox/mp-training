import java.util.Scanner;

public class GenerateTag {
    private static String generate(String s) {
    	String t="#";
    	String []ans=s.split(" ");
    	for(int i=0;i<ans.length;i++) {
    		if(i==0) {
    			t+=ans[i].toLowerCase();
    		}
    		else {
    			t+=((char)(ans[i].charAt(0)-'a'+'A'))+ans[i].substring(1);
    		}
    	}
    	String a="";
    	for(int i=0;i<100 && i<t.length();i++)a+=t.charAt(i);
    	return a;
    }
	public static void main(String[] args) {
		System.out.println("enter string");
		Scanner sc=new Scanner(System.in);
		String s=sc.nextLine();
		System.out.println(generate(s));
	}

}
