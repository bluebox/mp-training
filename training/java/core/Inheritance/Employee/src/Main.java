import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Salary s=new Salary("Bhanu","2004-06-21","2025-05-21",654536875343L,"2024-05-21",76543.46,true);
		Scanner sc=new Scanner(System.in);
		boolean x=true;
		do {
			System.out.println("1.Age\n2.collectPay\n3.retire\n4.terminate\n5.Exit\n");
			System.out.println("Enter thwe choice");
			switch(sc.nextInt()) {
				case 1->System.out.println(s.getAge());
				case 2->{
					System.out.printf("%.2f",s.collectPay());
					System.out.println();
				}
				
				case 3->{
						s.retire();
						s=null;
						x=false;
				}
				case 4->{
					s.terminate();
					s=null;
					x=false;
				}
				case 5->x=false;
				default->System.out.println("Enter correct choice");
			}
		}while(x);
		HourlyEmployee h=new HourlyEmployee("Bhanu","2004-06-21","2025-05-21",654536875343L,"2024-05-21", 6543.64);
		do {
			System.out.println("1.Age\n2.collectPay\n3.getDoublePay\n4.terminate\n5.Exit\n");
			System.out.println("Enter thwe choice");
			switch(sc.nextInt()) {
				case 1->System.out.println(h.getAge());
				case 2->{
					System.out.printf("%.2f",h.collectPay());
					System.out.println();
				}
				case 3->{
					System.out.printf("%.2f",h.getDoublePay());
					System.out.println();
				}
				case 4->{
					s.terminate();
					s=null;
					x=true;
				}
				case 5->x=true;
				default->System.out.println("Enter correct choice");
			}
		}while(!x);
}
}
