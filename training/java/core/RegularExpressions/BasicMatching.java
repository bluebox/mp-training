package training.java.core.RegularExpressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasicMatching {
    public static void main(String[] args) {
        // Pattern pattern = Pattern.compile("srisai");
        // String name="my name is srisai kogurwar";
        // Matcher matcher= pattern.matcher(name);
        // System.out.println(matcher.find());
        // System.out.println(matcher);
        // System.out.println(matcher.start());
        // System.out.println(matcher.group());

        String text="hi there,how are you ?  hi sir my name is sai";
        String ptext="hi";
        Pattern p2=Pattern.compile(ptext);
        Matcher m2=p2.matcher(text);
        System.out.println(text.length());
        System.out.println(text.lastIndexOf(ptext));
        System.out.println(text.indexOf(ptext));
        while(m2.find()){
            System.out.println(m2.group() +" index is at "+m2.start());
        }
    }
}
