import java.util.*;

class Floor{
    double width;
    double length;
    public Floor(double width,double length){
       if(width<0){
           this.width=0;
       }else{
           this.width=width;
       }
       if(length<0){
           this.length=0;
       }else{
           this.length=length;
       }
    }
    double getArea(){
        return this.width*this.length;
    }
}
class CarpetCostCalc{
    public static void main(String[] args){
        Floor f=new Floor(2.75,4);
        Carpet c=new Carpet(3.5);
        Calculator calc=new Calculator(f,c);
        System.out.println("Total Cost="+calc.getTotalCost());
    }
}
class Carpet{
    double cost;
    public Carpet(double cost){
       if(cost<0){
          this.cost=0;
       }else{
          this.cost=cost;
       }
    }
    double getCost(){
        return this.cost;
    }
}
class Calculator{
   Floor floor;
   Carpet carpet;
   public Calculator(Floor floor,Carpet carpet){
      this.floor=floor;
      this.carpet=carpet;
   }
   double getTotalCost(){
       return floor.getArea()*carpet.getCost();
   }
}