import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Worker s=new Worker("Bhanu","2004-06-21","2025-05-21",654536875343L,"2024-05-21",76543.46,3000,5,true);
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
						s.isRetire();
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
}
}
