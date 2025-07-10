package DeadLock;

public class Main extends Thread {
	
	public static void main(String[] args) {
		
		Numbers num = new Numbers();
		Remove rnum =new Remove();
		
		Thread number = new Thread(() -> {
			try {
                for (int i = 1; i <= 25; i++) {
                    num.add(i);
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
            	e.setStackTrace(null);
            }
		});
		
		Thread remove = new Thread(() -> {
			try {
                for (int i = 1; i < 25; i++) {
                    rnum.remove(num.getBuffer());
                    Thread.sleep(100); 
                }}catch (InterruptedException e) {
            	e.setStackTrace(null);
            }
		});
		
		number.start();
		remove.start();

	}

}
