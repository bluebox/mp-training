import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

//-----------------dead lock prevention with reentrant lock try lock-------------------------


/*
public class DeadLockHandsOn {
    private final ReentrantLock lock1 = new ReentrantLock();
    private final ReentrantLock lock2 = new ReentrantLock();

    public void methodA() {
        boolean gotLock1 = false;
        boolean gotLock2 = false;

        try {
            gotLock1 = lock1.tryLock(100, TimeUnit.MILLISECONDS);
            if (gotLock1) {
                System.out.println(Thread.currentThread().getName() + " acquired lock1");

                Thread.sleep(100); // Simulate some work

                gotLock2 = lock2.tryLock(100, TimeUnit.MILLISECONDS);
                if (gotLock2) {
                    System.out.println(Thread.currentThread().getName() + " acquired lock2");
                    System.out.println(Thread.currentThread().getName() + " executing methodA");
                } else {
                    System.out.println(Thread.currentThread().getName() + " failed to acquire lock2");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " failed to acquire lock1");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (gotLock2) lock2.unlock();
            if (gotLock1) lock1.unlock();
        }
    }

    public void methodB() {
        boolean gotLock2 = false;
        boolean gotLock1 = false;

        try {
            gotLock2 = lock2.tryLock(100, TimeUnit.MILLISECONDS);
            if (gotLock2) {
                System.out.println(Thread.currentThread().getName() + " acquired lock2");

                Thread.sleep(90); // Simulate some work

                gotLock1 = lock1.tryLock(105, TimeUnit.MILLISECONDS);
                if (gotLock1) {
                    System.out.println(Thread.currentThread().getName() + " acquired lock1");
                    System.out.println(Thread.currentThread().getName() + " executing methodB");
                } else {
                    System.out.println(Thread.currentThread().getName() + " failed to acquire lock1");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " failed to acquire lock2");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (gotLock1) lock1.unlock();
            if (gotLock2) lock2.unlock();
        }
    }

    public static void main(String[] args) {
        DeadLockHandsOn obj = new DeadLockHandsOn();

        Thread t1 = new Thread(obj::methodA, "Thread1");
        Thread t2 = new Thread(obj::methodB, "Thread2");

        t1.start();
        t2.start();
    }
}

*/
//--------------------dead lock with reentrant lock-----------------------------------------------

public class DeadLockHandsOn {
	private final ReentrantLock lock1 = new ReentrantLock();
	private final ReentrantLock lock2 = new ReentrantLock();
	
	public void methodA() {
		
			if(lock1.tryLock(100 , TimeUnit.MILLISECONDS)) {
				
			
				System.out.println(Thread.currentThread().getName() + " has lock1");
			
				try {
					Thread.sleep(100);
				
				//trying to get lock2
				
					if(lock2.tryLock(100 , TimeUnit.MILLISECONDS)) {
					
				
						System.out.println(Thread.currentThread().getName() + " has lock2");
					}
					else {
						System.out.println("thread1 failed to acquire lock2");
					}
				
				}
			
				catch(Exception e) {
					e.printStackTrace();
				}finally {
					lock2.unlock();
					lock1.unlock();
				}
				}
				else {
					System.out.println("thread1 failed to acquire lock1");
				}
	}
	
	public void methodB() {
		
			if(lock2.tryLock(100 , TimeUnit.MILLISECONDS)) {
				
			
			System.out.println(Thread.currentThread().getName() + " has lock1");
			
			try {
				Thread.sleep(100);
				
				//trying to get lock1
				if(lock1.tryLock(100 , TimeUnit.MILLISECONDS)) {
					
				
					
				System.out.println(Thread.currentThread().getName() + " has lock1");
				}
				else {
					System.out.println("thread2 failed to acquire lock1");
				}
					
				
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				lock2.unlock();
				lock1.unlock();
			}
			}
			else {
				System.out.println("thread2 failed to acquire lock2");
			}
		
		
	}
	public static void main(String args[]) {
		
		DeadLockHandsOn obj = new DeadLockHandsOn();
		
		Thread t1 = new Thread(()-> obj.methodA() , "Thread1");
		Thread t2 = new Thread(()-> obj.methodB() , "Thread2");
		
		t2.start();
		t1.start();
	}
}







