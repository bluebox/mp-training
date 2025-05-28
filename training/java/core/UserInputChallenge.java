import java.util.Scanner;

public class UserInputChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int c=1;
        int sum = 0;
        while (c<6){
            System.out.println("enter #"+c+" :");
            int n = sc.nextInt();
            sum = sum + n;
            c+=1;
        }

        System.out.println(sum);

    }
}
