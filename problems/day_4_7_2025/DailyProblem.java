package day_4_7_2025;

public class DailyProblem<T> {
    int front;
    int last;
    int [] queue;
    int length;
    int  size;
    
    public DailyProblem(int length) {
    	this.length=length;
    	queue=new int[length];
    	this.front=0;
    	this.last=0;
    	this.size=0;
    	}
    
    
    public void push(int item) {
    	if(size<length) {
    		queue[last]=item;
    		last=(last+1)%length;
    		size++;
    	}else {
    		System.out.println("The queue is full");
    	}
    }
    
    public int pop() {
    	if(!isEmpty()) {
    		int element=queue[front];
    		front=(front+1)%length;
    		size--;
    		return element;
    	}else {
    		System.out.println("No element to return");
    		return -1;
    	}
    }
    
    public int peek() {
    	if (isEmpty()) {
            System.out.println("queue is empty.");
            return -1; 
        }
        return queue[front];
    }
    
    public boolean isEmpty() {
    	return size==0;
    }
    
    public boolean isFull() {
    	return size==length;
    }
    
    public int getsize() {
    	return size;
    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       DailyProblem queue=new DailyProblem<Integer>(5);
       queue.push(2);
       queue.push(2);
       queue.push(2);
       queue.push(2);
       queue.push(2);
       System.out.println(queue.peek());
       System.out.println( queue.pop());
       System.out.println( queue.pop());
      System.out.println( queue.getsize());
       
		
		
	}

}