//-----------------dead lock with reentrant lock-------------------------
/*
public class DeadLockHandsOn {
	private final ReentrantLock lock1 = new ReentrantLock();
	private final ReentrantLock lock2 = new ReentrantLock();
	
	public void methodA() {
		
			lock1.lock();
			System.out.println(Thread.currentThread().getName() + " has lock1");
			
			try {
				Thread.sleep(100);
				
				//trying to get lock2
				
				lock2.lock();
				System.out.println(Thread.currentThread().getName() + " has lock2");
					
				
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}finally {
				lock2.unlock();
				lock1.unlock();
			}
		
	}
	
	public void methodB() {
			lock1.lock();
			System.out.println(Thread.currentThread().getName() + " has lock1");
			
			try {
				Thread.sleep(100);
				
				//trying to get lock1
				lock2.lock();
					
				System.out.println(Thread.currentThread().getName() + " has lock2");
					
				
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				lock2.unlock();
				lock1.unlock();
			}
		
		
	}
	public static void main(String args[]) {
		
		DeadLockHandsOn obj = new DeadLockHandsOn();
		
		Thread t1 = new Thread(()-> obj.methodA() , "Thread1");
		Thread t2 = new Thread(()-> obj.methodB() , "Thread2");
		
		t2.start();
		t1.start();
	}
}


*/



//-----------------dead lock overcome by normal object------------------
/*
public class DeadLockHandsOn {
    private final Object pen = new Object();
    private final Object notes = new Object();

    public void methodA(){
        synchronized(pen){
            System.out.println(Thread.currentThread().getName() +"holding pen");
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

            synchronized(notes){
                System.out.println(Thread.currentThread().getName() +"holding notes");
            }
        }
    }

    public void methodB(){
        synchronized(pen){
            System.out.println(Thread.currentThread().getName() +"holding pen");
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

            synchronized(notes){
                System.out.println(Thread.currentThread().getName() +"holding notes");
            }
        }
    }

    public static void main(String[] args) {
        DeadLockHandsOn obj = new DeadLockHandsOn();

        Thread t1 = new Thread(()-> obj.methodA() , "Thread-1");
        Thread t2 = new Thread(()-> obj.methodB() , "Thread-2");

        t1.start();
        t2.start();

    }
}
*/
//--------------------------dead lock overcome by Lock object---------------------------------------------------

/*

public class DeadLockHandsOn {
	private final Lock lock1 = new ReentrantLock();
	private final Lock lock2 = new ReentrantLock();
	
	public void methodA() {
			lock1.lock();
			System.out.println(Thread.currentThread().getName() + " has lock1");
			
			try {
				Thread.sleep(100);
				
				//trying to get lock2
				
				lock2.lock();
				System.out.println(Thread.currentThread().getName() + " has lock2");
					
				
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}finally {
				lock2.unlock();
				lock1.unlock();
			}
		
	}
	
	public void methodB() {
			lock1.lock();
			System.out.println(Thread.currentThread().getName() + " has lock1");
			
			try {
				Thread.sleep(100);
				
				//trying to get lock1
				lock2.lock();
					
				System.out.println(Thread.currentThread().getName() + " has lock2");
					
				
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				lock2.unlock();
				lock1.unlock();
			}
		
		
	}
	public static void main(String args[]) {
		
		DeadLockHandsOn obj = new DeadLockHandsOn();
		
		Thread t1 = new Thread(()-> obj.methodA() , "Thread1");
		Thread t2 = new Thread(()-> obj.methodB() , "Thread2");
		
		t2.start();
		t1.start();
	}
}

*/


//-----------------------deadlock with normal object--------------------------------------
/*

public class DeadLockHandsOn {
	private final Object lock1 = new Object();
	private final Object lock2 = new Object();
	
	public void methodA() {
		synchronized(lock1) {
			System.out.println(Thread.currentThread().getName() + " has lock1");
			
			try {
				Thread.sleep(100);
				
				//trying to get lock2
				synchronized(lock2) {
					
					System.out.println(Thread.currentThread().getName() + " has lock2");
					
				}
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void methodB() {
		synchronized(lock2) {
			System.out.println(Thread.currentThread().getName() + " has lock2");
			
			try {
				Thread.sleep(100);
				
				//trying to get lock2
				synchronized(lock1) {
					
					System.out.println(Thread.currentThread().getName() + " has lock1");
					
				}
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String args[]) {
		
		DeadLockHandsOn obj = new DeadLockHandsOn();
		
		Thread t1 = new Thread(()-> obj.methodA() , "Thread1");
		Thread t2 = new Thread(()-> obj.methodB() , "Thread2");
		
		t1.start();
		t2.start();
	}
}
*/

