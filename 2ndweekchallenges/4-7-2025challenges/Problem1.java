
public class Problem1 {
    
    private int front=-1;
    private int back=-1;
    private int[] arr=new int[5];

    public int getFront() {
        return front;
    }
    public int getBack() {
        return back;
    }
    public void setBack(int back) {
        this.back = back;
    }
    public void setFront(int front) {
        this.front = front;
    }
    public static void main(String[] args) {
        int[] arr=new int[5];
        
        Problem1 p=new Problem1();
        p.addElement(10);
        p.addElement(12);
        p.addElement(15);
        p.printElements();
        p.addElement(21);
        p.deleteElement();
        p.deleteElement();
        p.addElement(33);
        p.deleteElement();
        p.deleteElement();
        p.deleteElement();
        p.addElement(25);
        p.printElements();
  }
    public  void addElement(int element){
        
        if(this.front==-1){
                    
            this.front++;
            this.back++;
            this.arr[this.front]=element;
        }
        else{
            this.back++;
            if(this.back-this.front>=this.arr.length){
                System.out.println(" overflow");
                for(int i=this.front;i<=this.back;i++){
                    System.out.println(this.arr[i]);
                }

                return;
            }
            
            this.arr[this.back%arr.length]=element;

        }

    }
    public void deleteElement(){
        if(this.front==-1){
            System.out.println("is empty");
            return;
        }
        else{
            if(this.front%arr.length==this.back%this.arr.length){
                this.front=-1;
                this.back=-1;
                System.out.println("is empty");
                return;
            }
            this.front++;
            System.out.println("front"+this.front);
            System.out.println("back"+this.back);
            for(int i=this.front;i<=this.back;i++){
                    System.out.println("front"+this.front+" "+"back "+this.back+" "+this.arr[i%this.arr.length]);
                }

        }

    }
    public void printElements(){

        if(this.front!=-1 ){
            for(int i=this.front;i<=this.back;i++){
                    System.out.println("front"+this.front+" "+"back "+this.back+" "+this.arr[i%this.arr.length]);
                }
        }

    }
}
