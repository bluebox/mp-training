public class ComparisionOperators {
    public static void main(String[] args) {
        
        int a = 6;
        int b = 4;


        if(a > b){
            System.out.println("a is greather than B");
        }
        else if(a < b){
            System.out.println("b is greater than a");
        }
        else if(a == b) {
            System.out.println("both a and b are equal");
        }

        if (a > 10 && b < 20){
            System.out.println("a greather than 10 and b is less than 20");
        }

        if (a > 10 || b < 20){
            System.out.println("a greather than 10 or b is less than 20");
        }
    }
}
