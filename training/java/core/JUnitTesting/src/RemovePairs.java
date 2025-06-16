import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

public class RemovePairs {
    String output;
    public String remove(String input){
        if(input==null || input.length()<2){
            return input;
        }
        String res="";
        res=res+input.charAt(0);
        for(int i=1;i<input.length();i++){
            if(input.charAt(i)!=input.charAt(i-1)){
                res+=input.charAt(i);
            }
        }
        return res;
    }
}
