public class Queue {
    private int[] arr;
    private int fornt , rear;
    private int maxsize;

    public Queue (int size) {
        arr = new int[size];
        fornt = -1;
        rear = -1;
        maxsize = size;
    }
    public boolean isFull() {
        return rear == maxsize -1;

    }
    public boolean isEmpty() {
        return fornt == -1;

    }
    public void enqueue(int value) {
        if (isFull()) {
            System.out.println("queue is full!");
            return;
        }
        if(fornt == -1) fornt = 0;
        rear++;
        arr[rear] = value;
        System.out.println("Enqueued: " + value);


    }
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("queue is empty!");
            return -1;

        } 


        int dequeuedValue = arr[fornt];
        if (fornt == rear) {
            fornt = rear = -1;
        } else {
            fornt++;
        }
        return dequeuedValue;
    }

    public static void main(String[] args) {
        Queue q = new Queue (5);
        q.enqueue(148);
        q.enqueue(249);
        q.enqueue(48);
        System.out.println("Dequeued: " + q.dequeue());
    }
}


