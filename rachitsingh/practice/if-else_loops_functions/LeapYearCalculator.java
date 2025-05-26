import java.util.Scanner;

class LeapYearCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter a year: ");
        int year = input.nextInt();
        
        if(isLeapYear(year))
        {
            System.out.println("Given year is a leap year");
        }
        else
        {
            System.out.println("Given year is a not a leap year");
        }
        input.close();
    }
    public static boolean isLeapYear(int year)
    {
        if(year < 1 || year > 9999)
        {
            return false;
        }
        if(year % 100 == 0)
        {
            if(year % 400 == 0)
            {
                return true;
            }
        }
        else if(year % 4 == 0)
        {
            return true;
        }
        return false;
    }
}
