public class Main {

    public static void main(String[] args) {
       Shoewarehouse t1=new Shoewarehouse(5);
        Shoewarehouse t2=new Shoewarehouse(5);
        t1.start();
        t2.start();
       t1.receiveorder(4);
       t1.receiveorder(5);
       t2.receiveorder(7);
       t2.receiveorder(11);
       t1.receiveorder(33);
       t2.receiveorder(44);
       
    }
}