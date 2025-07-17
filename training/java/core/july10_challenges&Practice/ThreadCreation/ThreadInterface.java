package threadcreation;

class ThreadInterface implements Runnable{
	public void run() {
		System.out.println("The running thread name:"+Thread.currentThread().getName());
		System.out.println("The status is"+this);
		System.out.println("The first 5 odd numbers:");
		int c=0;
		int i=1;
		while(c<=5) {
			System.out.println(i);
			try {
				Thread.sleep(250);
				
			}
			catch(InterruptedException e){
				e.printStackTrace();
				
			}

			i+=2;
			c+=1;
		}
		
	}
}