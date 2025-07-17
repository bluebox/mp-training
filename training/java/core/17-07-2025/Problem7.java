import java.util.HashMap;

public class Problem7 {
    public static void main(String[] args) {
        String s = "XXXXIII";

        System.out.println("the integer from roman number is " + RomanToInteger(s));


    }

    public static int RomanToInteger(String s ){

        HashMap<Character,Integer> hm = new HashMap<Character,Integer>();

        hm.put('I',1);
        hm.put('V',5);
        hm.put('X',10);
        hm.put('L',50);
        hm.put('C',100);
        hm.put('D', 500);
        hm.put('M', 1000);

        
        int a = hm.get(s.charAt(s.length()-1));
        for (int i= s.length()-2;i>=0;i--){
            if((hm.get(s.charAt(i))< hm.get(s.charAt(i+1)))){

                a = a-hm.get(s.charAt(i));

            }
            else{
                a = a +hm.get(s.charAt(i));

            }
        }
          return a;

    }
}
