package Practice.june25;

import java.util.Scanner;

/*Grade Calculator
Write a method gradeCalculator(int marks) that returns a char grade based on marks:
90 and above: 'A'
80 to 89: 'B'
70 to 79: 'C'
Below 70: 'F'
Use if-else statements inside the method. Call it in main and print the grade.*/
public class GradeCalculator {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter marks of the student");
		int marks=sc.nextInt();
		if(marks>=0 && marks<101) {
			int grade=gradeCalculator(marks);
			System.out.println(grade);
		}
		else {
			System.out.println("Enter valid marks of a student");
		}
		sc.close();
	}
	public static char gradeCalculator(int marks) {
		if(marks >= 90) 
			return 'A';
		else if (marks >= 80 && marks < 90)
			return 'B';
		else if (marks >= 70 && marks < 80)
			return 'C';
		else
			return 'F';
	}

}
