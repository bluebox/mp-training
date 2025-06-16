public class AreaCalc{
   public static void main(String[] args){
      AreaCalc areacalc=new AreaCalc();
      areacalc.Rectangle(4.5,2.5);
      areacalc.Circle(3.5);
    }
    public void Rectangle(double l,double w){
       if(l<=0||w<=0){
          System.out.println("Invalid dimension");
       }else{
          System.out.println("Rectangle Area="+(l*w));
       }
    }
    public void Circle(double r){
        if(r<=0){
            System.out.println("Invalid radius");
        }else{
            System.out.println("Circle Area="+(Math.PI*r*r));
        }
    }
}