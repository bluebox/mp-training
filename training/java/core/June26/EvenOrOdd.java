package June26;

public class EvenOrOdd {
    public static void main(String[] args) {
        int i=5;
        System.out.println("Even Numbers from 5 to 20 are");
        while(i<=20){
            if(isEvenNumber(i)) System.out.println(i);
            i++;
        }
    }
    public static boolean isEvenNumber(int number){
        if(number%2==0) return true;
        return false;
    }
}

