package July10.Practice;

public class Main {

	public static void main(String[] args) {

		Thread current = Thread.currentThread();
		
		 System.out.println(current); System.out.println(current.getId());
		 System.out.println(current.getName());
		 System.out.println(current.getPriority());
		 System.out.println(current.isAlive());
		 System.out.println(current.getClass());
		 System.out.println(current.getState());
		 
		Thread a = new main2();
		Thread b = new Thread(new main3());

		a.start();
		b.start();
//		a.run();
//		b.run();

		/*try {
			a.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		for (int i = 0; i < 3; i++) {
			try {
				System.out.print("0 ");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("\nFinished Exceution");
	}
}
